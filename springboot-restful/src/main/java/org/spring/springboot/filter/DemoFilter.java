package org.spring.springboot.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DemoFilter implements Filter{

    @Override
    public void destroy() {
        System.out.println("DemoFilter准备销毁...");  
        
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        System.out.println("==>DemoFilter启动");
        
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException, ServletException {
        // 强制类型转换  
        HttpServletRequest request = (HttpServletRequest)arg0;  
        HttpServletResponse response = (HttpServletResponse)arg1;  
        System.out.println("DemoFilter执行=URL=>"+request.getRequestURL().toString());
        System.out.println("DemoFilter执行=URI=>"+request.getRequestURI());
        // 将请求转发到目的地  
        chain.doFilter(arg0, arg1);            
    }

}
