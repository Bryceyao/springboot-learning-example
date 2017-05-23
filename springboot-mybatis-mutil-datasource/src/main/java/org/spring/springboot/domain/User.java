package org.spring.springboot.domain;

/**
 * 用户实体类
 *
 * Created by bysocket on 07/02/2017.
 */
public class User {

    /**
     * 城市编号
     */
    private Long id;

    /**
     * 城市名称
     */
    private String userName;

    /**
     * 描述
     */
    private String description;

    private City city;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    Date: 2017-05-22 17:50:26
    

    SET FOREIGN_KEY_CHECKS=0;

    -- ----------------------------
    -- Table structure for user
    -- ----------------------------
    DROP TABLE IF EXISTS user;
    CREATE TABLE user (
      id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
      user_name varchar(25) DEFAULT NULL COMMENT '姓名',
      description varchar(25) DEFAULT NULL COMMENT '描述',
      PRIMARY KEY (id)
    ) ENGINE=InnoDB AUTO_INCREMENT=4406 DEFAULT CHARSET=utf8;

    -- ----------------------------
    -- Records of user
    -- ----------------------------
    INSERT INTO user VALUES ('1', '张三', '朋友');
    INSERT INTO user VALUES ('2', '李四', '同事');*/
}
