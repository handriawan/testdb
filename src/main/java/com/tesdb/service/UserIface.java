package com.tesdb.service;

import java.io.FileNotFoundException;
import java.util.List;
import com.tesdb.model.UserModel;

import net.sf.jasperreports.engine.JRException;

public interface UserIface {

	List<UserModel> selectAll();
	
	UserModel selectOneUserView(int id);
	
	UserModel selectOneUserEdit(int id);
	
	void selectOneUserSave(UserModel userModel);
	
	void addUser(UserModel userModel);
	
	UserModel findUserByLoginId(String loginId);
	
	String exportReport(String reportFormat) throws FileNotFoundException, JRException ;
	
	void updatePassword(UserModel userModel);
	
	
	
}
