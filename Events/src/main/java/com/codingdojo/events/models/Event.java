package com.codingdojo.events.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name="events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 5, max = 50, message="Name of event must be 50 characters or fewer")
    private String name;
    @Size(min = 10, message="Description must be at least 10 characters")
    private String description;
    @Size(min=5, message="Location must be 5 characters or more")
    private String location;
    @Size(min=2, max=2, message="State must be 2 characters")
    private String state;
    @Future(message="Date must be in a future")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User host;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name="users_events",
        inverseJoinColumns = @JoinColumn(name="user_id"),
        joinColumns = @JoinColumn(name="event_id")
    )
    private List<User> attendees;

    @OneToMany(mappedBy="event", fetch=FetchType.LAZY)
    private List<Message> messages;
    // This will not allow the createdAt column to be updated after creation
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    public Event(){}
    
    public Event(String name, String desc, String loc, String state, Date date, User u){
    	this.name = name;
    	this.description = desc;
    	this.location = loc;
    	this.state = state;
    	this.date = date;
    	this.host = u;
    }
    
    public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public User getHost() {
		return host;
	}



	public void setHost(User host) {
		this.host = host;
	}



	public List<User> getAttendees() {
		return attendees;
	}



	public void setAttendees(List<User> attendees) {
		this.attendees = attendees;
	}



	public List<Message> getMessages() {
		return messages;
	}



	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}



	// other getters and setters removed for brevity
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}