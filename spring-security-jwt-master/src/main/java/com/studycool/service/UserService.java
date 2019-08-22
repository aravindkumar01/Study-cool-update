package com.studycool.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.studycool.model.User;

public interface UserService {

    User save(User user,String role);
    List<User> findAll();
    void delete(long id);
    User findOne(String username);

    User findById(Long id);
}
