package com.decorpot.services;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.decorpot.utils.DecorpotConstants;

@Service
public class S3Uploader {

	private static final Logger logger = LoggerFactory
			.getLogger(S3Uploader.class);
	private static final String LOGGER_PREFIX = "[S3Uploader] ";

	private static AWSCredentials awsCredentials;
	private AmazonS3 s3 = new AmazonS3Client(awsCredentials);
	private com.amazonaws.regions.Region usWest2 = Region
			.getRegion(Regions.AP_SOUTHEAST_1);

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

		System.out.println(LOGGER_PREFIX + " upload file to " + folderName
				+ "fileName " + file.getName());

		try {
			s3.setRegion(usWest2);
			s3.putObject(new PutObjectRequest(DecorpotConstants.BUCKET,
					folderName + file.getName(), file)
					.withCannedAcl(CannedAccessControlList.PublicRead));
		} catch (Exception e) {
			logger.error(LOGGER_PREFIX + e.getMessage(), e);
			throw e;
		}
	}

	public void printBucketList() {
		try {
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
			AmazonS3 s3 = new AmazonS3Client(awsCredentials);
			com.amazonaws.regions.Region usWest2 = Region
					.getRegion(Regions.US_WEST_2);
			s3.setRegion(usWest2);
			for (Bucket bucket : s3.listBuckets()) {
				System.out.println(" buckets " + bucket.getName());
			}
		} catch (Exception e) {
			logger.error(LOGGER_PREFIX + e.getMessage(), e);
			throw e;
		}
	}

}
