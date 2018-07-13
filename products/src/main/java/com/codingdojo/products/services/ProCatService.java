package com.codingdojo.products.services;

import org.springframework.stereotype.Service;

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
}
