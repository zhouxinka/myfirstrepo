package com.example.testSemaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class TestSemaphore {
    public static void main(String[] args) {
        TestSemaphore testSemaphore=new TestSemaphore();
        Semaphore semaphore=new Semaphore(5);//信号量：表示最多只能同时5个线程并发访问
        for(int i=0;i<10;i++){
            String name="vip-"+i;
            new Thread(()->{
                try {
                    semaphore.acquire();//拿到令牌
                    testSemaphore.service(name);//拿到令牌才能执行方法
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();//释放令牌
                }

            }).start();
        }
    }

    public void service(String name){

        try {
            System.out.println("欢迎贵宾"+name+"来了");
            Thread.sleep(new Random().nextInt(3000));
            System.out.println("贵宾"+name+"走了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
