package com.example.testbioandnio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * BIO 阻塞IO
 */
public class TestBIO {
    static ExecutorService es= Executors.newCachedThreadPool();
    public static void main(String[] args) {
        try {
            ServerSocket ss=new ServerSocket(8080);
            while(true){
                Socket conn=ss.accept();//没有连接时候线程执行此处会阻塞，这就是BIO的弊端
                es.submit((Callable<String>) () -> {
                        byte[] request=new byte[1024];
                        conn.getInputStream().read(request);//没有数据时候也会阻塞
                        System.out.println("请求数据是："+new String(request));
                        return new String("请求数据是："+new String(request));
                });

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

    }
}
