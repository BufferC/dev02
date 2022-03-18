package com.fc.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

// 声明一个切面类
@Aspect
@Component
public class AnnotationAspect {
    @Pointcut("execution(* com.fc.service.impl.*ServiceImpl.*(..))")
    public static void pointcut() {}

    @Before("AnnotationAspect.pointcut()")
    public void before() {
        System.out.println("前置通知，会在方法执行前执行");
    }

    @AfterReturning("AnnotationAspect.pointcut()")
    public void afterReturning() {
        System.out.println("后置通知，会在方法执行后执行");
    }

    @AfterThrowing("AnnotationAspect.pointcut()")
    public void afterThrowing() {
        System.out.println("异常通知，发生异常执行");
    }

    @After("AnnotationAspect.pointcut()")
    public void after() {
        System.out.println("最终通知，无论是否发生异常都会执行");
    }

    @Around("AnnotationAspect.pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("环绕通知，之前");

        // 执行方法
        Object proceed = joinPoint.proceed();

        System.out.println("环绕通知，之后");

        return proceed;
    }
}
