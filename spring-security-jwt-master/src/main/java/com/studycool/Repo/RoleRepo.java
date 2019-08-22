package com.studycool.Repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.studycool.model.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

	
	  	@Transactional
	   @Query("select r.id from Role r where  r.name= :role")	 	
	   long finId(String role);

}
