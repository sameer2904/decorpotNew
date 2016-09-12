package com.decorpot.datasource.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.decorpot.datasource.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, String>{
	public User findByUsername(String username);
	
	@Query("SELECT u FROM User u WHERE u.userRole.roleName <> 'customer'")
    public List<User> findInternalUsers();

}
