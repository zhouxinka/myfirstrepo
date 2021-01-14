package com.example.testbioandnio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * NIO newIO
 */
public class TestNIO {
    static ExecutorService es= Executors.newCachedThreadPool();
    public static void main(String[] args) {
        try {
            //监听端口
            ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);//显式的配置为非阻塞模式，不然默认是阻塞模式
            serverSocketChannel.bind(new InetSocketAddress(8080));
            System.out.println("NIO启动了...");
            //JDK操作系统封装的多路复用机制
            //事件机制开启
            Selector selector = Selector.open();
            while (true){
                //获取连接
                SocketChannel conn = serverSocketChannel.accept();//非阻塞，所以conn可能是null
                if(conn==null){//判断conn
                    continue;
                }
                conn.configureBlocking(false);
                //有请求数据过来时在处理
                conn.register(selector, SelectionKey.OP_READ);
                selector.select();
                //得到所有事件
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator=selectionKeys.iterator();
                while(iterator.hasNext()){//遍历事件
                    SelectionKey event = iterator.next();//得到事件
                    //判断事件是什么类型
                    if(event.isReadable()){
                        es.submit((Callable<String>) ()-> {
                            SocketChannel channel =(SocketChannel) event.channel();
                            //读取请求数据，处理请求，返回响应
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            channel.read(byteBuffer);
                            byteBuffer.flip();
                            System.out.println("请求数据是："+new String(byteBuffer.array()));
                            //请求怎么传到spring servlet,后续处理...,那么这个就需要netty框架实现
                            return "请求数据是："+new String(byteBuffer.array());
                        });
                    }
                    ;
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

    }
}
