package org.spring.springboot.filter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class FilterConfig extends WebMvcConfigurerAdapter{
    @Bean
    public FilterRegistrationBean getDemoFilter(){
        DemoFilter demoFilter=new DemoFilter();
        FilterRegistrationBean registrationBean=new FilterRegistrationBean();
        registrationBean.setFilter(demoFilter);
        List<String> urlPatterns=new ArrayList<String>();
        urlPatterns.add("/api/lcity");//拦截路径，可以添加多个
        urlPatterns.add("/api/fcity/*");
        urlPatterns.add("/api/callBackCenter");
        registrationBean.setUrlPatterns(urlPatterns);
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
