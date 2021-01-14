package com.example.emp.service;

import org.springframework.stereotype.Service;

@Service
public class NormalStrategy implements CalStrategy{
    @Override
    public String userType() {
        return "普通";
    }

    @Override
    public double discount(double fee) {
        return fee*0.9;
    }
}
