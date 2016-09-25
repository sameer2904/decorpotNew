package com.decorpot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.decorpot.rest.model.CustomerDetails;
import com.decorpot.rest.model.CustomerSummary;
import com.decorpot.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService custServ;
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST)
	public int createNewCustomer(@RequestBody CustomerDetails customerDetails) throws Exception {
		return custServ.createCustomer(customerDetails);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public CustomerDetails getCustomerDetails(@PathVariable("id") Integer id) {
		return custServ.getCustomerDetails(id);
	}
	
	@RequestMapping(value = "/tasks/details", method = RequestMethod.GET)
	public List<CustomerSummary> getAllCustomersTaskSummary() {
		return custServ.getAllCustomerDetails();
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public CustomerDetails updateCustomer(@RequestBody CustomerDetails customerDetails) throws Exception {
		return custServ.updateCustomer(customerDetails);
	}
	
}
