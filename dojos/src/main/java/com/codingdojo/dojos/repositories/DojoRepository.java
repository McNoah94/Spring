package com.codingdojo.dojos.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.dojos.models.Dojo;

@Repository
public interface DojoRepository extends CrudRepository<Dojo, Long>{
    // this method retrieves all the Dojos from the database
    List<Dojo> findAll();
    // this method find a Dojo by their description
    List<Dojo> findByName(String name);
}