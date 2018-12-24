package com.chain.task.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.chain.task.entity.JobAndTrigger;

/**
 * @ClassName: JobAndTriggerMapper
 */
@Mapper
public interface JobAndTriggerMapper {
    List<JobAndTrigger> getJobAndTriggerDetails();

    JobAndTrigger getJobAndTrigger();
}
