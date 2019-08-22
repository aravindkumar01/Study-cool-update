package com.studycool.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studycool.model.Univercity;

@Repository
public interface UnivercityRepo extends JpaRepository<Univercity, Long> {

	Univercity findById(long id);
	
}
