package com.altran.hackaton.be.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUser extends User {
		
	private static final long serialVersionUID = -4524107535458315326L;
	
	private String token;

	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities, String token) {
		super(username, password, authorities);
		this.token = token;
	}

	public String getToken() {
		return token;
	}
	
	
}
