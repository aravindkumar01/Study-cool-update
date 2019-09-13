package com.studycool.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.studycool.Repo.CourseRepo;
import com.studycool.Repo.UnivercityRepo;
import com.studycool.Repo.UserDetailsRepo;
import com.studycool.Repo.UsersRepo;
import com.studycool.model.Course;
import com.studycool.model.Univercity;
import com.studycool.model.User;
import com.studycool.model.UserDetails;
import com.studycool.service.EmailServiceImp;

@Service
public class UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	@Autowired
	UserDetailsRepo repo;

	@Autowired
	UserServiceImpl userService;

	@Autowired
	CourseRepo course;

	@Autowired
	UsersRepo userRepo;

	@Autowired
	UnivercityRepo uniRepo;

	public String newUser(UserDetails user) {
		try {

			Course c = course.findById(user.getCourse_id());
			user.setCourse(c.getName());

			Univercity uni = uniRepo.findById(user.getUnivercity_id());
			user.setUnivercity(uni.getName());

			if (user.getId() == 0) {
				if (userService.findByEmail(user.getUsername()) != null) {
					return "Email already registerd";
				}

			}

			// insert username,password in users table
			User u = new User();
			if (user.getId() != 0) // update in user details
			{
				long id = userService.findByid(user.getUsername());
				u.setId(id);
			}

			repo.save(user); // insert to user details table
			String pass = String.valueOf(user.getMobile()).substring(6);
			u.setUsername(user.getUsername());
			u.setPassword(user.getLast_name() + pass);
			String msg = "Your userpassword:" + user.getLast_name() + pass;
			EmailServiceImp.sendSimpleMessage(user.getUsername(), "Welcome to StudyCool!", msg);
			userService.save(u, user.getRole());

			// end
			return "Sucess";
		} catch (Exception e) {

			return e.toString();
		}
	}

	public String deleteUser(String username)

	{
		try {

			repo.deleteByEmail(username);
			userService.deleteByUser(username);
			return "deleted";
		} catch (Exception e) {
			return e.toString();
			// TODO: handle exception
		}
	}

	public UserDetails findByUsername(String username)

	{
		try {

			return repo.findByUsername(username);

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}

	public List<UserDetails> getAllUsers() {

		try {

			return repo.findAll();
		} catch (Exception e) {
			System.out.println("user_details find all:" + e);
			// TODO: handle exception
		}
		return null;
	}

	public Optional<UserDetails> findById(long id) {
		try {
			return repo.findById(id);
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}
	}

	public boolean changePassword(Map<String, String> value) {

		try {

			User u = userRepo.findByUsername(value.get("username"));

			if (bcryptEncoder.matches(value.get("oldpassword"), u.getPassword())) {

				u.setPassword(bcryptEncoder.encode(value.get("newpassword")));
				userRepo.save(u);
				return true;
			}

			return false;
			// bcryptEncoder.encode(rawPassword)
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return false;
	}

	public List<User> getAllPass() {

		try {

			return userRepo.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			// TODO: handle exception
		}
	}

	public User getUser(long id) {
		try {

			return userRepo.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			// TODO: handle exception
		}
	}

	public boolean updateUser(User user) {
		try {

			user.setPassword(bcryptEncoder.encode(user.getPassword()));
			userRepo.save(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
			// TODO: handle exception
		}
	}

	public boolean forgetPassword(String username) {
		try {

			User u = userRepo.findByUsername(username);

			if (u != null) {
				String a[] = username.split("@");
				u.setPassword(bcryptEncoder.encode(a[0]));
				EmailServiceImp.sendSimpleMessage(username, "Temporary Passord", "Your Passeord:"+a[0]);
				userRepo.save(u);
				return true;
			}

			// EmailServiceImp.sendSimpleMessage(username, subject, message);
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
			// TODO: handle exception
		}
	}

}
