package com.example.emp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SaleService {
    @Autowired
    VipStrategy vipStrategy;
    @Autowired
    NormalStrategy normalStrategy;
    //策略模式
    public double sale(String userType,double fee){
        if("vip".equals(userType)){//策略模式
            return vipStrategy.discount(fee);
        }else if("normal".equals(userType)){
            return normalStrategy.discount(fee);
        }else{
            return fee;
        }
    }
    //策略模式+组合模式
    Map<String,CalStrategy> map=new HashMap<>();
    //这个构造器是Spring默认的，将这个接口的实现类装进这个集合
    public SaleService(List<CalStrategy> calStrategies){
        //将list里面的实现类放入map
        for (CalStrategy calStrategy : calStrategies) {
            map.put(calStrategy.userType(),calStrategy);
        }
    }

    public double newSale(String userType,double fee){
        CalStrategy calStrategy=map.get(userType);
        double d=calStrategy.discount(fee);
        return d;
    }

}
