package com.chain.wp.coin.config;

public abstract class CoinConstant {
    /**
     * 数字加密货币Aip缓存[临时缓存]
     */
    public final static String CURRENCY_API = "coin";

    /** assets交易费率变化的信息缓存key */
    public final static String ASSETS_QUOTATION_RATE = "assets_quotation_rate";
    /** assets历史趋势图表信息缓存key */
    public final static String ASSETS_QUOTATION_RATE_HISTORY_24H = "assets_quotation_rate_history_24h";
    /** assets概要缓存key */
    public final static String ASSETS_GENERAL = "assets_general";
    /** 法币趋势缓存key */
    public final static String LEGALCURRENCY_TREND = "legalcurrency_trend";
    /** exchange的top浮动缓存 */
    public final static String TOP_FLOATING_EXCHANGES_ = "top_floating_exchanges";
    /** 以交易所为单位，列出所有的市场信息 */
    public final static String EXCHANGE_MARK_INFO = "exchange_mark_info";
    /** 以市场为单位，列出所有的交易所信息 */
    public final static String MARK_INFO_EXCHANGE = "mark_info_exchange";
    /** BTC监视器 line & olhcv 信息 */
    public final static String BTC_MONITOR_LINE_OHLCV = "btc_monitor_line_olhcv";
    /** BTC监视器 rate 信息 */
    public final static String BTC_MONITOR_RATE = "btc_monitor_rate";
    /** 24小时虚拟货币市场交易总资产信息 */
    public final static String ONE_DAY_CAP = "one_day_cap";
}
