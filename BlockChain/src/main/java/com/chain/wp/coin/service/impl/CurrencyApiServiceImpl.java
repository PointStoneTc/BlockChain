package com.chain.wp.coin.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chain.redis.service.RedisServiceI;
import com.chain.util.DateUtils;
import com.chain.util.HttpUtil;
import com.chain.wp.coin.config.CoinConfigProperties;
import com.chain.wp.coin.config.CoinConstant;
import com.chain.wp.coin.entity.Asset;
import com.chain.wp.coin.entity.BtcMonitorRateHistory;
import com.chain.wp.coin.entity.Exchange;
import com.chain.wp.coin.page.AssetInfoExchange;
import com.chain.wp.coin.page.AssetQuotation;
import com.chain.wp.coin.page.BtcMonitor;
import com.chain.wp.coin.page.ExchangeGeneral;
import com.chain.wp.coin.page.ExchangeMarkInfo;
import com.chain.wp.coin.page.ExchangeMarkInfoDetail;
import com.chain.wp.coin.page.ExchangeRiseFall;
import com.chain.wp.coin.page.MarkInofExchange;
import com.chain.wp.coin.page.MarkInofExchangeDetail;
import com.chain.wp.coin.page.OneDayMarketCapInfo;
import com.chain.wp.coin.service.CurrencyApiServiceI;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("currencyApiService")
@Transactional
public class CurrencyApiServiceImpl implements CurrencyApiServiceI {
    private static final Logger logger = LoggerFactory.getLogger(CurrencyApiServiceImpl.class);
    @Autowired
    private CoinConfigProperties coinConfigProperties;
    @Autowired
    private RedisServiceI redisService;
    @Autowired
    private HttpUtil httpUtil;

    public boolean exchanges(String key) throws Exception {
        return false;
    }

    public boolean assets() throws Exception {
        return false;
    }

    public boolean symbols() throws Exception {
        return false;
    }

    @SuppressWarnings("unchecked")
    public List<AssetQuotation> assetTrend() throws Exception {
        // 1.获取assets交易变化的信息
        List<AssetQuotation> currentData = (List<AssetQuotation>) redisService.get(CoinConstant.CURRENCY_API + "_" + CoinConstant.ASSETS_QUOTATION_RATE);

        // 2.获取assets历史趋势图表信息
        Map<String, double[]> historyData = (Map<String, double[]>) redisService.get(CoinConstant.CURRENCY_API + "_" + CoinConstant.ASSETS_QUOTATION_RATE_HISTORY_24H);

        for (AssetQuotation item : currentData) {
            item.setSeries(historyData.get(item.getSpecificRate().getBaseSymbol()));
        }

        return currentData;
    }

    public BtcMonitor btcMonitorLine_OHLCV() throws Exception {
        BtcMonitor btcMonitor;
        Object cacheObj = redisService.get(CoinConstant.CURRENCY_API + "_" + CoinConstant.BTC_MONITOR_LINE_OHLCV);

        if (cacheObj == null) { // 缓存为空
            btcMonitor = new BtcMonitor();
        } else { // 已经存在缓存
            btcMonitor = (BtcMonitor) cacheObj;
        }
        return btcMonitor;
    }

    public BtcMonitorRateHistory btcMonitorRate() throws Exception {
        BtcMonitorRateHistory rate;
        Object cacheObj = redisService.get(CoinConstant.CURRENCY_API + "_" + CoinConstant.BTC_MONITOR_RATE);

        if (cacheObj == null) { // 缓存为空
            rate = new BtcMonitorRateHistory();
        } else { // 已经存在缓存
            rate = (BtcMonitorRateHistory) cacheObj;
        }
        return rate;
    }

    @SuppressWarnings("unchecked")
    public Map<String, List<Asset>> assetsGeneral(String name) throws Exception {
        Object cacheObj;
        Map<String, List<Asset>> map = new HashMap<String, List<Asset>>();
        if ("ALL".equals(name) || "OTHER".equals(name)) {
            cacheObj = redisService.get(CoinConstant.CURRENCY_API + "_" + CoinConstant.ASSETS_GENERAL + "_" + name);
            if (cacheObj != null)
                map.put(name, (ArrayList<Asset>) cacheObj);
        } else {
            String[] splits = name.split("-");
            if (name.indexOf("-") <= 0 || splits.length != 2)
                return null;

            for (int s = (byte) splits[0].charAt(0); s <= (byte) splits[splits.length - 1].charAt(0); s++) {
                cacheObj = redisService.get(CoinConstant.CURRENCY_API + "_" + CoinConstant.ASSETS_GENERAL + "_" + (char) s);
                if (cacheObj != null)
                    map.put(String.valueOf((char) s), (ArrayList<Asset>) cacheObj);
            }
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, List<ExchangeGeneral>> exchangeGeneral(String name) throws Exception {
        Object cacheObj;
        Map<String, List<ExchangeGeneral>> map = new HashMap<String, List<ExchangeGeneral>>();
        if ("ALL".equals(name) || "OTHER".equals(name)) {
            cacheObj = redisService.get(CoinConstant.CURRENCY_API + "_" + CoinConstant.EXCHNAGES_GENERAL + "_" + name);
            if (cacheObj != null)
                map.put(name, (ArrayList<ExchangeGeneral>) cacheObj);
        } else {
            String[] splits = name.split("-");
            if (name.indexOf("-") <= 0 || splits.length != 2)
                return null;

            for (int s = (byte) splits[0].charAt(0); s <= (byte) splits[splits.length - 1].charAt(0); s++) {
                cacheObj = redisService.get(CoinConstant.CURRENCY_API + "_" + CoinConstant.EXCHNAGES_GENERAL + "_" + (char) s);
                if (cacheObj != null)
                    map.put(String.valueOf((char) s), (ArrayList<ExchangeGeneral>) cacheObj);
            }
        }
        return map;
    }

    public void topMarketTrend() throws Exception {
        // TODO Auto-generated method stub

    }

    public void legalcurrencyTrend() throws Exception {
        // TODO Auto-generated method stub

    }

    @SuppressWarnings("unchecked")
    public List<ExchangeRiseFall> topFloatingExchange() throws Exception {
        List<ExchangeRiseFall> list = (List<ExchangeRiseFall>) redisService.get(CoinConstant.CURRENCY_API + "_" + CoinConstant.LEGALCURRENCY_TREND);
        return list;
    }

    @SuppressWarnings("unchecked")
    public ExchangeMarkInfo exchangeMarketInfo(int id) throws Exception {
        Object cacheObj = redisService.get(CoinConstant.CURRENCY_API + "_" + CoinConstant.EXCHANGE_MARK_INFO);
        boolean mustQuery = false;

        Map<String, ExchangeMarkInfo> exchangeMarkInfoMap = null;
        if (cacheObj == null) { // redis中没有任何缓存数据
            exchangeMarkInfoMap = new HashMap<String, ExchangeMarkInfo>();
            mustQuery = true;
        } else { // 查找缓存中是否包含此exchange_id的缓存
            exchangeMarkInfoMap = (Map<String, ExchangeMarkInfo>) cacheObj;
            for (String key : exchangeMarkInfoMap.keySet()) {
                String exchangeId = key.split(";")[0];
                if (exchangeId.equals(id))
                    return exchangeMarkInfoMap.get(key);
            }
            mustQuery = true;
        }

        ExchangeMarkInfo markInfo = new ExchangeMarkInfo();
        if (mustQuery) {
            String url = coinConfigProperties.getMarkInfoExchangeJob().getUrl();
            String convert = coinConfigProperties.getMarkInfoExchangeJob().getConvert();
            int limit = coinConfigProperties.getMarkInfoExchangeJob().getLimit();
            JSONObject object = JSONObject.fromObject(setGetHasHeader(url + "?id=" + id + "&convert=" + convert + "&limit=" + limit)).getJSONObject("data");
            JSONArray json = object.getJSONArray("market_pairs");
            Exchange exchange = new Exchange();
            exchange.setExchangeid(object.getInt("id"));
            exchange.setName(object.getString("name"));
            exchange.setSlug(object.getString("slug"));
            for (int i = 0; i < json.size(); i++) {
                ExchangeMarkInfoDetail detail = new ExchangeMarkInfoDetail();
                detail.setEchangeId(object.getInt("id"));
                detail.setEchangeName(object.getString("name"));
                detail.setEchangeSlug(object.getString("slug"));
                detail.setNumNarketPairs(object.getInt("num_market_pairs"));
                detail.setMarketPair(json.getJSONObject(i).getString("market_pair"));
                detail.setMarketPairBaseId(json.getJSONObject(i).getJSONObject("market_pair_base").getInt("currency_id"));
                detail.setMarketPairBaseSymbol(json.getJSONObject(i).getJSONObject("market_pair_base").getString("currency_symbol"));
                detail.setMarketPairType(json.getJSONObject(i).getJSONObject("market_pair_base").getString("currency_type"));
                detail.setMarketPairQuoteId(json.getJSONObject(i).getJSONObject("market_pair_quote").getInt("currency_id"));
                detail.setMarketPairQuoteSymbol(json.getJSONObject(i).getJSONObject("market_pair_quote").getString("currency_symbol"));
                detail.setQuotePirce(new BigDecimal(json.getJSONObject(i).getJSONObject("quote").getJSONObject("exchange_reported").getString("price")));
                detail.setQuoteVolume24hBase((new BigDecimal(json.getJSONObject(i).getJSONObject("quote").getJSONObject("exchange_reported").getString("volume_24h_base"))));
                detail.setQuoteVolume24hQuote((new BigDecimal(json.getJSONObject(i).getJSONObject("quote").getJSONObject("exchange_reported").getString("volume_24h_quote"))));
                detail.setBTCPrice(new BigDecimal(json.getJSONObject(i).getJSONObject("quote").getJSONObject("BTC").getString("price")));
                detail.setBTCVolume24h(new BigDecimal(json.getJSONObject(i).getJSONObject("quote").getJSONObject("BTC").getString("volume_24h")));
                detail.setLastUpdated(
                        DateUtils.parseDate(json.getJSONObject(i).getJSONObject("quote").getJSONObject("BTC").getString("last_updated"), DateUtils.datetimeISO8601Format));
                markInfo.getMarkInfoList().add(detail);
            }
            markInfo.setExchange(exchange);
        }
        exchangeMarkInfoMap.put(id + ";" + DateUtils.getMillis(new Date()), markInfo);
        redisService.set(CoinConstant.CURRENCY_API + "_" + CoinConstant.EXCHANGE_MARK_INFO, exchangeMarkInfoMap);
        return markInfo;
    }

    @SuppressWarnings("unchecked")
    public MarkInofExchange marketInfoExchange(int base, int quote, int start) throws Exception {
        Object cacheObj = redisService.get(CoinConstant.CURRENCY_API + "_" + CoinConstant.MARK_INFO_EXCHANGE);
        Map<String, MarkInofExchange> markInofExchangeMap = new HashMap<String, MarkInofExchange>();
        // key的组合方式为 baseId_quoteId;start/timelong
        if (cacheObj != null) { // 查找缓存中是否包含此base_quot的缓存，如果包含直接返回缓存里的数据
            markInofExchangeMap = (Map<String, MarkInofExchange>) cacheObj;
            for (String key : markInofExchangeMap.keySet()) {
                String[] splits = key.split(";");
                String[] temp = splits[0].split("_");
                int baseId = Integer.parseInt(temp[0]);
                int quoteId = Integer.parseInt(temp[1]);
                int start_ed = Integer.parseInt(splits[1].split("/")[0]);
                if (baseId == base && quoteId == quote && start_ed == start)
                    return markInofExchangeMap.get(key);
            }
        }

        MarkInofExchange markInfo = new MarkInofExchange();
        String url = coinConfigProperties.getMarketInfoExchange().getUrl();
        String convert = coinConfigProperties.getMarketInfoExchange().getConvert();
        int limit = coinConfigProperties.getMarketInfoExchange().getLimit();
        int oldstart = start;
        while (true) {
            JSONObject object = JSONObject.fromObject(setGetHasHeader(url + "?id=" + base + "&convert=" + convert + "&limit=" + limit + "&start=" + start)).getJSONObject("data");
            markInfo.setId(object.getInt("id"));
            markInfo.setName(object.getString("name"));
            markInfo.setSymbol(object.getString("symbol"));
            markInfo.setNum_market_pairs(object.getInt("num_market_pairs"));

            JSONArray json = object.getJSONArray("market_pairs");
            for (int i = 0; i < json.size(); i++) {
                markInfo.setMarket_pairs(json.getJSONObject(i).getString("market_pair"));
                int baseId = json.getJSONObject(i).getJSONObject("market_pair_base").getInt("currency_id");
                int quoteId = json.getJSONObject(i).getJSONObject("market_pair_quote").getInt("currency_id");
                if (base == baseId && quote == quoteId) {
                    MarkInofExchangeDetail detail = new MarkInofExchangeDetail();
                    Exchange exchange = new Exchange();
                    exchange.setExchangeid(json.getJSONObject(i).getJSONObject("exchange").getInt("id"));
                    exchange.setName(json.getJSONObject(i).getJSONObject("exchange").getString("name"));
                    exchange.setSlug(json.getJSONObject(i).getJSONObject("exchange").getString("slug"));
                    detail.setMarketPair(json.getJSONObject(i).getString("market_pair"));
                    detail.setMarketPairBaseID(json.getJSONObject(i).getJSONObject("market_pair_base").getInt("currency_id"));
                    detail.setMarketPairBaseSymbol(json.getJSONObject(i).getJSONObject("market_pair_base").getString("currency_symbol"));
                    detail.setMarketPairBaseType(json.getJSONObject(i).getJSONObject("market_pair_base").getString("currency_type"));
                    detail.setMarketPairQuoteID(json.getJSONObject(i).getJSONObject("market_pair_quote").getInt("currency_id"));
                    detail.setMarketPairQuoteSymbol(json.getJSONObject(i).getJSONObject("market_pair_quote").getString("currency_symbol"));
                    detail.setQuotePirce(new BigDecimal(json.getJSONObject(i).getJSONObject("quote").getJSONObject("exchange_reported").getString("price")));
                    detail.setQuoteVolume24hBase((new BigDecimal(json.getJSONObject(i).getJSONObject("quote").getJSONObject("exchange_reported").getString("volume_24h_base"))));
                    detail.setQuoteVolume24hQuote((new BigDecimal(json.getJSONObject(i).getJSONObject("quote").getJSONObject("exchange_reported").getString("volume_24h_quote"))));
                    detail.setConvertPrice(new BigDecimal(json.getJSONObject(i).getJSONObject("quote").getJSONObject(convert).getString("price")));
                    detail.setConvertVolume24h(new BigDecimal(json.getJSONObject(i).getJSONObject("quote").getJSONObject(convert).getString("volume_24h")));
                    detail.setLastUpdate(
                            DateUtils.parseDate(json.getJSONObject(i).getJSONObject("quote").getJSONObject(convert).getString("last_updated"), DateUtils.datetimeISO8601Format));
                    detail.setExchange(exchange);
                    markInfo.getExchangeList().add(detail);
                }
            }
            start += limit;
            if (markInfo.getExchangeList().size() > 0 || ((start - oldstart) / limit) > 1) // 已经有数数据了,不必循环
                break;
        }

        markInfo.setStart(start);
        if (markInfo.getExchangeList().size() > 0) {
            markInofExchangeMap.put(base + "_" + quote + ";" + oldstart + "/" + DateUtils.getMillis(new Date()), markInfo);
            redisService.set(CoinConstant.CURRENCY_API + "_" + CoinConstant.MARK_INFO_EXCHANGE, markInofExchangeMap);
        }
        return markInfo;
    }

    public AssetInfoExchange assetInfoExchange(int id) throws Exception {
        Object cacheObj = redisService.get(CoinConstant.CURRENCY_API + "_" + CoinConstant.ASSET_INFO_EXCHANGE + "_" + id);
        if (cacheObj != null)
            return (AssetInfoExchange) cacheObj;

        AssetInfoExchange assetInfoExchange = new AssetInfoExchange();
        Map<Integer, String> map = new HashMap<Integer, String>();
        String url = coinConfigProperties.getAssetInfoExchange().getUrl();
        String convert = coinConfigProperties.getAssetInfoExchange().getConvert();
        int limit = coinConfigProperties.getAssetInfoExchange().getLimit();
        int start = 1;
        while (true) {
            System.out.println("assetInfoExchange start ======= : " + start);
            JSONArray json = JSONObject.fromObject(setGetHasHeader(url + "?start=" + start + "&limit=" + limit + "&convert=" + convert + "&id=" + id)).getJSONObject("data")
                    .getJSONArray("market_pairs");
            if (json.size() == 0)
                break;

            for (int i = 0; i < json.size(); i++) {
                JSONObject item = json.getJSONObject(i);
                ExchangeGeneral exchangeGeneral = new ExchangeGeneral();
                exchangeGeneral.setEid(item.getJSONObject("exchange").getInt("id"));
                exchangeGeneral.setSlug(item.getJSONObject("exchange").getString("slug"));
                exchangeGeneral.setName(item.getJSONObject("exchange").getString("name"));
                if (!map.containsKey(Integer.valueOf(exchangeGeneral.getEid()))) {
                    map.put(Integer.valueOf(exchangeGeneral.getEid()), exchangeGeneral.getName());
                    assetInfoExchange.getList().add(exchangeGeneral);
                }
            }

            start += limit;
        }

        if (assetInfoExchange.getList().size() > 0)
            redisService.set(CoinConstant.CURRENCY_API + "_" + CoinConstant.ASSET_INFO_EXCHANGE + "_" + id, assetInfoExchange, 60 * 60 * 24l);
        return assetInfoExchange;

    }

    public OneDayMarketCapInfo onedayCap() throws Exception {
        Object cacheObj = redisService.get(CoinConstant.CURRENCY_API + "_" + CoinConstant.ONE_DAY_CAP);
        if (cacheObj != null)
            return (OneDayMarketCapInfo) cacheObj;

        return null;
    }

    private String setGetHasHeader(String url) throws Exception {
        String header = coinConfigProperties.getApiKeyName() + ";" + coinConfigProperties.getApiKeyValue();
        return httpUtil.sendGet(url, header);
    }

}

