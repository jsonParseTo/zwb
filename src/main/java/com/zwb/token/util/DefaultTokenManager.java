package com.zwb.token.util;

import java.util.Date;

import javax.crypto.SecretKey;
import javax.security.auth.Subject;

import com.zwb.util.DateConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;

//头部、载荷与签名
public class DefaultTokenManager{
	private static final SecretKey JWTSECRET = MacProvider.generateKey();
	private static final long EXPIREDATE = 2*60*1000;
	
//	private static final String AUTHSECRET = "XX#$%()(#*!()!KL<><MQLMNQNQJQK sdfkjsdrow32234545fdf>?N<:{LWPW";

	public static <T> String createTokenByJjwt(String subject) throws Exception{
		long current = System.currentTimeMillis() + EXPIREDATE;
		Date date = new Date(current);
		String token = Jwts.builder()
				.setSubject(subject)
				.signWith(SignatureAlgorithm.HS512, JWTSECRET)
				.setExpiration(date)
				.compact();
		return token;
	}
	public static String verifyTokenOfJjwt(String token){
		String user = null;
		try {
			Jws<Claims> claims = Jwts.parser()
					.setSigningKey(JWTSECRET)
					.parseClaimsJws(token);
			user = claims.getBody().getSubject().toString();
		} catch (SignatureException e) {
			 System.out.println(e.getMessage());
			 e.printStackTrace();
		   
		}
		return user;
	}

	
	public static void main(String[] args) throws Exception {
		
		String token = DefaultTokenManager.createTokenByJjwt("lucia");
		System.out.println("token : "+token);
		Thread.sleep(1000);
//		String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsdWNpYSIsImV4cCI6MTUyNzIzNTIwMX0.opNW72II1sSFY6wFBK4OXARph3TLETnWx0zTChdB6139tPkurMsQ71BsZYTmJEXVpo1B2GFrsqHqc2xuILGijg";
//		Jws<Claims> claims = DefaultTokenManager.verifyTokenOfJjwt(token);
//		System.out.println(claims.getBody().getSubject());
		
		
		
		
//		String token = DefaultTokenManager.createTokenByAuth("zwb");
//		System.out.println("token : "+token);
//		Map<String, Claim> claims = DefaultTokenManager.verifyTokenOfAuth(token);
//		System.out.println(claims.get("name").asString());
	}

}

//	public static String createTokenByAuth(String username) throws Exception{
//		LocalDateTime datetime = LocalDateTime.now();
//		Date initDay = DateConverter.ConverterLocalDateTimeToDate(datetime);
//		LocalDateTime expire = datetime.plusMinutes(1);
//		Date expireDay = DateConverter.ConverterLocalDateTimeToDate(expire);
//		
//		Map<String, Object> headerClaims = new HashMap<String, Object>();
//		headerClaims.put("alg", "HS256");
//		headerClaims.put("typ", "JWT");
//		String token = JWT.create().withHeader(headerClaims)
//					.withClaim("name", username)
//					.withExpiresAt(expireDay)
//					.withIssuedAt(initDay)
//					.sign(Algorithm.HMAC256(AUTHSECRET));
//		return token;
//	}

//	public static Map<String, Claim> verifyTokenOfAuth(String token) throws Exception {
//		JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(AUTHSECRET)).build();
//		DecodedJWT decodeJWT = null;
//		decodeJWT = jwtVerifier.verify(token);
//		return decodeJWT.getClaims();
//	
//	}