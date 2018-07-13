package com.codingdojo.products.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
			return "product/" + p.getId();
		}
	}
}