package com.example.testThreadPool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 自己定义的线程池
 */
public class FixedSizeThreadPool {
    private BlockingQueue<Runnable> blockingQueue;
    private List<Thread> workers;

    public static class Worker extends Thread{//这是具体工作的每个线程
        private FixedSizeThreadPool pool;
        public Worker(FixedSizeThreadPool pool) {
            this.pool = pool;
        }
        public void run(){
            while(true){//重复使用
                Runnable task=null;
                try {
                    task=this.pool.blockingQueue.take();//使用take()函数，如果队列中没有数据，则线程wait释放CPU，而poll()则不会等待，直接返回null；同样，空间耗尽时offer()函数不会等待，直接返回false，而put()则会wait，因此如果你使用while(true)来获得队列元素，千万别用poll()，CPU会100%的。没任务来他就阻塞,等到调用submit()加入任务就不阻塞了继续往下执行
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(task!=null){
                    task.run();
                    System.out.println("线程"+Thread.currentThread().getName()+"执行完毕");
                }
            }
        }
    }
    public FixedSizeThreadPool(int poolSize, int taskSize) {
        if(poolSize<=0 || taskSize<=0){
            throw new IllegalArgumentException("非法参数");
        }
        this.blockingQueue=new LinkedBlockingQueue<>(taskSize);
        this.workers= Collections.synchronizedList(new ArrayList<>());
        for(int i=0;i<poolSize;i++){
            Worker worker=new Worker(this);//new了几遍线程池中就有几个线程
            workers.add(worker);
            worker.start();

        }
    }
    public boolean submit(Runnable task){
        return this.blockingQueue.offer(task);//offer(),加入任务，队列满时，返回false
    }
}
