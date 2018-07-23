package com.codingdojo.shows.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.codingdojo.shows.models.User;
import com.codingdojo.shows.services.MainService;

@Component
public class MainValidator {
	
	public final MainService s;
	
	public MainValidator(MainService m) {
		this.s = m;
	}
	
	public void validateUser(Object target, Errors errors) {
		User user = (User) target;
		if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            // 3
            errors.rejectValue("passwordConfirmation", "Match");
        }
		System.out.println(s.findByEmail(user.getEmail()));
        if(s.findByEmail(user.getEmail()) != null)
    		errors.rejectValue("email", "exists");
	}
}
