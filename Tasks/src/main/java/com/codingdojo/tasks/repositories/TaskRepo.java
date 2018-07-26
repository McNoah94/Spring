package com.codingdojo.tasks.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.tasks.models.Task;

@Repository
public interface TaskRepo extends CrudRepository<Task, Long>{
    // this method retrieves all the books from the database
    List<Task> findAll();
}