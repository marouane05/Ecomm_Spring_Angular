package com.ecom.dtos;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
public class UserDto {
	
	
	private long  userId;

	@NotNull(message="username should not be null")
    private String userName;

	@NotNull(message="password should not be null")
    private String password;

	@NotNull(message="role should not be null")
    private String role;

	@NotNull(message="isActive should not be null")
    private long isActive;
    
  
    String jwtToken;
}
