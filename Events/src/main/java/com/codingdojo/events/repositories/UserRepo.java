package com.codingdojo.events.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.events.models.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long>{
    // this method retrieves all the users from the database
    List<User> findAll();
    // this method find a user by their description
    List<User> findByfnameContaining(String search);
    // this method counts how many titles contain a certain string
    Long countByLocationContaining(String search);
    // this method deletes a user that starts with a specific title
    Long deleteByfnameStartingWith(String search);
    
    User findByEmail(String search);
}