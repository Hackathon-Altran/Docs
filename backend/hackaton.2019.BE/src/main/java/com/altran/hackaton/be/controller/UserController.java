package com.altran.hackaton.be.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.altran.hackaton.be.dao.CarRepository;
import com.altran.hackaton.be.model.Car;

@RestController
public class UserController {
	
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private CarRepository carRepo;

	@PostMapping({"/api/users/login"})
	@ResponseBody
	public ResponseEntity<String> login(@RequestBody String user, @RequestBody String pwd) {
		logger.info("UserController/login");
		Car car = carRepo.findByUsername(user);
		if (car != null) {
			return new ResponseEntity<String>(car.getToken(),HttpStatus.OK);	
		}
		return new ResponseEntity<String>("There is no car associated to the user.", 
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
