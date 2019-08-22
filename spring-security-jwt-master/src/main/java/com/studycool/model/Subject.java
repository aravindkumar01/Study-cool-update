package com.studycool.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="subject")
public class Subject{
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="id")
    private Long id;
	
	
	@Column(name="course_id")	
	private long course_id;
	
	@Column(name="name")	
	private String name;
	
	@Column(name="year")	
	private int year;
	
	@Column(name="units")	
	private int units;
	
	@Column(name="semster")	
	private String semster;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public long getCourse_id() {
		return course_id;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public void setCourse_id(long course_id) {
		this.course_id = course_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getSemster() {
		return semster;
	}

	public void setSemster(String semster) {
		this.semster = semster;
	}
	
	

}