package com.example.demo;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}