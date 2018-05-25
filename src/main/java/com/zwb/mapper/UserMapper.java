package com.zwb.mapper;


import com.zwb.entity.User;

public interface UserMapper {
	public User login(User user);

	public void addUser(User user);

	public void updateUser(User user);
}
