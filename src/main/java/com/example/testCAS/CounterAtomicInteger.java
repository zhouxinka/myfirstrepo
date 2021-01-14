package com.example.testCAS;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterAtomicInteger implements Counter{
    volatile AtomicInteger atomicInteger=new AtomicInteger(0);
    @Override
    public int increase() {
        return atomicInteger.incrementAndGet();
    }

    @Override
    public int decrease() {
        return atomicInteger.decrementAndGet();
    }
    @Override
    public int get(){
        return atomicInteger.get();
    }
}
