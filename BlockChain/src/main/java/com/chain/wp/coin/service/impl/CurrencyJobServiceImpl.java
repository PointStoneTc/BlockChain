package com.chain.wp.coin.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chain.base.service.MyBatisCommonServiceI;
import com.chain.redis.service.RedisServiceI;
import com.chain.util.DateUtils;
import com.chain.util.HttpUtil;
import com.chain.wp.coin.config.CoinConfigProperties;
import com.chain.wp.coin.config.CoinConstant;
import com.chain.wp.coin.entity.Asset;
import com.chain.wp.coin.entity.BtcMonitorLineHistory;
import com.chain.wp.coin.entity.BtcMonitorOhlcvHistory;
import com.chain.wp.coin.entity.BtcMonitorRateHistory;
import com.chain.wp.coin.entity.FloatExchange;
import com.chain.wp.coin.entity.SpecificRate;
import com.chain.wp.coin.page.AssetQuotation;
import com.chain.wp.coin.page.BtcMonitor;
import com.chain.wp.coin.page.ExchangeMarkInfo;
import com.chain.wp.coin.page.MarkInofExchange;
import com.chain.wp.coin.page.OneDayMarketCapInfo;
import com.chain.wp.coin.service.BtcMonitorLineHistoryServiceI;
import com.chain.wp.coin.service.BtcMonitorOhlcvHistoryServiceI;
import com.chain.wp.coin.service.BtcMonitorRateHistoryServiceI;
import com.chain.wp.coin.service.CurrencyJobServiceI;
import com.chain.wp.coin.service.FloatExchangeServiceI;
import com.chain.wp.coin.service.SpecificRateServiceI;

import jodd.bean.BeanUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("currencyJobService")
@Transactional
public class CurrencyJobServiceImpl implements CurrencyJobServiceI {
  private static final int BTC_MONITOR_COLLECTION_FREQUENCY = 288;
  private static final int ASSETS_QUOTATION_RATEHISTORY_24H = 96;

  @Autowired
  private CoinConfigProperties coinConfigProperties;
  @Autowired
  private RedisServiceI redisService;
  @Autowired
  private HttpUtil httpUtil;

  @Autowired
  private SpecificRateServiceI specificRateService;
  @Autowired
  private FloatExchangeServiceI floatExchangeService;
  @Autowired
  private BtcMonitorOhlcvHistoryServiceI btcMonitorOhlcvHistoryService;
  @Autowired
  private BtcMonitorLineHistoryServiceI btcMonitorLineHistoryService;
  @Autowired
  private BtcMonitorRateHistoryServiceI btcMonitorRateHistoryService;
  @Autowired
  private MyBatisCommonServiceI myBatisCommonService;

  @SuppressWarnings("unchecked")
  public boolean setAssetsQuotationRateCurrent_Job() throws Exception {
    String url = coinConfigProperties.getAssetsQuotationRateCurrent().getUrl();
    String id_base = coinConfigProperties.getAssetsQuotationRateCurrent().getId_base();
    String id_quote = coinConfigProperties.getAssetsQuotationRateCurrent().getId_quote();
    JSONObject json = JSONObject.fromObject(setGetHasHeader(url + "?symbol=" + id_base + "&convert=" + id_quote));

    // 1.更新最新的货币报价信息
    List<AssetQuotation> assetQuotationList = new ArrayList<AssetQuotation>();
    Map<String, String> assetQuotationListKeys = new HashMap<String, String>();
    JSONObject data = JSONObject.fromObject(json.get("data"));

    String[] bases = id_base.split("\\,");
    for (String base : bases) {
      JSONObject obj1 = data.getJSONObject(base);
      AssetQuotation assetQuotation = new AssetQuotation();
      SpecificRate rate = new SpecificRate();
      rate.setBaseId(Integer.valueOf(obj1.getInt("id")));
      rate.setBaseSymbol(base);
      rate.setQuoteSymbol(id_quote);
      rate.setBaseName(obj1.getString("name"));

      JSONObject obj2 = obj1.getJSONObject("quote").getJSONObject(id_quote);
      rate.setPrice(obj2.getDouble("price"));
      rate.setVolume24h(obj2.getDouble("volume_24h"));
      rate.setPercentChange1h(obj2.getDouble("percent_change_1h"));
      rate.setPercentChange24h(obj2.getDouble("percent_change_24h"));
      rate.setPercentChange7d(obj2.getDouble("percent_change_7d"));
      rate.setMarketCap(obj2.getDouble("market_cap"));
      rate.setLastUpdated(DateUtils.parseDate(obj2.getString("last_updated"), DateUtils.datetimeISO8601Format));
      rate.setCreateDate(new Date());
      assetQuotation.setSpecificRate(rate);
      assetQuotationList.add(assetQuotation);
      assetQuotationListKeys.put(rate.getBaseSymbol(), rate.getBaseSymbol());
      specificRateService.insert(rate);
    }

    // 2.获得上一次的数据，对比排序是数据是否发生变化，如果发生变化，
    Map<String, double[]> histories = (Map<String, double[]>) redisService.get(CoinConstant.CURRENCY_API + "_" + CoinConstant.ASSETS_QUOTATION_RATE_HISTORY_24H);

    List<String> newSymbolList = new ArrayList<String>();
    List<String> lostSymbolList = new ArrayList<String>();

    // 3.找到新的卡片数据
    if (histories != null)
      for (AssetQuotation item : assetQuotationList)
        if (!histories.containsKey(item.getSpecificRate().getBaseSymbol()))
          newSymbolList.add(item.getSpecificRate().getBaseSymbol());

    // 4.找到老的卡片数据
    if (histories != null)
      for (String item : histories.keySet())
        if (!assetQuotationListKeys.containsKey(item))
          lostSymbolList.add(item);

    // 5.删除老的卡片数据
    if (histories != null)
      for (String item : lostSymbolList)
        histories.remove(item);

    // 6.增加卡片的历史数据
    if (newSymbolList.size() > 0) {
      setAssetsQuotationRateHistory24h_Job();
    }

    // 7.增加新的卡片数据
    redisService.set(CoinConstant.CURRENCY_API + "_" + CoinConstant.ASSETS_QUOTATION_RATE, assetQuotationList);
    return true;
  }

  public boolean setAssetsQuotationRateHistory24h_Job() throws Exception {
    String id_base = coinConfigProperties.getAssetsQuotationRateCurrent().getId_base();
    String id_quote = coinConfigProperties.getAssetsQuotationRateCurrent().getId_quote();

    Map<String, double[]> histories = new HashMap<String, double[]>();
    String[] base = id_base.split("\\,");


    for (String key : base) {
      String sql = "select t.price from (select @rownum:=@rownum+1 AS rownum, r.base_symbol, r.price from (SELECT @rownum:=0) r, cc_specific_rate r where r.base_symbol = '" + key
          + "' and r.quote_symbol = '" + id_quote + "' order by r.last_updated desc limit " + ASSETS_QUOTATION_RATEHISTORY_24H + ") t where t.rownum mod " + 15 + " = 1";
      List<LinkedHashMap<String, Object>> list = myBatisCommonService.superManagerSelect(sql);
      double[] rates = new double[list.size()];
      for (int i = 0; i < list.size(); i++) {
        rates[i] = (double) list.get(i).get("price");
      }
      histories.put(key, rates);
    }
    redisService.set(CoinConstant.CURRENCY_API + "_" + CoinConstant.ASSETS_QUOTATION_RATE_HISTORY_24H, histories);
    return true;
  }

  public boolean setAssetsGeneral_Job() throws Exception {
    String url = coinConfigProperties.getAssetsGeneralJob().getUrl();
    int limit = coinConfigProperties.getAssetsGeneralJob().getLimit();
    String convert = coinConfigProperties.getAssetsGeneralJob().getConvert();
    String sort = coinConfigProperties.getAssetsGeneralJob().getSort();
    String sort_dir = coinConfigProperties.getAssetsGeneralJob().getSort_dir();

    // 1.初始化数组，数组倒数第二个存不符合命名规范的货币，最后一个存所有
    String indexc = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    List<List<Asset>> list = new ArrayList<List<Asset>>();
    for (int i = 0; i < indexc.length() + 2; i++) {
      List<Asset> temp = new ArrayList<Asset>();
      list.add(temp);
    }
    list.add(new ArrayList<Asset>()); // 存储其他名字的货币

    int start = 1;
    while (true) {
      System.out.println("start ======= : " + start);
      JSONArray json = JSONObject.fromObject(setGetHasHeader(url + "?start=" + start + "&limit=" + limit + "&convert=" + convert + "&sort=" + sort + "&sort_dir=" + sort_dir))
          .getJSONArray("data");
      if (json.size() == 0)
        break;

      for (int i = 0; i < json.size(); i++) {
        JSONObject item = json.getJSONObject(i);
        Asset asset = new Asset();
        asset.setCid(Integer.valueOf(item.getInt("id")));
        asset.setName(item.getString("name"));
        asset.setSymbol(item.getString("symbol"));
        asset.setSlug(item.getString("slug"));
        asset.setCirculatingSupply(item.getString("circulating_supply"));
        asset.setTotalSupply(item.getString("total_supply"));
        asset.setMaxSupply(item.getString("max_supply"));
        asset.setNumMarketPairs(item.getString("num_market_pairs"));
        JSONObject convertJson = item.getJSONObject("quote").getJSONObject(convert);
        asset.setQuotePrice(convertJson.getString("price"));
        asset.setQuoteVolume24h(convertJson.getString("volume_24h"));
        asset.setQuotePercentChange1h(convertJson.getString("percent_change_1h"));
        asset.setQuotePercentChange24h(convertJson.getString("percent_change_24h"));
        asset.setQuotePercentChange7d(convertJson.getString("percent_change_7d"));
        asset.setQuoteMarketCap(convertJson.getString("market_cap"));
        asset.setLastUpdated((DateUtils.parseDate(convertJson.getString("last_updated"), DateUtils.datetimeISO8601Format)));

        int idx = indexc.indexOf(asset.getSymbol().toUpperCase().charAt(0));
        if (idx < 0) {
          list.get(list.size() - 2).add(asset);
        } else {
          list.get(idx).add(asset);
        }
        list.get(list.size() - 1).add(asset);
      }

      start += limit;
    }
    for (int i = 0; i < indexc.length(); i++) {
      if (list.get(i).size() > 0)
        redisService.set(CoinConstant.CURRENCY_API + "_" + CoinConstant.ASSETS_GENERAL + "_" + indexc.charAt(i), list.get(i));
    }
    redisService.set(CoinConstant.CURRENCY_API + "_" + CoinConstant.ASSETS_GENERAL + "_OTHER", list.get(list.size() - 2));
    redisService.set(CoinConstant.CURRENCY_API + "_" + CoinConstant.ASSETS_GENERAL + "_ALL", list.get(list.size() - 1));
    return true;
  }

  public void setTopMarketTrend_Job() throws Exception {
    // TODO 不做
  }

  public void setLegalcurrencyTrend_Job() throws Exception {
    // TODO 不做
  }

  public boolean setTopFloatingExchange_Job() throws Exception {
    // 1.查询最新的交易所浮动API数据
    String url = coinConfigProperties.getTopFloatingExchange().getUrl();
    String market_type = coinConfigProperties.getTopFloatingExchange().getMarket_type();
    String convert = coinConfigProperties.getTopFloatingExchange().getConvert();
    int limit = coinConfigProperties.getTopFloatingExchange().getLimit();
    String sort_dir_desc = coinConfigProperties.getTopFloatingExchange().getSort_dir_desc();
    String sort_dir_asc = coinConfigProperties.getTopFloatingExchange().getSort_dir_asc();
    StringBuffer urlBuffer =
        new StringBuffer(url).append("?market_type=").append(market_type).append("&convert=").append(convert).append("&limit=").append(limit).append("&sort_dir=");
    JSONObject fireTop10Json = JSONObject.fromObject(setGetHasHeader(urlBuffer.toString() + sort_dir_desc));

    JSONObject coldTop10Json = JSONObject.fromObject(setGetHasHeader(urlBuffer.toString() + sort_dir_asc));

    // 2.持久或保存
    List<FloatExchange> fireTop10JsonList = setExchangeRiseFall(fireTop10Json.getJSONArray("data"), convert.split(","));
    List<FloatExchange> coldTop10JsonList = setExchangeRiseFall(coldTop10Json.getJSONArray("data"), convert.split(","));
    floatExchangeService.insertByBatch(fireTop10JsonList);
    floatExchangeService.insertByBatch(coldTop10JsonList);
    List<FloatExchange> floatExchangeList = new ArrayList<FloatExchange>();
    floatExchangeList.addAll(fireTop10JsonList);
    floatExchangeList.addAll(coldTop10JsonList);

    // 3.更新缓存
    redisService.set(CoinConstant.CURRENCY_API + "_" + CoinConstant.LEGALCURRENCY_TREND, floatExchangeList);
    return true;
  }

  @SuppressWarnings("unchecked")
  public boolean setExchangeMarkInfo_Job() throws Exception {
    // 1.判断是否有缓存信息
    Object cacheObj = redisService.get(CoinConstant.CURRENCY_API + "_" + CoinConstant.EXCHANGE_MARK_INFO);

    if (cacheObj == null)
      return true;

    Map<String, ExchangeMarkInfo> exchangeMarkInfos = (Map<String, ExchangeMarkInfo>) cacheObj;
    // 2.判断缓存的时效性是否大于5分钟（超过5分钟失效）
    for (String key : exchangeMarkInfos.keySet()) {
      String updateTime = key.split(";")[1];
      int difSeconds = DateUtils.dateDiff('s', DateUtils.getCalendar(), DateUtils.getCalendar(Long.parseLong(updateTime)));
      if (difSeconds >= 300)
        exchangeMarkInfos.remove(key);
    }

    redisService.set(CoinConstant.CURRENCY_API + "_" + CoinConstant.EXCHANGE_MARK_INFO, exchangeMarkInfos);
    return true;
  }

  @SuppressWarnings("unchecked")
  public boolean setMarkInfoExchange_Job() throws Exception {
    // 1.判断是否有缓存信息
    Object cacheObj = redisService.get(CoinConstant.CURRENCY_API + "_" + CoinConstant.MARK_INFO_EXCHANGE);

    if (cacheObj == null)
      return true;

    Map<String, MarkInofExchange> exchangeMarkInfos = (Map<String, MarkInofExchange>) cacheObj;
    // 2.判断缓存的时效性是否大于1分钟（超过1分钟失效）
    for (String key : exchangeMarkInfos.keySet()) {
      String updateTime = key.split(";")[1];
      int difSeconds = DateUtils.dateDiff('s', DateUtils.getCalendar(), DateUtils.getCalendar(Long.parseLong(updateTime)));
      if (difSeconds >= 60)
        exchangeMarkInfos.remove(key);
    }

    redisService.set(CoinConstant.CURRENCY_API + "_" + CoinConstant.MARK_INFO_EXCHANGE, exchangeMarkInfos);
    return true;
  }

  public boolean setBtcMonitorLine_OHLCV_Job() throws Exception {
    // 1.读取Line（汇率）API信息
    String line_url = coinConfigProperties.getBtcMonitorLineOHLCV().getLine().getUrl();
    String line_symbol = coinConfigProperties.getBtcMonitorLineOHLCV().getLine().getSymbol();
    String line_convert = coinConfigProperties.getBtcMonitorLineOHLCV().getLine().getConvert();
    int line_amount = coinConfigProperties.getBtcMonitorLineOHLCV().getLine().getAmount();

    JSONObject line_quote =
        JSONObject.fromObject(setGetHasHeader(line_url + "?symbol=" + line_symbol + "&convert=" + line_convert + "&amount=" + line_amount)).getJSONObject("data");

    String[] line_converts = line_convert.split(",");
    BtcMonitorLineHistory lineHistoryItem = new BtcMonitorLineHistory();
    for (String convert : line_converts) {
      JSONObject item = line_quote.getJSONObject("quote").getJSONObject(convert);
      BeanUtil.pojo.setProperty(lineHistoryItem, "price" + convert.substring(0, 1) + convert.substring(1).toLowerCase(), Double.valueOf(item.getDouble("price")));
    }
    lineHistoryItem.setBaseSymbol(line_symbol);
    lineHistoryItem.setLastUpdated(DateUtils.parseDate(line_quote.getString("last_updated"), DateUtils.datetimeISO8601Format));
    lineHistoryItem.setCreateDate(new Date());

    // 2.持久化Line（汇率）信息
    btcMonitorLineHistoryService.insert(lineHistoryItem);

    // 3.读取OHLCV API信息
    String ohlcv_url = coinConfigProperties.getBtcMonitorLineOHLCV().getOhlcv().getUrl();
    String ohlcv_symbol = coinConfigProperties.getBtcMonitorLineOHLCV().getOhlcv().getSymbol();
    String ohlcv_convert = coinConfigProperties.getBtcMonitorLineOHLCV().getOhlcv().getConvert();

    JSONObject ohlcv_quote = JSONObject.fromObject(setGetHasHeader(ohlcv_url + "?symbol=" + ohlcv_symbol + "&convert=" + ohlcv_convert)).getJSONObject("data")
        .getJSONObject(line_symbol).getJSONObject("quote");

    JSONObject item = ohlcv_quote.getJSONObject(ohlcv_convert);
    BtcMonitorOhlcvHistory ohlcvHistoryItem = new BtcMonitorOhlcvHistory();
    ohlcvHistoryItem.setBaseSymbol(ohlcv_symbol);
    ohlcvHistoryItem.setQuoteSymbol(ohlcv_convert);
    ohlcvHistoryItem.setOpen(item.getDouble("open"));
    ohlcvHistoryItem.setHigh(item.getDouble("high"));
    ohlcvHistoryItem.setLow(item.getDouble("low"));
    ohlcvHistoryItem.setClose(item.getDouble("close"));
    ohlcvHistoryItem.setVolume(item.getDouble("volume"));
    ohlcvHistoryItem.setLastUpdated(DateUtils.parseDate(item.getString("last_updated"), DateUtils.datetimeISO8601Format));
    ohlcvHistoryItem.setCreateDate(new Date());

    // 4.持久化OHLCV信息
    btcMonitorOhlcvHistoryService.insert(ohlcvHistoryItem);

    // 5.缓存数据
    BtcMonitor btcMonitor;
    Object cacheObj = redisService.get(CoinConstant.CURRENCY_API + "_" + CoinConstant.BTC_MONITOR_LINE_OHLCV);

    long line_count = myBatisCommonService.getCount("select count(1) ct from cc_btc_monitor_line_history");
    long ohlcv_count = myBatisCommonService.getCount("select count(1) ct from cc_btc_monitor_ohlcv_history");

    if (cacheObj == null) { // 缓存为空
      btcMonitor = new BtcMonitor();
      List<BtcMonitorLineHistory> lineHisList;
      List<BtcMonitorOhlcvHistory> ohlcvHisList;

      // 获取line数据
      if (line_count > BTC_MONITOR_COLLECTION_FREQUENCY) {// 每5分钟一个点位，24小时等于12*24个点位
        lineHisList =
            btcMonitorLineHistoryService.selectAll(Integer.valueOf((int) (line_count - BTC_MONITOR_COLLECTION_FREQUENCY)), Integer.valueOf(BTC_MONITOR_COLLECTION_FREQUENCY));
      } else {
        lineHisList = btcMonitorLineHistoryService.selectAll(null, null);
      }
      btcMonitor.getLine_his_queue().addAll(lineHisList);

      // 获取ohlcv数据
      if (ohlcv_count > BTC_MONITOR_COLLECTION_FREQUENCY) { // 每5分钟一个点位，24小时等于12*24个点位
        ohlcvHisList =
            btcMonitorOhlcvHistoryService.selectAll(Integer.valueOf((int) (ohlcv_count - BTC_MONITOR_COLLECTION_FREQUENCY)), Integer.valueOf(BTC_MONITOR_COLLECTION_FREQUENCY));
      } else {
        ohlcvHisList = btcMonitorOhlcvHistoryService.selectAll(null, null);
      }
      btcMonitor.getOhlcv_his_queue().addAll(ohlcvHisList);
    } else { // 已经存在缓存
      btcMonitor = (BtcMonitor) cacheObj;
      // 先poll，再add
      if (btcMonitor.getLine_his_queue().size() > BTC_MONITOR_COLLECTION_FREQUENCY) {
        btcMonitor.getLine_his_queue().poll();
      }
      btcMonitor.getLine_his_queue().add(lineHistoryItem);

      if (btcMonitor.getOhlcv_his_queue().size() > BTC_MONITOR_COLLECTION_FREQUENCY) {
        btcMonitor.getOhlcv_his_queue().poll();
      }
      btcMonitor.getOhlcv_his_queue().add(ohlcvHistoryItem);
    }

    redisService.set(CoinConstant.CURRENCY_API + "_" + CoinConstant.BTC_MONITOR_LINE_OHLCV, btcMonitor);
    return true;
  }

  public boolean setBtcMonitorRate_Job() throws Exception {
    // 1.查询最新的BTC-法币的价格API数据
    String url = coinConfigProperties.getBtcMonitorRate().getUrl();
    String symbol = coinConfigProperties.getBtcMonitorRate().getSymbol();
    String convert = coinConfigProperties.getBtcMonitorRate().getConvert();
    JSONObject quote =
        JSONObject.fromObject(setGetHasHeader(url.toString() + "&symbol=" + symbol + "&convert=" + convert)).getJSONObject("data").getJSONObject(symbol).getJSONObject("quote");

    BtcMonitorRateHistory rate = new BtcMonitorRateHistory();
    rate.setBaseSymbol(symbol);
    // 2.持久或保存
    for (String key : convert.split(",")) {
      JSONObject item = quote.getJSONObject(key);
      if ("USD".equals(key)) {
        rate.setVolume24hUsd(item.getDouble("volume_24h"));
        rate.setPercentChange1hUsd(item.getDouble("percent_change_1h"));
        rate.setPercentChange24hUsd(item.getDouble("percent_change_24h"));
        rate.setPercentChange7dUsd(item.getDouble("percent_change_7d"));
        rate.setMarketCapUsd(item.getDouble("market_cap"));
        rate.setLastUpdated(DateUtils.parseDate(item.getString("last_updated"), DateUtils.datetimeISO8601Format));
      }
      BeanUtil.pojo.setProperty(rate, "price" + key.substring(0, 1) + key.substring(1).toLowerCase(), Double.valueOf(item.getDouble("price")));
    }
    rate.setCreateDate(new Date());

    btcMonitorRateHistoryService.insert(rate);
    redisService.set(CoinConstant.CURRENCY_API + "_" + CoinConstant.BTC_MONITOR_RATE, rate);
    return true;
  }

  public boolean setOnedayCap_Job() throws Exception {
    String url = coinConfigProperties.getOnedayHourCapJob().getUrl();
    String convent = coinConfigProperties.getOnedayHourCapJob().getConvert();
    JSONObject json = JSONObject.fromObject(setGetHasHeader(url + "?convert=" + convent)).getJSONObject("data");
    OneDayMarketCapInfo oneDayMarketCapInfo = new OneDayMarketCapInfo();
    oneDayMarketCapInfo.setBtc_dominance(json.getDouble("btc_dominance"));
    oneDayMarketCapInfo.setEth_dominance(json.getDouble("eth_dominance"));
    oneDayMarketCapInfo.setActive_cryptocurrencies(json.getDouble("active_cryptocurrencies"));
    oneDayMarketCapInfo.setActive_market_pairs(json.getDouble("active_market_pairs"));
    oneDayMarketCapInfo.setActive_exchanges(json.getDouble("active_exchanges"));
    oneDayMarketCapInfo.setLast_updated(DateUtils.parseDate(json.getString("last_updated"), DateUtils.datetimeISO8601Format));

    for (String key : convent.split(",")) {
      JSONObject quote_item = json.getJSONObject("quote").getJSONObject(key);
      oneDayMarketCapInfo.addQuote(key, quote_item.getDouble("total_market_cap"), quote_item.getDouble("total_volume_24h"));
    }

    redisService.set(CoinConstant.CURRENCY_API + "_" + CoinConstant.ONE_DAY_CAP, oneDayMarketCapInfo);
    return false;
  }

  /*--------------------------------------------
  |             私有方法                        |
  ============================================*/
  @SuppressWarnings("unused")
  private List<FloatExchange> setExchangeRiseFall(JSONArray array, String[] converts) throws ParseException {
    List<FloatExchange> floatExchangeList = new ArrayList<FloatExchange>();
    int count = 0;
    for (int i = 0; i < array.size(); i++) {
      if (count == 10) {
        break;
      }

      JSONObject item = array.getJSONObject(i);

      int num = item.getInt("num_market_pairs");
      if (num > 0) {
        FloatExchange exchange = new FloatExchange();
        exchange.setExchangeId(item.getInt("id"));
        exchange.setName(item.getString("name"));
        exchange.setSlug(item.getString("slug"));
        exchange.setNumMarketPairs(item.getInt("num_market_pairs"));
        exchange.setLastUpdated(DateUtils.parseDate(item.getString("last_updated"), DateUtils.datetimeISO8601Format));
        JSONObject quote = item.getJSONObject("quote");
        exchange.setbVolume24h(quote.getJSONObject(converts[0]).getDouble("volume_24h"));
        exchange.setqVolume24h(quote.getJSONObject(converts[1]).getDouble("volume_24h"));
        exchange.setCreateDate(new Date());
        floatExchangeList.add(exchange);
        count++;
      }
    }
    return floatExchangeList;
  }

  private String setGetHasHeader(String url) throws Exception {
    String header = coinConfigProperties.getApiKeyName() + ";" + coinConfigProperties.getApiKeyValue();
    return httpUtil.sendGet(url, header);
  }

}
