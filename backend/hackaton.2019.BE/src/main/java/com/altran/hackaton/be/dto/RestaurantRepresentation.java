package com.altran.hackaton.be.dto;

import com.altran.hackaton.be.model.Location;
import com.altran.hackaton.be.model.Restaurant;

public class RestaurantRepresentation {

	private String restaurantId;
	
	private String name;
	
	private String address;
	
	private Location location;
	
	private double distance;

	RestaurantRepresentation(String restaurantId, String name, String address, Location location,
			double distance) {
		super();
		this.restaurantId = restaurantId;
		this.name = name;
		this.address = address;
		this.location = location;
		this.distance = distance;
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public Location getLocation() {
		return location;
	}

	public double getDistance() {
		return distance;
	}

	public static RestaurantRepresentation from(Restaurant restaurant, Location location) {		
		return new RestaurantRepresentation(
				restaurant.getIdentifier(), 
				restaurant.getName(), 
				restaurant.getAddress(), 
				restaurant.location(), 
				location.distance(restaurant.location()));
	}
}
