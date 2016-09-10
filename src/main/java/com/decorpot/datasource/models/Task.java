package com.decorpot.datasource.models;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name= "TASK")
public class Task {

	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	@Column(name = "TASK_NAME")
	private String taskName;
	
	@Column(name = "START_DATE", columnDefinition = "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private Date startDate = new java.sql.Date((new java.util.Date()).getTime());
	
	@Column(name = "END_DATE")
	private Date endDate;
	
	@Column(name = "ESTIMATED_DATE")
	private Date estimatedDate;
	
	@Column(name = "HOURS")
	private Integer hoursLogged;
	
	@Column(name = "STATE_TAG")
	private String stateTag;
	
	@Column(name = "FOR_CUSTOMER")
	@Type(type = "yes_no")
	private boolean forCustomer;
	
	@Column(name = "CUSTOMER_ID")
	private Integer customerId;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="PARENT_ID")
	private Task parentId;

	@OneToMany(mappedBy="parentId")
	private Set<Task> subTasks = new HashSet<>();
	
	@OneToMany(mappedBy="task", cascade=CascadeType.ALL)
	private Set<TaskUpdates> updates = new HashSet<>();
	
	@Column(name = "ASSIGNED_TO")
	private String assignedTo;
	
	@Column(name = "STATUS")
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Integer getHoursLogged() {
		return hoursLogged;
	}

	public void setHoursLogged(Integer hoursLogged) {
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

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Task getParentId() {
		return parentId;
	}

	public void setParentId(Task parentId) {
		this.parentId = parentId;
	}

	public Set<Task> getSubTasks() {
		return subTasks;
	}

	public void setSubTasks(Set<Task> subTasks) {
		this.subTasks = subTasks;
	}

	public Set<TaskUpdates> getUpdates() {
		return updates;
	}

	public void setUpdates(Set<TaskUpdates> updates) {
		this.updates = updates;
	}

	
	
}
