package com.chain.task.dao;

import java.util.List;

import com.chain.task.entity.JobAndTrigger;

/**
 * @ClassName: JobAndTriggerMapper
 */
public interface JobAndTriggerMapper {
    List<JobAndTrigger> getJobAndTriggerDetails();

    JobAndTrigger getJobAndTrigger();
}
