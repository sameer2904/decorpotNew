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
		List<User> users = userRepo.findByUsername(username);
		if (CollectionUtils.isEmpty(users) && users.size() > 0) {
			return users.get(0);
		}
		return null;
	}
	
	public String createNewUser(com.decorpot.rest.model.User user) throws Exception {
		User newUser = new User();
		UserRole userRole = new UserRole();
		newUser.setPassword(user.getPassword());
		newUser.setUsername(user.getUserName());
		newUser.setEmail(user.getEmail());
		newUser.setName(user.getName());
		userRole.setUser(newUser);
		userRole.setRoleName(user.getRole());
		newUser.setUserRole(userRole);
		if(findByUsername(user.getUserName()) != null) {
			throw new Exception("user already exsists");
		}
		try{
			userRepo.save(newUser);
		}catch(Exception e) {
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
