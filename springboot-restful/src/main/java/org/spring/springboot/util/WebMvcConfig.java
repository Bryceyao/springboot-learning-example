package org.spring.springboot.util;

import org.spring.springboot.util.interceptor.AccessTokenVerifyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * MVC 设置
 *
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
 
    @Bean
    public AccessTokenVerifyInterceptor tokenVerifyInterceptor() {
        return new AccessTokenVerifyInterceptor();
    }
 
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenVerifyInterceptor()).addPathPatterns("/api");
        super.addInterceptors(registry);
    }
 
}