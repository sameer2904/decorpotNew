package com.decorpot.services;

import java.io.File;

import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.regions.Region;
import com.decorpot.utils.DecorpotConstants;

@Service
public class S3Uploader {

	private static AWSCredentials awsCredentials;
	private AmazonS3 s3 = new AmazonS3Client(awsCredentials);
	private com.amazonaws.regions.Region usWest2 = Region
			.getRegion(Regions.US_WEST_2);
	
	static {
		awsCredentials = new AWSCredentials() {

			@Override
			public String getAWSSecretKey() {
				// TODO Auto-generated method stub
				return "uUiXYJzi2pZjNZDkmInMbjbhQE53Sz435ya3npju";
			}

			@Override
			public String getAWSAccessKeyId() {
				// TODO Auto-generated method stub
				return "AKIAJLNU25WFYGL6YPYQ";
			}
		};
	}

	public void s3PutImage(String folderName, File file) {
		
		s3.setRegion(usWest2);
		s3.putObject(new PutObjectRequest(DecorpotConstants.BUCKET, folderName
				+ "/", file));
	}

}
