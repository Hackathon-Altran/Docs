package com.altran.hackaton.be.dao;

import org.springframework.data.repository.CrudRepository;

import com.altran.hackaton.be.model.Car;

public interface CarRepository extends CrudRepository<Car, Integer> {

	Car findByUsername(String username);
	
}
