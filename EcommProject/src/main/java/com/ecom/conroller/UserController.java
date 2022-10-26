package com.ecom.conroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dtos.UserDto;
import com.ecom.service.UserService;



@RestController
@RequestMapping("/")
public class UserController {
	
	@Autowired UserService userService;
	
	@PostMapping("/")
	UserDto CreateUser(UserDto userDto) {
		
		return userService.createUser(userDto);
	}
	
	@GetMapping("/h")
	Map<String,String> msg(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("nom", "marouane");
		return map;
	}

}
