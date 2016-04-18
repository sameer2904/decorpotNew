package com.decorpot.services;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.decorpot.utils.ImageProcessorService;

@Service
public class SpaceUploaderService {
	
	@Autowired
	private ImageProcessorService imageProcessorService;
	
	public void uploadSpaceImage(File file) {
		imageProcessorService.uploadSpaceImages(file);
	}

}
