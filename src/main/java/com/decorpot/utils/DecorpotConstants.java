package com.decorpot.utils;

import java.util.Arrays;
import java.util.List;

public interface DecorpotConstants {
	
    String BUCKET_LOCATION = "https://s3-ap-southeast-1.amazonaws.com/decorpotreponew/";
	String DECORPOT_TRANSACTION_MANAGER = "decorpotTransactionManager";
	String BUCKET = "decorpotreponew";
	String SPACE_IMAGE_LOCATION = "spaces/";
	String FLOOR_PLAN_LOCATION = "floorplane/";
	String APARTMENT_IMAGE_LOCATION = "apartmentImages/";
	String PAST_WORK_IMAGE_LOCATION = "pastworks/";
	List<String> spaceImageSizes = Arrays.asList(new String[]{"1900x1080", "475x270"});
	List<String>  pastWorkImageSizes = Arrays.asList(new String[]{"1900x1080", "475x270"});
}
