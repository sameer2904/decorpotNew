package com.decorpot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.decorpot.rest.model.PastWork;
import com.decorpot.services.PastWorkService;
import com.decorpot.utils.ImageProcessorService;

@RestController
@RequestMapping(value="/pastWork")
public class PastWorkController {
    
    @Autowired
    private PastWorkService pastWorkService;
    
    @Autowired
    private ImageProcessorService imageProcessorService;
    
    public void getAllPastWork() {
        
    }
    
    public void getPastWorkById() {
        
    }
    
    public void uploadPastWork(@RequestBody PastWork pastWork) {
        
    }

}
