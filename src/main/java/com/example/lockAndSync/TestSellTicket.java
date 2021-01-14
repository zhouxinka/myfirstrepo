package com.example.lockAndSync;

public class TestSellTicket {
    public static void main(String[] args) {
        SellTicket_Sync st=new SellTicket_Sync();
        //SellTicket_Lock st = new SellTicket_Lock();
        for(int i=1;i<=3;i++){//模拟三个窗口一起卖这100张票
            new Thread(st,"窗口"+i).start();
        }
    }
}
