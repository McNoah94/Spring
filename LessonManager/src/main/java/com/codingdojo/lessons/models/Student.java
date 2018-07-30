package com.codingdojo.lessons.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="students")
public class Student{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentID;
	private boolean active;
	private int age;
	
	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
	
	public Student() {}
	
	public Student(String fname, String lname, int age, String school_year, String email, String password, String passwordConfirmation) {
		this.active = true;
		this.age = age;
//		this.studentID = (Long) Math.floor(Math.random() * 100000);
	}
}
