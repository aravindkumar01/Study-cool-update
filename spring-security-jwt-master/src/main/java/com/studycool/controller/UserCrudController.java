package com.studycool.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studycool.Repo.UsersRepo;
import com.studycool.model.UserDetails;
import com.studycool.service.impl.UserDetailsService;




@Controller
@CrossOrigin("http://localhost:4200")
public class UserCrudController {
	@Autowired
	UserDetailsService service;
	

	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/api/user")
	public @ResponseBody ResponseEntity<List<UserDetails>> getall()
	{
		try {
			List<UserDetails> user =service.getAllUsers();
	        if (user.isEmpty()) {
	            return new ResponseEntity(HttpStatus.NO_CONTENT);
	            // You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<UserDetails>>(user, HttpStatus.OK);
			
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		return null;
		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/api/user/{id}")
	public @ResponseBody Optional<UserDetails> getuser(@PathVariable("id")long id)
	{
		try {
			 
	        return service.findById(id);
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
			// TODO: handle exception
		}
	
		
	}
	
	   @PreAuthorize("hasAnyRole('ADMIN')")
	   @RequestMapping(value="/api/user/{username}", method = RequestMethod.DELETE)
	  	public @ResponseBody String deleteUser(@PathVariable("username") String username)

	  	{
	  		try {
	  			
	  			return service.deleteUser(username);
	  		} catch (Exception e) {
	  			return e.toString();
	  			// TODO: handle exception
	  		}
	  	}	
	   
	   
	   @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
		@GetMapping("/api/user/get/{username}")
		public @ResponseBody ResponseEntity<UserDetails> getUser(@PathVariable("username") String username)
		{
			try {
				UserDetails user =service.findByUsername(username);
		        if (user==null) {
		            return new ResponseEntity(HttpStatus.NO_CONTENT);
		            // You many decide to return HttpStatus.NOT_FOUND
		        }
		        return new ResponseEntity<UserDetails>(user, HttpStatus.OK);
				
			} catch (Exception e) {
				System.out.println(e);
				// TODO: handle exception
			}
			return null;
			
		}
		
	   @PreAuthorize("hasAnyRole('ADMIN','STUDENT','STAFF')")
 		@PostMapping("/api/user/password")
 		public @ResponseBody ResponseEntity<?> getUser(@RequestBody Map<String,String> value)
 		{
 			try {
 				
 				
 			
 			
 				return new ResponseEntity<Boolean>(service.changePassword(value), HttpStatus.OK);
 			} catch (Exception e) {
 				e.printStackTrace();
 				
 			}
 			return null;
 			
 		}

}
