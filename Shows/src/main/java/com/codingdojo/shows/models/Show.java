package com.codingdojo.shows.models;

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
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="shows")
public class Show {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Size(min = 2, message="Network must have at least 2 characters")
    private String network;
	@Size(min = 5, message="Title must have at least 5 characters")
    private String title;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
        name="users_ratings",
        inverseJoinColumns = @JoinColumn(name="user_id"),
        joinColumns = @JoinColumn(name="show_id")
	   )
    private List<User> ratings;
	
	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    public Show() {}
    
    public Show(String title, String net) {
    	this.title = title;
    	this.network = net;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<User> getRatings() {
		return ratings;
	}

	public void setRatings(List<User> ratings) {
		this.ratings = ratings;
	}
    
}
