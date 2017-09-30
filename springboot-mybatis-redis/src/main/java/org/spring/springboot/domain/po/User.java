package org.spring.springboot.domain.po;

import com.google.common.base.MoreObjects;
import org.spring.springboot.domain.tables.TUser;
import com.welab.xdao.annotation.Entity;
import java.io.Serializable;


/**
 * 
 */

@Entity(table=TUser.class)
public class User implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;//ID
	private String userName;//姓名
	private String description;//描述

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getUserName () {
        return userName;
    }

    public void setUserName (String userName) {
        this.userName = userName;
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