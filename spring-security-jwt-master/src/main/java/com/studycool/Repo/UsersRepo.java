package com.studycool.Repo;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.studycool.model.User;

@Repository
public interface UsersRepo extends JpaRepository<User, Long> {

	
	   @Transactional
	   @Query("select u from User u where u.active=1 and u.username= :username")
	   Optional<User> findByEma(String username);
	   	
	   @Transactional
	   @Query("select u.id from User u where u.active=1 and u.username= :email")	   
	   long findId(String email);
	   
	   User findByUsername(String username);
	   
	   	@Transactional
	     @Modifying
	     @Query("UPDATE User d SET d.active=0 WHERE d.id= :id")  
		 void deleteById(long id);
	   	
	 	@Transactional
	     @Modifying
	     @Query("UPDATE User d SET d.active=0 WHERE d.username= :email")  
		 void deleteByEmail(String email);



}