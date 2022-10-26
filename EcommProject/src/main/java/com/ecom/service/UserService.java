package com.ecom.service;

import com.ecom.dtos.UserDto;



public interface UserService {

	UserDto createUser(UserDto u);
	
	UserDto getUser(Long id);
	
	UserDto getUserByName(String username);
	UserDto loginUser(UserDto loginDto);
}
