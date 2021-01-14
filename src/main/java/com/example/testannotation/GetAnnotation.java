package com.example.testannotation;

import com.sun.org.apache.xml.internal.dtm.ref.dom2dtm.DOM2DTM;

import java.io.ObjectInput;
import java.lang.annotation.*;
import java.lang.reflect.Method;

public class GetAnnotation {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @Documented
    public @interface VIP{
        String start() default "";
    }
    public static void main(String[] args) {
        Method eatMethod = null;
        Method sleepMethod=null;
        try {
            Class clazz=Dog.class;
            Object dog=clazz.newInstance();
            eatMethod = clazz.getMethod("eat");
            VIP annotation = eatMethod.getAnnotation(VIP.class);//获取方法上的注解
            System.out.println(annotation.start());
            eatMethod.invoke(dog);
            sleepMethod=clazz.getMethod("sleep",String.class);
            sleepMethod.invoke(dog,"AAA");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

    }

}
class Dog{
    @GetAnnotation.VIP(start="这是修饰在eat方法上的注解")
    public void eat(){
        System.out.println("狗类的eat()方法...");
    }
    @GetAnnotation.VIP(start="这是修饰在eat方法上的注解")
    public void sleep(String string){
        System.out.println("狗类的sleep()方法..."+string);
    }
}
