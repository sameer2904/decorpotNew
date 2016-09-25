package com.decorpot.datasource.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "TASK_TEMPLATE")
public class TaskTemplate {

	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	@Column(name = "TASK_NAME")
	private String taskName;
	
	@Column(name = "STATE")
	private String state;
	
	@Column(name = "PREVIOUS_TASK")
	private String prevTask;
	
	@Column(name = "ESTIMATED_DAYS")
	private Integer estDays;
	
	@Column(name = "DEFAULT_ASSIGNEE")
	private String defaultAssignee;
	
	@Column(name = "BUDGET_TYPE")
	private String budgetType;
	
	@Column(name = "EXECUTION_TYPE")
	private String executionType;

	public String getBudgetType() {
		return budgetType;
	}

	public void setBudgetType(String budgetType) {
		this.budgetType = budgetType;
	}

	public String getExecutionType() {
		return executionType;
	}

	public void setExecutionType(String executionType) {
		this.executionType = executionType;
	}

	public String getDefaultAssignee() {
		return defaultAssignee;
	}

	public void setDefaultAssignee(String defaultAssignee) {
		this.defaultAssignee = defaultAssignee;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPrevTask() {
		return prevTask;
	}

	public void setPrevTask(String prevTask) {
		this.prevTask = prevTask;
	}

	public Integer getEstDays() {
		return estDays;
	}

	public void setEstDays(Integer estDays) {
		this.estDays = estDays;
	}
	
	
}
