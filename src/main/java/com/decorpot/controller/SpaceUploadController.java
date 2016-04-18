package com.decorpot.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.decorpot.rest.model.Kitchen;
import com.decorpot.services.SpaceUploaderService;
import com.decorpot.utils.DecorpotUtils;

@RestController
@RequestMapping(value = "/upload")
public class SpaceUploadController {

	@Autowired
	private SpaceUploaderService spaceUploader;

	@RequestMapping(value = "/space/images", method = RequestMethod.POST)
	public void uploadImages(@RequestParam("file") MultipartFile file)
			throws Exception {

		if (file != null) {
			File imageFile = DecorpotUtils.multipartToFile(file);
			spaceUploader.uploadSpaceImage(imageFile);
		}
	}

	@RequestMapping(value = "/space/kitchen", method = RequestMethod.POST)
	public Integer uploadKitchen(@RequestBody Kitchen kit) {
		try {
			return spaceUploader.uploadKitchen(kit);
		} catch (Exception e) {
			throw e;
		}
	}

}
