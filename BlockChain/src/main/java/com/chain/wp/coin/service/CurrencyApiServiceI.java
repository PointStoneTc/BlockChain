package com.chain.wp.coin.service;

import java.util.List;
import java.util.Map;

import com.chain.wp.coin.entity.Asset;
import com.chain.wp.coin.entity.BtcMonitorRateHistory;
import com.chain.wp.coin.page.AssetQuotation;
import com.chain.wp.coin.page.BtcMonitor;
import com.chain.wp.coin.page.ExchangeMarkInfo;
import com.chain.wp.coin.page.ExchangeRiseFall;
import com.chain.wp.coin.page.MarkInofExchange;
import com.chain.wp.coin.page.OneDayMarketCapInfo;

public interface CurrencyApiServiceI {
    /**
     * 所有交易所信息
     * 
     * @return
     * @throws Exception
     */
    boolean exchanges(String key) throws Exception;

    /**
     * 所有资产信息
     * 
     * @return
     * @throws Exception
     */
    boolean assets() throws Exception;

    /**
     * 所有标志信息
     * 
     * @return
     * @throws Exception
     */
    boolean symbols() throws Exception;

    /**
     * 
     * @return
     * @throws Exception
     */
    List<AssetQuotation> assetTrend() throws Exception;

    /**
     * BTC监视器固定法币汇率和OHLCV的历史信息
     * 
     * @return
     */
    BtcMonitor btcMonitorLine_OHLCV() throws Exception;

    /**
     * BTC监控器的实时费率
     * 
     * @return
     * @throws Exception
     */
    BtcMonitorRateHistory btcMonitorRate() throws Exception;

    /**
     * 24小时交易总量超过200BTC的交易市场
     * 
     * @return
     * @throws Exception
     */
    void topMarketTrend() throws Exception;

    /**
     * 24小时交法币交易总量
     * 
     * @return
     * @throws Exception
     */
    void legalcurrencyTrend() throws Exception;

    /**
     * 浮动大的前10位交易所
     * 
     * @return
     * @throws Exception
     */
    List<ExchangeRiseFall> topFloatingExchange() throws Exception;

    /**
     * 资产概要信息
     * 
     * @param name
     * @return
     * @throws Exception
     */
    Map<String, List<Asset>> assetsGeneral(String name) throws Exception;

    /**
     * 以交易所为单位所有的市场信息
     * 
     * @param id 交易所id
     * @return
     * @throws Exception
     */
    ExchangeMarkInfo exchangeMarketInfo(int id) throws Exception;

    /**
     * 以市场为单位所有的交易所信息
     * 
     * @param base
     * @param quot
     * @param start
     * @return
     * @throws Exception
     */
    MarkInofExchange marketInfoExchange(int base, int quot, int start) throws Exception;

    /**
     * 24小时所有市场交易总量信息
     * 
     * @return
     * @throws Exception
     */
    OneDayMarketCapInfo onedayCap() throws Exception;
}
