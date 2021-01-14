package com.example.testCAS;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CounterLock implements Counter{
    volatile int i=0;

    @Override
    public int increase() {
        return 0;
    }

    @Override
    public int decrease() {
        return 0;
    }

    @Override
    public int get() {
        return 0;
    }
}
