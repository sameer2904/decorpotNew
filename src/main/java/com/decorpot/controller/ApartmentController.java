package com.decorpot.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.decorpot.rest.model.ApartmentConfigs;
import com.decorpot.rest.model.Packages;
import com.decorpot.services.ApartmentService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

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
	public ApartmentConfigs getAllFloorPlanByApartmentName(@PathVariable("apartmentName") String apartmentName) {
		return apartmentService.getAllFloorPlanByApartmentName(apartmentName);
	}

	@RequestMapping(value = "/{apartmentType}/{floodPlanId}", method = RequestMethod.GET)
	public List<Packages> getAllPackagesByFloorplanId(@PathVariable("apartmentType") String apartmentType,
			@PathVariable("floodPlanId") int floodPlanId) throws JsonParseException, JsonMappingException, IOException {

		if(apartmentType.equals("3BHK")) {
			return apartmentService.get3BHKApartmentByFloorplanId(floodPlanId);
		}else{
			return apartmentService.get2BHKApartmentByFloorplanId(floodPlanId);
		}
	}
}
