package com.chain.task.job;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class NewJob implements BaseJob {
    public NewJob() {}

    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.err.println("New Job执行时间: " + new Date());
    }
}
