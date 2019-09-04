package com.studycool.controller;

import java.util.List;

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

import com.studycool.model.Role;
import com.studycool.service.impl.RoleService;


@Controller
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class RoleController {
	
	@Autowired
	RoleService service;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/role")
	public @ResponseBody String newRole(@RequestBody Role role)
	{
		try {
			
			return service.newRole(role);
		} catch (Exception e) {
			
			return e.toString();
			// TODO: handle exception
		}
		
		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/role")
	public @ResponseBody ResponseEntity<List<Role>> getall()
	{
		try {
			List<Role> role =service.getAllRole();
			if(role.isEmpty()) {
	            return new ResponseEntity(HttpStatus.NO_CONTENT);
	            // You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Role>>(role, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			// TODO: handle exception
		}
		return null;
		
	}
	
	

	@GetMapping("/role/regs")
	public @ResponseBody ResponseEntity<List<Role>> getAllRegs()
	{
		try {
			List<Role> role =service.getAllRoleReg();
			if(role.isEmpty()) {
	            return new ResponseEntity(HttpStatus.NO_CONTENT);
	            // You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Role>>(role, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			// TODO: handle exception
		}
		return null;
		
	}
	
	
	@GetMapping("/role/{id}")
	public @ResponseBody ResponseEntity<?> getRole(@PathVariable("id") long id)
	{
		try {
			Role role =service.getRole(id);
			if(role==null) {
	            return new ResponseEntity<String>("failled to retrive",HttpStatus.NO_CONTENT);
	            // You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<Role>(role, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			// TODO: handle exception
		}
		return null;
		
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/role/{id}")
	public @ResponseBody String deleteRole(@PathVariable("id") long id)
	{
		try {
			
			return service.deleteUser(id);
		} catch (Exception e) {
			
			return e.toString();
			// TODO: handle exception
		}
		
		
	}

}
