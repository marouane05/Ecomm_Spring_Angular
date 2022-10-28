package com.ecom.config.auth.security;


import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.ecom.exceptions.auth.InvalidTokenException;
import com.ecom.exceptions.auth.markdownTokenAuthException;
import com.ecom.repository.UserRepository;
import com.ecom.service.TokenService;


@Component
public class MarkdownAuthProvider extends AbstractUserDetailsAuthenticationProvider{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired TokenService tokenService ; 
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException{
		
	}
	
	@Override 
	protected UserDetails retrieveUser(String username,UsernamePasswordAuthenticationToken authenticationToken)throws AuthenticationException{
		
		final String token = (String) authenticationToken.getCredentials();
		
		if(token.isEmpty()) {
		//	return new User(username,,token, AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS"));
		return new User("username","",AuthorityUtils.createAuthorityList("Role_anonymous"));
		}
		//userDao.findByJwtToken(token);
	
		Optional<com.ecom.model.User> markdownUserModelOptional = userRepository.findByJwtToken(token);
		
		if(markdownUserModelOptional.isPresent()) {
			
			final com.ecom.model.User UserModel = markdownUserModelOptional.get();
			
			try {
			tokenService.validateToken(UserModel.getJwtToken());
			
			} catch(InvalidTokenException e) {
				
				UserModel.setJwtToken(null);
				userRepository.save(UserModel);
			}
			
			//temporarty
			
			String[] RoleArr = new String [5];
			RoleArr[0]="ROLE_"+UserModel.getRole();
			
			/*return new User("username","",
				
			
					AuthorityUtils.createAuthorityList(
							UserModel.getRole().stream()
							.map(rolename->"ROLE_"+rolename)
							.toArray(String[]::new)
							)); */
			
			return new User("username","",AuthorityUtils.createAuthorityList(RoleArr));
			
		}
		
		
		throw new markdownTokenAuthException("user not found for token"+token);
	}

}
