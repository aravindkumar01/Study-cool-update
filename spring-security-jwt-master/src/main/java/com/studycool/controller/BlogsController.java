package com.studycool.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.studycool.model.Blogs;
import com.studycool.service.impl.BlogService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value="/api")
public class BlogsController {
	
	@Autowired
	BlogService service;

	public static final Logger logger = LoggerFactory.getLogger(CourseController.class);
	
	@PostMapping("/blogs")
	@PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
	public @ResponseBody ResponseEntity<?> newSave(@RequestBody Blogs blog)
	{
		try {
			boolean result=service.newSave(blog);
	        if (!result) {
	            return new ResponseEntity<String>("unable to post", HttpStatus.OK);
	            // You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<String>("your blog posted!", HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			// TODO: handle exception
		}
		return null;
		
	}

	
	
	@GetMapping("/blogs/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
	public @ResponseBody ResponseEntity<?> getOne(@PathVariable("id") long id	)
	{
		try {
			Blogs blogs =service.findById(id);
	        if (blogs==null) {
	            return new ResponseEntity(HttpStatus.NO_CONTENT);
	            // You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<Blogs>(blogs, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			// TODO: handle exception
		}
		return null;
		
	}
	
	
	

	@GetMapping("/blogs")
	@PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
	public @ResponseBody ResponseEntity<?> getAll()
	{
		try {
			List<Blogs> blogs =service.findAll();
	        if (blogs==null) {
	            return new ResponseEntity<String>("unable to get!", HttpStatus.OK);
	            // You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Blogs>>(blogs, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			// TODO: handle exception
			return null;
		}
		
		
	}

	
	@GetMapping("/blogs/dash")
	@PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
	public @ResponseBody ResponseEntity<?> getAllDash()
	{
		try {
			List<Blogs> blogs =service.getForDash();
	        if (blogs==null) {
	            return new ResponseEntity<String>("unable to get!", HttpStatus.OK);
	            // You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Blogs>>(blogs, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			// TODO: handle exception
			return null;
		}
		
		
	}
	
	
	@DeleteMapping("/blogs/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
	public @ResponseBody ResponseEntity<?> delete(@PathVariable("id") long id){
		try {
			boolean result =service.delete(id);
	        if (!result) {
	            return new ResponseEntity<String>("unable to delete!", HttpStatus.OK);
	            // You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<String>("deleted", HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			// TODO: handle exception
			return null;
		}
		
		
	}
	
	
	

}
