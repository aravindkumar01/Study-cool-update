package com.studycool.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin("http://localhost:4200")
public class ViewController {

	
	 @RequestMapping(value = "/login", method = RequestMethod.GET)
	    public String loginPage() {
	        return "login";
	    }
	 

	/*
	 * @RequestMapping(value = "/login", method = RequestMethod.POST) public String
	 * loginPage(User user) { System.out.println(user.getEmail());
	 * 
	 * return "login"; }
	 */
	 
	@GetMapping("/AccessDenied")
	public String AccessDenied()
	{
		
		return "AccessDenied";
	}
	
	@RequestMapping("/sucess")
	public @ResponseBody  ResponseEntity<Boolean> sucess()
	{
		
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	@RequestMapping("/unsucess")
	public @ResponseBody boolean unsucess()
	{
		
		return false;
	}
	
	@GetMapping("/register")
	public String register()
	{
		
		return "register";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/admin/hello")
	public String helloSecure()
	{
		
		return "admin hello";
		
	}
	   
	  @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
	   @GetMapping("/user/hello")
		public String helloUser()
		{
			
			return "user hello";
			
		}
}
