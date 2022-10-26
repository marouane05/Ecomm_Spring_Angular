package com.ecom.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.exceptions.auth.InvalidTokenException;
import com.ecom.model.User;
import com.ecom.service.AuthSigningKeyResolver;
import com.ecom.service.TokenService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;

@Service
public class TokenServiceImp implements TokenService {
	
	@Autowired 
	AuthSigningKeyResolver authSigningKeyResolver;

	@Override
	public void validateToken(String jwtToken) throws InvalidTokenException  {
		
		try {
		
		Jwts.parserBuilder().setSigningKeyResolver(authSigningKeyResolver)
		.build().parse(jwtToken);
		
		} catch(ExpiredJwtException| MalformedJwtException | SignatureException | IllegalArgumentException e) {
			
			throw new InvalidTokenException("invalid token ", e);
		}
		
	};
	
	@Override
	public void generateToken(User UserModel) {
		
		String jwtToken ; 
		
		jwtToken = Jwts.builder().setSubject(UserModel.getUserName())
				.setAudience(UserModel.getRole().toString())
				.compact();
		
		UserModel.setJwtToken(jwtToken);
	
	}
	
}