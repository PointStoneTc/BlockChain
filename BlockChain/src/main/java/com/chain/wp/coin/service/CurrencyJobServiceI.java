package com.chain.wp.coin.service;

public interface CurrencyJobServiceI {
    /**
     * 更新行情固定的资产的交易费率变化信息缓存(计划任务调用)
     * 
     * @return
     * @throws Exception
     */
    boolean setAssetsQuotationRateCurrent_Job() throws Exception;

    /**
     * 更新行情固定的资产的交易费率变化24h历史信息缓存(计划任务调用)
     * 
     * @return
     * @throws Exception
     */
    boolean setAssetsQuotationRateHistory24h_Job() throws Exception;

    /**
     * 更新资产概要信息缓存(计划任务调用)
     * 
     * @return
     * @throws Exception
     */
    boolean setAssetsGeneral_Job() throws Exception;

    /**
     * 更新交易所概要信息缓存(计划任务调用)
     * 
     * @return
     * @throws Exception
     */
    boolean setExchangesGeneral_Job() throws Exception;

    /**
     * 更新24小时交易总量超过200BTC的交易市场信息缓存(计划任务调用)
     * 
     * @return
     * @throws Exception
     */
    void setTopMarketTrend_Job() throws Exception;

    /**
     * 更新24小时交法币交易总量信息缓存(计划任务调用)
     * 
     * @return
     * @throws Exception
     */
    void setLegalcurrencyTrend_Job() throws Exception;

    /**
     * 更新浮动大的前10位交易所信息缓存(计划任务调用)
     * 
     * @return
     * @throws Exception
     */
    boolean setTopFloatingExchange_Job() throws Exception;

    /**
     * 更新以交易所为单位所有的市场信息
     * 
     * @return
     * @throws Exception
     */
    boolean setExchangeMarkInfo_Job() throws Exception;

    /**
     * 更新以市场为单位所有的交易所信息
     * 
     * @return
     * @throws Exception
     */
    boolean setMarkInfoExchange_Job() throws Exception;

    /**
     * 更新BTC监视器固定法币汇率和OHLCV的历史信息(计划任务调用)
     * 
     * @return
     * @throws Exception
     */
    boolean setBtcMonitorLine_OHLCV_Job() throws Exception;

    /**
     * 更新BTC监视器的费率信息(计划任务调用)
     * 
     * @return
     * @throws Exception
     */
    boolean setBtcMonitorRate_Job() throws Exception;

    /**
     * 更新24小时市场交易概况(计划任务调用)
     * 
     * @return
     * @throws Exception
     */
    boolean setOnedayCap_Job() throws Exception;
}
