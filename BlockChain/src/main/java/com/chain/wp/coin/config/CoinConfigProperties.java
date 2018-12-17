package com.chain.wp.coin.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "coin.job")
public class CoinConfigProperties {
    private String apiKeyName;
    private String apiKeyValue;
    private AssetsQuotationRateCurrent assetsQuotationRateCurrent;
    private TopFloatingExchange topFloatingExchange;
    private MarkInfoExchangeJob markInfoExchangeJob;
    private MarketInfoExchange marketInfoExchange;
    private BtcMonitorLineOHLCV btcMonitorLineOHLCV;
    private BtcMonitorRate btcMonitorRate;
    private AssetsGeneralJob assetsGeneralJob;

    public String getApiKeyName() {
        return apiKeyName;
    }

    public void setApiKeyName(String apiKeyName) {
        this.apiKeyName = apiKeyName;
    }

    public String getApiKeyValue() {
        return apiKeyValue;
    }

    public void setApiKeyValue(String apiKeyValue) {
        this.apiKeyValue = apiKeyValue;
    }

    public AssetsQuotationRateCurrent getAssetsQuotationRateCurrent() {
        return assetsQuotationRateCurrent;
    }

    public static class ThreadPool {
        private int cc;

        public int getCc() {
            return cc;
        }
    }

    public void setAssetsQuotationRateCurrent(AssetsQuotationRateCurrent assetsQuotationRateCurrent) {
        this.assetsQuotationRateCurrent = assetsQuotationRateCurrent;
    }

    public TopFloatingExchange getTopFloatingExchange() {
        return topFloatingExchange;
    }

    public void setTopFloatingExchange(TopFloatingExchange topFloatingExchange) {
        this.topFloatingExchange = topFloatingExchange;
    }

    public MarkInfoExchangeJob getMarkInfoExchangeJob() {
        return markInfoExchangeJob;
    }

    public void setMarkInfoExchangeJob(MarkInfoExchangeJob markInfoExchangeJob) {
        this.markInfoExchangeJob = markInfoExchangeJob;
    }

    public MarketInfoExchange getMarketInfoExchange() {
        return marketInfoExchange;
    }

    public void setMarketInfoExchange(MarketInfoExchange marketInfoExchange) {
        this.marketInfoExchange = marketInfoExchange;
    }

    public BtcMonitorLineOHLCV getBtcMonitorLineOHLCV() {
        return btcMonitorLineOHLCV;
    }

    public void setBtcMonitorLineOHLCV(BtcMonitorLineOHLCV btcMonitorLineOHLCV) {
        this.btcMonitorLineOHLCV = btcMonitorLineOHLCV;
    }

    public BtcMonitorRate getBtcMonitorRate() {
        return btcMonitorRate;
    }

    public void setBtcMonitorRate(BtcMonitorRate btcMonitorRate) {
        this.btcMonitorRate = btcMonitorRate;
    }

    public AssetsGeneralJob getAssetsGeneralJob() {
        return assetsGeneralJob;
    }

    public void setAssetsGeneralJob(AssetsGeneralJob assetsGeneralJob) {
        this.assetsGeneralJob = assetsGeneralJob;
    }

    /**
     * @Title 指定虚拟货币的卡片取值
     *
     */
    public static class AssetsQuotationRateCurrent {
        private String url;
        private String id_base;
        private String id_quote;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getId_base() {
            return id_base;
        }

        public void setId_base(String id_base) {
            this.id_base = id_base;
        }

        public String getId_quote() {
            return id_quote;
        }

        public void setId_quote(String id_quote) {
            this.id_quote = id_quote;
        }
    }

    public class AssetsQuotationRateCurrentParam {
        private String id_base;
        private String id_quote;

        public String getId_base() {
            return id_base;
        }

        public void setId_base(String id_base) {
            this.id_base = id_base;
        }

        public String getId_quote() {
            return id_quote;
        }

        public void setId_quote(String id_quote) {
            this.id_quote = id_quote;
        }
    }

    public static class TopFloatingExchange {
        private String url;
        private String sort;
        private String sort_dir_desc;
        private String sort_dir_asc;
        private String market_type;
        private String convert;
        private int limit;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getSort_dir_desc() {
            return sort_dir_desc;
        }

        public void setSort_dir_desc(String sort_dir_desc) {
            this.sort_dir_desc = sort_dir_desc;
        }

        public String getSort_dir_asc() {
            return sort_dir_asc;
        }

        public void setSort_dir_asc(String sort_dir_asc) {
            this.sort_dir_asc = sort_dir_asc;
        }

        public String getMarket_type() {
            return market_type;
        }

        public void setMarket_type(String market_type) {
            this.market_type = market_type;
        }

        public String getConvert() {
            return convert;
        }

        public void setConvert(String convert) {
            this.convert = convert;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }
    }

    public static class MarkInfoExchangeJob {
        private String url;
        private String convert;
        private int limit;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getConvert() {
            return convert;
        }

        public void setConvert(String convert) {
            this.convert = convert;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }
    }

    public static class MarketInfoExchange {
        private String url;
        private String convert;
        private int limit;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getConvert() {
            return convert;
        }

        public void setConvert(String convert) {
            this.convert = convert;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

    }

    /**
     * @Title 比特币监控器的参数
     *
     */
    public static class BtcMonitorLineOHLCV {
        private OHLCV_Line line;
        private OHLCV_ohlcv ohlcv;

        public OHLCV_Line getLine() {
            return line;
        }

        public void setLine(OHLCV_Line line) {
            this.line = line;
        }

        public OHLCV_ohlcv getOhlcv() {
            return ohlcv;
        }

        public void setOhlcv(OHLCV_ohlcv ohlcv) {
            this.ohlcv = ohlcv;
        }

    }

    public static class OHLCV_Line {
        private String url;
        private String symbol;
        private String convert;
        private int amount;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getConvert() {
            return convert;
        }

        public void setConvert(String convert) {
            this.convert = convert;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }
    }

    public static class OHLCV_ohlcv {
        private String url;
        private String symbol;
        private String convert;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getConvert() {
            return convert;
        }

        public void setConvert(String convert) {
            this.convert = convert;
        }

    }

    /**
     * @Title 比特币监视器中的法币汇率换算
     *
     */
    public static class BtcMonitorRate {
        private String url;
        private String symbol;
        private String convert;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getConvert() {
            return convert;
        }

        public void setConvert(String convert) {
            this.convert = convert;
        }
    }

    /**
     * @Title
     *
     */
    public static class AssetsGeneralJob {
        private String url;
        private String convert;
        private int limit;
        private String sort;
        private String sort_dir;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getConvert() {
            return convert;
        }

        public void setConvert(String convert) {
            this.convert = convert;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getSort_dir() {
            return sort_dir;
        }

        public void setSort_dir(String sort_dir) {
            this.sort_dir = sort_dir;
        }
    }

}


