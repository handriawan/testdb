package com.tesdb.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.tesdb.model.UserModel;
import com.tesdb.service.UserIface;

@Controller
public class UserController {
	

    @Autowired
    UserIface userIface;
    
    @RequestMapping("/hd")
    public String dasboard() {
    	return "menu";
    }
    
    @RequestMapping("/user")
    public String userSemua(Model model) {
    	List<UserModel> users=userIface.selectAll();
    	model.addAttribute("users", users);
    	return "pages/tables/list-user";
    }
    
    @RequestMapping("/user/view/{id}")
    public String userSatuView(Model model,@PathVariable(value="id")int id) {
    	UserModel getuser=userIface.selectOneUserView(id);
    	model.addAttribute("user", getuser);
    	return "pages/forms/user-view";
    }
    
    
    @RequestMapping("/user/edit/{id}")
    public String userSatuEdit(Model model,@PathVariable(value="id")int id) {
    	UserModel getuser=userIface.selectOneUserEdit(id);
    	model.addAttribute("user", getuser);
    	return "pages/forms/user-edit";
    }
  
			 
  
    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public String updateUser(
    		@RequestParam(value = "id", required = false) int id,
			 @RequestParam(value = "nama", required = false) String nama,
			 @RequestParam(value = "nim", required = false) int nim,
			 @RequestParam(value = "tgl", required = false) String tgl	 
    		
    		) 
    	{
    
    	 UserModel userModel = new UserModel(id,nama,nim,tgl);
	      userIface.selectOneUserSimpan(userModel);
    	
	      return "redirect:/user/view/"+id;
    	}
   
   
}
			
