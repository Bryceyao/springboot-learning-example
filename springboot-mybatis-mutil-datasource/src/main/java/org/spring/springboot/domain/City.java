package org.spring.springboot.domain;

import java.io.Serializable;

/**
 * 城市实体类
 *
 * Created by bysocket on 07/02/2017.
 */
public class City {

    /**
     * 城市编号
     */
    private Long id;

    /**
     * 省份编号
     */
    private Long provinceId;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 描述
     */
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    /*
    Navicat MySQL Data Transfer

    Source Server         : MySql3307
    Source Server Version : 50633
    Source Host           : localhost:3307
    Source Database       : test

    Target Server Type    : MYSQL
    Target Server Version : 50633
    File Encoding         : 65001

    Date: 2017-05-22 17:50:19
    

    SET FOREIGN_KEY_CHECKS=0;

    -- ----------------------------
    -- Table structure for city
    -- ----------------------------
    DROP TABLE IF EXISTS city;
    CREATE TABLE city (
      id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '城市编号',
      province_id int(10) unsigned NOT NULL COMMENT '省份编号',
      city_name varchar(25) DEFAULT NULL COMMENT '城市名称',
      description varchar(25) DEFAULT NULL COMMENT '描述',
      PRIMARY KEY (id)
    ) ENGINE=InnoDB AUTO_INCREMENT=4405 DEFAULT CHARSET=utf8;

    -- ----------------------------
    -- Records of city
    -- ----------------------------
    INSERT INTO city VALUES ('1101', '11', '北京市', '故事');
    INSERT INTO city VALUES ('4301', '43', '长沙市', '定居地');
    INSERT INTO city VALUES ('4403', '44', '深圳市', '工作城市');*/
}
