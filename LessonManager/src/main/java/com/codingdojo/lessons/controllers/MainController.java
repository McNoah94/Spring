package com.codingdojo.lessons.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.lessons.models.Teacher;
import com.codingdojo.lessons.models.User;
import com.codingdojo.lessons.services.MainService;
import com.codingdojo.lessons.validator.MainValidator;

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
		model.addAttribute("teacher", new Teacher());
		return "logReg.jsp";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(Model model, HttpSession session, @RequestParam("logID") Long logID, @RequestParam("logPass") String pass){
		if(s.authenticateTeacher(logID, pass)) {
			session.setAttribute("teacher", s.findByTeacherID(logID));
			return "redirect:/home";
		}
		else {
			model.addAttribute("error", "Email/password incorrect");
			return "redirect:/";
		}
	}
	
	@RequestMapping("/home")
	public String home(HttpSession session, Model model){
		model.addAttribute("teacher", session.getAttribute("teacher"));
		return "index.jsp";
	}
}
