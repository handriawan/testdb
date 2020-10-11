package com.tesdb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;


import com.tesdb.model.UserModel;
@Component
@Mapper
public interface UserMapper {
	UserModel findUserByLoginId(@Param("loginId") String loginId);
	int setUserInfo(@Param("param") UserModel param);

	@Select("SELECT user_id,login_id,user_name,nim,tgl FROM user")
	  @Results(value= {
	    		@Result(property = "id", column = "user_id"),
	    		@Result(property = "loginId", column = "login_id"),
	            @Result(property = "nama", column = "user_name"),
	            @Result(property = "nim", column = "nim"),
	            @Result(property = "tgl", column = "tgl"),
	            })
    List<UserModel> ambilSemua();
	
	@Select("SELECT user_id,login_id,user_name,nim,tgl FROM user WHERE user_id=#{id}")
	 @Results(value= {
	    		@Result(property = "id", column = "user_id"),
	    		@Result(property = "loginId", column = "login_id"),
	            @Result(property = "nama", column = "user_name"),
	            @Result(property = "nim", column = "nim"),
	            @Result(property = "tgl", column = "tgl"),
	            })
    UserModel ambilSatuUser(int id);

	
    @Update("UPDATE user SET user_name=#{nama},login_id=#{loginId}, nim=#{nim}, tgl=#{tgl} WHERE user_id=#{id}")
    void updateSatuUser(UserModel userModel);
	 
    @Insert("INSERT INTO user (user_id,login_id,user_name,nim,tgl) VALUES (#{id},#{loginId},#{nama},#{nim},#{tgl})")
   	void simpanData(UserModel userModel);
   	
    @Update("UPDATE user SET password=#{password} WHERE user_id=#{id}")
    void updateUserPassword(UserModel userModel);
}
