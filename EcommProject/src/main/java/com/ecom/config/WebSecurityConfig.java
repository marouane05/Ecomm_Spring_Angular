package com.ecom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.ecom.config.auth.security.MarkdownAuthProvider;


//@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired MarkdownAuthProvider markdownAuthProvider;
	/*
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	*/
	
		@Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.csrf().disable();
	      /*  http
	        .httpBasic()
	      .and()
	        .authorizeRequests()
	          .antMatchers("/index.html", "/", "/home", "/login","/token/validate").permitAll()
	          .anyRequest().authenticated()
	          .and().csrf().disable(); */
	        
	      /*  http
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().authenticationProvider(markdownAuthProvider)
			.and()
			.addFilterBefore(null, AnonymousAuthenticationFilter.class)
			.authorizeRequests()
			.anyRequest().authenticated().and().csrf().
			 disable()
			.httpBasic().disable().logout().disable().cors();*/
	        
	    }
}
