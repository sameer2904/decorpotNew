package com.decorpot.rest.model;

import java.util.Date;
import java.util.Set;


public class Task {

	private String taskName;
	private Date startDate;
	private Date endDate;
	private Date estimatedDate;
	private int hoursLogged;
	private String stateTag;
	private boolean forCustomer;
	private String customerId;
	private Integer parentId;
	private String assignedTo;
	private Set<Task> subTasks;
	
	//this is computed status showing if any deadline was missed or task end date is coming close. Enum created.
	private String taskStatus;
	
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getEstimatedDate() {
		return estimatedDate;
	}
	public void setEstimatedDate(Date estimatedDate) {
		this.estimatedDate = estimatedDate;
	}
	public int getHoursLogged() {
		return hoursLogged;
	}
	public void setHoursLogged(int hoursLogged) {
		this.hoursLogged = hoursLogged;
	}
	public String getStateTag() {
		return stateTag;
	}
	public void setStateTag(String stateTag) {
		this.stateTag = stateTag;
	}
	public boolean isForCustomer() {
		return forCustomer;
	}
	public void setForCustomer(boolean forCustomer) {
		this.forCustomer = forCustomer;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Set<Task> getSubTasks() {
		return subTasks;
	}
	public void setSubTasks(Set<Task> subTasks) {
		this.subTasks = subTasks;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	
	
	
}
