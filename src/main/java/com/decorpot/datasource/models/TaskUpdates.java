package com.decorpot.datasource.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "TASK_UPDATES")
public class TaskUpdates {
	
	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	@Column(name = "UPDATE")
	private String update;
	
	@Column(name = "CREATED_DATE", columnDefinition = "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private Date createdDate = new java.sql.Date((new java.util.Date()).getTime());
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@ManyToOne
	@JoinColumn(name="TASK_ID", referencedColumnName = "ID")
	private Task task;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

}
