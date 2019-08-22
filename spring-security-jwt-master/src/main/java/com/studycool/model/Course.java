package com.studycool.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="course")
public class Course {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="course_id")
    private Long id;
	
	@Column(name="name")
    private String name;
	
	@Column(name="years")
    private int years;
	
	/*
	 * @Column(name="univercity_id") private long univercity_id;
	 */
	
	
	public Long getId() {
		return id;
	}

	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
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
	
	@Column(name="u_id")
	private long u_id;
	
	
	
	
	//changed
	
	  public long getU_id() {
		return u_id;
	}

	public void setU_id(long u_id) {
		this.u_id = u_id;
	}

	public Course(String name) { this.name=name;}
	 
	 
	 
	
	
		@ManyToOne
	    @JoinColumn(name="univercity_id")
	    private Univercity univercity;
		
		public Univercity getUnivercity() {
			return univercity;
		}

		public void setUnivercity(Univercity univercity) {
			this.univercity = univercity;
		}

		public Course() {
			
		}
	 
//new
	
}