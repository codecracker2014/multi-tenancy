package com.paccounting.security;

public class OtpResponse {
	private String status;
	private OtpInnerResponse response;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public OtpInnerResponse getResponse() {
		return response;
	}
	public void setResponse(OtpInnerResponse response) {
		this.response = response;
	}
	
	

}
