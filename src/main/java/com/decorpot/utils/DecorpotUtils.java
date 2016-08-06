package com.decorpot.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class DecorpotUtils {
	
	public static File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException 
	{
	        File convFile = new File( multipart.getOriginalFilename().replaceAll(" ", "_"));
	        multipart.transferTo(convFile);
	        return convFile;
	}

}
