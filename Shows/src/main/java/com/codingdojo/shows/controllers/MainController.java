package com.codingdojo.shows.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.shows.models.Show;
import com.codingdojo.shows.models.User;
import com.codingdojo.shows.services.MainService;
import com.codingdojo.shows.validator.MainValidator;

@Controller
public class MainController {
	
	private final MainService s;
	private final MainValidator v;
	
	public MainController(MainService s, MainValidator v) {
		this.s = s;
		this.v = v;
	}
	
	@RequestMapping("/")
	public String index(Model model, RedirectAttributes attr) {
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
	public String login(RedirectAttributes attr, Model model, HttpSession session, @RequestParam("logEmail") String email, @RequestParam("logPass") String pass){
		if(s.authenticateUser(email, pass)) {
			session.setAttribute("user", s.findByEmail(email).getId());
			return "redirect:/home";
		}
		else {
			attr.addFlashAttribute("error", "Email/password incorrect");
			return "redirect:/";
		}
	}
	
	@RequestMapping("/home")
	public String home(Model model, HttpSession session){
		if(session.getAttribute("user") == null)
			return "redirect:/";
		User u = s.findUser((Long) session.getAttribute("user"));
		model.addAttribute("user", u);
		return "index.jsp";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/";
	}
	
	@RequestMapping("/shows/new")
	public String add(HttpSession session, Model model) {
		if(session.getAttribute("user") == null)
			return "redirect:/";
		model.addAttribute("show", new Show());
		return "add.jsp";
	}
}
