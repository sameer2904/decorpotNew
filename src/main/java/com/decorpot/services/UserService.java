package com.decorpot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.decorpot.datasource.models.User;
import com.decorpot.datasource.models.UserRole;
import com.decorpot.datasource.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	public User findByUsername(String username) {
		
		User users = userRepo.findOne(username);
		return users;
	}

	public String createNewUser(com.decorpot.rest.model.User user) throws Exception {
		User newUser = new User();
		UserRole userRole = new UserRole();
		newUser.setPassword(user.getPassword());
		newUser.setUsername(user.getUserName());
		newUser.setEmail(user.getEmail());
		newUser.setName(user.getName());
		userRole.setUser(newUser);
		newUser.setPhone(user.getPhone());
		userRole.setRoleName(user.getRole());
		newUser.setUserRole(userRole);
		newUser.setActive(true);
		if (findByUsername(user.getUserName()) != null) {
			throw new Exception("user already exsists");
		}
		try {
			userRepo.save(newUser);
		} catch (Exception e) {
			// System.out.println("error object " + newUser.toString());
			throw e;
		}

		return "success";
	}

	/**
	 * authorization
	 */
	public boolean auth(String username, String password) {
		User user = findByUsername(username);
		if (user != null && password.equals(user.getPassword())) {
			return true;
		}
		return false;
	}

}
