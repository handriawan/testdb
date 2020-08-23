package com.tesdb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;


import com.tesdb.model.UserModel;

@Mapper
public interface UserMapper {

	@Select("SELECT id,nama,nim,DATE_FORMAT(tgl, '%d-%m-%Y') as tgl_as ,tgl FROM t_user")
	  @Results(value= {
	    		@Result(property = "id", column = "id"),
	            @Result(property = "nama", column = "nama"),
	            @Result(property = "nim", column = "nim"),
	            @Result(property = "tgl", column = "tgl"),
	            })
    List<UserModel> ambilSemua();
	
	@Select("SELECT id,nama,nim,DATE_FORMAT(tgl, '%d-%m-%Y') as tgl_as,tgl FROM t_user WHERE id=#{id}")
	 @Results(value= {
	    		@Result(property = "id", column = "id"),
	            @Result(property = "nama", column = "nama"),
	            @Result(property = "nim", column = "nim"),
	            @Result(property = "tgl", column = "tgl"),
	            })
    UserModel ambilSatuUser(int id);
	
	
	@Select("SELECT id,nama,nim,DATE_FORMAT(tgl, '%d-%m-%Y') as tgl_as,tgl FROM t_user WHERE id=#{id}")
	 @Results(value= {
	    		@Result(property = "id", column = "id"),
	            @Result(property = "nama", column = "nama"),
	            @Result(property = "nim", column = "nim"),
	            @Result(property = "tgl", column = "tgl"),
	 })
   UserModel ambilSatuUserEdit(int id);
	
    @Update("UPDATE t_user SET nama=#{nama}, nim=#{nim}, tgl=#{tgl} WHERE id=#{id}")
    void updateSatuUser(UserModel userModel);
	 
    @Insert("INSERT INTO t_user (id,nama,nim,tgl) VALUES (#{id},#{nama},#{nim},#{tgl}})")
   	void simpanData(UserModel userModel);
   	
}
