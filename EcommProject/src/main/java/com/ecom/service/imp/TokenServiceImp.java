package com.ecom.service.imp;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ecom.exceptions.auth.InvalidTokenException;
import com.ecom.model.User;
import com.ecom.service.AuthSigningKeyResolver;
import com.ecom.service.TokenService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.SignatureException;

@Service
public class TokenServiceImp implements TokenService {
	

	
	@Value("${jwt.secret.key}")
	String Secret ;
	
	
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
						.setIssuedAt(Date.from(Instant.ofEpochSecond(1466796822L)))
						.setExpiration(Date.from(Instant.ofEpochSecond(4622470422L)))
						//.signWith(SignatureAlgorithm.HS256, "marouaneproject".getBytes("UTF-8"))
						.signWith(SignatureAlgorithm.HS256, Secret)
						.compact();
				UserModel.setJwtToken(jwtToken);
			
			
		

		
		
		
		
	}
	
}