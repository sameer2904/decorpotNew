package com.decorpot.services;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.decorpot.datasource.models.TaskTemplate;
import com.decorpot.datasource.models.User;
import com.decorpot.datasource.repository.TaskRepository;
import com.decorpot.datasource.repository.TaskTemplateRepo;
import com.decorpot.rest.model.CustomerDetails;
import com.decorpot.rest.model.Task;
import com.decorpot.utils.DecorpotConstants;
import com.decorpot.utils.decorpotTx;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private TaskTemplateRepo taskTemplateRepo;

	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ApplicationMailer emailer;

	@decorpotTx
	public List<Task> createTasksForState(String state, int customerId) {
		
		List<TaskTemplate> templates = taskTemplateRepo.findByState(state);
		
		return templates.stream().map(t -> {
			com.decorpot.datasource.models.Task task  = new com.decorpot.datasource.models.Task();
			java.sql.Date estimatedDate = new java.sql.Date((new java.util.Date((new java.util.Date()).getTime() + 
					TimeUnit.DAYS.toMillis( t.getEstDays() ))).getTime());
			String body = "Task - " + t.getTaskName() + 
					" has been created for you. Kindly go to the link to check. http://decorpot.com/#/customers/" + customerId;
			task.setAssignedTo(t.getDefaultAssignee());
			task.setCustomerId(customerId);
			task.setForCustomer(true);
			task.setHoursLogged(0);
			task.setStateTag(state);
			task.setStatus(DecorpotConstants.status.open.toString());
			task.setTaskName(t.getTaskName());
			task.setStartDate(new java.sql.Date((new java.util.Date()).getTime()));
			task.setEstimatedDate(estimatedDate);
			
			
			task = taskRepo.save(task);
			emailer.sendViaGMail(t.getDefaultAssignee(), "task - " + t.getTaskName() +  " has been created for you. estimated date - " 
			+ estimatedDate.toString(), body);
			
			return convertDbTaskToRest(task);
		}).collect(Collectors.toList());
		
	}
	
	@decorpotTx
	public Task changeTaskStatus(int taskId, String status) {
		com.decorpot.datasource.models.Task task  = taskRepo.findOne(taskId);
		String body = "Task - " + task.getTaskName() + 
				" status got changed from " + task.getStatus() + " to " + status  
				+ ". Kindly go to the link to check. http://decorpot.com/#/customers/" + task.getCustomerId();
		
		task.setStatus(status);
		if(status.equals(DecorpotConstants.status.wip.toString())) {
			task.setStatus(status);
			task = taskRepo.save(task);
		}
		
		if(status.equals(DecorpotConstants.status.closed.toString())) {
			task.setStatus(status);
			task.setEndDate(new java.sql.Date((new java.util.Date()).getTime()));
			task = taskRepo.save(task);
		}
		
		
		emailer.sendViaGMail(task.getAssignedTo(), "task - " + task.getTaskName() +  " status got changed" 
				, body);
		
		return convertDbTaskToRest(task);
	}

	@decorpotTx
	public Task updateTask(Task task) {
		return null;
	}

	// TODO(shomil):additional logics for making more sense should be added.
	@decorpotTx
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

	@decorpotTx
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

	@decorpotTx
	public Task getTaskById(int taskId) {
		return convertDbTaskToRest(taskRepo.findOne(taskId));
	}

	// its primarily for creating sub task, later can be converted to creating
	// parent task as well
	@decorpotTx
	public Task createTask(Task task) {

		com.decorpot.datasource.models.Task t = convertRestTaskToDb(task);

		if (task.getParentId() != null) {
			com.decorpot.datasource.models.Task parentTask = taskRepo.findOne(task.getParentId());
			t.setParentId(parentTask);
		}

		t = taskRepo.save(t);

		task.setTaskId(t.getId());
		return task;

	}

	@decorpotTx
	public Task reassignTask(int taskId, String userId) throws Exception {
		User user = userService.findByUsername(userId);
		com.decorpot.datasource.models.Task task = taskRepo.findOne(taskId);
		if (user == null) {
			throw new Exception("wrong userid");
		}
		
		task.setAssignedTo(userId);
		
		String body = "Task - " + task.getTaskName() + 
				" now has been assigned to you" 
				+ ". Kindly go to the link to check. http://decorpot.com/#/customers/" + task.getCustomerId();
		emailer.sendViaGMail(task.getAssignedTo(), "task - " + task.getTaskName() +  " has been assigned to you" 
				, body);
		
		return convertDbTaskToRest(taskRepo.save(task));
		
		
	}

	@decorpotTx
	public void addWorkingHours(int taskId, int hours) {
		com.decorpot.datasource.models.Task tsk = taskRepo.findOne(taskId);
		tsk.setHoursLogged((tsk.getHoursLogged() != null ? tsk.getHoursLogged() : 0) + hours);
		taskRepo.save(tsk);
	}
	
	@decorpotTx
	public List<Task> getTaskByCustomerId(Integer customerId) {
		
		CustomerDetails custdetails = customerService.getCustomerDetails(customerId);
		List<com.decorpot.datasource.models.Task> tasks = taskRepo.findTaskByCustomerIdAndStateTag(customerId, custdetails.getState());
		
		if(tasks != null) {
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
		
		return null;
		
	}
	

	// TODO(sameer): convert this to generics models seems to have same
	// structure.
	private Task convertDbTaskToRest(com.decorpot.datasource.models.Task task) {
		Task t = new Task();
		t.setCustomerId(task.getCustomerId());
		t.setEndDate(task.getEndDate());
		t.setEstimatedDate(task.getEstimatedDate());
		t.setHoursLogged(task.getHoursLogged());
		t.setParentId(task.getParentId() != null ? task.getParentId().getId() : null);
		t.setStartDate(task.getStartDate());
		t.setStateTag(task.getStateTag());
		t.setTaskName(task.getTaskName());
		t.setAssignedTo(task.getAssignedTo());
		t.setForCustomer(task.isForCustomer());
		t.setTaskStatus(task.getStatus());
		t.setTaskId(task.getId());

		return t;
	}

	private com.decorpot.datasource.models.Task convertRestTaskToDb(Task task) {
		com.decorpot.datasource.models.Task t = new com.decorpot.datasource.models.Task();
		t.setCustomerId(task.getCustomerId());
		t.setEndDate(new java.sql.Date((task.getEndDate()).getTime()));
		t.setEstimatedDate(new java.sql.Date((task.getEstimatedDate()).getTime()));
		t.setHoursLogged(task.getHoursLogged());
		t.setStartDate(new java.sql.Date((task.getStartDate()).getTime()));
		t.setStateTag(task.getStateTag());
		t.setTaskName(task.getTaskName());
		t.setTaskName(task.getTaskName());
		t.setAssignedTo(task.getAssignedTo());
		t.setForCustomer(task.isForCustomer());
		t.setStatus(task.getTaskStatus());

		return t;
	}
}
