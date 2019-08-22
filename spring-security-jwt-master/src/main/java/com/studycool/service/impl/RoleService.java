package com.studycool.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studycool.Repo.RoleRepo;
import com.studycool.Repo.UserRoleRepo;
import com.studycool.model.Role;
import com.studycool.model.UserRole;

@Service
public class RoleService {

	
	@Autowired
	RoleRepo repo;
	
	
	
	public String newRole(Role role)
	
	{
		
		try {
			repo.save(role);
			return "added";
			
		} catch (Exception e) {
			return e.toString();
			// TODO: handle exception
		}
	}
	
	
	public String deleteUser(long id)
	{
		
		try {
			
			repo.deleteById(id);
			return "deleted";
		} catch (Exception e) {
			return e.toString();
			// TODO: handle exception
		}
	}


	public List<Role> getAllRole() {
		
		
		try {
			return repo.findAll();
		} catch (Exception e) {
			
			System.out.println(e);
		}
		return null;
	}

/*******************add user role****************/
	@Autowired
	UserRoleRepo roleRepo;
	public String newUserRole(long user_id, String role) {
		
		try {
			
			long role_id=repo.finId(role);
			UserRole r=new UserRole();
			
			r.setRole_id(role_id);
			r.setUser_id(user_id);
			roleRepo.save(r);
			return "user_role table added";
			
			
		} catch (Exception e) {
			
			return e.toString();
		}
	//	return role;
		
	}


	public void updateRole(long id, String role) {
		try {
			long role_id=repo.finId(role);
			UserRole r=new UserRole();
				r.setUser_id(id);
				r.setRole_id(role_id);			
			//roleRepo.updatUserRole(role_id,id);
				roleRepo.save(r);
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		
	}
}
