package com.paccounting.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paccounting.dao.EntityDao;
import com.paccounting.entities.TempUser;
import com.paccounting.entities.User;
import com.paccounting.model.NewUser;
@Service
public class PhoneVerificationService {
	
	@Autowired
	private EntityDao dao;
	public int sendOtp(String countryCode,NewUser user)
	{
		
		
		User user2=dao.getObject(User.class, user.getMob());
		if(user2!=null){
			return 1;
		}
		int otp=SendOTP.generateOTP(countryCode, user.getMob());
		if(otp>-1)
		{   
			TempUser tempUser1=dao.findTempUser(user.getMob());
			if(tempUser1==null)
			{
				TempUser tempUser=new TempUser();
				tempUser.setMob(user.getMob());
				tempUser.setOtp(otp);
				tempUser.setName(user.getName());
				tempUser.setPass(user.getPass());
				dao.saveEntity(tempUser);
			}
			else
			{
				tempUser1.setOtp(otp);
				dao.updateEntity(tempUser1);
			}
			return 0;
		}
		else
		{
			return 2;
		}
	}
	public boolean verify(NewUser newUser) {
		
		TempUser tempUser=dao.findTempUser(newUser.getMob());
		if(tempUser!=null&&tempUser.getOtp()==newUser.getOtp())
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	

}
