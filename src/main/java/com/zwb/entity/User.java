package com.zwb.entity;

import java.util.Date;
import java.util.List;

public class User{
	/**
	 * 
	 */
	private Integer userId;
	private String userName;
	private String passWord;
	private String salt;
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
	
	
}
