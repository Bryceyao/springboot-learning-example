package org.spring.springboot.aspectJ;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.springboot.aspectJ.service.AnnotatioService;
import org.spring.springboot.aspectJ1.com.service.City1Service;
import org.spring.springboot.aspectJ1.service.City2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Test11 {
    
    @Autowired
    private ExecutioService executioService;
    @Autowired
    private AnnotatioService annotatioService;
    @Autowired
    private City1Service city1Service;
    @Autowired
    private City2Service city2Service;
    
    /**
     * 测试表达式匹配路径
     */
    @Test
    public void test1(){
        System.out.println("Test11 login==== "+executioService.login("tom", "123456"));
        System.out.println("Test11 hello==== "+executioService.hello("bryce"));
        System.out.println("Test11 1findAllCity==== "+JSONObject.toJSONString(city1Service.findAllCity()));
        System.out.println("Test11 2findAllCity==== "+JSONObject.toJSONString(city2Service.findAllCity()));
    }
    
    
    @Test
    public void test3(){
        System.out.println("Test11==== "+annotatioService.login("tom", "123456"));
    }
    
    @Test
    public void test4(){
        System.out.println("Test11==== "+annotatioService.hello("bryce"));
    }
}