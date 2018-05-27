package com.zwb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zwb.entity.User;
import com.zwb.mapper.UserMapper;
import com.zwb.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	public User login(User user) {
		return userMapper.login(user);
	}

	public void addUser(User user) {
		userMapper.addUser(user);
		
	}

	public void updateUser(User user) {
		userMapper.updateUser(user);
	}

	@Override
	public List<User> listAllUser() {
		// TODO Auto-generated method stub
		return userMapper.listAllUser();
	}

	

}
