package com.decorpot.datasource.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.decorpot.datasource.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	public List<User> findByUsername(String username); 

}
