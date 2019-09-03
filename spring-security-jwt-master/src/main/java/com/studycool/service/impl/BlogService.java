package com.studycool.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studycool.Repo.BlogRepo;
import com.studycool.model.Blogs;

@Service
public class BlogService {
	
	@Autowired
	BlogRepo repo;
	
	public boolean newSave(Blogs blog) {
		
		try {
			repo.save(blog);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public Blogs findById(long id) {
		
		try {
			
			return repo.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			// TODO: handle exception
		}
	}
	
	
	public List<Blogs> findAll(){
		
		try {
			
			return repo.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;		
		}
		
	}
	
	public List<Blogs> getForDash(){
		
		try {
			
			return repo.getBolgsForDash();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			// TODO: handle exception
		}
	}

	public boolean delete(long id) {
		try {
			repo.delete(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
			// TODO: handle exception
		}
	}
}
