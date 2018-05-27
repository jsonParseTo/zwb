package com.zwb.service;


import java.util.List;

import com.zwb.entity.User;


public interface UserService {
	public User login(User user);

	public void addUser(User user);

	public void updateUser(User user);

	public List<User> listAllUser();
}
