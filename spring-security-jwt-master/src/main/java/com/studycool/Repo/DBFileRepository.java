package com.studycool.Repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.studycool.model.DBFile;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, Long>{
	
	
	DBFile findById(long id);
	
	
	@Query("select d.id,d.fileName,d.option,d.description,d.status from DBFile d")
	List<DBFile> getallFiles();
	
	@Transactional 
	@Modifying
	@Query("update DBFile d set d.status=:status where d.id=:id")
	void updateStatus(int status,long id);
}
