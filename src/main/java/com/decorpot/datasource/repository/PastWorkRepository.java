package com.decorpot.datasource.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.decorpot.datasource.models.PastWork;

public interface PastWorkRepository extends JpaRepository<PastWork, Integer>{
    
    public PastWork findById(Integer id);

}
