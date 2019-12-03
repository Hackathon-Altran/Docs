package com.altran.hackaton.be.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.altran.hackaton.be.dao.CarRepository;
import com.altran.hackaton.be.dao.UserRepository;
import com.altran.hackaton.be.security.JWTAuthenticationFilter;
import com.altran.hackaton.be.security.JWTLoginFilter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private static Logger logger = LogManager.getLogger(WebSecurityConfiguration.class);
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CarRepository carRepo;
	
	private com.altran.hackaton.be.security.UserDetailsService userDetailsService;

	private PasswordEncoder encoder;
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		logger.info("configure");
		
		httpSecurity
			.csrf().disable()
			.authorizeRequests()				
				.antMatchers("/resources/**").permitAll()
				.antMatchers(HttpMethod.POST, "/api/users/login").permitAll()			
				.anyRequest().authenticated()
				//.anyRequest().permitAll()
			.and()
			.formLogin().disable()			
			.logout().permitAll()
			.and()
			
			// filtra requisições de login
			.addFilterBefore(new JWTLoginFilter("/api/users/login", authenticationManager(), carRepo),
	                UsernamePasswordAuthenticationFilter.class)
			
			// filtra outras requisições para verificar a presença do JWT no header
			.addFilterBefore(new JWTAuthenticationFilter(),
	                UsernamePasswordAuthenticationFilter.class);
		
//		httpSecurity.authorizeRequests().anyRequest().permitAll();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// cria uma conta default
//		auth.inMemoryAuthentication()
//			.withUser("admin")
//			.password("{noop}password")
//			.roles("ADMIN");
		
		auth.userDetailsService(userDetailsService())
			.passwordEncoder(encoder());
	}
	
	@Bean
	public PasswordEncoder encoder() {
		if (encoder == null) {
			encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		}
		return encoder;
	}

	@Bean
	public UserDetailsService userDetailsService() {
		if (userDetailsService == null) {
			this.userDetailsService = new com.altran.hackaton.be.security.UserDetailsService(
					userRepo, carRepo, encoder()); 
		}
		return userDetailsService;
	}
	
}
