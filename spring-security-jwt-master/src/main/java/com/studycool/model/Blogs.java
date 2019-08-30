package com.studycool.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="blogs")
public class Blogs {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="blog_id")
    private Long id;
	

	@Column(name="tittle")
    private String tittle;
	
	
	
	@Column(name="tags")
    private String tags;
	
	@Lob 
	@Column(name="content")
    private String content;
	
	@Column(name="username")
    private String username;
	
	
	@Column(name="status")
    private int status=1;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
