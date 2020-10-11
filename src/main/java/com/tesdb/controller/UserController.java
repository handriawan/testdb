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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.tesdb.model.UserModel;
import com.tesdb.service.UserIface;

@Controller
public class UserController {
	
    @Autowired
    UserIface userIface;
    
	 Logger logger = LoggerFactory.getLogger(UserController.class);
    
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
  
    
    @RequestMapping("/user/changepass/{id}")
    public String changePassword(Model model,@PathVariable(value="id")int id) {
    	UserModel getPassModel=userIface.selectOneUserEdit(id);
    	model.addAttribute("user", getPassModel);
    	return "pages/forms/user-change-pwd";
    }
    
    @RequestMapping(value="/user/updatepassword", method=RequestMethod.POST)
	public String updatePassword(
			@RequestParam(value="password", required=false) String password,
			@RequestParam(value="id", required=false) int id,
			@RequestParam(value="tgl", required=false) String tgl,
			RedirectAttributes redirAttrs
			) throws ParseException
	{
		try {
			
			UserModel userModel = new UserModel(id,password,tgl);
			userIface.updatePassword(userModel);
			
		}catch(DataIntegrityViolationException e) {
			 logger.error("history already exist");
		     redirAttrs.addFlashAttribute("message", "Data already exist");
		        
		}catch(Exception f) {
			redirAttrs.addFlashAttribute("message",f.getMessage());
		}
		
		return "redirect:/user";
	}
    
    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public String userSatuSave(
    		
    		 @RequestParam(value = "id", required = false) int id,
    		 @RequestParam(value = "loginId", required = false) String login_id,
			 @RequestParam(value = "nama", required = false) String nama,
			 @RequestParam(value = "nim", required = false) int nim,
			 @RequestParam(value = "tgl", required = false) String tgl,
			 RedirectAttributes redirAttrs )  throws ParseException
    	
    {   
    	try {  
    		
    	  UserModel userModel = new UserModel(id,nama,nim,tgl,login_id);
	      userIface.selectOneUserSave(userModel); 
	      
    	} catch (DataIntegrityViolationException e) {
	        System.out.println("history already exist");
	        logger.error("history already exist");
	        redirAttrs.addFlashAttribute("message", "Data already exist");
	        
	    } catch (Exception f) {
	    	 redirAttrs.addFlashAttribute("message", f.getMessage());
	    }
	 
	 redirAttrs.addFlashAttribute("message", "Success");
	      return "redirect:/user/view/"+id;
    }
    
    
    
    @GetMapping(value = {"/","login"})
    public ModelAndView getLoginPage() {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("login");
    	return modelAndView;
    }
    
    
}
   