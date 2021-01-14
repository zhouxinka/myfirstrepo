package com.example.testCallable;

import java.util.concurrent.*;

/**
 * 此场景用于异步执行两个操作，提高效率，节约时间
 */
public class TestCallableByMyFutureTask {
    public static void main(String[] args) {
        Callable call1=new Callable() {
            @Override
            public String call() throws Exception {
                return "call1  ";
            }
        };

        Callable call2 = new Callable() {
            @Override
            public String call() throws Exception {
                return "  call2  ";
            }
        };
        //用自己自己定义的FutureTask
        MyFutureTask<String> ft1=new MyFutureTask<>(call1);
        MyFutureTask<String> ft2=new MyFutureTask<>(call2);
        new Thread(ft1).start();
        new Thread(ft2).start();
        try {
            System.out.println(ft1.get()+"AND"+ft2.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
