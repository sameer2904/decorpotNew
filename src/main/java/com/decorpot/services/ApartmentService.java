package com.decorpot.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		List<com.decorpot.datasource.models.ApartmentConfig> apartmentConfigs = (List<ApartmentConfig>) apartmentConfigRepository
				.findAll();
		List<ApartmentConfigs> configs = new ArrayList<>();
		apartmentConfigs.forEach(a -> {
			ApartmentConfigs aConfs = new ApartmentConfigs();
			aConfs.setAddress(a.getAddress());
			aConfs.setApartmentName(a.getApartmentName());
			aConfs.setId(a.getId());
			aConfs.setImage(
					DecorpotConstants.BUCKET_LOCATION + DecorpotConstants.APARTMENT_IMAGE_LOCATION + a.getImage());
			configs.add(aConfs);
		});
		return configs;
	}

	public ApartmentConfigs getAllFloorPlanByApartmentName(String apartmentName) {
		com.decorpot.datasource.models.ApartmentConfig apartmentConfigs = apartmentConfigRepository
				.findByApartmentName(apartmentName);

		ApartmentConfigs configs = new ApartmentConfigs();

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
		return configs;
	}

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
			//List<Map<String, Object>> packages = new ArrayList<>();
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

			for (int i = 0; i < maxLen; i++) {
				Map<String, Object> pkg = new HashMap<>();
				pkg.put(DecorpotConstants.KITCHEN, kitchens.get(i < kitchenSize ? i : (i - kitchenSize)));
				pkg.put(DecorpotConstants.MASTER_BEDROOM,
						masterBedrooms.get(i < masterBedroomSize ? i : (i - masterBedroomSize)));
				pkg.put(DecorpotConstants.GUEST_BEDROOM,
						guestBedrooms.get(i < guestBedroomsSize ? i : (i - guestBedroomsSize)));
				pkg.put(DecorpotConstants.KIDS_BEDROOM,
						kidsBedrooms.get(i < kidsBedroomsSize ? i : (i - kidsBedroomsSize)));
				pkg.put(DecorpotConstants.LIVING_DINING,
						livingAndDinings.get(i < livingAndDiningsSize ? i : (i - livingAndDiningsSize)));
				Packages pkgs = new Packages();
				//pkgs.setApartmentName();
				
			}
			DataCache.put("3BHK" + id, packages);
			return packages;
		}
	}

}
