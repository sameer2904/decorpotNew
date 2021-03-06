package com.decorpot.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.decorpot.datasource.models.User;
import com.decorpot.datasource.models.UserRole;
import com.decorpot.datasource.repository.UserRepository;
import com.decorpot.utils.decorpotTx;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	public User findByUsername(String username) {
		System.out.println("username " + username);
		User users = userRepo.findByUsername(username);
		System.out.println("users " + users.getEmail());
		return users;
	}

	@decorpotTx
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
			newUser = userRepo.save(newUser);
		} catch (Exception e) {
			// System.out.println("error object " + newUser.toString());
			throw e;
		}

		return "success";
	}

	/**
	 * authorization
	 */
	@decorpotTx
	public com.decorpot.rest.model.User auth(String username, String password) {
		User user = findByUsername(username);
		com.decorpot.rest.model.User usr= new com.decorpot.rest.model.User();
		if (user != null && password.equals(user.getPassword())) {
			usr.setEmail(user.getEmail());
			usr.setName(user.getName());
			usr.setPhone(user.getPhone());
			usr.setUserName(user.getUsername());
			usr.setRole(user.getUserRole().getRoleName());
			usr.setRole(user.getUserRole().getRoleName());
			return usr;
		}
		return null;
	}
	
	@decorpotTx
	public List<com.decorpot.rest.model.User> getAllInternalUsers() {
		return userRepo.findInternalUsers().stream().map(u -> convertDBUserToRest(u)).collect(Collectors.toList());
	}
	
	private com.decorpot.rest.model.User convertDBUserToRest(User user) {
		com.decorpot.rest.model.User user2 = new com.decorpot.rest.model.User();
		user2.setEmail(user.getEmail());
		user2.setName(user.getName());
		user2.setPhone(user.getPhone());
		user2.setRole(user.getUserRole().getRoleName());
		user2.setUserName(user.getUsername());
		return user2;
	}

}
