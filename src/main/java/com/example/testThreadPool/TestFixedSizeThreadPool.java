package com.example.testThreadPool;

public class TestFixedSizeThreadPool {
    public static void main(String[] args) {
        FixedSizeThreadPool fixedSizeThreadPool=new FixedSizeThreadPool(3,6);
        for(int i=0;i<10;i++){
            fixedSizeThreadPool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("往blockingQueue中加入任务");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
