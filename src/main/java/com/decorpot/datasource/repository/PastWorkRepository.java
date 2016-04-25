package com.decorpot.datasource.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.decorpot.datasource.models.PastWork;

public interface PastWorkRepository extends JpaRepository<PastWork, Integer>{
    
    public PastWork findById(Integer id);
    
    public List<PastWork> findByActiveTrue();
    
    public PastWork findByApartmentName(String apartmentName);

}
