package com.studycool.Repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.studycool.model.UserRole;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, Long>{

	
	@Transactional
    @Modifying
    @Query("UPDATE UserRole d SET d.role_id= :role_id  WHERE d.user_id= :id")  
	void updatUserRole(long role_id,long id);

}
