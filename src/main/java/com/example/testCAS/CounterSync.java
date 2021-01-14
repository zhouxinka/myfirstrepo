package com.example.testCAS;

public class CounterSync implements Counter{
    volatile  int i=0;

    @Override
    public synchronized int increase() {
        return i++;
    }

    @Override
    public synchronized int decrease() {
        return i--;
    }

    @Override
    public int get() {
        return i;
    }
}
