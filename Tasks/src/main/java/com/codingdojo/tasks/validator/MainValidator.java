package com.codingdojo.tasks.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.codingdojo.tasks.models.Task;
import com.codingdojo.tasks.models.User;
import com.codingdojo.tasks.services.MainService;

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
        if(s.findByEmail(user.getEmail()) != null) {
    		errors.rejectValue("email", "exists");
        }
	}
	
	public void validateTask(Object target, Errors errors) {
		Task task = (Task) target;
		if(task.getTitle().length() < 5) {
			errors.rejectValue("title", "length");
		}
	}
}
