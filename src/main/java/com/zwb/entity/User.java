package com.zwb.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.crazycake.shiro.AuthCachePrincipal;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.j2objc.annotations.Property;
import com.zwb.jackson.util.MyDateJsonDeserialize;
import com.zwb.jackson.util.MyDateJsonSerialize;


public class User implements Serializable, AuthCachePrincipal{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@JsonIgnore(value=false)
	private Integer userId;
	@JsonIgnore(value=false)
	private String userName;
	
	@JsonIgnore(value=false)
	private String passWord;
	
	private MultipartFile image;
	
	@JsonIgnore(value=false)
	private String salt;
	
//	@JsonSerialize(using=MyDateJsonSerialize.class)
//	@JsonDeserialize(using=MyDateJsonDeserialize.class)
	@JsonIgnore(value=false)
	private Date cTime;
	
	private List<Role> userRoles;
	
	public User() {
	}

	public User(String userName, String passWord) {
		this.userName = userName;
		this.passWord = passWord;
	}
 

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public List<Role> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<Role> userRoles) {
		this.userRoles = userRoles;
	}

	@Override
	public String getAuthCacheKey() {
		return getUserName();
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", passWord=" + passWord + ", salt=" + salt
				+ ", cTime=" + cTime + ", userRoles=" + userRoles + "]";
	}
	
	
}
