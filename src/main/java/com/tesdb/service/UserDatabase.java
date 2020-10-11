package com.tesdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tesdb.dao.RoleMapper;
import com.tesdb.dao.UserMapper;
import com.tesdb.dao.UserRoleMapper;
import com.tesdb.domain.UserPrincipal;
import com.tesdb.model.UserModel;

@Service
public class UserDatabase implements UserDetailsService,UserIface{

	@Autowired 
	UserMapper userMapper;
	
	@Autowired
	RoleMapper roleMapper;
	
	@Autowired
	UserRoleMapper userRoleMapper;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
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
		return userMapper.ambilSatuUser(id);
	}

	@Override
	public void selectOneUserSave(UserModel userModel) {
		userMapper.updateSatuUser(userModel);
	}

	@Override
	public void addUser(UserModel userModel) {
		userMapper.simpanData(userModel);
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserModel user = userMapper.findUserByLoginId(username);
		return new UserPrincipal(user);
	}

	@Override
	public UserModel findUserByLoginId(String loginId) {
		// TODO Auto-generated method stub
		return userMapper.findUserByLoginId(loginId);
	}

	@Override
	public void updatePassword(UserModel userModel) {
		// TODO Auto-generated method stub
		userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
		userMapper.updateUserPassword(userModel);
		
	}



	



}
