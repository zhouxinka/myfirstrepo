package com.example.testAspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;

@Component
@org.aspectj.lang.annotation.Aspect
public class Aspect {
    @Around("@annotation(MyTransactional)")//目标方法是加了MyTransactional注解的方法
    public void doMyTranscational(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("目标方法执行之前");
        try {
            System.out.println("目标方法："+proceedingJoinPoint.getSignature()+"/"+proceedingJoinPoint.getSourceLocation());
            proceedingJoinPoint.proceed();
            System.out.println("目标方法执行之后");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }
}
