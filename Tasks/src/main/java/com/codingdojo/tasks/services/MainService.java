package com.codingdojo.tasks.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.codingdojo.tasks.models.Task;
import com.codingdojo.tasks.models.User;
import com.codingdojo.tasks.repositories.TaskRepo;
import com.codingdojo.tasks.repositories.UserRepo;

@Service
public class MainService {
    // adding the user repo as a dependency
    private final UserRepo userRepo;
    private final TaskRepo taskRepo;
    
    public MainService(UserRepo ur, TaskRepo tr) {
        this.userRepo = ur;
        this.taskRepo = tr;
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
    
    // TASK SPECIFIC METHODS
    
    public Task createTask(Task task) {
    	return taskRepo.save(task);
    }
    
    public List<Task> allTasks(){
    	return taskRepo.findAll();
    }
    
    public Task findTask(Long id) {
    	Optional<Task> t = taskRepo.findById(id);
    	return t.get();
    }
    
    public void deleteTask(Long id){
    	taskRepo.deleteById(id);
    }
    
    public void updateTask(Task t) {
    	taskRepo.save(t);
    }
}