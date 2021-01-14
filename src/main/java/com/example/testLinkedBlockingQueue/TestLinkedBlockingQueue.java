package com.example.testLinkedBlockingQueue;

import com.example.info.pojo.Info;

import java.util.concurrent.LinkedBlockingQueue;

public class TestLinkedBlockingQueue {
    public static void main(String[] args) {

        try {
            LinkedBlockingQueue linkedBlockingQueue=new LinkedBlockingQueue();
            Info info=new Info();
            info.setId(1l);
            linkedBlockingQueue.put(info);
            Info info1=(Info)linkedBlockingQueue.poll();
            System.out.println(info1.getId());
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("helloWorld...");
        }
    }

}
