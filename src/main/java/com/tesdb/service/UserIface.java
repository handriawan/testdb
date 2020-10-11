package com.tesdb.service;

import java.util.List;
import com.tesdb.model.UserModel;

public interface UserIface {

	List<UserModel> selectAll();
	
	UserModel selectOneUserView(int id);
	
	UserModel selectOneUserEdit(int id);
	
	void selectOneUserSave(UserModel userModel);
	
	void addUser(UserModel userModel);
	
	UserModel findUserByLoginId(String loginId);
	
	void updatePassword(UserModel userModel);
}
