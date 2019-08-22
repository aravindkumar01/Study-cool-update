package com.studycool.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="univercity")
public class Univercity {

	

	

	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}



	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}



	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="univercity_id")
    private long id;
	
	@Column(name="name")
    private String name;
	
	
	@Column(name="address")
    private String address;
	
	@Column(name="location")
    private String location;
	
	
	//changed
	
	//@manyToMany(mappedBy="univercity")
	  
	  @OneToMany(mappedBy="univercity")
	  @JsonIgnore
	    private Set<Course> course;


	  public Univercity()
	  {
		  
	  }
	public Set<Course> getCourse() {
		return course;
	}

	public void setCourse(Set<Course> course) {
		this.course = course;
	}

	public void setId(long id) {
		this.id = id;
	}
	  
	
	
	  public Univercity(String name, String address,String location) { this.name =
	  name; this.address=address; this.location=location; }
	 
	
}
