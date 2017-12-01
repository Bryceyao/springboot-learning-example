package org.spring.springboot.aspectJ.service;

import org.spring.springboot.aspectJ.Annotatio.AroundAuthority;
import org.spring.springboot.aspectJ.Annotatio.BeforeAuthority;
import org.springframework.stereotype.Component;

@Component
public class AnnotatioService {
    @AroundAuthority(methodName="login")
    public String login(String userName,String pwd){
        System.out.println("LoginService login "+userName+"_"+pwd);
        return userName+"_"+pwd;
    }
    
    @BeforeAuthority(methodName="hello")
    public String hello(String name) {
        System.out.println("执行hello："+name);
        return "hello返回参数 "+name;
    }
}
