package org.spring.springboot.service.impl;

import java.util.List;

import org.spring.springboot.dao.cluster.CityDao;
import org.spring.springboot.dao.master.UserDao;
import org.spring.springboot.dao.rela.UserCityDao;
import org.spring.springboot.domain.City;
import org.spring.springboot.domain.User;
import org.spring.springboot.domain.UserCity;
import org.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务实现层
 *
 * Created by bysocket on 07/02/2017.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao; // 主数据源

    @Autowired
    private CityDao cityDao; // 从数据源
    
    @Autowired
    private UserCityDao userCityDao; // 从数据源

    @Override
    public List<User> findByName(String userName) {
        List<User> users = userDao.findByName(userName);
        if(users!=null){
            for (User user : users) {
                UserCity rela = userCityDao.findUserId(user.getId());
                if(rela!=null){
                    City city = cityDao.findById(rela.getCityId());
                    user.setCity(city);
                }
            }
        }
        return users;
    }
}
