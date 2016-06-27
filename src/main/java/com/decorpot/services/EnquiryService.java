package com.decorpot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.decorpot.datasource.models.EnquiryForm;
import com.decorpot.datasource.repository.EnquiryRepository;
import com.decorpot.rest.model.EnquiryModel;

@Service
public class EnquiryService {
	
	@Autowired
	private EnquiryRepository enqRepo;
	
	@Autowired
	private ApplicationMailer emailService ;
	
	
	public void submitEnquiry(EnquiryModel eqModel) {
		
		EnquiryForm enquiryForm = new EnquiryForm();
		
		enquiryForm.setEmail(eqModel.getEmail());
		enquiryForm.setFloorPlan(eqModel.getImage());
		enquiryForm.setMessage(eqModel.getMessage());
		enquiryForm.setName(eqModel.getName());
		enquiryForm.setPhone(eqModel.getPhone());
		
		enqRepo.save(enquiryForm);
		
		emailService.sendMail(eqModel.getEmail(), "Enquiry Recieved @Decorpot.com", eqModel.getMessage());
		emailService.sendMail("sales@decorpot.com", "Enquiry Recieved @Decorpot.com", eqModel.getMessage() + " floorplan url -> " + "https://s3-ap-southeast-1.amazonaws.com/decorpotreponew/enquiry/"+eqModel.getImage());
		
	}

}
