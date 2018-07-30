package com.codingdojo.lessons.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.codingdojo.lessons.models.Teacher;
import com.codingdojo.lessons.services.MainService;

@Component
public class MainValidator {
	
	public final MainService s;
	
	public MainValidator(MainService m) {
		this.s = m;
	}
	
	public void validateTeacher(Object target, Errors errors) {
		Teacher teacher = (Teacher) target;
		if (!teacher.getConfirmPassword().equals(teacher.getPassword())) {
            // 3
            errors.rejectValue("passwordConfirmation", "Match");
        }
		
        if(s.findByEmail(teacher.getEmail()) != null)
    		errors.rejectValue("email", "exists");
	}
}
