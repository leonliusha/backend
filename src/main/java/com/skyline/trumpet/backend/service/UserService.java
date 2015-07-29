package com.skyline.trumpet.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skyline.trumpet.backend.model.User;
import com.skyline.trumpet.backend.persistence.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	public void userRegister(User user){
		 userMapper.userRegister(user);
	}
	
	public List<User> userLogin(User user){
		return userMapper.userLogin(user);
	}
}
