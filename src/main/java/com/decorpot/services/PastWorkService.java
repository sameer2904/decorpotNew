package com.decorpot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.decorpot.datasource.repository.PastWorkRepository;
import com.decorpot.rest.model.PastWork;

@Service
public class PastWorkService {
    
    @Autowired
    private PastWorkRepository pastWorkRepository;
    
    public void uploadPastWork(PastWork pastWork) {
        
        com.decorpot.datasource.models.PastWork work = new com.decorpot.datasource.models.PastWork();
        work.setApartmentName(pastWork.getApartmentName());
        work.setImages(String.join(",", pastWork.getImages()));
        
    }

}
