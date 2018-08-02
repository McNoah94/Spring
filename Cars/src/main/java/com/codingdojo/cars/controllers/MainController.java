package com.codingdojo.cars.controllers;

import java.util.Collection;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping("/cars/all")
	@CrossOrigin(origins = "http://localhost:4200")
	public Collection<Car> cars(){
		return this.cr.findAll();
	}
	
	@RequestMapping("/car/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Optional<Car> findCar(@PathVariable ("id") Long id) {
		return this.cr.findById(id);
	}
}
