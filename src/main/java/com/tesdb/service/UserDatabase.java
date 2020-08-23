package com.tesdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tesdb.dao.UserMapper;
import com.tesdb.model.UserModel;

@Service
public class UserDatabase implements UserIface{

	@Autowired 
	UserMapper userMapper;
	
	@Override
	public List<UserModel> selectAll() {    
		return userMapper.ambilSemua();
	}

	@Override
	public UserModel selectOneUserView(int id) {
		return userMapper.ambilSatuUser(id);
	}

	@Override
	public UserModel selectOneUserEdit(int id) {
		return userMapper.ambilSatuUserEdit(id);
	}

	@Override
	public void selectOneUserSimpan(UserModel userModel) {
		userMapper.updateSatuUser(userModel);
	}

	@Override
	public void addUser(UserModel userModel) {
		userMapper.simpanData(userModel);
		
	}



	



}
