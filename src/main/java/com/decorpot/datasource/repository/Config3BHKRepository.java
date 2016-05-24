package com.decorpot.datasource.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.decorpot.datasource.models.Config3BHK;

@Repository
public interface Config3BHKRepository extends CrudRepository<Config3BHK, Integer> {

	List<Config3BHK> findByApartmentId(int apartmentId);
}
