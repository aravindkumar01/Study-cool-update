package com.studycool.Repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.studycool.model.UserDetails;



@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails, Long>{

	
	@Transactional
     @Modifying(clearAutomatically = true)
     @Query("UPDATE UserDetails d SET d.active=0 WHERE d.username = :username")  
	 void deleteByEmail(String username);

}