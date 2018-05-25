package com.zwb.test.spring;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zwb.entity.User;
import com.zwb.service.UserService;



public class UserTest {
	private ApplicationContext ctx;
	@Before
	public void init(){
		ctx = new ClassPathXmlApplicationContext("config/applicationContext.xml");
	}
	
	@Test
	public void testLogin(){
		UserService userService = (UserService) ctx.getBean("userService");
		User u = new User("zwb", "888888");
		User user = userService.login(u);
		System.out.println("Username : "+user.getUsername()+" , Password : "+user.getPassword());
	}
	
	@Test
	public void testaddUser(){
		UserService userService = (UserService) ctx.getBean("userService");
		User user = new User("zwb2", "888888");
		userService.addUser(user);
	}
	
	@Test
	public void testupdateUser(){
		UserService userService = (UserService) ctx.getBean("userService");
		User user = new User("zwb", "999999");
		user.setId(4);
		userService.updateUser(user);
	}
}
