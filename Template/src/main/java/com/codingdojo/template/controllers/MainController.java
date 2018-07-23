package com.codingdojo.template.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.template.models.User;
import com.codingdojo.template.services.MainService;
import com.codingdojo.template.validator.MainValidator;

@Controller
public class MainController {
	
	private final MainService s;
	private final MainValidator v;
	
	public MainController(MainService s, MainValidator v) {
		this.s = s;
		this.v = v;
	}
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("user", new User());
		return "logReg.jsp";
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String register(Model model, @Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session){
		v.validateUser(user, result);
		if(result.hasErrors()) {
			return"logReg.jsp";
		}
		else {
			s.createUser(user);
			model.addAttribute("success", "Successfully registered. Please login.");
			model.addAttribute("user", new User());
			return "logReg.jsp";
		}
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(Model model, HttpSession session, @RequestParam("logEmail") String email, @RequestParam("logPass") String pass){
		if(s.authenticateUser(email, pass)) {
			session.setAttribute("user", s.findByEmail(email).getId());
			return "redirect:/home";
		}
		else {
			model.addAttribute("error", "Email/password incorrect");
			return "redirect:/";
		}
	}
	
	@RequestMapping("/home")
	public String home() {
		return "index.jsp";
	}
}
