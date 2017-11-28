package org.spring.springboot.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.spring.springboot.domain.City;
import org.spring.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 城市 Controller 实现 Restful HTTP 服务
 *
 * Created by bysocket on 07/02/2017.
 */
@RestController
@RequestMapping(value = "city")
public class CityRestController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/api/fcity1/{id}")
    public City findOneCity1(@PathVariable("id") Long id) {
        return cityService.findCityById(id);
    }
    
    @RequestMapping(value = "/api/fcity/{id}", method = RequestMethod.GET)
    public City findOneCity(@PathVariable("id") Long id) {
        return cityService.findCityById(id);
    }

    @RequestMapping(value = "/api/lcity", method = RequestMethod.GET)
    public List<City> findAllCity() {
        return cityService.findAllCity();
    }

    @RequestMapping(value = "/api/ccity", method = RequestMethod.POST)
    public void createCity(@RequestBody City city) {
        cityService.saveCity(city);
    }

    @RequestMapping(value = "/api/mcity", method = RequestMethod.PUT)
    public void modifyCity(@RequestBody City city) {
        cityService.updateCity(city);
    }

    @RequestMapping(value = "/api/dcity/{id}", method = RequestMethod.DELETE)
    public void modifyCity(@PathVariable("id") Long id) {
        cityService.deleteCity(id);
    }

    @RequestMapping(value = "/api/callBackCenter", method = RequestMethod.DELETE)
    public void callBackCenter() {
        System.out.println("=====callBackCenter====");
    }

    @RequestMapping(value = {"/api1/req"}, method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseEntity<Map<String, Object>> req1(HttpServletRequest request) {
        String name= request.getParameter("name");
        System.out.println(name);
        System.out.println(request.getRequestURI());
        System.out.println(request.getRequestURL());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("exception", "com.wolaidai.loanprocedure.exception.BusinessException");
        map.put("message", "userId为0|totalRate为null|handlingFee为null");
        map.put("error", "Precondition Failed");
        map.put("status", "412");
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
//        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }
    
    @RequestMapping(value = {"/api/req","/api/req1","/api/req2"}, method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseEntity<Map<String, Object>> req(HttpServletRequest request) {
        String requestStr= getBodyString(request);
        System.out.println(requestStr);
        System.out.println(request.getRequestURI());
        System.out.println(request.getRequestURL());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("exception", "com.wolaidai.loanprocedure.exception.BusinessException");
        map.put("message", "userId为0|totalRate为null|handlingFee为null");
        map.put("error", "Precondition Failed");
        map.put("status", "412");
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    } 
    
    /** 
     * 获取请求Body 
     * 
     * @param request 
     * @return 
     */  
    public static String getBodyString(ServletRequest request) {  
        StringBuilder sb = new StringBuilder();  
        InputStream inputStream = null;  
        BufferedReader reader = null;  
        try {  
            inputStream = request.getInputStream();  
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));  
            String line = "";  
            while ((line = reader.readLine()) != null) {  
                sb.append(line);  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (inputStream != null) {  
                try {  
                    inputStream.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
            if (reader != null) {  
                try {  
                    reader.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return sb.toString();  
    }  
}
