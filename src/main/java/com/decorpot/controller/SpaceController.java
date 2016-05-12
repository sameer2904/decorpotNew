package com.decorpot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
