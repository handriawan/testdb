package com.tesdb.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.tesdb.model.RoleModel;

@Component
@Mapper
public interface RoleMapper {

	RoleModel getRoleInfo(@Param("role") String role);
}
