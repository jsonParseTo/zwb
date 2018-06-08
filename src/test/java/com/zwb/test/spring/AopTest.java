package com.zwb.test.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zwb.action.UserAction;
import com.zwb.entity.User;


public class AopTest {
	
	
public static void main(String[] args) throws Exception{
//	 ApplicationContext ctx =  new ClassPathXmlApplicationContext("config/applicationContext.xml","config/spring-shiro.xml","config/spring-redis.xml");
//
//	 AopImpl aop = (AopImpl) ctx.getBean("aopImpl");
//	 aop.say("hellos");
	 
	UserAction u = new UserAction();
	User user = new User("zwb","888888");
	u.login(user);
	}
}
