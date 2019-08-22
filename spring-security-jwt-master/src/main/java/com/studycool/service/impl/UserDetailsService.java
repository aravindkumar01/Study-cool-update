package com.studycool.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studycool.Repo.UserDetailsRepo;
import com.studycool.model.User;
import com.studycool.model.UserDetails;
import com.studycool.service.UserService;


@Service
public class UserDetailsService {
	@Autowired
	UserDetailsRepo repo;
	
	@Autowired
	UserServiceImpl userService;
	
	public String newUser(UserDetails user)
	{
		try {
			
			if(user.getId()==0)
			{
				if(userService.findByEmail(user.getUsername())!=null)
				{
					return  "Email already registerd";	
				}
				
			}
			
					
			//insert username,password in users table
			User u=new User();
			 if(user.getId()!=0) //update in user details
			 {
				 long id=userService.findByid(user.getUsername());
				u.setId(id); 
			 }
				
			 repo.save(user);	//insert to user details table
				String pass=String.valueOf(user.getMobile()).substring(6);	
					u.setUsername(user.getUsername());
					u.setPassword(user.getLast_name()+pass);
			userService.save(u,user.getRole());
			
			
				//end
			return "Sucess";
		} catch (Exception e) {
			
			return e.toString();
		}
	}
	
	
	
	
	public String deleteUser(String username)
	
	{
		try {
			
			repo.deleteByEmail(username);
			userService.deleteByUser(username);
			return "deleted";
		} catch (Exception e) {
			return e.toString();
			// TODO: handle exception
		}
	}




	public List<UserDetails> getAllUsers() {
		
		
		try {
			
			return repo.findAll();
		} catch (Exception e) {
			System.out.println("user_details find all:"+e);
			// TODO: handle exception
		}
		return null;
	}




	public Optional<UserDetails> findById(long id) {
		try {
			return repo.findById(id);
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}
	}
}
