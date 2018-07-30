package com.codingdojo.lessons.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="teachers")
public class Teacher{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private long teacherID;
	private String email;
	private String fname;
	private String lname;
	private boolean active;
	private String department;
	private String password;
	@Transient
	private String confirmPassword;
	
	public Teacher() {}
	
	public Teacher(String fname, String lname, String email, String password, String confirmPassword, String department) {
		this.password = password;
		this.active = true;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(long teacherID) {
		this.teacherID = teacherID;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}
	
	
	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
}
