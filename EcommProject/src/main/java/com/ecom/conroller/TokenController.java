package com.ecom.conroller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.service.TokenService;



@RestController
@RequestMapping("/token")
public class TokenController {

	
	
	

		@Autowired TokenService tokenService;
		
		@PostMapping("/validate")
		public void validateForm(HttpServletRequest httpServletRequest ) throws Exception {
			
			
			String authHeader = httpServletRequest.getHeader("Authorization");
			String token=null;
			if(!(authHeader).isEmpty()) {
				
			 token = authHeader.split("\\s")[1];
				
				
			}
			
			tokenService.validateToken(token);
	
		}
		
		
		@PostMapping("/valid")
		Map<String,String> msg(){
			Map<String,String> map = new HashMap<String,String>();
			map.put("nom", "marouane");
			return map;
		}

	

}
