package com.skyline.trumpet.backend.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skyline.trumpet.backend.model.User;
import com.skyline.trumpet.backend.service.UserService;
import com.skyline.trumpet.backend.util.UtilTools;

@RestController
public class UserController {

	@Resource
	private UserService userService;
	
	@RequestMapping(value = "/userRegister", method = RequestMethod.POST,consumes = "application/json")
	public User userRegister(@RequestBody User user){
		if(UtilTools.validUser(user)){
			userService.userRegister(user);
		}
		return user;
	}
	
	@RequestMapping(value="/userLogin", method=RequestMethod.POST,consumes="application/json")
	public User userLogin(@RequestBody User user){
		User loginUser = null;
		List<User> users =  userService.userLogin(user);
		if(users!= null && users.size()>0){
			loginUser = users.get(0);
		}
		return loginUser;
	}
	
	
	
}
