package com.decorpot.datasource.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.decorpot.datasource.models.TaskTemplate;

public interface TaskTemplateRepo extends CrudRepository<TaskTemplate, Integer>{
	
	public List<TaskTemplate> findByState(String state);
	public TaskTemplate findByTaskName(String taskName);

}
