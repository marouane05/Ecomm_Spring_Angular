package com.ecom.config.auth.security;


import java.io.IOException;

import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.util.StringUtils;
//import org.apache.commons.lang.StringUtils;

import net.minidev.json.JSONObject;

public class AuthFilter extends AbstractAuthenticationProcessingFilter{
	
	public static final int SC_FORBIDDEN=403;
	
	public AuthFilter(OrRequestMatcher orRequestMatcher) {
		super(orRequestMatcher);
	}
	
	/*protected AutFilter(RequestMatcher requireAuthenticationRequestMatcher) {
		super(requireAuthenticationRequestMatcher);
	}*/
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,HttpServletResponse response)
	throws AuthenticationException, IOException, ServletException{
		String tokenUnstripped = request.getHeader("Authorization");
		//String token = StringUtils.removeStart(Optional.ofNullable(token).orElse(""),"Bearer").trim();
		String token = tokenUnstripped.replace("Bearer","");
		
		Authentication authentication;
		if(token.isEmpty()) {
			authentication = new UsernamePasswordAuthenticationToken("guest","");
			
		} else {
			authentication = new UsernamePasswordAuthenticationToken("user",token);
		}
		
		return getAuthenticationManager().authenticate(authentication);
	}
	
	@Override 
	protected void successfulAuthentication(HttpServletRequest request,HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException,ServletException{

		SecurityContextHolder.getContext().setAuthentication(authResult);
		chain.doFilter(request, response);
	}
	
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request,HttpServletResponse response,AuthenticationException failed) throws IOException ,ServletException{
		response.setStatus(SC_FORBIDDEN);
		JSONObject jsonObject= new JSONObject();
		
		jsonObject.put("error", failed.getCause());
		jsonObject.put("errorMessage", failed.getMessage());
		
		response.getWriter().print(jsonObject);
		response.getWriter().flush();
	}
	
}