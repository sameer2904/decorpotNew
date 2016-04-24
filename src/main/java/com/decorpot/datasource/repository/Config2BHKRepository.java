package com.decorpot.datasource.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.decorpot.datasource.models.Config2BHK;

@Repository
public interface Config2BHKRepository extends CrudRepository<Config2BHK, String> {

}
