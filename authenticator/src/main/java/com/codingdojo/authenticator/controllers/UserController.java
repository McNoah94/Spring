package com.codingdojo.authenticator.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.authenticator.models.User;
import com.codingdojo.authenticator.services.UserService;
import com.codingdojo.authenticator.validators.UserValidator;

// imports removed for brevity
@Controller
public class UserController {
    private final UserService userService;
    private final UserValidator userValidator;
    
    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }
    
    @RequestMapping("/registration")
    public String registerForm(@ModelAttribute("user") User user) {
        return "regPage.jsp";
    }
    @RequestMapping("/login")
    public String login() {
        return "logPage.jsp";
    }
    
    @RequestMapping(value="/registration", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
        // if result has errors, return the registration page (don't worry about validations just now)
        // else, save the user in the database, save the user id in session, and redirect them to the /home route
        userValidator.validate(user, result);
        if(result.hasErrors())
            return "regPage.jsp";
        else{
            User u =userService.registerUser(user);
            session.setAttribute("user", u.getId());
            return "redirect:/home";
        }
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
        // if the user is authenticated, save their user id in session
        // else, add error messages and return the login page
        if(userService.authenticateUser(email, password)){
            session.setAttribute("user", userService.findByEmail(email).getId());
            return "redirect:/home";
        }
        else{
            model.addAttribute("error", "Email or password are incorrect");
            return "logPage.jsp";
        }
    }
    
    @RequestMapping("/home")
    public String home(HttpSession session, Model model) {
        // get user from session, save them in the model and return the home page
        model.addAttribute("user", userService.findUserById((Long) session.getAttribute("user")));
        return "homePage.jsp";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        // invalidate session
        // redirect to login page
        session.removeAttribute("user");
        return "redirect:/login";
    }
}