package com.studycool.Repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.studycool.model.Sylabus;

@Repository
public interface SylabusRepo extends JpaRepository<Sylabus, Long>{

	@Transactional
    @Modifying
    @Query("select s.id,s.topic,s.unit_number,s.subject_id from Sylabus s where s.subject_id = :id")	
	List<Sylabus> getallSylabusBySubject(long id);
	
	@Transactional
    @Modifying
    @Query("select s.id from Sylabus s where s.subject_id = :id")	
	List<Long> getallIdSylabusBySubject(long id);


	@Transactional
    @Modifying
    @Query("select s.id,s.topic,s.unit_number,s.subject_id from Sylabus s ")	
	List<Sylabus> getallSylabus();
	
    @Query("delete  from Sylabus s where s.subject_id = :id")		
	void deleteSylabusBySubject(Long id);
	
	Sylabus findById(long id);
	
	

}