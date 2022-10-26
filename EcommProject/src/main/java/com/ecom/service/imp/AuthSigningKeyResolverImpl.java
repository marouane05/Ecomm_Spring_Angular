package com.ecom.service.imp;

import java.security.Key;
import java.util.Base64;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ecom.service.AuthSigningKeyResolver;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.security.Keys;

@Service
public class AuthSigningKeyResolverImpl implements AuthSigningKeyResolver {

	@Value("${jwt.secret.key}")
	String secretkeyString;
	
	private SecretKey secretKey;
	
	@Override
	public Key resolveSigningKey(JwsHeader header, Claims claims) {
		// TODO Auto-generated method stub
		return getSercretkey();
	}

	@Override
	public Key resolveSigningKey(JwsHeader header, String plaintext) {
		// TODO Auto-generated method stub
		return getSercretkey();
	}

	@Override
	public SecretKey getSercretkey() {
		// TODO Auto-generated method stub
		
		if(secretKey==null) {
			this.secretKey = Keys.hmacShaKeyFor(Base64.getEncoder().encode(this.secretkeyString.getBytes()));
			
		}
		return this.secretKey;
		}

}
