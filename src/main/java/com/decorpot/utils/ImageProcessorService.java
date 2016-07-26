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
			s3Uploader.s3PutImage(
					DecorpotConstants.ORIGINAL_SPACE_IMAGE_LOCATION, file);
			file.delete();
		}
	}
	
	public void uploadFloorPlan(File file) {
	    if(file != null) {
	        s3Uploader.s3PutImage(
                    DecorpotConstants.FLOOR_PLAN_LOCATION, file);
	        file.delete();
	    }
	}
	
	public void uploadApartmentImages(File file) {
	    if(file != null) {
	        s3Uploader.s3PutImage(
                    DecorpotConstants.APARTMENT_IMAGE_LOCATION, file);
	        file.delete();
	    }
	}
	
	public void uploadEnquiryImages(File file) {
	    if(file != null) {
	        s3Uploader.s3PutImage(
                    DecorpotConstants.ENQUIRY_IMAGE_LOCATION, file);
	        file.delete();
	    }
	}
	
	public void uploadPastWorkImage(File file) throws Exception {
		System.out.println("uploading past work");

        if (file != null) {
        	System.out.println("uploading past work with name -> " + file.getName());
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

			File outputImage = new File(width + "x" + height + file.getName().replaceAll(" ", "_"));
			if (outputImage == null) {
				throw new Exception(LOGGER_PREFIX
						+ " image file cannot be created");
			}
			BufferedImage resizedImage = resizeImage(originalImage, width, height);//  size(originalImage,
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
	
	public BufferedImage resizeImage(BufferedImage inputImage, int resultWidth, int resultHeight) {
	    // first get the width and the height of the image
	    int originWidth = inputImage.getWidth();
	    int originHeight = inputImage.getHeight();
	    
	    // let us check if we have to scale the image
	    if (originWidth <= resultWidth && originHeight <= resultHeight) {
	        // we don't have to scale the image, just return the origin
	        return inputImage;
	    }

	    // Scale in respect to width or height?
	    Scalr.Mode scaleMode = Scalr.Mode.AUTOMATIC;

	    // find out which side is the shortest
	    int maxSize = 0;
	    if (originHeight > originWidth) {
	        // scale to width
	        scaleMode = Scalr.Mode.FIT_TO_WIDTH;
	        maxSize = resultWidth;
	    } else if (originWidth >= originHeight) {
	        scaleMode = Scalr.Mode.FIT_TO_HEIGHT;
	        maxSize = resultHeight;
	    }

	    // Scale the image to given size
	    BufferedImage outputImage = Scalr.resize(inputImage, Scalr.Method.QUALITY, scaleMode, maxSize);

	    // okay, now let us check that both sides are fitting to our result scaling
	    if (scaleMode.equals(Scalr.Mode.FIT_TO_WIDTH) && outputImage.getHeight() > resultHeight) {
	        // the height is too large, resize again
	        outputImage = Scalr.resize(outputImage, Scalr.Method.QUALITY, Scalr.Mode.FIT_TO_HEIGHT, resultHeight);
	    } else if (scaleMode.equals(Scalr.Mode.FIT_TO_HEIGHT) && outputImage.getWidth() > resultWidth) {
	        // the width is too large, resize again
	        outputImage = Scalr.resize(outputImage, Scalr.Method.QUALITY, Scalr.Mode.FIT_TO_WIDTH, resultWidth);
	    }

	    // now we have an image that is definitely equal or smaller to the given size
	    // Now let us check, which side needs black lines
	    int paddingSize = 0;
	    if (outputImage.getWidth() != resultWidth) {
	        // we need padding on the width axis
	        paddingSize = (resultWidth - outputImage.getWidth()) / 2;
	    } else if (outputImage.getHeight() != resultHeight) {
	        // we need padding on the height axis
	        paddingSize = (resultHeight - outputImage.getHeight()) / 2;
	    }

	    // we need padding?
	    if (paddingSize > 0) {
	        // add the padding to the image
	        outputImage = Scalr.pad(outputImage, paddingSize);

	        // now we have to crop the image because the padding was added to all sides
	        int x = 0, y = 0, width = 0, height = 0;
	        if (outputImage.getWidth() > resultWidth) {
	            // set the correct range
	            x = paddingSize;
	            y = 0;
	            width = outputImage.getWidth() - (2 * paddingSize);
	            height = outputImage.getHeight();
	        } else if (outputImage.getHeight() > resultHeight) {
	            // set the correct range
	            x = 0;
	            y = paddingSize;
	            width = outputImage.getWidth();
	            height = outputImage.getHeight() - (2 * paddingSize);
	        }
	        
	        // Crop the image 
	        if (width > 0 && height > 0) {
	            outputImage = Scalr.crop(outputImage, x, y, width, height);
	        }
	    }
	    
	    // flush both images
	    inputImage.flush();
	    outputImage.flush();
	    
	    // return the final image
	    return outputImage;
	}
}
