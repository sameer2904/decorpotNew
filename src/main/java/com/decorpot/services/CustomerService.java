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
	
	public String createCustomer(com.decorpot.rest.model.CustomerDetails customerDetails) throws Exception {
		
		if(userService.findByUsername(customerDetails.getUserName()) != null) {
			CustomerDetails details = new CustomerDetails();
			details.setCustName(customerDetails.getCustName());
			details.setBhk(customerDetails.getBhk());
			details.setPhone(customerDetails.getPhone());
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
				custRepo.save(details);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return "success";
						
		}else {
			throw new Exception("Customer id exists");
		}
		
	}
	

}
