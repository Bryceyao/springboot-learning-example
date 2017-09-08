package org.spring.springboot.controller;

import org.spring.springboot.domain.City;
import org.spring.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 城市 Controller 实现 Restful HTTP 服务
 *
 * Created by bysocket on 07/02/2017.
 */
@RestController
public class CityRestController {

    @Autowired
    private CityService cityService;

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

    @RequestMapping(value = "/api/req", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> req() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("exception", "com.wolaidai.loanprocedure.exception.BusinessException");
        map.put("message", "userId为0|totalRate为null|handlingFee为null");
        map.put("error", "Precondition Failed");
        map.put("status", "412");
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
//        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }
}
