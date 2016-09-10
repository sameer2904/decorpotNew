package com.decorpot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.decorpot.datasource.models.CustomerDetails;
import com.decorpot.datasource.repository.CustomerRepository;
import com.decorpot.rest.model.CustomerSummary;
import com.decorpot.rest.model.User;
import com.decorpot.utils.DecorpotConstants;
import com.decorpot.utils.decorpotTx;
import com.decorpot.rest.model.TaskSummary;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository custRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TaskService taskService;
	
	@decorpotTx
	public int createCustomer(com.decorpot.rest.model.CustomerDetails customerDetails) throws Exception {
		
		if(userService.findByUsername(customerDetails.getUserName()) == null) {
			CustomerDetails details = new CustomerDetails();
			details.setCustName(customerDetails.getCustName());
			details.setBhk(customerDetails.getBhk());
			details.setPhone(customerDetails.getPhone());
			details.setBudgetType(customerDetails.getBudgetType());
			details.setState("sales");
			User user = new User();
			user.setEmail(customerDetails.getUserName());
			user.setName(customerDetails.getCustName());
			user.setPassword("abc123");
			user.setPhone(customerDetails.getPhone());
			user.setRole("customer");
			user.setUserName(customerDetails.getUserName());
			try {
				userService.createNewUser(user);
				details.setUser(userService.findByUsername(customerDetails.getUserName()));
				details = custRepo.save(details);
				taskService.createTasksForState("sales", details.getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw e;
			}
			
			return details.getId();
						
		}else {
			throw new Exception("Customer id exists");
		}
		
	}
	
	@decorpotTx
	public com.decorpot.rest.model.CustomerDetails getCustomerDetails(Integer id) {
		CustomerDetails customerDetails = custRepo.findOne(id);
		com.decorpot.rest.model.CustomerDetails details = new com.decorpot.rest.model.CustomerDetails();
		details.setBhk(customerDetails.getBhk());
		details.setCustName(customerDetails.getCustName());
		details.setPhone(customerDetails.getPhone());
		details.setUserName(customerDetails.getUser().getUsername());
		details.setBudgetType(customerDetails.getBudgetType());
		details.setId(customerDetails.getId());
		details.setState(customerDetails.getState());
		
		return details;
	}
	
	@decorpotTx
	public List<CustomerSummary> getAllCustomerDetails() {
		List<CustomerDetails> customerDetails = (List<CustomerDetails>) custRepo.findAll();
		return customerDetails.stream().map(c -> {
			CustomerSummary customerSummary = new CustomerSummary();
			customerSummary.setCustomerId(c.getId());
			customerSummary.setState(c.getState());
			customerSummary.setCustomerName(c.getCustName());
			customerSummary.setTaskSummary(
			  taskService.getTaskByCustomerId(c.getId()).stream().map(t -> {
				TaskSummary taskSummary = new TaskSummary();
				if(t.getEstimatedDate().compareTo(new java.util.Date()) < 0) {
					taskSummary.setStatus(DecorpotConstants.taskStatus.red.toString());
				} else {
					taskSummary.setStatus(DecorpotConstants.taskStatus.orange.toString());
				}
				taskSummary.setTaskName(t.getTaskName());
				taskSummary.setEstimatedDate(t.getEstimatedDate());
				return taskSummary;
			}).collect(Collectors.toList()));
			return customerSummary;
		}).collect(Collectors.toList());
	}

}
