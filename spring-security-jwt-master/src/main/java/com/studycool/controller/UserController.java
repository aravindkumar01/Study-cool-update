package com.studycool.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.studycool.model.User;
import com.studycool.model.UserDetails;
import com.studycool.service.UserService;
import com.studycool.service.impl.UserDetailsService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserDetailsService details;

    //@Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/api/users", method = RequestMethod.GET)
    public List<User> listUser(){
        return userService.findAll();
    }

    //@Secured("ROLE_USER")
    //@PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/api/user/login/{id}", method = RequestMethod.GET)
    public User getOne(@PathVariable(value = "id") Long id){
    	
        return userService.findById(id);
    }

    
    

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public  @ResponseBody ResponseEntity<String> saveUser(@RequestBody UserDetails user){
    	//return details.newUser(user);
    	return new ResponseEntity<String>(details.newUser(user), HttpStatus.OK);
       // return userService.save(user);
    }

    @RequestMapping(value="/api/user", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<String> adminUser(@RequestBody UserDetails user){
    	return new ResponseEntity<String>(details.newUser(user), HttpStatus.OK);
       // return userService.save(user);
    }
    
    
    
   
  	  	
 

}
