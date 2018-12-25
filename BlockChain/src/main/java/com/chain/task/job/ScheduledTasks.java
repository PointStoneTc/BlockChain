package com.chain.task.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
// @EnableScheduling
public class ScheduledTasks {
    @Autowired
    // private HomeServiceI homeService;

    // @Scheduled(cron = "0 0/5 * * * ?")
    public void getWpHomeView() {
        System.out.println("Scheduling Tasks Examples By Cron: The time is now " + dateFormat().format(new Date()));
        try {
            // homeService.home();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private SimpleDateFormat dateFormat() {
        return new SimpleDateFormat("HH:mm:ss");
    }
}
