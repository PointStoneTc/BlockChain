package com.chain.task.servcie;

import java.util.List;

import com.chain.task.entity.JobAndTrigger;

public interface JobAndTriggerServiceI {
    /**
     * 
     * @return
     */
  public List<JobAndTrigger> getPageJob();

    /**
     * @Title: getPageJobmod @Description: TODO(查询定时任务) @param @return 参数 @return JobAndTriggerDto
     *         返回类型 @throws
     */
  public JobAndTrigger getPageJobmod();

    /**
     * @Title: addJob @Description: TODO(添加任务) @param @param jobClassName 任务路径名称 @param @param
     *         jobGroupName 任务分组 @param @param cronExpression cron时间规则 @param @throws Exception
     *         参数 @return void 返回类型 @throws
     */
  public void addJob(String jobClassName, String jobGroupName, String cronExpression) throws Exception;

    /**
     * @Title: updateJob @Description: TODO(更新定时任务) @param @param jobClassName 任务路径名称 @param @param
     *         jobGroupName 任务分组 @param @param cronExpression cron时间规则 @param @throws Exception
     *         参数 @return void 返回类型 @throws
     */
  public void updateJob(String jobClassName, String jobGroupName, String cronExpression) throws Exception;

    /**
     * @Title: deleteJob @Description: TODO(删除定时任务) @param @param jobClassName 任务路径名称 @param @param
     *         jobGroupName 任务分组 @param @throws Exception 参数 @return void 返回类型 @throws
     */
  public void deleteJob(String jobClassName, String jobGroupName) throws Exception;

    /**
     * @Title: pauseJob @Description: TODO(暂停定时任务) @param @param jobClassName 任务路径名称 @param @param
     *         jobGroupName 任务分组 @param @throws Exception 参数 @return void 返回类型 @throws
     */
  public void pauseJob(String jobClassName, String jobGroupName) throws Exception;

    /**
     * @Title: resumejob @Description: TODO(恢复任务) @param @param jobClassName 任务路径名称 @param @param
     *         jobGroupName 任务分组 @param @throws Exception 参数 @return void 返回类型 @throws
     */
  public void resumejob(String jobClassName, String jobGroupName) throws Exception;
}
