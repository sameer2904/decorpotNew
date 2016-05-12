package com.decorpot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.decorpot.rest.model.Bedroom;
import com.decorpot.rest.model.Kitchen;
import com.decorpot.services.SpaceService;

@RestController
@RequestMapping(value = "/space")
public class SpaceController {

	@Autowired
	private SpaceService spaceService;
	
	@RequestMapping(value = "/kitchen", method = RequestMethod.GET)
	public List<Kitchen> getAllKitchens() {
		return spaceService.getAllKitchens();
	}
	
	@RequestMapping(value = "/kitchen/{id}", method = RequestMethod.GET)
	public Kitchen getAllKitchens(@PathVariable("id") int id) {
		return spaceService.getKitchensById(id);
	}
	
	@RequestMapping(value = "/master_bedroom", method = RequestMethod.GET)
	public List<Bedroom> getAllMasterBedrooms() {
		return spaceService.getAllMasterBedrooms();
	}
	
	@RequestMapping(value = "/master_bedroom/{id}", method = RequestMethod.GET)
	public Bedroom getMasterBedroomById(@PathVariable("id") int id) {
		return spaceService.getMasterBedroomById(id);
	}
	
	@RequestMapping(value = "/guest_bedroom", method = RequestMethod.GET)
	public List<Bedroom> getAllGuestBedrooms() {
		return spaceService.getAllGuestBedrooms();
	}
	
	@RequestMapping(value = "/guest_bedroom/{id}", method = RequestMethod.GET)
	public Bedroom getGuestBedroomById(@PathVariable("id") int id) {
		return spaceService.getGuestBedroomById(id);
	}
	
	@RequestMapping(value = "/kids_bedroom", method = RequestMethod.GET)
	public List<Bedroom> getAllKidsBedrooms() {
		return spaceService.getAllKidsBedrooms();
	}
	
	@RequestMapping(value = "/kids_bedroom/{id}", method = RequestMethod.GET)
	public Bedroom getKidsBedroomById(@PathVariable("id") int id) {
		return spaceService.getKidsBedroomById(id);
	}
	
}
