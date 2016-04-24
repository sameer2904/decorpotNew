package com.decorpot.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.decorpot.rest.model.Bedroom;
import com.decorpot.rest.model.Kitchen;
import com.decorpot.services.SpaceService;
import com.decorpot.utils.DecorpotUtils;

@RestController
@RequestMapping(value = "/upload")
public class SpaceUploadController {

	@Autowired
	private SpaceService spaceUploader;

	@RequestMapping(value = "/space/images", method = RequestMethod.POST)
	public void uploadImages(@RequestParam("file") MultipartFile file)
			throws Exception {

		if (file != null) {
			File imageFile = DecorpotUtils.multipartToFile(file);
			spaceUploader.uploadSpaceImage(imageFile);
		}
	}

	@RequestMapping(value = "/space/kitchen", method = RequestMethod.POST)
	public Integer  uploadKitchen(@RequestBody Kitchen kit) {
		try {
			return spaceUploader.uploadKitchen(kit);
		} catch (Exception e) {
			throw e;
		}
	}

	@RequestMapping(value = "/space/master_bedroom", method = RequestMethod.POST)
	public Integer  uploadMasterBedroom(@RequestBody Bedroom bed) {
		try {
			return spaceUploader.uploadMasterBedroom(bed);
		} catch (Exception e) {
			throw e;
		}
	}
	
	@RequestMapping(value = "/space/guest_bedroom", method = RequestMethod.POST)
	public Integer  uploadGuestBedroom(@RequestBody Bedroom bed) {
		try {
			return spaceUploader.uploadGuestBedroom(bed);
		} catch (Exception e) {
			throw e;
		}
	}
	
	@RequestMapping(value = "/space/kids_bedroom", method = RequestMethod.POST)
	public Integer  uploadKidsBedroom(@RequestBody Bedroom bed) {
		try {
			return spaceUploader.uploadKidsBedroom(bed);
		} catch (Exception e) {
			throw e;
		}
	}
}
