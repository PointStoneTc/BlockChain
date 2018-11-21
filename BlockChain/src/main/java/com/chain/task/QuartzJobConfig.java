package com.chain.task;

import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzJobConfig {
    /**
     * 方法调用任务明细工厂Bean
     * 
     * @param scheduledTasks
     * @return
     */
    @Bean(name = "jobDetail")
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(ScheduledTasks scheduledTasks) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        // 例如每5s执行一次任务，但是当前任务还没有执行完，就已经过了5s了，
        // 如果此处为true，则下一个任务会执行，如果此处为false，则下一个任务会等待上一个任务执行完后，再开始执行
        jobDetail.setConcurrent(false);
        jobDetail.setName("data-web-homeView");// 设置任务的名字
        jobDetail.setGroup("data-web");// 设置任务的分组，这些属性都可以存储在数据库中，在多任务的时候使用
        // 为需要执行的实体类对应的对象
        jobDetail.setTargetObject(scheduledTasks);
        // reportCurrentByCront为需要执行的方法
        // 通过这几个配置，告诉JobDetailFactoryBean我们需要执行定时执行ScheduleTask类中的sayHello方法
        jobDetail.setTargetMethod("reportCurrentByCron");
        return jobDetail;
    }

    /**
     * 表达式触发器工厂Bean,配置定时任务的触发器，也就是什么时候触发执行定时任务.
     */
    @Bean(name = "jobTrigger")
    public CronTriggerFactoryBean myFirstExerciseJobTrigger(@Qualifier("jobDetail") MethodInvokingJobDetailFactoryBean scheduledTasksBean) {
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        tigger.setJobDetail(scheduledTasksBean.getObject());
        tigger.setCronExpression("0/10 * * * * ?"); // 什么是否触发，Spring Scheduler Cron表达式
        tigger.setName("data-web-homeViewTrigger");
        return tigger;
    }

    /**
     * Details：定义quartz调度工厂
     */
    @Bean(name = "schedulerFactory")
    public SchedulerFactoryBean schedulerFactory(@Qualifier("jobTrigger") Trigger jobTrigger) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        // 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
        bean.setOverwriteExistingJobs(true); // 延时启动
        bean.setStartupDelay(10); // 注册触发器
        bean.setTriggers(jobTrigger);
        return bean;
    }
}
