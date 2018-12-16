package com.chain.wp.coin.service.impl;

import java.math.BigDecimal;
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
import com.chain.redis.service.IRedisService;
import com.chain.util.DateUtils;
import com.chain.util.HttpUtil;
import com.chain.wp.coin.config.CoinConfigProperties;
import com.chain.wp.coin.config.CoinConstant;
import com.chain.wp.coin.entity.BtcMonitorLineHistory;
import com.chain.wp.coin.entity.BtcMonitorOhlcvHistory;
import com.chain.wp.coin.entity.BtcMonitorRateHistory;
import com.chain.wp.coin.entity.FloatExchange;
import com.chain.wp.coin.entity.SpecificRate;
import com.chain.wp.coin.page.AssetGeneral;
import com.chain.wp.coin.page.AssetQuotation;
import com.chain.wp.coin.page.BtcMonitor;
import com.chain.wp.coin.page.ExchangeMarkInfo;
import com.chain.wp.coin.page.MarkInofExchange;
import com.chain.wp.coin.service.AssetServiceI;
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
  private IRedisService redisService;

  @Autowired
  private HttpUtil httpUtil;

  @Autowired
  private AssetServiceI assetService;
  @Autowired
  private SpecificRateServiceI specificRateService;
  @Autowired
  private FloatExchangeServiceI floatExchangeServiceI;
  @Autowired
  private BtcMonitorOhlcvHistoryServiceI btcMonitorOhlcvHistoryService;
  @Autowired
  private BtcMonitorLineHistoryServiceI btcMonitorLineHistoryService;
  @Autowired
  private BtcMonitorRateHistoryServiceI btcMonitorRateHistoryService;
  @Autowired
  private MyBatisCommonServiceI myBatisCommonService;


  @SuppressWarnings("unchecked")
  @Override
  public boolean setAssetsQuotationRateCurrent_Job() throws Exception {
    String url = coinConfigProperties.getAssetsQuotationRateCurrent().getUrl();
    String id_base = coinConfigProperties.getAssetsQuotationRateCurrent().getId_base();
    String id_quote = coinConfigProperties.getAssetsQuotationRateCurrent().getId_quote();
    JSONObject json = JSONObject
        .fromObject(httpUtil.sendGet(url + "?symbol=" + id_base + "&convert=" + id_quote, coinConfigProperties.getApiKeyName() + ";" + coinConfigProperties.getApiKeyValue()));

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

  @Override
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

  @Override
  public boolean setAssetsGeneral_Job() throws Exception {
    String url = coinConfigProperties.getAssetsGeneralJob().getUrl();
    int limit = coinConfigProperties.getAssetsGeneralJob().getLimit();
    String convert = coinConfigProperties.getAssetsGeneralJob().getConvert();
    String sort = coinConfigProperties.getAssetsGeneralJob().getSort();
    String sort_dir = coinConfigProperties.getAssetsGeneralJob().getSort_dir();
    JSONObject object = JSONObject.fromObject(httpUtil.sendGet(url + "?limit=" + limit + "&convert=" + convert + "&sort=" + sort + "&sort_dir=" + sort_dir,
        coinConfigProperties.getApiKeyName() + ";" + coinConfigProperties.getApiKeyValue()));
    JSONArray json = JSONArray.fromObject(object.get("data"));
    Map<String, List<AssetGeneral>> assetGeneralMap = new LinkedHashMap<String, List<AssetGeneral>>();
    List<AssetGeneral> list = new ArrayList<AssetGeneral>();
    for (int i = 0; i < json.size(); i++) {
      AssetGeneral assetGeneral = new AssetGeneral();
      JSONObject job = json.getJSONObject(i);
      String flag = job.getString("name").substring(0, 1);
      assetGeneral.setName(job.getString("name"));http://browser.360.cn/se/event/mango/mango_skin.html?src=weishi
      assetGeneral.setSymbol(job.getString("symbol"));
      assetGeneral.setTradeVolume(new BigDecimal(job.getJSONObject("quote").getJSONObject("BTC").getString("volume_24h")));
      switch (flag) {
        case "A":
          list.add(assetGeneral);
          assetGeneralMap.put("A", list);
          break;
        case "B":
          list.add(assetGeneral);
          assetGeneralMap.put("B", list);
          break;
        case "C":
          list.add(assetGeneral);
          assetGeneralMap.put("C", list);
          break;
        case "D":
          list.add(assetGeneral);
          assetGeneralMap.put("D", list);
          break;
        case "E":
          list.add(assetGeneral);
          assetGeneralMap.put("E", list);
          break;
        case "F":
          list.add(assetGeneral);
          assetGeneralMap.put("F", list);
          break;
        case "G":
          list.add(assetGeneral);
          assetGeneralMap.put("G", list);
          break;
        case "H":
          list.add(assetGeneral);
          assetGeneralMap.put("H", list);
          break;
        case "I":
          list.add(assetGeneral);
          assetGeneralMap.put("I", list);
          break;
        case "J":
          list.add(assetGeneral);
          assetGeneralMap.put("J", list);
          break;
        case "K":
          list.add(assetGeneral);
          assetGeneralMap.put("K", list);
          break;
        case "L":
          list.add(assetGeneral);
          assetGeneralMap.put("L", list);
          break;
        case "M":
          list.add(assetGeneral);
          assetGeneralMap.put("M", list);
          break;
        case "N":
          list.add(assetGeneral);
          assetGeneralMap.put("N", list);
          break;
        case "O":
          list.add(assetGeneral);
          assetGeneralMap.put("O", list);
          break;
        case "P":
          list.add(assetGeneral);
          assetGeneralMap.put("P", list);
          break;
        case "Q":
          list.add(assetGeneral);
          assetGeneralMap.put("Q", list);
          break;
        case "R":
          list.add(assetGeneral);
          assetGeneralMap.put("R", list);
          break;
        case "S":
          list.add(assetGeneral);
          assetGeneralMap.put("S", list);
          break;
        case "T":
          list.add(assetGeneral);
          assetGeneralMap.put("T", list);
          break;
        case "U":
          list.add(assetGeneral);
          assetGeneralMap.put("U", list);
          break;
        case "V":
          list.add(assetGeneral);
          assetGeneralMap.put("V", list);
          break;
        case "W":
          list.add(assetGeneral);
          assetGeneralMap.put("W", list);
          break;
        case "X":
          list.add(assetGeneral);
          assetGeneralMap.put("X", list);
          break;
        case "Y":
          list.add(assetGeneral);
          assetGeneralMap.put("Y", list);
          break;
        case "Z":
          list.add(assetGeneral);
          assetGeneralMap.put("Z", list);
          break;
        case "0":
          list.add(assetGeneral);
          assetGeneralMap.put("0", list);
          break;
        case "1":
          list.add(assetGeneral);
          assetGeneralMap.put("1", list);
          break;
        case "2":
          list.add(assetGeneral);
          assetGeneralMap.put("2", list);
          break;
        case "3":
          list.add(assetGeneral);
          assetGeneralMap.put("3", list);
          break;
        case "4":
          list.add(assetGeneral);
          assetGeneralMap.put("4", list);
          break;
        case "5":
          list.add(assetGeneral);
          assetGeneralMap.put("5", list);
          break;
        case "6":
          list.add(assetGeneral);
          assetGeneralMap.put("6", list);
          break;
        case "7":
          list.add(assetGeneral);
          assetGeneralMap.put("7", list);
          break;
        case "8":
          list.add(assetGeneral);
          assetGeneralMap.put("8", list);
          break;
        case "9":
          list.add(assetGeneral);
          assetGeneralMap.put("9", list);
          break;
      }
    }
    redisService.set(CoinConstant.CURRENCY_API + "_" + CoinConstant.ASSETS_GENERAL, assetGeneralMap);
    return true;
  }

  @Override
  public void setTopMarketTrend_Job() throws Exception {
    // TODO 不做
  }

  @Override
  public void setLegalcurrencyTrend_Job() throws Exception {
    // TODO 不做
  }

  @Override
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
    JSONObject fireTop10Json =
        JSONObject.fromObject(httpUtil.sendGet(urlBuffer.toString() + sort_dir_desc, coinConfigProperties.getApiKeyName() + ";" + coinConfigProperties.getApiKeyValue()));

    JSONObject coldTop10Json =
        JSONObject.fromObject(httpUtil.sendGet(urlBuffer.toString() + sort_dir_asc, coinConfigProperties.getApiKeyName() + ";" + coinConfigProperties.getApiKeyValue()));

    // 2.持久或保存
    List<FloatExchange> fireTop10JsonList = setExchangeRiseFall(fireTop10Json.getJSONArray("data"), convert.split(","));
    List<FloatExchange> coldTop10JsonList = setExchangeRiseFall(coldTop10Json.getJSONArray("data"), convert.split(","));
//    floatExchangeService.insertByBatch(fireTop10JsonList);
//    floatExchangeService.insertByBatch(coldTop10JsonList);
    List<FloatExchange> floatExchangeList = new ArrayList<FloatExchange>();
    floatExchangeList.addAll(fireTop10JsonList);
    floatExchangeList.addAll(coldTop10JsonList);

    // 3.更新缓存
    redisService.set(CoinConstant.CURRENCY_API + "_" + CoinConstant.LEGALCURRENCY_TREND, floatExchangeList);

    return true;
  }

  @SuppressWarnings("unchecked")
  @Override
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
  @Override
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

  @Override
  public boolean setBtcMonitorLine_OHLCV_Job() throws Exception {
    // 1.读取Line（汇率）API信息
    String line_url = coinConfigProperties.getBtcMonitorLineOHLCV().getLine().getUrl();
    String line_symbol = coinConfigProperties.getBtcMonitorLineOHLCV().getLine().getSymbol();
    String line_convert = coinConfigProperties.getBtcMonitorLineOHLCV().getLine().getConvert();
    int line_amount = coinConfigProperties.getBtcMonitorLineOHLCV().getLine().getAmount();

    JSONObject line_quote =
        JSONObject.fromObject(httpUtil.sendGet(line_url + "?symbol=" + line_symbol + "&convert=" + line_convert + "&amount=" + line_amount)).getJSONObject("data");

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

    JSONObject ohlcv_quote = JSONObject.fromObject(httpUtil.sendGet(ohlcv_url + "?symbol=" + ohlcv_symbol + "&convert=" + ohlcv_convert)).getJSONObject("data")
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
//
//    long line_count = getCountForJdbc("select count(1) from cc_btc_monitor_line_history").longValue();
//    long ohlcv_count = getCountForJdbc("select count(1) from cc_btc_monitor_ohlcv_history").longValue();
//
//    if (cacheObj == null) { // 缓存为空
//      btcMonitor = new BtcMonitor();
//      String sql;
//      List<BtcMonitorLineHistory> lineHisList;
//      List<BtcMonitorOhlcvHistory> ohlcvHisList;
//
//      // 获取line数据
//      if (line_count > BTC_MONITOR_COLLECTION_FREQUENCY) {// 每5分钟一个点位，24小时等于12*24个点位
//        sql =
//            "select id, base_symbol baseSymbol, price_jpy priceJpy, price_usd priceUsd, price_eur priceEur, price_cny priceCny, last_updated lastUpdated from cc_btc_monitor_line_history limit "
//                + (line_count - BTC_MONITOR_COLLECTION_FREQUENCY) + ", " + BTC_MONITOR_COLLECTION_FREQUENCY;
//        lineHisList = findObjForJdbc(sql, 1, BTC_MONITOR_COLLECTION_FREQUENCY, BtcMonitorLineHistory.class);
//      } else {
//        lineHisList = this.getList(BtcMonitorLineHistory.class);
//      }
//      btcMonitor.getLine_his_queue().addAll(lineHisList);
//
//      // 获取ohlcv数据
//      if (ohlcv_count > BTC_MONITOR_COLLECTION_FREQUENCY) { // 每5分钟一个点位，24小时等于12*24个点位
//        sql = "select id, open, high, low, close, last_updated lastUpdated from cc_btc_monitor_ohlcv_history h limit " + (ohlcv_count - BTC_MONITOR_COLLECTION_FREQUENCY) + ", "
//            + BTC_MONITOR_COLLECTION_FREQUENCY;
//        ohlcvHisList = findObjForJdbc(sql, 1, BTC_MONITOR_COLLECTION_FREQUENCY, BtcMonitorOhlcvHistory.class);
//      } else {
//        ohlcvHisList = this.getList(BtcMonitorOhlcvHistory.class);
//      }
//      btcMonitor.getOhlcv_his_queue().addAll(ohlcvHisList);
//    } else { // 已经存在缓存
//      btcMonitor = (BtcMonitor) cacheObj;
//      // 先poll，再add
//      if (btcMonitor.getLine_his_queue().size() > BTC_MONITOR_COLLECTION_FREQUENCY) {
//        btcMonitor.getLine_his_queue().poll();
//      }
//      btcMonitor.getLine_his_queue().add(lineHistoryItem);
//
//      if (btcMonitor.getOhlcv_his_queue().size() > BTC_MONITOR_COLLECTION_FREQUENCY) {
//        btcMonitor.getOhlcv_his_queue().poll();
//      }
//      btcMonitor.getOhlcv_his_queue().add(ohlcvHistoryItem);
//    }

//    redisService.set(CoinConstant.CURRENCY_API + "_" + CoinConstant.BTC_MONITOR_LINE_OHLCV, btcMonitor);
    return true;
  }

  @Override
  public boolean setBtcMonitorRate_Job() throws Exception {
    // 1.查询最新的BTC-法币的价格API数据
    String url = coinConfigProperties.getBtcMonitorRate().getUrl();
    String symbol = coinConfigProperties.getBtcMonitorRate().getSymbol();
    String convert = coinConfigProperties.getBtcMonitorRate().getConvert();
    JSONObject quote =
        JSONObject.fromObject(httpUtil.sendGet(url.toString() + "&symbol=" + symbol + "&convert=" + convert)).getJSONObject("data").getJSONObject(symbol).getJSONObject("quote");

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
    redisService.remove(CoinConstant.CURRENCY_API + "_" + CoinConstant.BTC_MONITOR_RATE);
    redisService.set(CoinConstant.CURRENCY_API + "_" + CoinConstant.BTC_MONITOR_RATE, rate);
    return true;
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

}
