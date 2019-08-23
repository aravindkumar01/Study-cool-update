package com.studycool.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="users_details")

public class UserDetails {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="id")
    private long id;
	
	
	@Column(name="first_name")
	private String first_name;

	@Column(name="last_name")
	private String last_name;
	
	@Column(name="course_id")
	private long course_id;
	
	
	@Column(name="username")
	private String username;

	
	
	@Column(name="mobile")
	private String mobile;

	public String getUsername() {
		return username;
	}


	public long getCourse_id() {
		return course_id;
	}


	public void setCourse_id(long course_id) {
		this.course_id = course_id;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	@Column(name="role")
	private String role;
	
	@Column
	@CreationTimestamp
	private LocalDateTime createDateTime;
	
	
	@Column(name="univercity")
	private String univercity;

	@Column(name="course")
	private String course;

	@Column(name="active")
	private int active=1;
	

	public String getLast_name() {
		return last_name;
	}


	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}



	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}


	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}


	public String getUnivercity() {
		return univercity;
	}


	public void setUnivercity(String univercity) {
		this.univercity = univercity;
	}


	public String getCourse() {
		return course;
	}


	public void setCourse(String course) {
		this.course = course;
	}


	

	


	public String getFirst_name() {
		return first_name;
	}


	public int getActive() {
		return active;
	}


	public void setActive(int active) {
		this.active = active;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

}
