package com.ecom.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LoginForm {
	
	@NotNull(message="username should not be null")
	String username;
	
	@NotNull(message="password should not be null")
	String Password ;
}
