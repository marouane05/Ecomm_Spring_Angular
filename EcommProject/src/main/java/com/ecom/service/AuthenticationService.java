package com.ecom.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecom.model.User;
import com.ecom.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService{

	@Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

	@Override 
	public UserDetails loadUserByUsername(String username) {
		
		Optional<User> OptionalUser = userRepository.findByUserName(username);
		
		
		if(OptionalUser.isPresent()) {
			
			User user = OptionalUser.get();
			
			
			UserBuilder builder = org.springframework.security.core.userdetails.User
	  				.withUsername(username);
			
			builder.password(bCryptPasswordEncoder.encode(user.getPassword()));
			builder.roles(user.getRole());
			
			return builder.build();

			
		
		} 
		
	
			
		throw new UsernameNotFoundException(username);
		
		
		
		//here we are using org.springframework.... because we have 2 User declaration
		
	}
	
	
	
}
