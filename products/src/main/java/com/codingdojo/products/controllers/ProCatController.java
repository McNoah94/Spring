package com.codingdojo.products.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.products.models.Category;
import com.codingdojo.products.models.Product;
import com.codingdojo.products.services.ProCatService;

@Controller
public class ProCatController {
	
	private final ProCatService pcs;
	
	public ProCatController(ProCatService pcs) {
		this.pcs = pcs;
	}
	
	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("category", new Category());
		return "index.jsp";
	}
	
	@RequestMapping(value="/product", method=RequestMethod.POST)
	public String newProduct(@Valid @ModelAttribute("product") Product p, BindingResult result){
		if(result.hasErrors()) {
			return "redirect:/";
		}
		else {
			pcs.createProduct(p);
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="/category", method=RequestMethod.POST)
	public String newCat(@Valid @ModelAttribute("category") Category c, BindingResult result) {
		if(result.hasErrors())
			return "redirect:/";
		else {
			pcs.createCategory(c);
			return "redirect:/";
		}
	}
	
	@RequestMapping("/product/{id}")
	public String showProduct(Model model, @PathVariable("id") Long id) {
		Optional<Product> p = pcs.getProduct(id); 
		List<Category> c = pcs.getAllCategories();
		model.addAttribute("product", p.get());
		model.addAttribute("categories", c);
		model.addAttribute("category1", new Category());
		return "product.jsp";
	}
	
	@RequestMapping(value="/product/{id}", method=RequestMethod.POST)
	public String addCategory(@PathVariable("id") Long id, @RequestParam("name") String name){
		Optional<Product> p = pcs.getProduct(id);
		Optional<Category> c = pcs.getCategory(Long.parseLong(name));
		p.get().addCategory(c.get());
		pcs.save(p.get());
		return "redirect:/product/" + p.get().getId();
	}
}