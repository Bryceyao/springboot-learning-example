package org.spring.springboot.domain.po;

import com.google.common.base.MoreObjects;
import org.spring.springboot.domain.tables.TCity;
import com.welab.xdao.annotation.Entity;
import java.io.Serializable;


/**
 * 城市信息
 */

@Entity(table=TCity.class)
public class City implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;//城市编号
	private Integer provinceId;//省份编号
	private String cityName;//城市名称
	private String description;//描述

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public Integer getProvinceId () {
        return provinceId;
    }

    public void setProvinceId (Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityName () {
        return cityName;
    }

    public void setCityName (String cityName) {
        this.cityName = cityName;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).toString();
    }
}