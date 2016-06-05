package com.paccounting.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paccounting.dao.EntityDao;
import com.paccounting.entities.User;
import com.paccounting.model.NewUser;
import com.paccounting.security.PasswordEncoder;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private EntityDao dao;
	public boolean addUser(NewUser newUser) {
		if(newUser.getMob()!=null)
		{
			User user=dao.getObject(User.class,newUser.getMob());
			if(user!=null)
			{
				System.out.println("Existing user");
				return false;
			}
			else
			{
				user=new User();
				user.setMob(newUser.getMob());
				user.setName(newUser.getName());
				user.setPass(PasswordEncoder.md5Encrypt(newUser.getPass()));
				dao.saveEntity(user);
				return true;
			}
		}
		return false;
	}

}
