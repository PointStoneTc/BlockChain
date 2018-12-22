package com.chain.task.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chain.task.entity.JobAndTrigger;
import com.chain.task.servcie.JobAndTriggerService;

@Controller
@RequestMapping(value = "/job")
public class JobController {
    private final static Logger logger = LoggerFactory.getLogger(JobController.class);
    @Autowired
    private JobAndTriggerService jobAndTriggerService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        return "job";
    }

    @RequestMapping(value = "/datagrid", method = RequestMethod.POST)
    @ResponseBody
    public List<JobAndTrigger> datagrid(Model model) {
        return jobAndTriggerService.getPageJob();
    }

    /**
     * @Title: addJob
     * @Description: TODO(添加Job)
     * @param jobClassName 类名
     * @param jobGroupName 组名
     * @param cronExpression 表达式，如：0/5 * * * * ? (每隔5秒)
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addJob(@RequestParam(value = "jobClassName") String jobClassName, @RequestParam(value = "jobGroupName") String jobGroupName,
            @RequestParam(value = "cronExpression") String cronExpression, HttpServletResponse response) {
        try {
            jobAndTriggerService.addJob(jobClassName, jobGroupName, cronExpression);
            return "sucess";
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
    }

    /**
     * @Title: pauseJob
     * @Description: TODO(暂停Job)
     * @param jobClassName 类名
     * @param jobGroupName 组名
     */
    @RequestMapping(value = "/pause", method = RequestMethod.POST)
    @ResponseBody
    public String pauseJob(@RequestParam(value = "jobClassName") String jobClassName, @RequestParam(value = "jobGroupName") String jobGroupName, HttpServletResponse response) {
        try {
            jobAndTriggerService.pauseJob(jobClassName, jobGroupName);
            return "sucess";
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
    }

    /**
     * @Title: resumeJob
     * @Description: TODO(恢复Job)
     * @param jobClassName 类名
     * @param jobGroupName 组名
     */
    @RequestMapping(value = "/resume", method = RequestMethod.POST)
    @ResponseBody
    public String resumeJob(@RequestParam(value = "jobClassName") String jobClassName, @RequestParam(value = "jobGroupName") String jobGroupName, HttpServletResponse response) {
        try {
            jobAndTriggerService.resumejob(jobClassName, jobGroupName);
            return "sucess";
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
    }

    /**
     * @Title: rescheduleJob
     * @Description: TODO(重新设置Job)
     * @param jobClassName 类名
     * @param jobGroupName 组名
     * @param cronExpression 表达式
     */
    @RequestMapping(value = "/reschedule", method = RequestMethod.POST)
    @ResponseBody
    public String rescheduleJob(@RequestParam(value = "jobClassName") String jobClassName, @RequestParam(value = "jobGroupName") String jobGroupName,
            @RequestParam(value = "cronExpression") String cronExpression, HttpServletResponse response) {
        try {
            jobAndTriggerService.updateJob(jobClassName, jobGroupName, cronExpression);
            return "sucess";
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
    }

    /**
     * @Title: deleteJob
     * @Description: TODO(删除Job)
     * @param jobClassName 类名
     * @param jobGroupName 组名
     * @param cronExpression 表达式
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public String deleteJob(@RequestParam(value = "jobClassName") String jobClassName, @RequestParam(value = "jobGroupName") String jobGroupName, HttpServletResponse response) {
        try {
            jobAndTriggerService.deleteJob(jobClassName, jobGroupName);
            return "sucess";
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
    }
}
