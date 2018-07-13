package com.example.demo;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DojoController {

	@RequestMapping("/")
	public String index(HttpSession session){
		if(session.getAttribute("count") == null)
			session.setAttribute("count", 0);

		Object count = session.getAttribute("count");
		session.setAttribute("count", (int) session.getValue("count") + 1);
		return "index.jsp";
	}

	@RequestMapping("/date")
	public String date(Model model) {
		SimpleDateFormat formatter = new SimpleDateFormat("EEEEE', the' dd 'of' MMMMM',' yyyy");
		String date = formatter.format(new Date());
		System.out.println(date);
		model.addAttribute("date", date);
		return "date.jsp";
	}
	
	@RequestMapping("/time")
	public String time(Model model) {
		SimpleDateFormat formatter = new SimpleDateFormat("h':'m a");
		String time = formatter.format(new Date());
		model.addAttribute("time", time);
		return "time.jsp";
	}
	
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public String code(Model model, @RequestParam(value="code") String userCode, RedirectAttributes ra){
		String code = "bushido";
		if(userCode.equals(code))
			return "code.jsp";
		else{
			ra.addFlashAttribute("error", "You must train harder!");
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="/survey", method=RequestMethod.POST)
	public String survey(Model model, @RequestParam(value="name") String name, @RequestParam(value="location") String location, @RequestParam(value="language") String language, @RequestParam(value="comment") String comment){
		System.out.println(location);
		model.addAttribute("name", name);
		model.addAttribute("location", location);
		model.addAttribute("comment", comment);
		model.addAttribute("language", language);
		
		return "results.jsp";
	}
	
	@RequestMapping(value="/gold")
	public String gold(Model model, HttpSession session) {
		if(session.getAttribute("gold") == null)
			session.setAttribute("gold", 0);
		if(session.getAttribute("activities") == null){
			session.setAttribute("activities", new ArrayList<String>());
		}
		
		model.addAttribute("gold", session.getAttribute("gold"));
		model.addAttribute("activities");
		return "gold.jsp";
	}

	@RequestMapping(value="/addGold", method=RequestMethod.POST)
	public String addGold(HttpSession session, @RequestParam(value="box") String s){
		int i = 0;
		ArrayList<String> list = (ArrayList) session.getAttribute("activities");
		switch(s){
			case "cave":
				i = (5 + (int) Math.round((Math.random() * 5)));
				list.add("You earned " + i + " gold in the " + s + ". (" + new Date() + ")");
				break;
			case "house":
				i = (2 + (int) Math.round((Math.random() * 3)));
				list.add("You earned " + i + " gold in the " + s + ". (" + new Date() + ")");
				break;
			case "farm":
				i = (10 + (int) Math.round((Math.random() * 10)));
				list.add("You earned " + i + " gold in the " + s + ". (" + new Date() + ")");
				break;
			case "casino":
				i = (-50 + (int) Math.round((Math.random() * 100)));
				if(i < 0)
					list.add("You lost " + i + " gold in the " + s + ". (" + new Date() + ")");
				else
					list.add("You earned " + i + " gold in the " + s + ". (" + new Date() + ")");
				break;
		}
		session.setAttribute("activities", list);
		System.out.println(list);
		session.setAttribute("gold",  (int) session.getValue("gold") + i);
		return "redirect:/gold";
	}
}