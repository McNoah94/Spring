package com.codingdojo.products.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.products.models.Category;
import com.codingdojo.products.models.Product;
import com.codingdojo.products.repositories.CatRepo;
import com.codingdojo.products.repositories.ProductRepo;

@Service
public class ProCatService {
	private final ProductRepo productRepo;
	private final CatRepo catRepo;
	
	public ProCatService(ProductRepo proRepo, CatRepo catRepo) {
		this.catRepo = catRepo;
		this.productRepo = proRepo;
	}
	
	public void createProduct(Product p) {
		this.productRepo.save(p);
	}
	
	public void createCategory(Category c) {
		this.catRepo.save(c);
	}
	
	public Optional<Product> getProduct(Long id) {
		return this.productRepo.findById(id);
	}
	
	public List<Category> getAllCategories(){
		return this.catRepo.findAll();
	}
	
	public Optional<Category> getCategory(Long id){
		return this.catRepo.findById(id);
	}
	
	public void save(Product p) {
		this.productRepo.save(p);
	}
}
