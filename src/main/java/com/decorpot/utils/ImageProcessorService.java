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

	public void uploadSpaceImages(File file) {

		if (file != null) {
			DecorpotConstants.spaceImageSizes.forEach(s -> {
				String[] dimentions = s.split("x");
				try {
					File resizedFile = imageCompressor(file,
							Integer.parseInt(dimentions[0]),
							Integer.parseInt(dimentions[1]));
					s3Uploader.s3PutImage(
							DecorpotConstants.SPACE_IMAGE_LOCATION_PREFIX + s,
							resizedFile);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}

	}

	private File imageCompressor(File file, int width, int height)
			throws Exception {

		try {
			BufferedImage originalImage = ImageIO.read(file);
			System.out.println(file.getAbsolutePath() + "   " + file.getName());
			System.out.println("new file path " + file.getName() + width + "x"
					+ height);
			File ouputImage = new File(file, file.getName() + width + "x"
					+ height);
			BufferedImage resizedImage = Scalr.resize(originalImage, width,
					height, Scalr.OP_ANTIALIAS, Scalr.OP_BRIGHTER);//  size(originalImage,
																	// Scalr.Method.SPEED,
																	// Scalr.Mode.FIT_TO_WIDTH,580,
																	// 384,
																	// Scalr.OP_ANTIALIAS);
			ImageIO.write(resizedImage, "jpg", ouputImage);
			return ouputImage;
		} catch (Exception e) {
			logger.error(LOGGER_PREFIX + e.getMessage(), e);
			throw e;
		}
	}
}
