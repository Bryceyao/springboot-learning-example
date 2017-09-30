package org.spring.springboot.domain.po;

import com.google.common.base.MoreObjects;
import org.spring.springboot.domain.tables.TAreas;
import com.welab.xdao.annotation.Entity;
import java.io.Serializable;
import java.util.Date;


/**
 * 行政区划
 */

@Entity(table=TAreas.class)
public class Areas implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;//主键
	private String provinceCode;//省编码
	private String provinceName;//省名
	private String cityCode;//城市编码
	private Double cityLevel;//城市级别
	private String cityName;//城市名称
	private String districtCode;//区县编码
	private String districtName;//区县名称
	private Date createdAt;//创建时间
	private Date updatedAt;//修改时间

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getProvinceCode () {
        return provinceCode;
    }

    public void setProvinceCode (String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName () {
        return provinceName;
    }

    public void setProvinceName (String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityCode () {
        return cityCode;
    }

    public void setCityCode (String cityCode) {
        this.cityCode = cityCode;
    }

    public Double getCityLevel () {
        return cityLevel;
    }

    public void setCityLevel (Double cityLevel) {
        this.cityLevel = cityLevel;
    }

    public String getCityName () {
        return cityName;
    }

    public void setCityName (String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictCode () {
        return districtCode;
    }

    public void setDistrictCode (String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName () {
        return districtName;
    }

    public void setDistrictName (String districtName) {
        this.districtName = districtName;
    }

    public Date getCreatedAt () {
        return createdAt;
    }

    public void setCreatedAt (Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt () {
        return updatedAt;
    }

    public void setUpdatedAt (Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).toString();
    }
}