package com.decorpot.datasource.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.decorpot.datasource.models.ApartmentConfig;

@Repository
public interface ApartmentConfigRepository extends CrudRepository<ApartmentConfig, Integer>{
    
    public ApartmentConfig findByApartmentName(String apartmentName);

}
