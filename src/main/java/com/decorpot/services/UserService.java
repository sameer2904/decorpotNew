package com.decorpot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.decorpot.datasource.models.User;
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
