package com.decorpot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.decorpot.datasource.repository.TaskRepository;
import com.decorpot.rest.model.Task;

@Service
public class TaskService {

	
	@Autowired
	private TaskRepository taskRepo;
	
	
	public Task getTaskById(int taskId) {
		return null;
	}
	
	public List<Task> createTasksForState(String state) {
		return null;
	}
	
	public Task updateTask(Task task) {
		return null;
	}
}
