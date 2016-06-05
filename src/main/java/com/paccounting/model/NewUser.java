package com.paccounting.model;

public class NewUser {
	
	private String mob;
	private String name;
	private String pass;
	public String getMob() {
		return mob;
	}
	public void setMob(String mob) {
		if(mob.length()==10&&isIntegerRegex(mob))
		{
			this.mob = mob;
		}
		else
		{
			throw new IllegalArgumentException();
		}

	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	 public static boolean isIntegerRegex(String str) {
	        return str.matches("^[0-9]+$");
	 }
	

}
