package com.altran.hackaton.be.dao;

import org.springframework.data.repository.CrudRepository;

import com.altran.hackaton.be.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{

	User findByLogin(String login);
	
}
