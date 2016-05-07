package com.decorpot.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.decorpot.datasource.models.ApartmentConfig;
import com.decorpot.datasource.repository.ApartmentConfigRepository;
import com.decorpot.datasource.repository.Config2BHKRepository;
import com.decorpot.datasource.repository.Config3BHKRepository;
import com.decorpot.rest.model.ApartmentConfigs;
import com.decorpot.rest.model.Config2BHK;
import com.decorpot.utils.DecorpotConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ApartmentService {

	@Autowired
	private Config2BHKRepository config2BHKRepository;

	@Autowired
	private Config3BHKRepository config3bhkRepository;

	@Autowired
	private ApartmentConfigRepository apartmentConfigRepository;

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

}
