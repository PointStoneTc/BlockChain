package com.chain.wp.coin.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chain.wp.coin.config.CoinConfigProperties;
import com.chain.wp.coin.page.AssetGeneral;
import com.chain.wp.coin.page.AssetQuotation;
import com.chain.wp.coin.page.BtcMonitor;
import com.chain.wp.coin.page.ExchangeMarkInfo;
import com.chain.wp.coin.page.ExchangeRiseFall;
import com.chain.wp.coin.page.MarkInofExchange;
import com.chain.wp.coin.service.CurrencyApiServiceI;
import com.chain.wp.coin.service.CurrencyJobServiceI;

/**
 * @Title: Controller
 * @Description: 数字加密货币提供服务
 * @author 赵琦
 * @date 2018-09-03 18:00:00
 * @version V1.0
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/coinapi")
@RestController
public class CurrencyApiController {
    private static final Logger logger = LoggerFactory.getLogger(CurrencyApiController.class);

    /*--------------------------------------------
    |             Variable                       |
    ============================================*/

    /*--------------------------------------------
    |             Injection                      |
    ============================================*/
    @Autowired
    private CurrencyApiServiceI currencyApiService;

    @Autowired
    private CurrencyJobServiceI currencyJobService;

    @Autowired
    private CoinConfigProperties coinConfigProperties;

    @RequestMapping(value = "/cp", method = RequestMethod.GET)
    public CoinConfigProperties coinp(HttpServletResponse res) {
        return coinConfigProperties;
    }

    /*--------------------------------------------
    |             method                       |
    ============================================*/
    /**
     * 获取assetTrend信息
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/assetTrend", method = RequestMethod.GET)
    public List<AssetQuotation> assetTrend() throws Exception {
        List<AssetQuotation> list = currencyApiService.assetTrend();
        return list;
    }

    /**
     * 获取BTC Monitor信息
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/btcMonitorLineOHLCV", method = RequestMethod.GET)
    public BtcMonitor btcMonitorLineOHLCV() throws Exception {
        BtcMonitor btcMonitor = currencyApiService.btcMonitorLine_OHLCV();
        return btcMonitor;
    }

    /**
     * 获取BTC Monitor的实时费率
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/btcMonitorRate", method = RequestMethod.GET)
    public BtcMonitor btcMonitorRate() throws Exception {
        BtcMonitor btcMonitor = currencyApiService.btcMonitorLine_OHLCV();
        return btcMonitor;
    }

    /**
     * 24小时交易总量超过200BTC的交易市场
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/topTrendingMarket", method = RequestMethod.GET)
    public String topMarketTrend() throws Exception {
        return "true";
    }

    /**
     * 24小时交法币交易总量
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/legalCurrencyTrend", method = RequestMethod.GET)
    public String legalCurrencyTrend() throws Exception {
        return "true";
    }

    /**
     * 浮动大的前10位交易所
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/topFloatingExchange", method = RequestMethod.GET)
    public List<ExchangeRiseFall> topFloatingExchange() throws Exception {
        List<ExchangeRiseFall> list = currencyApiService.topFloatingExchange();
        return list;
    }

    /**
     * 以交易所为单位所有的市场信息
     * 
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/exchangeMarketInfo", method = RequestMethod.GET)
    public ExchangeMarkInfo exchangeMarketInfo(@RequestParam(value = "id", required = true) int id) throws Exception {
        ExchangeMarkInfo exchangeMarkInfo = currencyApiService.exchangeMarketInfo(id);
        return exchangeMarkInfo;
    }

    /**
     * 以市场为单位所有的交易所信息
     * 
     * @param base
     * @param quot
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/marketInfoExchange", method = RequestMethod.GET)
    public MarkInofExchange marketInfoExchange(@RequestParam(value = "base", required = true) int base, @RequestParam(value = "quot", required = true) int quot,
            @RequestParam(value = "page", required = true) int page) {
        MarkInofExchange markInfoExchange = null;
        try {
            markInfoExchange = currencyApiService.marketInfoExchange(base, quot, page);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return markInfoExchange;
    }

    /**
     * 获取asset的概要信息
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/assetsGeneral", method = RequestMethod.GET)
    public List<AssetGeneral> assetsGeneral() throws Exception {
        List<AssetGeneral> list = currencyApiService.assetsGeneral();
        return list;
    }

    @RequestMapping(value = "/tx", method = RequestMethod.GET)
    public boolean tx(@RequestParam(value = "base", required = true) int base, HttpServletResponse res) {
        try {
            switch (base) {
            case 1:
                // 1.测试更新行情固定的资产的交易费率变化信息缓存
                currencyJobService.setAssetsQuotationRateHistory24h_Job();
                currencyJobService.setAssetsQuotationRateCurrent_Job();
                break;
            case 2:
                // 2.测试更新资产概要信息缓存
                currencyJobService.setAssetsGeneral_Job();
                break;
            case 3:
                // 3.测试更新浮动大的前10位交易所信息缓存
                currencyJobService.setTopFloatingExchange_Job();
                break;
            default:
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}