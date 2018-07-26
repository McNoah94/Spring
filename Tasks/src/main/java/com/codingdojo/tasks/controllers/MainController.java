package com.codingdojo.tasks.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.List;

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

import com.codingdojo.tasks.models.Task;
import com.codingdojo.tasks.models.User;
import com.codingdojo.tasks.services.MainService;
import com.codingdojo.tasks.validator.MainValidator;

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
	public String login(RedirectAttributes attr, Model model, HttpSession session, @RequestParam("logEmail") String email, @RequestParam("logPass") String pass){
		if(s.authenticateUser(email, pass)) {
			session.setAttribute("user", s.findByEmail(email).getId());
			return "redirect:/tasks";
		}
		else {
			attr.addFlashAttribute("error", "Email/password incorrect");
			return "redirect:/";
		}
	}
	
	@RequestMapping("/tasks")
	public String home(HttpSession session, Model model) {
		User u = s.findUser((Long) session.getAttribute("user"));
		List<Task> t = s.allTasks();
		model.addAttribute("use", u);
		model.addAttribute("tasks", t);
		return "index.jsp";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/";
	}
	
	@RequestMapping("/tasks/new")
	public String create(HttpSession session, Model model) {
		model.addAttribute("user", s.findUser((Long) session.getAttribute("user")));
		model.addAttribute("users", s.allUsers());
		model.addAttribute("task", new Task());
		return "new.jsp";
	}
	
	@RequestMapping(value="/tasks/new", method=RequestMethod.POST)
	public String task(Model model, @Valid @ModelAttribute("task") Task task, BindingResult result, HttpSession session){
		v.validateTask(task, result);
		if(result.hasErrors()) {
			model.addAttribute("user", s.findUser((Long) session.getAttribute("user")));
			model.addAttribute("users", s.allUsers());
			model.addAttribute("task", new Task());
			model.addAttribute("errors", "Title must be at least 5 characters");
			System.out.println(result);
			return "new.jsp";
		}
		else {
			s.createTask(task);
			return "redirect:/tasks";
		}
	}
	
	@RequestMapping("/tasks/{id}")
	public String showTask(@PathVariable("id") Long id, Model model, HttpSession session) {
		model.addAttribute("task", s.findTask(id));
		model.addAttribute("use", s.findUser((Long) session.getAttribute("user")));
		return "task.jsp";
	}
	
	@RequestMapping("/tasks/{id}/remove")
	public String removeTask(@PathVariable("id") Long id, Model model) {
		s.deleteTask(id);
		return "redirect:/tasks";
	}
	
	@RequestMapping("/tasks/{id}/edit")
	public String updateTask(@PathVariable("id") Long id, Model model, HttpSession session) {
		User u = s.findUser((Long) session.getAttribute("user"));
		Task t = s.findTask(id);
		if(u.getId() != t.getCreator().getId())
			return "redirect:/tasks";
		model.addAttribute("user", u);
		model.addAttribute("users", s.allUsers());
		model.addAttribute("task", t);
		return "edit.jsp";
	}
	@RequestMapping(value="/tasks/{id}/edit", method=RequestMethod.POST)
	public String updateTask(@PathVariable("id") Long id, Model model, HttpSession session, @Valid @ModelAttribute("task") Task task, BindingResult result) {
		v.validateTask(task, result);
		if(result.hasErrors()) {
			model.addAttribute("user", s.findUser((Long) session.getAttribute("user")));
			model.addAttribute("users", s.allUsers());
			model.addAttribute("task", s.findTask(id));
			model.addAttribute("errors", "Title must be at least 5 characters");
			return "edit.jsp";
		}
		else {
			s.updateTask(task);
			return "redirect:/tasks";
		}
	}
	
	@RequestMapping("/tasks/{id}/complete")
	public String complete(@PathVariable("id") Long id, Model model) {
		Task t = s.findTask(id);
		t.setCompleted(true);
		s.updateTask(t);
		return "redirect:/tasks";
	}
}

