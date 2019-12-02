package com.altran.hackaton.be.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.altran.hackaton.be.dto.RestaurantRepresentation;
import com.altran.hackaton.be.service.RestaurantService;

@RestController
public class RestaurantController {
	
	@Autowired
	private RestaurantService restaurantService;

	@GetMapping({"/api/food/restaurants"})
	public ResponseEntity<List<RestaurantRepresentation>> listaRestaurants(@RequestParam String token, 
			@RequestParam double x, @RequestParam double y) {		
		List<RestaurantRepresentation> lista = restaurantService.listRestaurants(token, x, y);
		return lista.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<List<RestaurantRepresentation>>(lista, HttpStatus.OK);
	}
}
