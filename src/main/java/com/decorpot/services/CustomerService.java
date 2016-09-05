package com.decorpot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.decorpot.datasource.models.CustomerDetails;
import com.decorpot.datasource.repository.CustomerRepository;
import com.decorpot.rest.model.User;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository custRepo;
	
	@Autowired
	private UserService userService;
	
	public int createCustomer(com.decorpot.rest.model.CustomerDetails customerDetails) throws Exception {
		
		if(userService.findByUsername(customerDetails.getUserName()) == null) {
			CustomerDetails details = new CustomerDetails();
			details.setCustName(customerDetails.getCustName());
			details.setBhk(customerDetails.getBhk());
			details.setPhone(customerDetails.getPhone());
			details.setBudgetType(customerDetails.getBudgetType());
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
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw e;
			}
			
			return details.getId();
						
		}else {
			throw new Exception("Customer id exists");
		}
		
	}
	
	public com.decorpot.rest.model.CustomerDetails getCustomerDetails(Integer id) {
		CustomerDetails customerDetails = custRepo.findOne(id);
		com.decorpot.rest.model.CustomerDetails details = new com.decorpot.rest.model.CustomerDetails();
		details.setBhk(customerDetails.getBhk());
		details.setCustName(customerDetails.getCustName());
		details.setPhone(customerDetails.getPhone());
		details.setUserName(customerDetails.getUser().getUsername());
		
		return details;
	}	

}
