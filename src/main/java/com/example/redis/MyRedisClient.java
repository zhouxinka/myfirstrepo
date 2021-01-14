package com.example.redis;


import java.io.IOException;
import java.net.Socket;

public class MyRedisClient {
    Socket redisConnection=null;
    public MyRedisClient(String host,int port){
        try {
            redisConnection=new Socket(host,port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void set(String key,String value){
        StringBuilder request=new StringBuilder();
        request.append("*3").append("\r\n");//表示这次请求有三个参数即：SET KEY VALUE

        request.append("$3").append("\r\n");//"SET" 这个参数的长度
        request.append("SET").append("\r\n");//"SET" 这个参数的值

        request.append("$").append(key.getBytes().length).append("\r\n");//参数的长度
        request.append(key).append("\r\n");//参数的值

        request.append("$").append(value.getBytes().length).append("\r\n");//参数的长度
        request.append(value).append("\r\n");//参数的值
        System.out.println("这是发给redis客户端的请求数据："+request);

        //把这次请求发送到redis服务器
        try {
            redisConnection.getOutputStream().write(request.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String get(String key){
        StringBuilder request=new StringBuilder();
        request.append("*2").append("\r\n");

        request.append("$3").append("\r\n");//参数的长度
        request.append("GET").append("\r\n");//参数的值

        request.append("$").append(key.getBytes().length).append("\r\n");//参数的长度
        request.append(key).append("\r\n");//参数的值
        //把这次请求发送到redis服务器
        try {
            redisConnection.getOutputStream().write(request.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }


        byte[] data=new byte[1024];
        try {
            redisConnection.getInputStream().read(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new String(data);
    }

    public static void main(String[] args) {
        MyRedisClient myRedisClient=new MyRedisClient("127.0.0.1",6379);
        myRedisClient.set("test","test-1");
        System.out.println("----------------------------------------");
        System.out.println(myRedisClient.get("test"));

        //Jedis j=new Jedis("127.0.0.1",6379);
    }
}
