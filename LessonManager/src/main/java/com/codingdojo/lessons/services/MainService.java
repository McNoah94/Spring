package com.codingdojo.lessons.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.codingdojo.lessons.models.Teacher;
import com.codingdojo.lessons.models.User;
import com.codingdojo.lessons.repositories.StudentRepo;
import com.codingdojo.lessons.repositories.TeacherRepo;
import com.codingdojo.lessons.repositories.UserRepo;

@Service
public class MainService {
    
    private final UserRepo userRepo;
    private final TeacherRepo tr;
    private final StudentRepo sr;
    
    public MainService(UserRepo ur, TeacherRepo tr, StudentRepo sr) {
    	this.tr = tr;
    	this.sr = sr;
        this.userRepo = ur;
    }
    
    //TEACHER SPECIFIC COMMANDS
    public Teacher findByTeacherID(Long id) {
    	return tr.findByTeacherID(id).get();
    }
    
    public Teacher createTeacher(Teacher t) {
    	t.setTeacherID((long) Math.floor(Math.random() * 1000000));
    	String hashed = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt());
        t.setPassword(hashed);
    	return tr.save(t);
    }
    
    public Teacher findByEmail(String email) {
    	Teacher t = tr.findByEmail(email);
    	if(t != null)
    		return t;
    	else
    		return null;
    }
    
    public boolean authenticateTeacher(long id, String password) {
    	Optional<Teacher> t = tr.findByTeacherID(id);
    	System.out.println(t.get().getPassword());
    	if(BCrypt.checkpw(password, t.get().getPassword())) {
    		return true;
    	}
    	else
    		return false;
    }
    
    public List<Teacher> allTeachers(){
    	return tr.findAll();
    }
}