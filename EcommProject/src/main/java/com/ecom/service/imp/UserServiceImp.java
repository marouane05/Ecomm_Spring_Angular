package com.ecom.service.imp;

import java.util.Optional;
import java.util.logging.Logger;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecom.dtos.LoginFormDto;
import com.ecom.dtos.UserDto;
import com.ecom.model.LoginForm;
import com.ecom.model.User;
import com.ecom.repository.UserRepository;
import com.ecom.service.UserService;
import com.ecom.service.TokenService;

//import org.apache.logging.log4j.Logger;

@Service
public class UserServiceImp implements UserService {

	@Autowired ModelMapper modelMapper;
	@Autowired BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired TokenService tokenService; 
	@Autowired UserRepository userRepository;
	private final String UNKNOWN_USERNAME_OR_BAD_PASSWORD = "unkwown username or bad password";
	private static Logger logger = Logger.getLogger(UserServiceImp.class.getName());
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		// map User dto to user Model
		User userModel = modelMapper.map(userDto, User.class);
		
		userModel.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		tokenService.generateToken(userModel);
		String msg = "voilà le token: "+userModel.getJwtToken();
		logger.info(msg);
		System.out.print(msg);
		userRepository.save(userModel);
		//Map userModel to user Dto
		
		modelMapper.map(userModel, userDto);
		

		//dto input & output
		return userDto;
	}
	
	@Override
	public UserDto getUser(Long id) {
		// TODO Auto-generated method stub
		
		Optional<User> userModel = userRepository.findById(id);
		
		if(userModel.isPresent()) {
			return modelMapper.map(userModel.get(), UserDto.class);
		}
		return null;
	}
	
	
	
	@Override
	public UserDto loginUser(LoginFormDto loginFormDto) {
		
		LoginForm login = modelMapper.map(loginFormDto, LoginForm.class);
		
		Optional<User> optionalUserModel=userRepository.findByUserName(login.getUsername());
		
		if(optionalUserModel.isPresent()) {
			
			User userModel = optionalUserModel.get();
			
			if(bCryptPasswordEncoder.matches(login.getPassword(), userModel.getPassword())) {
				return modelMapper.map(userModel, UserDto.class);
			} else {
				throw new BadCredentialsException(UNKNOWN_USERNAME_OR_BAD_PASSWORD);
			} 
		}else {
			
			throw new BadCredentialsException(UNKNOWN_USERNAME_OR_BAD_PASSWORD);
		}
	}
	
	@Override
	public UserDto getUserByName(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
/*	@Override 
	public UserDto loginUser(UserDto userDto) {
		
		//Optional<User> optionalUserModel= userRepository.findByUserName(userDto.getUsername());
		
		if(optionalUserModel.isPresent()) {
			
			User mUserModel= optionalUserModel.get();
			
			if(bCryptPasswordEncoder.matches(loginDto.getPassword(), markdownUserModel.getPassword())) {
				
				return modelMapper.map(markdownUserModel, UserInfoDto.class);
				
			} else {
				
				throw new BadCredentialsException(UNKNOWN_USERNAME_OR_BAD_PASSWORD);
				
			}
		} else {
			
			throw new BadCredentialsException(UNKNOWN_USERNAME_OR_BAD_PASSWORD);
		}
	
	}

	@Override
	public UserInfoDto getUserByName(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	*/
	
	
}
