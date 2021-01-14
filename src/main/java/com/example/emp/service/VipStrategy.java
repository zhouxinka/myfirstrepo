package com.example.emp.service;

import org.springframework.stereotype.Service;

@Service
public class VipStrategy implements CalStrategy{
    @Override
    public String userType() {
        return "vip";
    }

    @Override
    public double discount(double fee) {
        return fee*0.8;
    }
}
