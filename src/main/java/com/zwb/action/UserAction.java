package com.zwb.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zwb.Expection.msgException;
import com.zwb.entity.User;
import com.zwb.service.UserService;
import com.zwb.token.util.DefaultTokenManager;
import com.zwb.token.util.Response;

@RestController
@RequestMapping("/user")
public class UserAction {

	@Resource
	private UserService userService;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public Response login(User user,HttpServletRequest req,HttpServletResponse rep) throws Exception{
		Response reponse= new Response();
		User currentuser = userService.login(user);
		if(null == currentuser){
			//throw new msgException("用户名或密码错误");
			reponse.failure("用户名或密码错误");
			return reponse;
		}
//		String username = currentuser.getUsername();
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("username", username);
//		map.put("token", DefaultTokenManager.createTokenByJjwt(username));
//		reponse.success(map);
//		Cookie cookie = new Cookie("JSESSIONID", req.getSession().getId());
//		cookie.setMaxAge(1000*5);
//		rep.addCookie(cookie);
		reponse.success();
		return reponse;
	}
	
	@RequestMapping(value="/findAllUser",method=RequestMethod.GET)
	public Response findAllUser(HttpServletRequest request) throws msgException{
		Response reponse= new Response();
		try {
		String user = (String) request.getSession().getAttribute("user");
		List<User> userList = new ArrayList<User>();
		if(("zwb").equals(user))
			userList = userService.listAllUser();
			reponse.success(userList);
		}catch(Exception e) {
			reponse.failure(e.getMessage());
		}
		return reponse;
	}
	
	
}
