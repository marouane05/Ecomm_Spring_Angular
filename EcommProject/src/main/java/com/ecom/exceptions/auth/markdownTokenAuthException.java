package com.ecom.exceptions.auth;

import org.springframework.security.core.AuthenticationException;

public class markdownTokenAuthException extends AuthenticationException {

	public markdownTokenAuthException(String s){
		super(s);
	}
	
}
