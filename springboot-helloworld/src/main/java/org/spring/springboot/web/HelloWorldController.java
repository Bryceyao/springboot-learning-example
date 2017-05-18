package org.spring.springboot.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Boot HelloWorld 案例
 *
 * Created by bysocket on 16/4/26.
 */
@RestController
@RequestMapping("/mvc")
public class HelloWorldController {

    @RequestMapping("/sayHello")
    public String sayHello() {
        return "Hello,World!";
    }
}
