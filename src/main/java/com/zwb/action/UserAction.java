package com.zwb.action;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mysql.jdbc.Security;
import com.zwb.Expection.msgException;
import com.zwb.entity.Role;
import com.zwb.entity.User;
import com.zwb.jackson.util.XunerJsonFilter;
import com.zwb.jackson.util.XunerJsonFilters;
import com.zwb.service.UserService;
import com.zwb.token.util.EncryptUtil;
import com.zwb.token.util.Response;

@RestController(value="userAction")
@RequestMapping("/user")
public class UserAction {

	@Resource
	private UserService userService;
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public Response register(User user,HttpServletRequest request,HttpServletResponse rep) throws Exception{
		Response reponse= new Response();
		if(!user.getImage().isEmpty()){
		String path = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/images");
		  //上传文件名
        String filename = user.getImage().getOriginalFilename();
        File filepath = new File(path,filename);
        //判断路径是否存在，如果不存在就创建一个
        if (!filepath.getParentFile().exists()) { 
            filepath.getParentFile().mkdirs();
        }
        //将上传文件保存到一个目标文件当中
        user.getImage().transferTo(new File(path + File.separator + filename));
        }
		user = EncryptUtil.EncryptUser(user);
		try{
	    	userService.addUser(user);
	    }catch(Exception e){
	    	return reponse.failure(e.getMessage());
	    }
		return reponse.success();
	}
	
	@RequestMapping(value="/toLogin")
	public Response register() throws Exception{
		Response reponse= new Response();
		return reponse.success("请先登录");
	}
	
	@RequestMapping(value="/unauthor")
	public Response unauthor() throws Exception{
		Response reponse= new Response();
		return reponse.success("暂无权限");
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@XunerJsonFilters(value = { @XunerJsonFilter(mixin=User.class,target={"salt"}) })
	public Response login(User user) throws Exception{
		Response reponse= new Response();
		String username = user.getUserName();
		UsernamePasswordToken token = new UsernamePasswordToken(username, user.getPassWord());
		//token.setRememberMe(true);
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
        	System.out.println("sessionId: "+currentUser.getSession().getId());
        	 System.out.println("用户[" + username + "]登录认证通过（这里可进行一些认证通过后的系统参数初始化操作）");
            User ruser = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
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
	
	@RequestMapping(value="/loginout",method=RequestMethod.POST)
	public Response loginOut() throws Exception{
		Response reponse= new Response();
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return reponse.success();
		
	}
	
	//@RequiresRoles(value={})
	@RequestMapping(value="/findAllUser",method=RequestMethod.GET)
	public Response findAllUser() throws msgException{
		Response reponse= new Response();
		try {
			System.out.println("sessionId:  "+SecurityUtils.getSubject().getSession().getId());
	   List<User> userList = new ArrayList<User>();
			userList = userService.listAllUser();
			reponse.success(userList);
		}catch(Exception e) {
			reponse.failure(e.getMessage());
		}
		return reponse;
	}
	
	@RequestMapping(value="/download",method=RequestMethod.GET)
	public ResponseEntity<byte[]>  download() throws Exception{
		 //下载文件路径
		String filename = "60432d7f9e2f07081bf7f5a9e224b899a801f23b.jpg";
        String path = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/images/");
        File file = new File(path + File.separator + filename);
        HttpHeaders headers = new HttpHeaders();  
        //下载显示的文件名，解决中文名称乱码问题  
        String downloadFielName = new String(filename.getBytes("UTF-8"),"iso-8859-1");
        //通知浏览器以attachment（下载方式）打开图片
        headers.setContentDispositionFormData("attachment", downloadFielName); 
        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
                headers, HttpStatus.OK);  
	}
	
	
}
