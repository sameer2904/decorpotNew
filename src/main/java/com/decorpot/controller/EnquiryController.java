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

import com.decorpot.rest.model.EnquiryModel;
import com.decorpot.services.EnquiryService;
import com.decorpot.utils.DecorpotUtils;
import com.decorpot.utils.ImageProcessorService;

@RestController
public class EnquiryController {
	
	@Autowired
	private EnquiryService enquiryService;
	
    @Autowired
    private ImageProcessorService imageProcessorService;
	
	@ResponseStatus(HttpStatus.OK)
    @RequestMapping(value= "/enquiry", method = RequestMethod.POST)
    public void configApartment(@RequestBody EnquiryModel enquiryModel) throws Exception {
        try{
        	enquiryService.submitEnquiry(enquiryModel);
        }catch(Exception e){
            throw e;
        }
    }

	@ResponseStatus(HttpStatus.OK)
	   @RequestMapping(value = "/enquiryImage", method = RequestMethod.POST)
	   public void uploadApartmentImages(@RequestParam("file") MultipartFile file)
	           throws Exception {

	      File imageFile = null;
	       if (file != null) {
	           imageFile = DecorpotUtils.multipartToFile(file);
	           imageProcessorService.uploadEnquiryImages(imageFile);
	       }
	   }
}
