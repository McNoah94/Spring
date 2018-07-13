package com.codingdojo.products.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.products.models.Product;

@Repository
public interface ProductRepo extends CrudRepository<Product, Long>{
    // this method retrieves all the Products from the database
    List<Product> findAll();
    // this method find a Product by their description
    List<Product> findByDescriptionContaining(String search);
    // this method counts how many titles contain a certain string
    Long countByNameContaining(String search);
    // this method deletes a Product that starts with a specific title
    Long deleteByNameStartingWith(String search);
}