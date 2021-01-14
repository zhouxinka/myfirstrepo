package com.example.emp.service;
//策略模式
public interface CalStrategy {
    public String userType();
    public double discount(double fee);
}
