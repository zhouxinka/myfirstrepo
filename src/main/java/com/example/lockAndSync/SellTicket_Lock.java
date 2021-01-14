package com.example.lockAndSync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SellTicket_Lock implements Runnable{
    int ticketNum=100;
    Lock lock=new ReentrantLock();//可重入锁
    @Override
    public void run() {
        while(ticketNum>0){
            lock.lock();//开启锁
                try{
                    if(ticketNum>0){
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName()+"卖了"+ticketNum--+"张票");
                    }
                }finally {
                    lock.unlock();//释放锁
                }



        }
    }
}
