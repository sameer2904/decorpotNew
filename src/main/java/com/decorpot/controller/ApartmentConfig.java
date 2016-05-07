package com.decorpot.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.decorpot.rest.model.ApartmentConfigs;
import com.decorpot.rest.model.Config2BHK;
import com.decorpot.rest.model.Config3BHK;
import com.decorpot.services.ApartmentService;
import com.decorpot.utils.DecorpotUtils;
import com.decorpot.utils.ImageProcessorService;

@RestController
@RequestMapping(value="/config")
public class ApartmentConfig {
    
    @Autowired
    private ImageProcessorService imageProcessorService;
    
    @Autowired
    private ApartmentService apartmentService;
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value= "/apartment", method = RequestMethod.POST)
    public void configApartment(@RequestBody ApartmentConfigs apartmentConfig) throws Exception {
        try{
            apartmentService.configApartment(apartmentConfig);
        }catch(Exception e){
            throw e;
        }
    }
    
   @ResponseStatus(HttpStatus.OK)
   @RequestMapping(value= "/apartment/2bhk", method = RequestMethod.POST)
   public void config2BHKApartment(@RequestBody Config2BHK config2bhk) throws Exception {
       try{
           System.out.println("controller floor plan " + config2bhk.getFloorPlan());
           apartmentService.config2BHKApartment(config2bhk);
       }catch(Exception e){
           throw e;
       }
   }
   
   @ResponseStatus(HttpStatus.OK)
   @RequestMapping(value= "/apartment/3bhk", method = RequestMethod.POST)
   public void config3BHKApartment(@RequestBody Config3BHK config3bhk) throws Exception {
       try{
           apartmentService.config3BHKApartment(config3bhk);
       }catch(Exception e){
           throw e;
       }
   }
   
   @ResponseStatus(HttpStatus.OK)
   @RequestMapping(value = "/floorPlans", method = RequestMethod.POST)
   public void uploadFloorPlan(@RequestParam("file") MultipartFile file)
           throws Exception {

      File imageFile = null;
       if (file != null) {
           imageFile = DecorpotUtils.multipartToFile(file);
           imageProcessorService.uploadFloorPlan(imageFile);
       }
   }
   
   @ResponseStatus(HttpStatus.OK)
   @RequestMapping(value = "/apartmentImage", method = RequestMethod.POST)
   public void uploadApartmentImages(@RequestParam("file") MultipartFile file)
           throws Exception {

      File imageFile = null;
       if (file != null) {
           imageFile = DecorpotUtils.multipartToFile(file);
           imageProcessorService.uploadApartmentImages(imageFile);
       }
   }
}
