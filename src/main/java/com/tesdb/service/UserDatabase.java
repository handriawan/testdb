package com.tesdb.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.tesdb.dao.RoleMapper;
import com.tesdb.dao.UserMapper;
import com.tesdb.dao.UserRoleMapper;
import com.tesdb.domain.UserPrincipal;
import com.tesdb.model.UserModel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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

	 public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
	        String path = "D:\\SpringHD";
	        List<UserModel> users = userMapper.ambilSemua();
	        //load file and compile it
	        File file = ResourceUtils.getFile("classpath:data_user.jrxml");
	        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
	        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(users);
	        Map<String, Object> parameters = new HashMap<>();
	        parameters.put("createdBy", "Java Techie");
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	        if (reportFormat.equalsIgnoreCase("html")) {
	            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\users.html");
	        }
	        if (reportFormat.equalsIgnoreCase("pdf")) {
	            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\users.pdf");
	        }

	        return "report generated in path : " + path;
	    }
	



}
