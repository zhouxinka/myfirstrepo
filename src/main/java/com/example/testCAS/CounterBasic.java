package com.example.testCAS;

public class CounterBasic implements Counter{
    volatile int i=0;
    @Override
    public int increase() {
        return i++;
    }

    @Override
    public int decrease() {
        return i--;
    }
    @Override
    public int get(){
        return i;
    }
}
