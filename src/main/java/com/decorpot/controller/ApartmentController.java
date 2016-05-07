package com.decorpot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.decorpot.rest.model.ApartmentConfigs;
import com.decorpot.services.ApartmentService;

@RestController
@RequestMapping(value = "/apartments")
public class ApartmentController {

	@Autowired
	private ApartmentService apartmentService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<ApartmentConfigs> getAllApartments() {
		return apartmentService.getAllApartments();
	}
}
