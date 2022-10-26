package com.ecom.service;

import com.ecom.model.User;
import com.ecom.exceptions.auth.InvalidTokenException;

public interface TokenService {

	void validateToken(String jwtToken) throws InvalidTokenException;
	void generateToken(User markdownUserModel);

}
