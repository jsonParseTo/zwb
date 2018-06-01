package com.zwb.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zwb.Expection.msgException;
import com.zwb.entity.Role;
import com.zwb.entity.User;
import com.zwb.service.UserService;
import com.zwb.token.util.EncryptUtil;
import com.zwb.token.util.Response;

@RestController
@RequestMapping("/user")
public class UserAction {

	@Resource
	private UserService userService;
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public Response register(User user,HttpServletRequest request,HttpServletResponse rep) throws Exception{
		Response reponse= new Response();
		user = EncryptUtil.EncryptUser(user);
		try{
	    	userService.addUser(user);
	    }catch(Exception e){
	    	return reponse.failure(e.getMessage());
	    }
		return reponse.success();
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public Response login(User user) throws Exception{
		Response reponse= new Response();
		String username = user.getUserName();
		UsernamePasswordToken token = new UsernamePasswordToken(username, user.getPassWord());
		token.setRememberMe(true);
		//获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            //在调用了login方法后，SecurityManager会收到AuthenticationToken，并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时，它会走到MyRealm.doGetAuthenticationInfo()方法中，具体验证方式详见此方法
            currentUser.login(token);
        }catch(UnknownAccountException uae){
            System.out.println("对用户[" + username + "]进行登录验证...验证未通过，未知账户");
            return reponse.success("用户名错误");
        }catch(IncorrectCredentialsException ice){
            System.out.println("对用户[" + username + "]进行登录验证...验证未通过，错误的凭证");
            return reponse.success("密码错误");
        }catch(LockedAccountException lae){
            System.out.println("对用户[" + username + "]进行登录验证...验证未通过，账户已锁定");
            return reponse.success("账户已锁定");
        }catch(ExcessiveAttemptsException eae){
            System.out.println("对用户[" + username + "]进行登录验证...验证未通过，错误次数过多");
            return reponse.success("用户名或密码错误次数过多");
        }catch(AuthenticationException ae){
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            System.out.println("对用户[" + username + "]进行登录验证...验证未通过，堆栈轨迹如下");
            ae.printStackTrace();
            return reponse.success("用户名或密码不正确");
         }
        //验证是否登录成功
        if(currentUser.isAuthenticated()){
            System.out.println("用户[" + username + "]登录认证通过（这里可进行一些认证通过后的系统参数初始化操作）");
            User ruser = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
            ruser.setPassWord(null);
            List<Role> roles = ruser.getUserRoles();
            return reponse.success(ruser);
        }else{
            token.clear();
            return reponse.success("用户名或密码错误");
        }
            
//		User currentuser = userService.login(user);
//		if(null == currentuser){
//			//throw new msgException("用户名或密码错误");
//			reponse.failure("用户名或密码错误");
//			return reponse;
//		}
//	
//		String username = currentuser.getUserName();
//		//token返回结果
////		String token = DefaultTokenManager.createTokenByJjwt(username);
////		reponse.success(token);
//		
//		//cookie
//		String sessionId = req.getSession().getId();
//		Constant.CookieMap.put(sessionId, username);
//		reponse.success();
//		return reponse;
	}
	
	@RequestMapping(value="/findAllUser",method=RequestMethod.GET)
	public Response findAllUser() throws msgException{
		Response reponse= new Response();
		try {
			User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
		List<User> userList = new ArrayList<User>();
		if(("zwb").equals(user.getUserName()))
			userList = userService.listAllUser();
			reponse.success(userList);
		}catch(Exception e) {
			reponse.failure(e.getMessage());
		}
		return reponse;
	}
	
	
}
