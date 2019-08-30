package com.studycool.Repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.studycool.model.Blogs;

@Repository
public interface BlogRepo extends JpaRepository<Blogs, Long>{

	
	Blogs findById(long id);
	
	@Transactional   
    @Query("select b.id,b.tittle,b.tags from Blogs b")	
	List<Blogs> getBolgsForDash();
	

	@Transactional
    @Modifying
    @Query("update Blogs b set b.status=0 where b.id= :id")	
	void delete(long id);
	
}
