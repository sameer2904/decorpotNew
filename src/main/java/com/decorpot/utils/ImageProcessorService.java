package com.decorpot.utils;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.decorpot.services.S3Uploader;

@Service
public class ImageProcessorService {
	private static final Logger logger = LoggerFactory
			.getLogger(ImageProcessorService.class);
	private static final String LOGGER_PREFIX = "[ImageProcessorService] ";

	@Autowired
	private S3Uploader s3Uploader;

	public void uploadSpaceImages(File file) throws Exception {

		if (file != null) {
			for (String size : DecorpotConstants.spaceImageSizes) {
				String[] dimentions = size.split("x");
				try {
					File resizedFile = imageCompressor(file,
							Integer.parseInt(dimentions[0]),
							Integer.parseInt(dimentions[1]));
					s3Uploader.s3PutImage(
							DecorpotConstants.SPACE_IMAGE_LOCATION, resizedFile);
					resizedFile.delete();
				} catch (Exception e) {
					throw e;
				}
			}
			file.delete();
		}
	}
	
	public void uploadFloorPlan(File file) {
	    if(file != null) {
	        s3Uploader.s3PutImage(
                    DecorpotConstants.FLOOR_PLAN_LOCATION, file);
	    }
	}
	
	public void uploadPastWorkImage(File file) throws Exception {

        if (file != null) {
            for (String size : DecorpotConstants.pastWorkImageSizes) {
                String[] dimentions = size.split("x");
                try {
                    File resizedFile = imageCompressor(file,
                            Integer.parseInt(dimentions[0]),
                            Integer.parseInt(dimentions[1]));
                    s3Uploader.s3PutImage(
                            DecorpotConstants.PAST_WORK_IMAGE_LOCATION, resizedFile);
                    resizedFile.delete();
                } catch (Exception e) {
                    throw e;
                }
            }
            file.delete();
        }
    }

	@SuppressWarnings("unused")
	private File imageCompressor(File file, int width, int height)
			throws Exception {

		try {
			BufferedImage originalImage = ImageIO.read(file);

			File outputImage = new File(width + "x" + height + file.getName());
			if (outputImage == null) {
				throw new Exception(LOGGER_PREFIX
						+ " image file cannot be created");
			}
			BufferedImage resizedImage = Scalr.resize(originalImage, width,
					height, Scalr.OP_ANTIALIAS, Scalr.OP_BRIGHTER);//  size(originalImage,
																	// Scalr.Method.SPEED,
																	// Scalr.Mode.FIT_TO_WIDTH,580,
																	// 384,
																	// Scalr.OP_ANTIALIAS);
			ImageIO.write(resizedImage, "jpg", outputImage);
			return outputImage;
		} catch (Exception e) {
			logger.error(LOGGER_PREFIX + e.getMessage(), e);
			throw e;
		}
	}
}
