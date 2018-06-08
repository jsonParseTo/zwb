package com.zwb.test.spring;

import org.springframework.stereotype.Component;

@Component("aopImpl")
public class AopImpl{

	
	public String say(String hello) {
		System.out.println(hello);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "aaa";
	}

}
