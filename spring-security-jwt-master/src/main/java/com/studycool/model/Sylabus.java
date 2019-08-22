package com.studycool.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sylabus")
public class Sylabus {
	
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="id")
    private Long id;
	
	@Column(name="topic")
    private String topic;
	
	@Column(name="unit_number")
    private int unit_number;
	
	@Column(name="subject_id")
	private long subject_id;

	public int getUnit_number() {
		return unit_number;
	}

	public void setUnit_number(int unit_number) {
		this.unit_number = unit_number;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public long getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(long subject_id) {
		this.subject_id = subject_id;
	}

//content
	@Column(name="file_name")
	private String file_name;
	
	@Column(name="file_path")
	private String file_path;

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	
	
	
	

}