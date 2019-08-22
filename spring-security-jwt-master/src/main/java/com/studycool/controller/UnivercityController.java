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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studycool.config.CustomErrorType;
import com.studycool.model.Univercity;
import com.studycool.service.impl.UnivercityService;

@Controller
@RequestMapping(value="/api")
@CrossOrigin("http://localhost:4200")
public class UnivercityController {
	

	public static final Logger logger = LoggerFactory.getLogger(UnivercityController.class);
	@Autowired
	UnivercityService service;
	

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/univercity")
	//public @ResponseBody String add(@RequestBody Univercity univercity)
	 public ResponseEntity<?> getSearchResultViaAjax(@RequestBody Univercity univercity)
	{
		try {		
				
			 
			  return ResponseEntity.ok(service.add(univercity));
		} catch (Exception e) {
			// TODO: handle exception
		System.out.println(e);
		}
		return null;
		
	}
	


		
	@GetMapping("/univercity")
	public @ResponseBody ResponseEntity<List<Univercity>> getall()
	{
		try {
			List<Univercity> univercity =service.getAllUnivercity();
	        if (univercity.isEmpty()) {
	            return new ResponseEntity(HttpStatus.NO_CONTENT);
	            // You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Univercity>>(univercity, HttpStatus.OK);
			
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		return null;
		
	}
	
	
	
	
	 @GetMapping("/univercity/{id}")
	    public ResponseEntity<?> getUnivercity(@PathVariable("id") long id) {
	     
		 try {
			 //logger.info("Fetching course with id {}", id);
			 Univercity univercity=service.getunivercityById(id);
		        if (univercity == null) {
		            logger.error("course with id {} not found.", id);
		            return new ResponseEntity(new CustomErrorType("Cousre with id " + id 
		                    + " not found"), HttpStatus.NOT_FOUND);
		        }
		        return new ResponseEntity<Univercity>(univercity, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return null;
			// TODO: handle exception
		}
		
		   
	    }
	 @PreAuthorize("hasAnyRole('ADMIN')")
	 @PutMapping("/univericty")
		public @ResponseBody ResponseEntity<String> delete(@RequestBody Univercity univercity)
		{
			try {
				
				//return service.updateUnivercity(univercity);
				 return new ResponseEntity<String>( service.updateUnivercity(univercity), HttpStatus.OK);
				
			} catch (Exception e) {
				return null;
				// TODO: handle exception
			}
		}
	 
	 
		@PreAuthorize("hasAnyRole('ADMIN')")
		@DeleteMapping("/univercity/{id}")
		public @ResponseBody ResponseEntity<Boolean> delete(@PathVariable("id") long id)
		{
			try {
				
				//return service.delete(id);
				 return ResponseEntity.ok( service.delete(id));
				
			} catch (Exception e) {
				return null;
				// TODO: handle exception
			}
		}
}
