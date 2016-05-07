package com.decorpot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.decorpot.rest.model.ApartmentBaseConfig;
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
	
	@RequestMapping(value = "/{apartmentName}", method = RequestMethod.GET)
	public List<ApartmentBaseConfig> getAllFloorPlanByApartmentName(@PathVariable("apartmentName") String apartmentName) {
		return apartmentService.getAllFloorPlanByApartmentName(apartmentName);
	}
}
