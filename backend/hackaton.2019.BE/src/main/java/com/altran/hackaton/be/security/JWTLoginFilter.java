package com.altran.hackaton.be.security;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.altran.hackaton.be.dao.CarRepository;
import com.altran.hackaton.be.model.Car;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
	
	Logger logger = LoggerFactory.getLogger(JWTLoginFilter.class);
	
	private CarRepository carRepo;
	
	public JWTLoginFilter(String url, AuthenticationManager authManager, CarRepository carRepo) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
		this.carRepo = carRepo;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		logger.info("JWTLoginFilter/attemptAuthentication");
		
		logger.info(request.getRequestURL().toString());
		
		AccountCredentials credentials =  new AccountCredentials();
		credentials.setUsername((String) request.getParameter("user"));
		credentials.setPassword((String) request.getParameter("pwd"));
				
		Authentication authentication = getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(
						credentials.getUsername(), 
						credentials.getPassword(), 
						Collections.emptyList()
						)
				);
		
		if (authentication.isAuthenticated()) {
			Car car = carRepo.findByUsername(credentials.getUsername());
			if (car != null) {
				Map<String, String> responseBody = new HashMap<String, String>();
				responseBody.put("token", car.getToken());
				response.getWriter()
					.write(
							new ObjectMapper()
								.writerWithDefaultPrettyPrinter()
								.writeValueAsString(responseBody));				
			}
		}
		
		return authentication;
	}
	
	@Override
	protected void successfulAuthentication(
			HttpServletRequest request, 
			HttpServletResponse response,
			FilterChain filterChain,
			Authentication auth) throws IOException, ServletException {
		
		TokenAuthenticationService.addAuthentication(response, auth.getName());
	}

}
