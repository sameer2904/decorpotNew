package com.decorpot.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
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

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public List<Task> getTaskByUser(@RequestParam(value = "end", required = false) String end,
			@RequestParam(value = "start", required = false) String start,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "userId", required = false) String userId) {
		System.out.println("lets check" + status + start + end + userId);
		return taskService.getTaskByUser(userId, status, start, end);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Task getTaskById(@PathVariable("id") Integer id) {
		return taskService.getTaskById(id);
	}

	@RequestMapping(value = "/state/{state}", method = RequestMethod.GET)
	public List<Task> getTaskByState(@PathVariable("state") String state) {
		return taskService.getTaskByState(state);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Task createTask(@RequestBody Task task) {
		return taskService.createTask(task);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/reassign/{taskId}", method = RequestMethod.PUT)
	public Task reassignTask(@RequestBody Task task) throws Exception {
		return taskService.reassignTask(task.getTaskId(), task.getAssignedTo());
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/log_hours/{taskId}/{hours}", method = RequestMethod.PUT)
	public void addWorkingHours(@PathVariable("taskId") int taskId, @PathVariable("userid") int hours) {
		taskService.addWorkingHours(taskId, hours);
	}

	@RequestMapping(value = "/state/{state}/{customerId}", method = RequestMethod.POST)
	public List<Task> createTaskByState(@PathVariable("state") String state,
			@PathVariable("state") Integer customerId) {
		return taskService.createTasksForState(state, customerId);
	}

	@RequestMapping(value = "/customers/{customerId}", method = RequestMethod.GET)
	public List<Task> getTaskByCustomerId(@PathVariable("customerId") Integer customerId) {
		return taskService.getTaskByCustomerId(customerId);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/status/{taskId}/{status}", method = RequestMethod.PUT)
	public Task changeTaskStatus(@PathVariable("taskId") int taskId, @PathVariable("status") String status) {
		return taskService.changeTaskStatus(taskId, status);
	}



}
