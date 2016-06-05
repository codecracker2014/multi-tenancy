package com.paccounting.security;

import java.io.IOException;
import java.security.MessageDigest;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;


public class AuthFilter extends GenericFilterBean{

	@Autowired
	@Qualifier("authenticationService")
	private AuthenticationService authenticationService;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
			System.out.println("Request in filter");
			String pass="123456";
			System.out.println(PasswordEncoder.md5Encrypt(pass));
			String url=((HttpServletRequest)request).getRequestURI().toString();
			System.out.println(url);
			MultiReadHttpServletRequest httpServletRequest=new MultiReadHttpServletRequest((HttpServletRequest)request);
			if("/paccounting/register".equals(url))
			{
				System.out.println("request for add new user");
				chain.doFilter(request, response);
			}
			else{
				if(authenticationService.authenticate(httpServletRequest))
				{
					System.out.println("Authorised passed");
					chain.doFilter(httpServletRequest, response);
				}
				else{
					System.out.println("failed auth");
					((HttpServletResponse)response).sendError(HttpServletResponse.SC_FORBIDDEN, "authentication failed");
				}
			}
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	

}
