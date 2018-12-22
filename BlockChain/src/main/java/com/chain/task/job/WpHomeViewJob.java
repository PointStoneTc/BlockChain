package com.chain.task.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chain.wp.restapi.service.HomeService;

public class WpHomeViewJob implements BaseJob {
    private final static Logger logger = LoggerFactory.getLogger(WpHomeViewJob.class);

    @Autowired
    private HomeService homeService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            homeService.home();
            logger.info("组装wordpress前台首页成功!");

            homeService.financeDepart();
            logger.info("组装financeDepart页成功!");

            homeService.rightPopular();
            logger.info("组装rightPopular页成功!");
        } catch (Exception e) {
            logger.error("组装前台首页出错,清联系管理员!", e);
        }
    }
}
