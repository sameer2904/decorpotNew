package com.decorpot.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.decorpot.datasource.models.User;
import com.decorpot.datasource.repository.TaskRepository;
import com.decorpot.rest.model.Task;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private UserService userService;

	public List<Task> createTasksForState(String state) {
		return null;
	}

	public Task updateTask(Task task) {
		return null;
	}

	// TODO(shomil):additional logics for making more sense should be added.
	public List<Task> getAllTasks() {
		List<com.decorpot.datasource.models.Task> tasks = (List<com.decorpot.datasource.models.Task>) taskRepo
				.findAll();

		return tasks.stream().map(t -> {
			Task tsk = convertDbTaskToRest(t);
			if (t.getSubTasks() != null) {
				tsk.setSubTasks(t.getSubTasks().stream().map(st -> {
					return convertDbTaskToRest(st);
				}).collect(Collectors.toSet()));
			}
			return tsk;
		}).collect(Collectors.toList());
	}

	public List<Task> getTaskByState(String state) {
		List<com.decorpot.datasource.models.Task> tasks = (List<com.decorpot.datasource.models.Task>) taskRepo
				.findTaskByStateTag(state);

		return tasks.stream().map(t -> {
			Task tsk = convertDbTaskToRest(t);
			if (t.getSubTasks() != null) {
				tsk.setSubTasks(t.getSubTasks().stream().map(st -> {
					return convertDbTaskToRest(st);
				}).collect(Collectors.toSet()));
			}
			return tsk;
		}).collect(Collectors.toList());
	}

	// TODO(sameer): convert this to generics models seems to have same
	// structure.
	private Task convertDbTaskToRest(com.decorpot.datasource.models.Task task) {
		Task t = new Task();
		t.setCustomerId(task.getCustomerId());
		t.setEndDate(task.getEndDate());
		t.setEstimatedDate(task.getEstimatedDate());
		t.setHoursLogged(task.getHoursLogged());
		t.setParentId(task.getParentId().getId());
		t.setStartDate(task.getStartDate());
		t.setStateTag(task.getStateTag());
		t.setTaskName(task.getTaskName());

		return t;
	}

	public Task getTaskById(int taskId) {
		return convertDbTaskToRest(taskRepo.findOne(taskId));
	}
	
	//its primarily for creating sub task, later can be converted to creating parent task as well
	public Task createTask(Task task) {
		
		return task;
		
	}
	
	public void reassignTask(int taskId, String userId) {
		User user  = userService.findByUsername(userId);
		com.decorpot.datasource.models.Task tsk = taskRepo.findOne(taskId);
		if(user != null) {
			tsk.setAssignedTo(userId);
			taskRepo.save(tsk);
		}		
	}
	
	public void addWorkingHours(int taskId, int hours) {
		com.decorpot.datasource.models.Task tsk = taskRepo.findOne(taskId);
		tsk.setHoursLogged((tsk.getHoursLogged() != null ? tsk.getHoursLogged() : 0) + hours);
		taskRepo.save(tsk);
	}
}
