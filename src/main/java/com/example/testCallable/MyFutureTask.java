package com.example.testCallable;

import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * 自己定义一个FutureTask,模仿jdk的FutureTask
 */
public class MyFutureTask<T> implements Runnable{
    Callable<T> callable;
    T result;
    volatile String state="NEW";
    //容器，把线程装起来
    LinkedBlockingQueue<Thread> queue=new LinkedBlockingQueue<>();
    public MyFutureTask(Callable callable) {
        this.callable = callable;
    }
    public T get(){
        if("END".equals(state)){
            return result;
        }
        while(!"END".equals(state)){
            queue.add(Thread.currentThread());
            LockSupport.park();//使其阻塞，直到run()方法执行完
        }
        return result;
    }
    @Override
    public void run() {
        try {
            result=callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            state="END";
        }
        Thread thread=queue.poll();
        while(thread!=null){
            LockSupport.unpark(thread);
            thread=queue.poll();
        }
    }
}
