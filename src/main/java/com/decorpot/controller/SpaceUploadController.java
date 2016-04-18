package com.decorpot.controller;

import java.io.File;
import java.io.IOException;

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
	
	@RequestMapping(value = "/images/space", method = RequestMethod.POST)
	public void uploadImages(@RequestParam("file") MultipartFile file)
			throws IOException {

		if (file != null) {
			System.out.println(file.getOriginalFilename());
			File imageFile = DecorpotUtils.multipartToFile(file);
			spaceUploader.uploadSpaceImage(imageFile);
		}
	}

	@RequestMapping(value = "/kitchen", method = RequestMethod.POST)
	public String uploadKitchen(@RequestBody Kitchen kit) {
		return "true";
	}

}
