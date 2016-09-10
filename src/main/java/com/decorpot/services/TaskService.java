package com.decorpot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.decorpot.datasource.models.User;
import com.decorpot.datasource.repository.TaskRepository;
import com.decorpot.rest.model.CustomerDetails;
import com.decorpot.rest.model.Task;
import com.decorpot.utils.DecorpotConstants;
import com.decorpot.utils.decorpotTx;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepo;

	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomerService customerService;

	@decorpotTx
	public List<Task> createTasksForState(String state, int customerId) {
		
		List<Task> tasks = new ArrayList<>();
		if(state == "sales") {
			com.decorpot.datasource.models.Task clientMeeting  = new com.decorpot.datasource.models.Task();
			clientMeeting.setAssignedTo("sales@decorpot.com");
			clientMeeting.setCustomerId(customerId);
			clientMeeting.setEstimatedDate(new java.sql.Date((new java.util.Date((new java.util.Date()).getTime() + TimeUnit.DAYS.toMillis( 5 ))).getTime()));
			clientMeeting.setForCustomer(true);
			clientMeeting.setHoursLogged(0);
			clientMeeting.setStartDate(new java.sql.Date((new java.util.Date()).getTime()));
			clientMeeting.setStateTag("sales");
			clientMeeting.setStatus(DecorpotConstants.taskStatus.orange.toString());
			clientMeeting.setTaskName("Client Meeting/Discussion");
			
			clientMeeting = taskRepo.save(clientMeeting);
			
			tasks.add(convertDbTaskToRest(clientMeeting));
			
			
			com.decorpot.datasource.models.Task initailQuote  = new com.decorpot.datasource.models.Task();
			initailQuote.setAssignedTo("sales@decorpot.com");
			initailQuote.setCustomerId(customerId);
			initailQuote.setEstimatedDate(new java.sql.Date((new java.util.Date((new java.util.Date()).getTime() + TimeUnit.DAYS.toMillis( 6 ))).getTime()));
			initailQuote.setForCustomer(true);
			initailQuote.setHoursLogged(0);
			initailQuote.setStartDate(new java.sql.Date((new java.util.Date()).getTime()));
			initailQuote.setStateTag("sales");
			initailQuote.setStatus(DecorpotConstants.taskStatus.orange.toString());
			initailQuote.setTaskName("initial quote");
			
			initailQuote = taskRepo.save(initailQuote);
			
			tasks.add(convertDbTaskToRest(initailQuote));
			
			com.decorpot.datasource.models.Task siteVisit  = new com.decorpot.datasource.models.Task();
			siteVisit.setAssignedTo("sales@decorpot.com");
			siteVisit.setCustomerId(customerId);
			siteVisit.setEstimatedDate(new java.sql.Date((new java.util.Date((new java.util.Date()).getTime() + TimeUnit.DAYS.toMillis( 13 ))).getTime()));
			siteVisit.setForCustomer(true);
			siteVisit.setHoursLogged(0);
			siteVisit.setStartDate(new java.sql.Date((new java.util.Date()).getTime()));
			siteVisit.setStateTag("sales");
			siteVisit.setStatus(DecorpotConstants.taskStatus.orange.toString());
			siteVisit.setTaskName("site visit");
			
			siteVisit = taskRepo.save(siteVisit);
			
			tasks.add(convertDbTaskToRest(siteVisit));
			
			com.decorpot.datasource.models.Task finalDiscussion  = new com.decorpot.datasource.models.Task();
			finalDiscussion.setAssignedTo("sales@decorpot.com");
			finalDiscussion.setCustomerId(customerId);
			finalDiscussion.setEstimatedDate(new java.sql.Date((new java.util.Date((new java.util.Date()).getTime() + TimeUnit.DAYS.toMillis( 18 ))).getTime()));
			finalDiscussion.setForCustomer(true);
			finalDiscussion.setHoursLogged(0);
			finalDiscussion.setStartDate(new java.sql.Date((new java.util.Date()).getTime()));
			finalDiscussion.setStateTag("sales");
			finalDiscussion.setStatus(DecorpotConstants.taskStatus.orange.toString());
			finalDiscussion.setTaskName("final discussiont");
			
			finalDiscussion = taskRepo.save(finalDiscussion);
			
			tasks.add(convertDbTaskToRest(finalDiscussion));
			
			com.decorpot.datasource.models.Task signingAdvance  = new com.decorpot.datasource.models.Task();
			signingAdvance.setAssignedTo("sales@decorpot.com");
			signingAdvance.setCustomerId(customerId);
			signingAdvance.setEstimatedDate(new java.sql.Date((new java.util.Date((new java.util.Date()).getTime() + TimeUnit.DAYS.toMillis( 22 ))).getTime()));
			signingAdvance.setForCustomer(true);
			signingAdvance.setHoursLogged(0);
			signingAdvance.setStartDate(new java.sql.Date((new java.util.Date()).getTime()));
			signingAdvance.setStateTag("sales");
			signingAdvance.setStatus(DecorpotConstants.taskStatus.orange.toString());
			signingAdvance.setTaskName("signing advance");
			
			signingAdvance = taskRepo.save(signingAdvance);
			
			tasks.add(convertDbTaskToRest(signingAdvance));
		}
		return tasks;
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
	public void reassignTask(int taskId, String userId) {
		User user = userService.findByUsername(userId);
		com.decorpot.datasource.models.Task tsk = taskRepo.findOne(taskId);
		if (user != null) {
			tsk.setAssignedTo(userId);
			taskRepo.save(tsk);
		}
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
