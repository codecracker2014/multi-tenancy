package com.paccounting.security;

public class OtpInnerResponse {

	private String code;
	private int oneTimePassword;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getOneTimePassword() {
		return oneTimePassword;
	}
	public void setOneTimePassword(int oneTimePassword) {
		this.oneTimePassword = oneTimePassword;
	}
	
}
