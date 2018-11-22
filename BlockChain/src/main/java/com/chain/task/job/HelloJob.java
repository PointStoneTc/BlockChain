package com.chain.task.job;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloJob implements BaseJob {
    public HelloJob() {}

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.err.println("Hello Job执行时间: " + new Date());
    }
}
