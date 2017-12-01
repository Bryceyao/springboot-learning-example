package org.spring.springboot.aspectJ.executio;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;


/**
 * 表达式匹配截面.
 * 
 * Created by Bryce Yao<sysyaoyulong@gmail.com> on 2017-11-29.
 */
@Component
@Aspect
public class ReleaseAspect {
    
    @AfterReturning(returning="rvt"
            ,pointcut="execution(* org.spring.springboot.*.service..*.login(..))||execution(* org.spring.springboot.aspectJ.service..*.hello(..))")
    public void afterReturning(JoinPoint joinPoint,Object rvt)
    {
        System.out.println("executio 模拟方法正常结束统计...返回值="+JSONObject.toJSONString(rvt));
    }
    
    @After("execution(* org.spring.springboot.aspectJ.service..*.*(..))")
    public void after(JoinPoint joinPoint)
    {
        System.out.println("executio 模拟方法结束统计...");
    }
    
    @Before("execution(* org.spring.springboot.*.*.service..*.*(..))")
    public void release(JoinPoint joinPoint)
    {
        System.out.println("executio 模拟方法开始前校验...");
        System.out.println("joinPoint.getKind()=="+joinPoint.getKind());
        System.out.println("joinPoint.getArgs()=="+joinPoint.getArgs());
        System.out.println("joinPoint.getClass()=="+joinPoint.getClass());
        System.out.println("joinPoint.getSignature()=="+joinPoint.getSignature());
        System.out.println("joinPoint.getSourceLocation()=="+joinPoint.getSourceLocation());
        System.out.println("joinPoint.getStaticPart()=="+joinPoint.getStaticPart());
        System.out.println("joinPoint.getTarget()=="+joinPoint.getTarget());
    }
}
