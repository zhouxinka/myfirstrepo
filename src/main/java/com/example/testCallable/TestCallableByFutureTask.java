package com.example.testCallable;

import java.util.concurrent.*;

public class TestCallableByFutureTask {
    public static void main(String[] args) {
        Callable call1=() -> {
            return "  call1  ";
        };

        Callable call2 = new Callable() {
            @Override
            public String call() throws Exception {
                return "  call2  ";
            }
        };

        FutureTask<String> ft1=new FutureTask<>(call1);
        FutureTask<String> ft2=new FutureTask<>(call2);
        new Thread(ft1).start();
        new Thread(ft2).start();
        try {
            System.out.println(ft1.get()+"AND"+ft2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //创建线程池，用线程池的submit()执行线程，效果等同于上面
        ExecutorService executorService= Executors.newCachedThreadPool();
        Future<String> submit = executorService.submit(call1);
        try {
            System.out.println("结果是："+submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
