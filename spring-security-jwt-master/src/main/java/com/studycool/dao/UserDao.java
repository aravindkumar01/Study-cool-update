package com.studycool.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.studycool.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
   // @Query("select u from User u where u.active=1")
    User findByUsername(String username);
}
