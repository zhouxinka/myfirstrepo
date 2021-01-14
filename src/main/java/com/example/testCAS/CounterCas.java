package com.example.testCAS;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class CounterCas implements Counter{
    volatile  int i;
    static Unsafe unsafe;
    //偏移量
    static long offset;
    static {
        try {
            //初始化unsafe
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe)theUnsafe.get(null);
            //将属性给offset
            Field field=CounterCas.class.getDeclaredField("i");
            offset=unsafe.objectFieldOffset(field);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int increase() {
        //修改内存数据 CAS
        for(;;){//相当于while(true) 自旋
            int current=i;
            int result=current+1;
            //提供对象和属性，帮你定位到内存地址,在内存地址中操作这个i(将result值给i,)
            if(unsafe.compareAndSwapInt(this,offset,current,result)){
                return result;
            }
        }

    }

    @Override
    public int decrease() {
        //修改内存数据 CAS
        for(;;){//相当于while(true) 自旋
            int current=i;
            int result=current-1;
            //提供对象和属性，帮你定位到内存地址,在内存地址中操作这个i
            if(unsafe.compareAndSwapInt(this,offset,current,result)){
                return result;
            }
        }
    }

    @Override
    public int get() {
        return i;
    }
}
