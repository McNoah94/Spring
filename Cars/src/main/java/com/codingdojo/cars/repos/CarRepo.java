package com.codingdojo.cars.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.codingdojo.cars.models.Car;

@RepositoryRestResource
public interface CarRepo extends JpaRepository<Car, Long> {
}