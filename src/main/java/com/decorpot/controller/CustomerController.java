package com.decorpot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.decorpot.rest.model.CustomerDetails;
import com.decorpot.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService custServ;
	
	@RequestMapping(method = RequestMethod.POST)
	public String createNewCustomer(@RequestBody CustomerDetails customerDetails) throws Exception {
		return custServ.createCustomer(customerDetails);
	}
	

}
