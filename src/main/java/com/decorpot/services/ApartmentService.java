package com.decorpot.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.util.CollectionUtils;
import com.decorpot.datasource.models.ApartmentConfig;
import com.decorpot.datasource.models.Config3BHK;
import com.decorpot.datasource.models.MasterBedroom;
import com.decorpot.datasource.repository.ApartmentConfigRepository;
import com.decorpot.datasource.repository.Config2BHKRepository;
import com.decorpot.datasource.repository.Config3BHKRepository;
import com.decorpot.rest.model.ApartmentBaseConfig;
import com.decorpot.rest.model.ApartmentConfigs;
import com.decorpot.rest.model.Bedroom;
import com.decorpot.rest.model.BedroomBaseConfig;
import com.decorpot.rest.model.Config2BHK;
import com.decorpot.rest.model.Kitchen;
import com.decorpot.rest.model.KitchenConfig;
import com.decorpot.rest.model.LivingAndDining;
import com.decorpot.rest.model.Packages;
import com.decorpot.rest.model.SpaceId;
import com.decorpot.utils.DataCache;
import com.decorpot.utils.DecorpotConstants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ApartmentService {

	@Autowired
	private Config2BHKRepository config2BHKRepository;

	@Autowired
	private Config3BHKRepository config3bhkRepository;

	@Autowired
	private ApartmentConfigRepository apartmentConfigRepository;

	@Autowired
	private SpaceService spaceService;

	public void configApartment(ApartmentConfigs apartmentConfig) {
		Date dateToday = new java.util.Date();
		java.sql.Date todayDate = new java.sql.Date(dateToday.getTime());

		com.decorpot.datasource.models.ApartmentConfig apartData = apartmentConfigRepository
				.findByApartmentName(apartmentConfig.getApartmentName());
		if (apartData == null) {
			apartData = new ApartmentConfig();
			apartData.setApartmentName(apartmentConfig.getApartmentName());
			apartData.setDate(todayDate);
			apartData.setActive(true);
			apartData.setImage(apartmentConfig.getImage());
			apartData.setAddress(apartmentConfig.getAddress());
			apartData = apartmentConfigRepository.save(apartData);
		}
	}

	public void config2BHKApartment(Config2BHK config2bhk) throws JsonProcessingException {

		Date dateToday = new java.util.Date();
		java.sql.Date todayDate = new java.sql.Date(dateToday.getTime());

		ApartmentConfig apartmentConfig = apartmentConfigRepository.findByApartmentName(config2bhk.getApartmentName());

		com.decorpot.datasource.models.Config2BHK config2bhkRepo = new com.decorpot.datasource.models.Config2BHK();
		ObjectMapper mapper = new ObjectMapper();

		config2bhkRepo.setApartmentId(apartmentConfig.getId());
		config2bhkRepo.setFloorPlan(config2bhk.getFloorPlan());
		config2bhkRepo.setApartmentType(config2bhk.getApartmentType());
		config2bhkRepo.setPlanName(config2bhk.getPlanName());
		config2bhkRepo.setKitchenConfig(mapper.writeValueAsString(config2bhk.getKitchen()));
		config2bhkRepo.setMasterBedroomConfig(mapper.writeValueAsString(config2bhk.getMasterBedroom()));
		config2bhkRepo.setOtherBedroomConfig(mapper.writeValueAsString(config2bhk.getOtherBedroom()));
		config2bhkRepo.setActive(true);
		config2bhkRepo.setDate(todayDate);
		System.out.println("repo floor " + config2bhkRepo.getFloorPlan());
		config2bhkRepo = config2BHKRepository.save(config2bhkRepo);
	}

	public void config3BHKApartment(com.decorpot.rest.model.Config3BHK config3bhk) throws JsonProcessingException {
		Date dateToday = new java.util.Date();
		java.sql.Date todayDate = new java.sql.Date(dateToday.getTime());

		ApartmentConfig apartmentConfig = apartmentConfigRepository.findByApartmentName(config3bhk.getApartmentName());

		com.decorpot.datasource.models.Config3BHK config3bhkRepo = new com.decorpot.datasource.models.Config3BHK();
		ObjectMapper mapper = new ObjectMapper();

		config3bhkRepo.setApartmentId(apartmentConfig.getId());
		config3bhkRepo.setFloorPlan(config3bhk.getFloorPlan());
		config3bhkRepo.setApartmentType(config3bhk.getApartmentType());
		config3bhkRepo.setPlanName(config3bhk.getPlanName());
		config3bhkRepo.setKitchenConfig(mapper.writeValueAsString(config3bhk.getKitchen()));
		config3bhkRepo.setMasterBedroomConfig(mapper.writeValueAsString(config3bhk.getMasterBedroom()));
		config3bhkRepo.setGuestBedroomConfig(mapper.writeValueAsString(config3bhk.getGuestBedroom()));
		config3bhkRepo.setKidsBedroomConfig(mapper.writeValueAsString(config3bhk.getKidsBedroom()));
		config3bhkRepo.setActive(true);
		config3bhkRepo.setDate(todayDate);
		config3bhkRepo = config3bhkRepository.save(config3bhkRepo);
	}

	public List<ApartmentConfigs> getAllApartments() {
		
		List<ApartmentConfigs> configs = new ArrayList<>();
		if(DataCache.getInstance().get("all_apartments") != null) {
			configs = (List<ApartmentConfigs>) DataCache.getInstance().get("all_apartments");
		}else{
			List<com.decorpot.datasource.models.ApartmentConfig> apartmentConfigs = (List<ApartmentConfig>) apartmentConfigRepository
					.findAll();
			
			configs = apartmentConfigs.stream().map(a -> apartmentModelConverter(a)).collect(Collectors.toList());
			DataCache.getInstance().put("all_apartments", configs);
		}
		
		return configs;
	}
	
	public ApartmentConfigs apartmentModelConverter(ApartmentConfig a ) {
		ApartmentConfigs aConfs = new ApartmentConfigs();
		aConfs.setAddress(a.getAddress());
		aConfs.setApartmentName(a.getApartmentName());
		aConfs.setId(a.getId());
		aConfs.setImage(
				DecorpotConstants.BUCKET_LOCATION + DecorpotConstants.APARTMENT_IMAGE_LOCATION + a.getImage());
		return aConfs;
	}
	
	

	public ApartmentConfigs getAllFloorPlanByApartmentName(String apartmentName) {
		com.decorpot.datasource.models.ApartmentConfig apartmentConfigs = apartmentConfigRepository
				.findByApartmentName(apartmentName);

		ApartmentConfigs configs = new ApartmentConfigs();
		if(DataCache.getInstance().get(apartmentName) !=null ) {
			configs = (ApartmentConfigs) DataCache.getInstance().get(apartmentName);
		}else {
			List<ApartmentBaseConfig> apartmentBaseConfigs = new ArrayList<>();
			List<Config3BHK> config3bhks = config3bhkRepository.findByApartmentId(apartmentConfigs.getId());
			List<com.decorpot.datasource.models.Config2BHK> config2bhks = config2BHKRepository
					.findByApartmentId(apartmentConfigs.getId());
			if (!CollectionUtils.isNullOrEmpty(config2bhks)) {
				config2bhks.forEach(c -> {
					ApartmentBaseConfig baseConfig = new ApartmentBaseConfig();
					baseConfig.setPlanId(c.getId());
					baseConfig.setApartmentName(apartmentName);
					baseConfig.setApartmentType(c.getApartmentType());
					baseConfig.setPlanName(c.getPlanName());
					baseConfig.setFloorPlan(
							DecorpotConstants.BUCKET_LOCATION + DecorpotConstants.FLOOR_PLAN_LOCATION + c.getFloorPlan());
					apartmentBaseConfigs.add(baseConfig);
				});
			}

			if (!CollectionUtils.isNullOrEmpty(config3bhks)) {
				config3bhks.forEach(c -> {
					ApartmentBaseConfig baseConfig = new ApartmentBaseConfig();
					baseConfig.setPlanId(c.getId());
					baseConfig.setApartmentName(apartmentName);
					baseConfig.setApartmentType(c.getApartmentType());
					baseConfig.setPlanName(c.getPlanName());
					baseConfig.setFloorPlan(
							DecorpotConstants.BUCKET_LOCATION + DecorpotConstants.FLOOR_PLAN_LOCATION + c.getFloorPlan());
					apartmentBaseConfigs.add(baseConfig);
				});
			}
			configs.setAddress(apartmentConfigs.getAddress());
			configs.setApartmentName(apartmentName);
			configs.setImage(DecorpotConstants.BUCKET_LOCATION + DecorpotConstants.APARTMENT_IMAGE_LOCATION
					+ apartmentConfigs.getImage());
			configs.setId(apartmentConfigs.getId());
			configs.setApartmentBaseConfigs(apartmentBaseConfigs);
			DataCache.getInstance().put(apartmentName, configs);
		}
		
		return configs;
	}

	@SuppressWarnings("unchecked")
	public List<Packages> get3BHKApartmentByFloorplanId(int id)
			throws JsonParseException, JsonMappingException, IOException {
		/**
		 * todo: change all functions in space service for by type to send a
		 * limit.
		 */
		if (DataCache.get("3BHK" + id) != null) {
			return (List<Packages>) DataCache.get("3BHK" + id);
		} else {
			List<Packages> packages = new ArrayList<>();
			// List<Map<String, Object>> packages = new ArrayList<>();
			int maxLen = 0;
			Config3BHK config3bhk = config3bhkRepository.findOne(id);
			ApartmentConfig apartmentConfig = apartmentConfigRepository.findOne(config3bhk.getApartmentId());
			ObjectMapper mapper = new ObjectMapper();
			KitchenConfig kitchenConfig = mapper.readValue(config3bhk.getKitchenConfig(), KitchenConfig.class);
			BedroomBaseConfig masterBedroomConfig = mapper.readValue(config3bhk.getMasterBedroomConfig(),
					BedroomBaseConfig.class);
			BedroomBaseConfig guestBedroomConfig = mapper.readValue(config3bhk.getGuestBedroomConfig(),
					BedroomBaseConfig.class);
			BedroomBaseConfig kidsBedroomConfig = mapper.readValue(config3bhk.getKidsBedroomConfig(),
					BedroomBaseConfig.class);

			List<Kitchen> kitchens = spaceService.getAllKitchensByType(kitchenConfig.getKitchenType());
			int kitchenSize = kitchens.size();
			List<Bedroom> masterBedrooms = spaceService
					.getAllMasterBedroomsByType(masterBedroomConfig.getWardrobeType());
			int masterBedroomSize = masterBedrooms.size();
			List<Bedroom> guestBedrooms = spaceService.getAllGuestBedroomsByType(guestBedroomConfig.getWardrobeType());
			int guestBedroomsSize = guestBedrooms.size();
			List<Bedroom> kidsBedrooms = spaceService.getAllKidsBedroomsByType(kidsBedroomConfig.getWardrobeType());
			int kidsBedroomsSize = kidsBedrooms.size();
			List<LivingAndDining> livingAndDinings = spaceService.getAllLivingAndDining();
			int livingAndDiningsSize = livingAndDinings.size();

			if (kitchens.size() > maxLen) {
				maxLen = kitchens.size();
			}

			if (masterBedrooms.size() > maxLen) {
				maxLen = masterBedrooms.size();
			}

			if (guestBedrooms.size() > maxLen) {
				maxLen = guestBedrooms.size();
			}

			if (kidsBedrooms.size() > maxLen) {
				maxLen = kidsBedrooms.size();
			}

			if (livingAndDinings.size() > maxLen) {
				maxLen = livingAndDinings.size();
			}

			int kitItr = 0, masItr = 0, gueItr = 0, kidItr = 0, livItr = 0;
			for (int i = 0; i < maxLen; i++) {
				double basePrice = 0;
				List<SpaceId> spaceIds = new ArrayList<>();
				kitItr = kitItr < kitchenSize ? kitItr : 0;
				masItr = masItr < masterBedroomSize ? masItr : 0;
				gueItr = gueItr < guestBedroomsSize ? gueItr : 0;
				kidItr = kidItr < kidsBedroomsSize ? kidItr : 0;
				livItr = livItr < livingAndDiningsSize ? livItr : 0;

				spaceIds.add(new SpaceId(DecorpotConstants.KITCHEN, kitchens.get(kitItr).getId()));
				basePrice += kitchens.get(kitItr).getBasePrice();

				spaceIds.add(new SpaceId(DecorpotConstants.MASTER_BEDROOM, masterBedrooms.get(masItr).getId()));
				basePrice += masterBedrooms.get(masItr).getBasePrice();

				spaceIds.add(new SpaceId(DecorpotConstants.GUEST_BEDROOM, guestBedrooms.get(gueItr).getId()));
				basePrice += guestBedrooms.get(gueItr).getBasePrice();

				spaceIds.add(new SpaceId(DecorpotConstants.KIDS_BEDROOM, kidsBedrooms.get(kidItr).getId()));
				basePrice += kidsBedrooms.get(kidItr).getBasePrice();

				spaceIds.add(new SpaceId(DecorpotConstants.LIVING_DINING, livingAndDinings.get(livItr).getId()));
				basePrice += livingAndDinings.get(livItr).getBasePrice();
				
				Packages pkgs = new Packages();
				pkgs.setApartmentName(apartmentConfig.getApartmentName());
				pkgs.setApartmentType(config3bhk.getApartmentType());
				pkgs.setBasePrice(basePrice);
				pkgs.setSpaceIds(spaceIds);
				pkgs.setImage(DecorpotConstants.BUCKET_LOCATION + DecorpotConstants.SPACE_IMAGE_LOCATION
						+ DecorpotConstants.spaceImageSizes.get(1)
						+ livingAndDinings.get(livItr).getImages().get(0));
				masItr++;
				kitItr++;
				livItr++;
				gueItr++;
				kidItr++;
				packages.add(pkgs);
				// pkgs.setApartmentName();

			}
			DataCache.put("3BHK" + id, packages);
			return packages;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Packages> get2BHKApartmentByFloorplanId(int id)
			throws JsonParseException, JsonMappingException, IOException {
		/**
		 * todo: change all functions in space service for by type to send a
		 * limit.
		 */
		if (DataCache.getInstance().get("2BHK" + id) != null) {
			return (List<Packages>) DataCache.get("2BHK" + id);
		} else {
			List<Packages> packages = new ArrayList<>();
			// List<Map<String, Object>> packages = new ArrayList<>();
			int maxLen = 0;
			com.decorpot.datasource.models.Config2BHK config2bhk = config2BHKRepository.findOne(id);
			ApartmentConfig apartmentConfig = apartmentConfigRepository.findOne(config2bhk.getApartmentId());
			ObjectMapper mapper = new ObjectMapper();
			KitchenConfig kitchenConfig = mapper.readValue(config2bhk.getKitchenConfig(), KitchenConfig.class);
			BedroomBaseConfig masterBedroomConfig = mapper.readValue(config2bhk.getMasterBedroomConfig(),
					BedroomBaseConfig.class);
			BedroomBaseConfig guestBedroomConfig = mapper.readValue(config2bhk.getOtherBedroomConfig(),
					BedroomBaseConfig.class);
			BedroomBaseConfig kidsBedroomConfig = mapper.readValue(config2bhk.getOtherBedroomConfig(),
					BedroomBaseConfig.class);

			List<Kitchen> kitchens = spaceService.getAllKitchensByType(kitchenConfig.getKitchenType());
			int kitchenSize = kitchens.size();
			List<Bedroom> masterBedrooms = spaceService
					.getAllMasterBedroomsByType(masterBedroomConfig.getWardrobeType());
			int masterBedroomSize = masterBedrooms.size();
			List<Bedroom> guestBedrooms = spaceService.getAllGuestBedroomsByType(guestBedroomConfig.getWardrobeType());
			int guestBedroomsSize = guestBedrooms.size();
			List<Bedroom> kidsBedrooms = spaceService.getAllKidsBedroomsByType(kidsBedroomConfig.getWardrobeType());
			int kidsBedroomsSize = kidsBedrooms.size();
			List<LivingAndDining> livingAndDinings = spaceService.getAllLivingAndDining();
			int livingAndDiningsSize = livingAndDinings.size();

			if (kitchens.size() > maxLen) {
				maxLen = kitchens.size();
			}

			if (masterBedrooms.size() > maxLen) {
				maxLen = masterBedrooms.size();
			}

			if (guestBedrooms.size() > maxLen) {
				maxLen = guestBedrooms.size();
			}

			if (kidsBedrooms.size() > maxLen) {
				maxLen = kidsBedrooms.size();
			}

			if (livingAndDinings.size() > maxLen) {
				maxLen = livingAndDinings.size();
			}

			int kitItr = 0, masItr = 0, gueItr = 0, kidItr = 0, livItr = 0;
			for (int i = 0; i < maxLen; i++) {

				double basePrice = 0;
				List<SpaceId> spaceIds = new ArrayList<>();
				kitItr = kitItr < kitchenSize ? kitItr : 0;
				masItr = masItr < masterBedroomSize ? masItr : 0;
				gueItr = gueItr < guestBedroomsSize ? gueItr : 0;
				kidItr = kidItr < kidsBedroomsSize ? kidItr : 0;
				livItr = livItr < livingAndDiningsSize ? livItr : 0;

				spaceIds.add(new SpaceId(DecorpotConstants.KITCHEN, kitchens.get(kitItr).getId()));
				basePrice += kitchens.get(kitItr).getBasePrice();

				spaceIds.add(new SpaceId(DecorpotConstants.MASTER_BEDROOM, masterBedrooms.get(masItr).getId()));
				basePrice += masterBedrooms.get(masItr).getBasePrice();

				if (i % 2 == 0) {
					spaceIds.add(new SpaceId(DecorpotConstants.GUEST_BEDROOM, guestBedrooms.get(gueItr).getId()));
					basePrice += guestBedrooms.get(gueItr).getBasePrice();
					gueItr++;
				} else {
					spaceIds.add(new SpaceId(DecorpotConstants.KIDS_BEDROOM, kidsBedrooms.get(kidItr).getId()));
					basePrice += kidsBedrooms.get(kidItr).getBasePrice();
					kidItr++;
				}

				spaceIds.add(new SpaceId(DecorpotConstants.LIVING_DINING, livingAndDinings.get(livItr).getId()));
				basePrice += livingAndDinings.get(livItr).getBasePrice();

				Packages pkgs = new Packages();
				pkgs.setApartmentName(apartmentConfig.getApartmentName());
				pkgs.setApartmentType(config2bhk.getApartmentType());
				pkgs.setBasePrice(basePrice);
				pkgs.setImage(DecorpotConstants.BUCKET_LOCATION + DecorpotConstants.SPACE_IMAGE_LOCATION
						+ DecorpotConstants.spaceImageSizes.get(1) + livingAndDinings.get(livItr).getImages().get(0));
				pkgs.setSpaceIds(spaceIds);
				masItr++;
				kitItr++;
				livItr++;
				packages.add(pkgs);

			}
			Collections.sort(packages, new Comparator<Packages>() {
				@Override
				public int compare(Packages o1, Packages o2) {
					return o1.getBasePrice() > o2.getBasePrice() ? 1 : -1;
				}
			});
			
			DataCache.getInstance().put("2BHK" + id, packages);
			return packages;
		}
	}

}
