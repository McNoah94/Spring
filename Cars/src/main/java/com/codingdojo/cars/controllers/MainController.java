package com.codingdojo.cars.controllers;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingdojo.cars.models.Car;
import com.codingdojo.cars.repos.CarRepo;

@RestController
public class MainController {
	private CarRepo cr;
	
	public MainController(CarRepo cr) {
		this.cr=cr;
	}
	
	@RequestMapping("/cars")
	@CrossOrigin(origins = "http://localhost:4200")
	public Collection<Car> cars(){
		return this.cr.findAll();
	}
}
