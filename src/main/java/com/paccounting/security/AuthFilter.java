package com.paccounting.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.filter.GenericFilterBean;


public class AuthFilter extends GenericFilterBean{

	@Autowired
	@Qualifier("authenticationService")
	private AuthenticationService authenticationService;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
			System.out.println("Request in filter");
			String pass="123456";
			    
/*			String authkey = "115131AV77EXUHM5755c3b5";
			//Multiple mobiles numbers separated by comma
			String mobiles = "7791897645";
			//Sender ID,While using route4 sender id should be 6 characters long.
			String senderId = "102234";
			//Your message to send, Add URL encoding here.
			String message = "Test message";
			//define route
			String route="default";

			//Prepare Url
			URLConnection myURLConnection=null;
			URL myURL=null;
			BufferedReader reader=null;

			//encoding message 
			String encoded_message=URLEncoder.encode(message);

			//Send SMS API
			String mainUrl="https://control.msg91.com/api/sendhttp.php?";

			//Prepare parameter string 
			StringBuilder sbPostData= new StringBuilder(mainUrl);
			sbPostData.append("authkey="+authkey); 
			sbPostData.append("&mobiles="+mobiles);
			sbPostData.append("&message="+encoded_message);
			sbPostData.append("&route="+route);
			sbPostData.append("&sender="+senderId);
			    
			//final string
			mainUrl = sbPostData.toString();
			try
			{
			    //prepare connection
			    myURL = new URL(mainUrl);
			    myURLConnection = myURL.openConnection();
			    myURLConnection.connect();
			    reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
			    //reading response 
			    String respons;
			    while ((respons = reader.readLine()) != null) 
			    //print response 
			    System.out.println(respons);
			    
			    //finally close connection
			    reader.close();
			} 
			catch (IOException e) 
			{ 
				e.printStackTrace();
			} 
			
*/			
			System.out.println(PasswordEncoder.md5Encrypt(pass));
			String url=((HttpServletRequest)request).getRequestURI().toString();
			
			MultiReadHttpServletRequest httpServletRequest=new MultiReadHttpServletRequest((HttpServletRequest)request);
			System.out.println("*"+url+"*");
			if("/paccounting/register".equals(url)||"/paccounting/register/".equals(url)||"/paccounting/verify".equals(url)||"/paccounting/verify/".equals(url))
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
