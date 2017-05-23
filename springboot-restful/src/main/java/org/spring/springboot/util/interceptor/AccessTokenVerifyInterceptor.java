package org.spring.springboot.util.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.springboot.domain.City;
import org.spring.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.alibaba.fastjson.JSONObject;


@Component
public class AccessTokenVerifyInterceptor extends HandlerInterceptorAdapter {
 
    @Autowired
    private CityService cityService;
 
    private final static Logger LOG = (Logger) LoggerFactory.getLogger(AccessTokenVerifyInterceptor.class);
 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        LOG.info("AccessToken executing ...");
        boolean flag = false;
        // token
        String accessToken = request.getParameter("token");
        if (StringUtils.isEmpty(accessToken)) {
            // 验证
            City c = verifyAccessToken(accessToken);
            // 时间过期
 
            // 用户验证
            if (c.getId()==null) {
                City user =cityService.findCityById(c.getId());
                if(user != null) {
                    request.setAttribute("userInfo", user);
                    
                }
            }
        }
 
        if (!flag) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.getWriter().print("AccessToken ERROR");
        }
 
        return flag;
    }
    
    
    private City verifyAccessToken(String accessToken){
        return JSONObject.parseObject(accessToken, City.class);
    }
}