package com.zwb.filter;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zwb.token.util.DefaultTokenManager;
import com.zwb.util.Constant;

/**
 * Servlet Filter implementation class TokenFilter
 */
@WebFilter("/TokenFilter")
public class TokenFilter implements Filter {

    /**
     * Default constructor. 
     */
    public TokenFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		HttpServletRequest hrequest = (HttpServletRequest)request;
		HttpServletResponse hresponse = (HttpServletResponse)response;
		//允许跨域请求中携带cookie		
		hresponse.addHeader("Access-Control-Allow-Credentials", "true");
//		if("OPTIONS".equals(hrequest.getMethod())) {
//		      //放行OPTIONS请求
//			chain.doFilter(request, response);
//		      return;
//		  }
		if(hrequest.getRequestURI().endsWith("/user/login")){
			chain.doFilter(hrequest, hresponse);
			return;
		}
		
//		String token = hrequest.getHeader(Constant.AUTH_TOKEN);
//		String user = DefaultTokenManager.verifyTokenOfJjwt(token);
//		hrequest.getSession().setAttribute("user", user);
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
