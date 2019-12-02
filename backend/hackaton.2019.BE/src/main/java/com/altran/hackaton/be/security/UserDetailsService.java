package com.altran.hackaton.be.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.altran.hackaton.be.dao.CarRepository;
import com.altran.hackaton.be.dao.UserRepository;
import com.altran.hackaton.be.model.Car;
import com.altran.hackaton.be.model.User;

public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
	
	private UserRepository userRepo;
	
	private CarRepository carRepo;
	
	private PasswordEncoder encoder;
	
	public UserDetailsService(UserRepository userRepo, CarRepository carRepo, PasswordEncoder encoder) {		
		this.userRepo = userRepo;
		this.carRepo = carRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepo.findByLogin(username);
		if (user != null) {						
			
			return org.springframework.security.core.userdetails.User
					.withUsername(username)
					.password(user.getPassword())
					.roles("ADMIN")
					.build();
		}
		
		Car car = carRepo.findByUsername(username);
		if (car != null) {
			return org.springframework.security.core.userdetails.User
					.withUsername(username)
					.password(car.getPassword())
					.roles("ADMIN")
					.build();
		}
		
		throw new UsernameNotFoundException("No user/car with this username found!");
	}

}
