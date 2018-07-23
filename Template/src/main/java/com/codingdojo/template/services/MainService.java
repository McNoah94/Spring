package com.codingdojo.template.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.codingdojo.template.models.User;
import com.codingdojo.template.repositories.UserRepo;

@Service
public class MainService {
    // adding the user repo as a dependency
    private final UserRepo userRepo;
    
    public MainService(UserRepo ur) {
        this.userRepo = ur;
    }
    // returns all the users
    public List<User> allUsers() {
        return userRepo.findAll();
    }
    // creates a user
    public User createUser(User user) {
    	String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepo.save(user);
    }
    // retrieves a user
    public User findUser(Long id) {
        Optional<User> optionalUser = userRepo.findById(id);
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }
    }
    
    public User findByEmail(String email) {
    	User u = userRepo.findByEmail(email);
    	if(u != null)
    		return u;
    	else
    		return null;
    }
    
    public boolean authenticateUser(String email, String pass) {
    	User u = userRepo.findByEmail(email);
    	if(u != null) {
    		if(BCrypt.checkpw(pass, u.getPassword())) {
    			return true;
    		}
    		else
    			return false;
    	}
    		else
    			return false;
    }
}