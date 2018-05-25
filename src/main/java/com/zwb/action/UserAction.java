package com.zwb.action;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zwb.Expection.msgException;
import com.zwb.entity.User;
import com.zwb.service.UserService;
import com.zwb.token.util.DefaultTokenManager;

@RestController
@RequestMapping("/user")
public class UserAction {

	@Resource
	private UserService userService;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public User login(User user) throws msgException{
		User currentuser = userService.login(user);
		if(null == currentuser){
			throw new msgException("用户名或密码错误");
		}
		System.out.println(currentuser.getUsername());
		return currentuser;
	}
}
