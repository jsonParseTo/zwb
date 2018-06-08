package com.zwb.token.util;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.zwb.entity.Permission;
import com.zwb.entity.Role;
import com.zwb.entity.User;
import com.zwb.mapper.RoleMapper;
import com.zwb.mapper.UserMapper;

public class MyRealm extends AuthorizingRealm {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//获取当前用户
	       User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
	       if(user==null) return null;
	      //把principals放session中，key=userId value=principals
	      //SecurityUtils.getSubject().getSession().setAttribute(String.valueOf(user.getUserId()),SecurityUtils.getSubject().getPrincipals());

	      SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
	      //赋予角色
	      if(null!=user.getUserRoles()&&user.getUserRoles().size()>0){
	    	  for(Role role:user.getUserRoles()){
	    		  info.addRole(role.getRoleName());
	    		  //赋予权限
	    		  for(Permission permission:role.getRolePermissions()){
		          //System.out.println(permission.getName());
	        	  info.addStringPermission(permission.getPermissionName());
	    		  }
	    	  }
	      }
	      return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		  UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
	      String userName = token.getPrincipal().toString();
	      User user = userMapper.findUserByUsername(userName);
	      if(user != null){
	          //登陆成功
	    	  List<Role> roles = roleMapper.findRoleByUserId(user.getUserId());
	    	  user.setUserRoles(roles);
	    	  Session session = SecurityUtils.getSubject().getSession();
	          session.setAttribute("user",user);
	           return new SimpleAuthenticationInfo(
	        		  user, //用户
	                  user.getPassWord(), //密码
	                  ByteSource.Util.bytes(userName),
	                  getName() //realm name
	          );
	      } else {
	          throw new UnknownAccountException();
	      }
	}

}
