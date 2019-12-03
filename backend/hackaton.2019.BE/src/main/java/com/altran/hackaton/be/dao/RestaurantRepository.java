package com.altran.hackaton.be.dao;

import org.springframework.data.repository.CrudRepository;

import com.altran.hackaton.be.model.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant, Integer>{

}
