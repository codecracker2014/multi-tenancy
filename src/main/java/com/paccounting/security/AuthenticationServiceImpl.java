package com.paccounting.security;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.paccounting.dao.EntityDao;
import com.paccounting.entities.User;
import com.paccounting.model.InputNotification;
import com.paccounting.services.UserService;

@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService{

	
	@Autowired
	private EntityDao dao;
	public boolean authenticate(ServletRequest request) {

		try {
			ObjectMapper mapper=new ObjectMapper();
			
			InputNotification inputNotification=mapper.readValue(request.getReader(), InputNotification.class);
			InputNotification inputNotification1=mapper.readValue(request.getInputStream(), InputNotification.class);
			System.out.println("Got user with+"+inputNotification.getSender().getMob());
			System.out.println("n2"+inputNotification1.getSender().getMob());
			User sender=inputNotification.getSender();
			User user =dao.getObject(User.class, sender.getMob());
			if(user==null)
				return false;
			if(user.getPass().equals(PasswordEncoder.md5Encrypt(sender.getPass())))
			{
				return true;
			}
			else
			{
				return false;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}


}
