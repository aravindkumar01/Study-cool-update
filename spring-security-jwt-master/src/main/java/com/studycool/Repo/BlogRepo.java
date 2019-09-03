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
	
	
    @Query("select id,tittle,tags,createDateTime from Blogs")	
	List<Blogs> getBolgsForDash();
	

	
    @Modifying
    @Query("update Blogs b set b.status=0 where b.id= :id")	
	void delete(long id);
	
}
