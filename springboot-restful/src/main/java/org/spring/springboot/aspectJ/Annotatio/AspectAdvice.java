package org.spring.springboot.aspectJ.Annotatio;

import java.util.Random;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;


/**
 * 注解引入截面.
 * 
 * Created by Bryce Yao<sysyaoyulong@gmail.com> on 2017-11-29.
 */
@Component
@Aspect
public class AspectAdvice {
    
    @Around(value ="@annotation(around)")
    public void aroundAuthority(ProceedingJoinPoint point,AroundAuthority around) throws Throwable{
        System.out.println("===method is :"+around.methodName());
        
        if(new Random().nextBoolean()){
            System.out.println(around.methodName()+"is accept");
            Object result = point.proceed();
            System.out.println(around.methodName()+"exec result:"+JSONObject.toJSONString(result));
        }
        else{
            System.out.println(around.methodName()+"is reject");
        }
    }
    
    @Before(value="@annotation(before)")
    public void beforeAuthority(JoinPoint point,BeforeAuthority before){
        Object[] args = point.getArgs();
        for (int i = 0; i < args.length; i++) {
            System.out.println("args=="+JSONObject.toJSONString(args));
        }
        System.out.println("before.methodName=="+before.methodName());
        System.out.println("before.param=="+before.param());
    }
}
