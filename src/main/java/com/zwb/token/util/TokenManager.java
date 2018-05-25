package com.zwb.token.util;

public interface TokenManager {
	String createToken(String username) throws Exception;  
	  
    boolean checkToken(String token);  
}
