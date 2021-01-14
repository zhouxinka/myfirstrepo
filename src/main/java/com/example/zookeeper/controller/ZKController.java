package com.example.zookeeper.controller;

import com.example.zookeeper.service.ZKservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zk")
public class ZKController {
    private Logger logger= LoggerFactory.getLogger(ZKController.class);
    @Autowired
    ZKservice zKservice;
    @GetMapping("/getProperties")
    public String getPropertiesByZK(@RequestParam("key") String key){
        return zKservice.getPropertiesByZK(key);
    }
}
