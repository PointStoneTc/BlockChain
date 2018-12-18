package com.chain.task.job;

import java.io.IOException;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chain.wp.coin.service.CurrencyJobServiceI;

/**
 * 浮动大的前10位交易所信息缓存-定时任务
 * 
 * @author zhaoqi
 *
 */
public class CoinTopFloatingExchangeJob implements BaseJob {
    private static final Logger logger = LoggerFactory.getLogger(CoinTopFloatingExchangeJob.class);

    @Autowired
    private CurrencyJobServiceI currencyJobService;

    public void run() {
        try {
            currencyJobService.setTopFloatingExchange_Job();
        } catch (IOException ioe) {
            logger.error("coinMarketCap API 请求异常!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        run();
    }
}
