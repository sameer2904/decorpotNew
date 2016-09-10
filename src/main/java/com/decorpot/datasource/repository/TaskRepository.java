package com.decorpot.datasource.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.decorpot.datasource.models.Task;

public interface TaskRepository extends CrudRepository<Task, Integer>{
	
	public List<Task> findTaskByStateTag(String stateTag);
	public List<Task> findTaskByCustomerIdAndStateTag(Integer customerId, String stateTag);

}
