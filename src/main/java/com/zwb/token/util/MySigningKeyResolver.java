package com.zwb.token.util;

import java.security.Key;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.SigningKeyResolver;

public class MySigningKeyResolver implements SigningKeyResolver {

	@Override
	public Key resolveSigningKey(JwsHeader header, Claims claims) {
		
		return null;
	}

	@Override
	public Key resolveSigningKey(JwsHeader header, String plaintext) {
		return null;
	}

}
