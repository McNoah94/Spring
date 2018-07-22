package com.codingdojo.events.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.codingdojo.events.models.Event;
import com.codingdojo.events.models.Message;
import com.codingdojo.events.models.User;
import com.codingdojo.events.repositories.EventRepo;
import com.codingdojo.events.repositories.MsgRepo;
import com.codingdojo.events.repositories.UserRepo;

@Service
public class EventService {
    private final EventRepo eventRepo;
    private final UserRepo userRepo;
    private final MsgRepo msgRepo;
    
    public EventService(EventRepo eventRepo, UserRepo userRepo, MsgRepo msgRepo) {
        this.eventRepo = eventRepo;
        this.userRepo = userRepo;
        this.msgRepo = msgRepo;
    }
    // EVENTS
    public void joinEvent(User u, Event e) {
    	List<Event> a =  u.getEvents();
    	a.add(e);
    	u.setEvents(a);
    	userRepo.save(u);
    }
    
    public void cancelEvent(User u, Event e) {
    	List<Event> a =  u.getEvents();
    	a.remove(e);
    	u.setEvents(a);
    	userRepo.save(u);
    }

    public List<Event> allEvents() {
        return eventRepo.findAll();
    }

    public Event createEvent(Event b) {
        return eventRepo.save(b);
    }

    public Event findEvent(Long id) {
        Optional<Event> optionalEvent = eventRepo.findById(id);
        if(optionalEvent.isPresent()) {
            return optionalEvent.get();
        } else {
            return null;
        }
    }
    
    public List<Event> findEventByState(String search){
    	return eventRepo.findByState(search);
    }
    
    public List<Event> findEventByStateExcept(String search){
    	return eventRepo.findByStateExcept(search);
    }

    // USERS
    public List<User> allUsers() {
        return userRepo.findAll();
    }

    public User createUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepo.save(user);
    }
    public User findUser(Long id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }
    
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = userRepo.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }

    // MESSAGES
    public List<Message> allMessages() {
        return msgRepo.findAll();
    }

    public Message createMessage(Message m) {
        return msgRepo.save(m);
    }

    public Message findMessage(Long id) {
        Optional<Message> msg = msgRepo.findById(id);
        if(msg.isPresent()) {
            return msg.get();
        } else {
            return null;
        }
    }

	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}
}