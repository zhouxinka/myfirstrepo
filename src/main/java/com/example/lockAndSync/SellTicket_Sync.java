package com.example.lockAndSync;

public class SellTicket_Sync implements Runnable{
    int ticketNum=100;
    @Override
    public void run() {
        while(ticketNum>0){
            synchronized (this){
                if(ticketNum>0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"卖了"+ticketNum--+"张票");
                }
            }

        }
    }
}
