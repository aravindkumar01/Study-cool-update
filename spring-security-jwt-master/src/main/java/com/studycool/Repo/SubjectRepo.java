package com.studycool.Repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.studycool.model.Subject;

@Repository
public interface SubjectRepo extends JpaRepository<Subject, Long> {

	
	
	@Transactional
    @Modifying
    @Query("DELETE FROM Subject s WHERE s.course_id=:id")
	void deleteSubjectByCourse(long id);

	
	@Transactional
    @Modifying
    @Query("select s.id from Subject s where s.course_id = :id")	
	List<Long> getAllSubjectId(long id);

	@Transactional
    @Modifying
    @Query("select s from Subject s where s.course_id = :id")		
	List<Subject> getAllSubject(long id);

	
	Subject findById(long id);
	
	@Transactional
    @Modifying
    @Query("select s from Subject s where s.course_id = :cid and s.year= :year")		
	List<Subject> getAllSubjectDash(long cid,int year);

	@Transactional
    @Modifying
    @Query("select s.name from Subject s where s.course_id = :cid and s.year= :year")		
	List<String> getAllSubjectTree(long cid,int year);
	
	
	@Transactional   
    @Query("select s from Subject s where s.course_id = :cid and s.name= :name")		
	Subject pagenumber(long cid,String name);

}