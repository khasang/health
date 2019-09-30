package org.health.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Aspect
public class Bard {

//    @Pointcut("execution(* org.health.servive.impl.KnightServiceImpl.getAchievement(..))")
//    public void serviceBefore() {
//
//    }
//
//    @Before(value = "serviceBefore()")
//    public void getSong(JoinPoint joinPoint) {
//        System.err.println("LALALALaaalala");
//    }

    @Around("execution(* org.health.servive.impl.KnightServiceImpl.getAchievement(..)) && args(val, ..)")
    public Object action(ProceedingJoinPoint joinPoint, String val) throws Throwable {
        long timeBefore = System.currentTimeMillis();
        Object obj = joinPoint.proceed();
        long timeAfter = System.currentTimeMillis();

        System.err.println("Lalalalaaaa");

        System.err.println("Knight defeat an enemy - " + val + " with " + ((timeAfter - timeBefore) / 1000) + "s");

        return obj;
    }

}
