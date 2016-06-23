package com.decorpot.datasource.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.decorpot.datasource.models.EnquiryForm;

@Repository
public interface EnquiryRepository extends CrudRepository<EnquiryForm, Integer>{

}
