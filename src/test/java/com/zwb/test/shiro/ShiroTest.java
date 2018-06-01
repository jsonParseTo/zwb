package com.zwb.test.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

public class ShiroTest {

	@Test
	public void getPasswordByNameAndSalt() {
			String hashAlgorithmName = "MD5";  
	        String credentials = "888888";  
	        int hashIterations = 1024;  
	        ByteSource credentialsSalt = ByteSource.Util.bytes("zwb1");  
	        Object obj = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);  
	        System.out.println(obj);  
	}

}
