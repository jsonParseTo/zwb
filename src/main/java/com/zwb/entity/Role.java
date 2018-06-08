package com.zwb.entity;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Integer roleId;
	private String roleName;
	private List<Permission> rolePermissions;
	
	public Role() {
	}

	
	public Integer getRoleId() {
		return roleId;
	}


	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}


	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<Permission> getRolePermissions() {
		return rolePermissions;
	}

	public void setRolePermissions(List<Permission> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}


	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", rolePermissions=" + rolePermissions + "]";
	}
	
	
}
