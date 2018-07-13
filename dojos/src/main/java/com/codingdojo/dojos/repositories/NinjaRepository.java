package com.codingdojo.dojos.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.dojos.models.Ninja;

@Repository
public interface NinjaRepository extends CrudRepository<Ninja, Long>{
    // this method retrieves all the Ninjas from the database
    List<Ninja> findAll();
}