package com.decorpot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.decorpot.rest.model.User;
import com.decorpot.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST)
	public String createUser(@RequestBody User user) throws Exception {
		System.out.println(user.toString());
		return userService.createNewUser(user);
	}
	
	
    @RequestMapping(value= "/login", method = RequestMethod.POST)
	public User login(@RequestBody User user) throws Exception {
		return userService.auth(user.getUserName(), user.getPassword());
	}
	

	@ResponseStatus(HttpStatus.OK)
    @RequestMapping(value= "/internal", method = RequestMethod.GET)
	public List<User> getAllInternalUsers() throws Exception {
		return userService.getAllInternalUsers();
	}
	
}
