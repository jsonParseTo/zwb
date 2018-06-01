package com.zwb.token.util;

import java.util.Date;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.zwb.entity.User;

public class EncryptUtil {

	public static User EncryptUser(User user) {
		String username = user.getUserName();
		String password = user.getPassWord();
		SimpleHash sh=new SimpleHash("MD5", password, ByteSource.Util.bytes(username), 1024);
		user.setSalt(sh.getSalt().toString());
	    user.setPassWord(sh.toString());
	    user.setcTime(new Date());
	    return user;
	}

}
