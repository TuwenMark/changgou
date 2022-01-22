package com.changgou.goods.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.context.annotation.Configuration;

/**
 * @Program: changgou
 * @Description: 中间件的切面
 * @Author: Mr.Ye
 * @Date: 2021-12-21 06:38
 **/
//@Aspect
@Configuration
public class MiddlewareAspect {
//    @Around("execution(* tk.mybatis.mapper.common.base..*.*(..))")
    @Around("execution(* com.github.pagehelper..*.*(..))")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("----环绕通知1开始----");
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("方法的返回值是：" + proceed);
        System.out.println("----环绕通知1结束");
        return proceed;
    }

    @Around("execution(* com.changgou.goods.service.impl.SpecServiceImpl.*(..))")
    public Object doAround1(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("----环绕通知2开始----");
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("方法的返回值是：" + proceed);
        System.out.println("----环绕通知2结束");
        return proceed;
    }
}
