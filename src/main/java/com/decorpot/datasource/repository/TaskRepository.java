package com.decorpot.datasource.repository;

import org.springframework.data.repository.CrudRepository;

import com.decorpot.datasource.models.Task;

public interface TaskRepository extends CrudRepository<Task, Integer>{

}
