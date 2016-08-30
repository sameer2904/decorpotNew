package com.decorpot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.decorpot.rest.model.Task;
import com.decorpot.services.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Task> getAllTask() {
		return taskService.getAllTasks();
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Task getTaskById(@PathVariable("id") Integer id) {
		return taskService.getTaskById(id);
	}
	
	@RequestMapping(value = "/state/{state}", method = RequestMethod.GET)
	public List<Task> getTaskByState(@PathVariable("state") String state) {
		return taskService.getTaskByState(state);
	}

}
