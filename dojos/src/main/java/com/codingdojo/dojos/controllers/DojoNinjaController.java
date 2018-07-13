package com.codingdojo.dojos.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.dojos.models.Dojo;
import com.codingdojo.dojos.models.Ninja;
import com.codingdojo.dojos.services.DojoNinjaService;

@Controller
public class DojoNinjaController{
	private final DojoNinjaService dns;
	
	public DojoNinjaController(DojoNinjaService dns) {
		this.dns = dns;
	}
	@RequestMapping("/dojo/new")
	public String newDojo(Model model){
		model.addAttribute("dojo", new Dojo());
		return "/dojo/dojo.jsp";
	}

	@RequestMapping("/ninja/new")
	public String newninja(Model model){
		model.addAttribute("ninja", new Ninja());
		List<Dojo> dojos = dns.allDojos();
		model.addAttribute("dojos", dojos);
		return "/ninja/ninja.jsp";
	}

	@RequestMapping("/")
	public String home(Model model){
		return "index.jsp";
	}
	
	@RequestMapping(value="/ninja", method=RequestMethod.POST)
	public String post(@Valid @ModelAttribute("ninja") Ninja ninja, BindingResult result){
		if(result.hasErrors())
			return "/ninja/ninja.jsp";
		else {
			dns.createNinja(ninja);
			return "redirect:/";
		}
	}

	@RequestMapping(value="/dojo", method=RequestMethod.POST)
	public String postDojo(@Valid @ModelAttribute("dojo") Dojo dojo, BindingResult result){
		if(result.hasErrors())
			return "/dojo/dojo.jsp";
		else{
			dns.createDojo(dojo);
			return "redirect:/";
		}
	}
	
	@RequestMapping("/dojo/{id}")
	public String dojoTable(Model model, @PathVariable(value="id") Long id){
		Dojo dojo = dns.findDojo(id);
		model.addAttribute("dojo", dojo);
		return "/dojo/dojoTable.jsp";
	}
}
