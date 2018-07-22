package com.codingdojo.events.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

import com.codingdojo.events.models.Event;
import com.codingdojo.events.models.User;
import com.codingdojo.events.services.EventService;

@Component
public class EventValidator{
	
	private final EventService s;
	
	public EventValidator(EventService e) {
		this.s = e;
	}

	public boolean supports(Class<?> clazz) {
        return Event.class.equals(clazz);
    }
    
    // 2
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        
        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            // 3
            errors.rejectValue("passwordConfirmation", "Match");
        }
        
        if(s.findByEmail(user.getEmail()) != null)
    		errors.rejectValue("email", "exists");
    }
    
    public void validateEvent(Object target, Errors errors) {
    	Event event = (Event) target;
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
    	
    	try {
			if(sdf.parse(event.getDate().toString()).before(sdf.parse(new Date().toString()))){
				errors.rejectValue("date", "before");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
