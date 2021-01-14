package com.example.testCAS;

public class Test {
    public static void main(String[] args) throws Exception{
        final Counter counter=new CounterBasic();//这种方式会造成两个线程同时争取资源，最后的i值不是20000
        //final Counter counter=new CounterAtomicInteger();//这是原子性Integer，所以最后i是20000
        //final Counter counter=new CounterCas();//利用CAS（比较和交换机制）内存操作
        //final Counter counter=new CounterSync();//加锁可以，但是影响性能

        for(int i=0;i<2;i++){
            new Thread(()->{
                long begin=System.nanoTime();
                for(int j=0;j<10000;j++){
                    counter.increase();
                }
                System.out.println("运算时间是："+(System.nanoTime()-begin)+"ns");
            }).start();
        }
        Thread.sleep(6000);
        System.out.println("结果是："+counter.get());
    }
}
