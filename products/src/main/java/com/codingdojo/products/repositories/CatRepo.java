package com.codingdojo.products.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.products.models.Category;

@Repository
public interface CatRepo extends CrudRepository<Category, Long>{
    // this method retrieves all the Categorys from the database
    List<Category> findAll();
    Long countByNameContaining(String search);
    // this method deletes a Category that starts with a specific title
    Long deleteByNameStartingWith(String search);
}