package com.studycool.service.impl;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studycool.Repo.UnivercityRepo;
import com.studycool.model.Univercity;

@Service
public class UnivercityService {
	
	
	
	@Autowired
	UnivercityRepo repo;
	@Autowired
	HttpSession session;
	
	@Autowired
	CourseService course;
	public boolean delete(long id) {
		try {
			

				repo.deleteById(id);
				course.deleteCourseByUnivercity(id);
				return true;
			
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	
	public String add(Univercity univercity) {
		// TODO Auto-generated method stub
		
		try {
			//System.out.println(session.getAttribute("role"));
			
				
				repo.save(univercity);
				return "sucess";
		} catch (Exception e) {
			// TODO: handle exception
			return e.toString();
		}
		
	}
	
	public List<Univercity> getAllUnivercity() {
		// TODO Auto-generated method stub
		
		try {
		
				return repo.findAll();
				
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		return null;
	}


	public Univercity getunivercityById(long id) {
		
		try {
			
			return repo.findById(id);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		// TODO Auto-generated method stub
		return null;
	}


	public String updateUnivercity(Univercity univercity) {
		try {
			repo.save(univercity);
			return ""+univercity.getName()+" Updated sucessfully!";
			
		} catch (Exception e) {
			return e.toString();	// TODO: handle exception
		}
	}

	
	

}
