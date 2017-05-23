package org.spring.springboot.dao.rela;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.UserCity;


@Mapper
public interface UserCityDao {
    UserCity findUserId(@Param("userId") Long userId);
    
    UserCity findCityId(@Param("cityId") Long cityId);
}
