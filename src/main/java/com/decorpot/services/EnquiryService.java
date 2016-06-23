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
	
	
	public void submitEnquiry(EnquiryModel eqModel) {
		
		EnquiryForm enquiryForm = new EnquiryForm();
		
		enquiryForm.setEmail(eqModel.getEmail());
		enquiryForm.setFloorPlan(eqModel.getImage());
		enquiryForm.setMessage(eqModel.getMessage());
		enquiryForm.setName(eqModel.getName());
		enquiryForm.setPhone(eqModel.getPhone());
		
		enqRepo.save(enquiryForm);
		
	}

}
