package com.paccounting.security;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;

public class PasswordEncoder {
	
	public static String md5Encrypt(String input)
	{
		String md5=null; 
		if(input==null)
			return null;
		try{
			MessageDigest digest=MessageDigest.getInstance("MD5");
			digest.update(input.getBytes(),0,input.length());
			md5=new BigInteger(1,digest.digest()).toString(16);
		}catch(Exception e)
		{
			e.printStackTrace();
				
		}
		return md5;
	}

}
