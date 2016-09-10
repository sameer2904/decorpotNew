package com.decorpot.rest.model;

import java.util.List;

public class CustomerSummary {
	
	private String customerName;
	private String state;
	private Integer customerId;
	private List<TaskSummary> taskSummary;
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public List<TaskSummary> getTaskSummary() {
		return taskSummary;
	}
	public void setTaskSummary(List<TaskSummary> taskSummary) {
		this.taskSummary = taskSummary;
	}
	

}
