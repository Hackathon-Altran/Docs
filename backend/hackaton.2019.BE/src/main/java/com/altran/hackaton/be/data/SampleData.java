package com.altran.hackaton.be.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.altran.hackaton.be.dao.CarRepository;
import com.altran.hackaton.be.dao.RestaurantRepository;
import com.altran.hackaton.be.dao.UserRepository;
import com.altran.hackaton.be.model.Car;
import com.altran.hackaton.be.model.Restaurant;
import com.altran.hackaton.be.model.User;

@Component
public class SampleData {
	
	@Autowired
	private RestaurantRepository restRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CarRepository carRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@EventListener
    public void appReady(ApplicationReadyEvent event) {		
		for (Restaurant restaurant: initializeRestaurants()) {
			restRepo.save(restaurant);
		}		
		for (User user: initializeUsers()) {
			userRepo.save(user);
		}
		for (Car car: initializeCars()) {
			carRepo.save(car);
		}
	}
	
	private List<User> initializeUsers(){
		List<User> users = new ArrayList<>();
		
		User user = new User();
		user.setId(1);
		user.setLogin("JOHN");
		user.setPassword(encoder.encode("Wick3"));		
		users.add(user);
		
		return users;
	}
	
	private List<Car> initializeCars(){
		
		List<Car> cars = new ArrayList<>();
		
		Car car = new Car();
		car.setIdentifier(1);
		car.setPassword(encoder.encode("CHEV2345"));
		car.setToken("XA23546437HYI23BGT657U79");
		car.setUsername("JOHN");
		cars.add(car);
		
		return cars;
	}

	private List<Restaurant> initializeRestaurants() {
		
		List<Restaurant> list = new ArrayList<>();
		
		Restaurant rest = new Restaurant();
		rest.setAddress("Avenida Central");
		rest.setIdentifier("ABC");
		rest.setName("Android Food");
		rest.setLatitude(31.222);
		rest.setLongitude(74.222);
		list.add(rest);
		
		return list;
	}

}
