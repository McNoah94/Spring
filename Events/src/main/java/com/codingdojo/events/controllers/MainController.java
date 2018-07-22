package com.codingdojo.events.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.events.models.Event;
import com.codingdojo.events.models.Message;
import com.codingdojo.events.models.User;
import com.codingdojo.events.services.EventService;
import com.codingdojo.events.validator.EventValidator;

@Controller
public class MainController {
	private final String STATES[] = {"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "DC", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};
	private final EventValidator v;
	private final EventService s;
	
	public MainController(EventValidator v, EventService s){
		this.v = v;
		this.s = s;
	}
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("states", this.STATES);
		model.addAttribute("user", new User());
		return "logReg.jsp";
	}

	@RequestMapping(value="/", method=RequestMethod.POST)
	public String register(Model model, @Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session){
		v.validate(user, result);
		if(result.hasErrors()) {
			return"logReg.jsp";
		}
		else {
			s.createUser(user);
			model.addAttribute("success", "Successfully registered. Please login.");
			model.addAttribute("user", new User());
			model.addAttribute("states", this.STATES);
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
			return "login.jsp";
		}
	}
	
	@RequestMapping("/home")
	public String home(Model model, HttpSession session, RedirectAttributes attr) {
		if(session.getAttribute("user") == null)
			return "redirect:/";
		User u = s.findUser((Long) session.getAttribute("user"));
		model.addAttribute("user", u);
		model.addAttribute("event", new Event());
		model.addAttribute("instate", this.s.findEventByState(u.getState()));
		model.addAttribute("outstate", this.s.findEventByStateExcept(u.getState()));
		model.addAttribute("states", this.STATES);
		
		System.out.println(attr);
		return "home.jsp";
	}
	
	@RequestMapping(value="/events", method=RequestMethod.POST)
	public String event(RedirectAttributes attr, HttpSession session, Model model, @Valid @ModelAttribute("event") Event event, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println(result);
			attr.addFlashAttribute("errors", result.getAllErrors());
			return "redirect:/home";
		}
		else {
			event.setHost(this.s.findUser((Long) session.getAttribute("user")));
			s.createEvent(event);
			return "redirect:/home";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/";
	}
	
	@RequestMapping("/events/{id}/join")
	public String join(HttpSession session, @PathVariable("id") Long id) {
		Event e = s.findEvent(id);
		User u = s.findUser((Long) session.getAttribute("user"));
		s.joinEvent(u, e);
		return "redirect:/home";
	}
	
	@RequestMapping("/events/{id}/cancel")
	public String cancel(HttpSession session, @PathVariable("id") Long id) {
		Event e = s.findEvent(id);
		User u = s.findUser((Long) session.getAttribute("user"));
		s.cancelEvent(u, e);
		return "redirect:/home";
	}
	
	@RequestMapping("/events/{id}")
	public String event(Model model, @PathVariable("id") Long id) {
		Event e = s.findEvent(id);
		model.addAttribute("event", e);
		model.addAttribute("msg", new Message());
		return "event.jsp";
	}
	
	@RequestMapping(value="/events/{id}", method=RequestMethod.POST)
	public String addMsg(HttpSession session, RedirectAttributes attr, @PathVariable("id") Long id, @Valid @ModelAttribute("msg") Message msg, BindingResult result){
		if(result.hasErrors()) {
			System.out.println(result);
			attr.addFlashAttribute("errors", result.getAllErrors());
			return "redirect:/home";
		}
		else {
			Event e = s.findEvent(id);
			User u = s.findUser((Long) session.getAttribute("user"));
			msg.setEvent(e);
			msg.setContent(u.getFname() + ": " + msg.getContent());
			s.createMessage(msg);
			return "redirect:/event/" + id;
		}
	}
}
