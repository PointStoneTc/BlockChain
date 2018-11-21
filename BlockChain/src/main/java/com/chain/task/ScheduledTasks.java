package com.chain.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduledTasks {
    public void reportCurrentTime() {
        System.out.println("Scheduling Tasks Examples: The time is now " + dateFormat().format(new Date()));
    }

    public void reportCurrentByCron() {
        System.out.println("Scheduling Tasks Examples By Cron: The time is now " + dateFormat().format(new Date()));
    }

    private SimpleDateFormat dateFormat() {
        return new SimpleDateFormat("HH:mm:ss");
    }
}
