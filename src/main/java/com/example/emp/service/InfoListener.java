package com.example.emp.service;

import com.example.emp.event.EmpEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class InfoListener implements ApplicationListener<EmpEvent> {
    @Override
    public void onApplicationEvent(EmpEvent empEvent) {
        //当监听到empEvent事件发布的时候执行此操作
        System.out.println("执行了InfoListener的onApplicationEvent代码......");
        //empEvent.getSource();
    }
}
