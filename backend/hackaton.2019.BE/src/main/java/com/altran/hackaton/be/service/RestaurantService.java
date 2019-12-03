package com.altran.hackaton.be.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altran.hackaton.be.dao.RestaurantRepository;
import com.altran.hackaton.be.dto.RestaurantRepresentation;
import com.altran.hackaton.be.model.Location;
import com.altran.hackaton.be.model.Restaurant;

@Service
public class RestaurantService {
	
	@Autowired
	private RestaurantRepository restRepo;

	public List<RestaurantRepresentation> listRestaurants(String token, double x, double y){
		Location location = Location.of(x,y);
		Collection<Restaurant> restaurants = new ArrayList<>();
		restRepo.findAll().forEach(restaurants::add);
		return restaurants
				.stream()
				.map(rest -> RestaurantRepresentation.from(rest, location))
				.collect(Collectors.toList());
	}
	
}
