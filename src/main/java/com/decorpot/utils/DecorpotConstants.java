package com.decorpot.utils;

import java.util.Arrays;
import java.util.List;

public interface DecorpotConstants {
	
	//aws keys
    String BUCKET_LOCATION = "https://s3-ap-southeast-1.amazonaws.com/decorpotreponew/";
	String DECORPOT_TRANSACTION_MANAGER = "decorpotTransactionManager";
	String BUCKET = "decorpotreponew";
	String SPACE_IMAGE_LOCATION = "spaces/";
	String FLOOR_PLAN_LOCATION = "floorplans/";
	String APARTMENT_IMAGE_LOCATION = "apartmentImages/";
	String PAST_WORK_IMAGE_LOCATION = "pastworks/";
	List<String> spaceImageSizes = Arrays.asList(new String[]{"885x503", "475x270"});
	List<String>  pastWorkImageSizes = Arrays.asList(new String[]{"885x503", "475x270"});
	
	
	//caching keys
	String KITCHEN = "kitchens";
	String L_SHAPED = "L-shaped";
	String U_SHAPED = "U-shaped";
	
	String MASTER_BEDROOM = "master_bedrooms";
	String GUEST_BEDROOM = "guest_bedrooms";
	String KIDS_BEDROOM = "kids_bedrooms";
	String LIVING_DINING = "living_dining";
	
	List<String> wardrobes = Arrays.asList(new String[]{"2-door", "3-door", "4-door", "5-door", "6-door"});
	
	enum themes{
		contemprory, modern
	}
	
	String ALL = "all";
	
	
}
