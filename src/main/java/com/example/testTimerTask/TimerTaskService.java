package com.example.testTimerTask;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@Service
public class TimerTaskService {
    @PostConstruct
    public void init(){
        System.out.println("这是@PostConstruct注解的方法,项目启动时候就会加载");
    }
    /**
     * 利用TimerTask实现的定时任务
     */
    public void testTimerTask(){

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("这是timertask定时任务");
            }
        },3000);
    }
    /**
     * 利用ScheduledExecutorService实现的定时任务
     */
    public void testScheduleAtFixedRate(){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(() ->{
            System.out.println("testScheduleAtFixedRate...");
        },0,3000, TimeUnit.MILLISECONDS);
    }



}
