package com.ecom.dtos;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LoginFormDto {
	
	@NotNull(message="username should not be null")
	String username;
	@NotNull(message="password should not be null")
	String password ;
}
