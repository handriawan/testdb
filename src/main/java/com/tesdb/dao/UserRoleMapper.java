package com.tesdb.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.tesdb.model.UserRoleModel;

@Component
@Mapper
public interface UserRoleMapper {
public void setUserRoleInfo(@Param("param") UserRoleModel param);
	
}
