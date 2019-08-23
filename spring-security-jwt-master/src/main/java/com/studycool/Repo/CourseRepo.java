package com.studycool.Repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.studycool.model.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course,Long> {

	/*@Transactional
    @Modifying
    @Query("select c from Course c where c.univercity_id = :id")
	List<Course> courseByUnivercity(long id);

}*/
	
	@Transactional
    @Modifying
    @Query("select c.id from Course c where c.u_id = :id")	
	List<Long> getallCourseIdByUnivericty(long id);
	

	@Transactional
    @Modifying
    @Query("delete from Course c where c.u_id = :id")	
	void deleteCourseByUnivercity(long id);
	
	
	Course findByName(String name);
	Course findById(long id);

}