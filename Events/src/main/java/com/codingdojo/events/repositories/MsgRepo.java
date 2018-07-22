package com.codingdojo.events.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.events.models.Message;

@Repository
public interface MsgRepo extends CrudRepository<Message, Long>{
    // this method retrieves all the books from the database
    List<Message> findAll();
    // this method find a book by their description
    List<Message> findByContentContaining(String search);
    // this method counts how many titles contain a certain string
}