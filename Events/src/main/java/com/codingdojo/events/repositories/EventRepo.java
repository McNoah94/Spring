package com.codingdojo.events.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.events.models.Event;

@Repository
public interface EventRepo extends CrudRepository<Event, Long>{
    // this method retrieves all the events from the database
    List<Event> findAll();
    // this method find an event by their description
    List<Event> findByDescriptionContaining(String search);
    // this method counts how many titles contain a certain string
    Long countByHostContaining(Long id);
    // this method deletes an event that starts with a specific title
    Long deleteByNameStartingWith(String search);
    
    @Query("SELECT e FROM Event e WHERE state = ?1")
    List<Event> findByState(String search);
    
    @Query("SELECT e FROM Event e WHERE state != ?1")
    List<Event> findByStateExcept(String search);
}