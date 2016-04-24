package com.decorpot.datasource.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.decorpot.datasource.models.Config3BHK;

@Repository
public interface Config3BHKRepository extends CrudRepository<Config3BHK, String> {

}
