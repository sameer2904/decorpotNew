package com.decorpot.datasource.repository;

import org.springframework.data.repository.CrudRepository;

import com.decorpot.datasource.models.CustomerDetails;

public interface CustomerRepository extends CrudRepository<CustomerDetails, Integer>{

}
