package com.decorpot.utils;

import java.util.Arrays;
import java.util.List;

public interface DecorpotConstants {
	
	String DECORPOT_TRANSACTION_MANAGER = "decorpotTransactionManager";
	String BUCKET = "decorpotreponew";
	String SPACE_IMAGE_LOCATION_PREFIX = "spaces_";
	String FLOOR_PLAN_LOCATION = "floorplane_";
	List<String> spaceImageSizes = Arrays.asList(new String[]{"1900x1080", "475x270"});
}
