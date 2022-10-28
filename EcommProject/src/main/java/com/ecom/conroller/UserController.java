package com.ecom.conroller;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dtos.LoginFormDto;
import com.ecom.dtos.UserDto;
import com.ecom.service.UserService;
import java.util.logging.Logger;


@RestController
@RequestMapping("/")
public class UserController {
	
	@Autowired UserService userService;
	
	Logger logger
    = Logger.getLogger(UserController.class.getName());
	
	//@PostMapping(value="/",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@PostMapping(value="/")
	UserDto CreateUser(UserDto userDto) {
		
		return userService.createUser(userDto);
	}
	
	@PostMapping("/login")
	UserDto Login(@RequestBody LoginFormDto loginFormDto) {
		
		logger.info("our login is : "+loginFormDto.getUsername());
		
		return userService.loginUser(loginFormDto);
	}
	
	@GetMapping("/h")
	Map<String,String> msg(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("nom", "marouane");
		return map;
	}

}
