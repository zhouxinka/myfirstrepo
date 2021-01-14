package com.example.zookeeper.service;

import com.example.zookeeper.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZKservice {
    @Autowired
    PropertiesUtil propertiesUtil;
    public String getPropertiesByZK(String key){
        return propertiesUtil.getProperties(key);
    }

}
