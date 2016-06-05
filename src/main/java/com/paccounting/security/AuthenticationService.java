package com.paccounting.security;

import javax.servlet.ServletRequest;

public interface AuthenticationService {
	
	public boolean authenticate(ServletRequest request);

}
