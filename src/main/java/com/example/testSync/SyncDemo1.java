package com.example.testSync;

public class SyncDemo1 {
        public void test1(){
            synchronized (this){//针对于当前对象的锁，同一个对象才锁才会有效，不是同一个对象就没有用
                try {
                    System.out.println(Thread.currentThread()+"开始执行...");
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread()+"执行完毕...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    public synchronized  void test2(){
        synchronized (this.getClass()){//针对于当前类的锁，不管创建几个对象，只要是同一个类的锁才会有效，不是同一个类就没有用
            try {
                System.out.println(Thread.currentThread()+"开始执行...");
                Thread.sleep(3000);
                System.out.println(Thread.currentThread()+"执行完毕...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized  void test3(){//锁仅仅加在方法上也是不行的，锁不住
            try {
                System.out.println(Thread.currentThread()+"开始执行...");
                Thread.sleep(3000);
                System.out.println(Thread.currentThread()+"执行完毕...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    public synchronized  static void test4(){//锁加在静态方法上是可以，因为静态方法运行是不需要实例化对象的
        try {
            System.out.println(Thread.currentThread()+"开始执行...");
            Thread.sleep(3000);
            System.out.println(Thread.currentThread()+"执行完毕...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
        /*new Thread(() -> {
            new SyncDemo1().test1();//这种情况线程不会有序进行即交替执行的，因为锁没有锁住同一个对象
        }).start();
        Thread.sleep(1000);//等待一秒钟让前面的线程启动起来
        new Thread(() -> {
            new SyncDemo1().test1();
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                new SyncDemo1().test1();
            }
        }).start();*/

        /*new Thread(() -> {
            new SyncDemo1().test2();//这种情况线程会有序进行即一个执行完毕才会执行下一个，因为锁是基于一个类的
        }).start();
        Thread.sleep(1000);//等待一秒钟让前面的线程启动起来
        new Thread(() -> {
            new SyncDemo1().test2();
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                new SyncDemo1().test2();
            }
        }).start();*/

        /*new Thread(() -> {
            new SyncDemo1().test3();////这种情况线程不会有序进行即交替执行的，因为锁没有锁住同一个对象（跟第一种同理）
        }).start();
        Thread.sleep(1000);//等待一秒钟让前面的线程启动起来
        new Thread(() -> {
            new SyncDemo1().test3();
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                new SyncDemo1().test3();
            }
        }).start();*/

        new Thread(() -> {
            new SyncDemo1().test4();//这种情况线程有序执行
        }).start();
        Thread.sleep(1000);//等待一秒钟让前面的线程启动起来
        new Thread(() -> {
            new SyncDemo1().test4();
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                new SyncDemo1().test4();
            }
        }).start();


        String string="入库备注：1223签收可做";
        System.out.println(string.substring(string.indexOf("签收可做")-4,string.indexOf("签收可做")-2));
        System.out.println(string.substring(string.indexOf("签收可做")-2,string.indexOf("签收可做")));
        System.out.println("0301".startsWith("0")?"ss":"aa");
        System.out.println("".length());
        System.out.println("".indexOf("签收可做"));

        System.out.println("hello".substring(-4,2));

    }
}
