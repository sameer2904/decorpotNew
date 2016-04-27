package com.decorpot.services;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.decorpot.datasource.models.GuestBedroom;
import com.decorpot.datasource.models.KidsBedroom;
import com.decorpot.datasource.models.Kitchen;
import com.decorpot.datasource.models.MasterBedroom;
import com.decorpot.datasource.repository.AddonRepository;
import com.decorpot.datasource.repository.GuestBedroomRepository;
import com.decorpot.datasource.repository.KidsBedroomRepository;
import com.decorpot.datasource.repository.KitchenRepository;
import com.decorpot.datasource.repository.MasterBedroomRepository;
import com.decorpot.rest.model.Addon;
import com.decorpot.rest.model.Space;
import com.decorpot.utils.ImageProcessorService;
import com.decorpot.utils.decorpotTx;

@Service
public class SpaceService {

	private static final Logger logger = LoggerFactory
			.getLogger(SpaceService.class);
	private static final String LOGGER_PREFIX = "[SpaceUploaderService] ";

	@Autowired
	private KitchenRepository kitchenRepository;
	
	@Autowired
	private GuestBedroomRepository guestBedroomRepository;
	
	@Autowired
	private KidsBedroomRepository kidsBedroomRepository;
	
	@Autowired
	private MasterBedroomRepository masterBedroomRepository;

	@Autowired
	private ImageProcessorService imageProcessorService;
	
	@Autowired
	private AddonRepository addonRepository;

	public void uploadSpaceImage(File file) throws Exception {
		imageProcessorService.uploadSpaceImages(file);
	}

	@decorpotTx
	public Integer uploadKitchen(com.decorpot.rest.model.Kitchen kit) {

		Kitchen kitchen = new Kitchen();
		kitchen.setBasePrice(kit.getBasePrice());
		kitchen.setDescription(kit.getDescription());
		kitchen.setHt(kit.getHt());
		kitchen.setWdth(kit.getWdth());
		kitchen.setImages(String.join(",", kit.getImages()));
		kitchen.setTitle(kit.getTitle());
		kitchen.setThemes(kit.getThemes());
		kitchen.setKitchenType(kit.getKitchenType());
		try {
			kitchen = kitchenRepository.save(kitchen);
			final int id = kitchen.getId();
            kit.getAddons().forEach(a ->{
                uploadAddons(a, id);
            });
            return id;
		} catch (Exception e) {
			logger.error(LOGGER_PREFIX + e.getMessage(), e);
			throw e;
		}
	}
	
	@decorpotTx
	public Integer uploadKidsBedroom(com.decorpot.rest.model.Bedroom bed) {

		KidsBedroom bedroom = new KidsBedroom();
		bedroom.setBasePrice(bed.getBasePrice());
		bedroom.setDescription(bed.getDescription());
		bedroom.setHt(bed.getHt());
		bedroom.setWdth(bed.getWdth());
		bedroom.setImages(String.join(",", bed.getImages()));
		bedroom.setTitle(bed.getTitle());
		bedroom.setThemes(bed.getThemes());
		bedroom.setWardrobeType(bed.getWardrobeType());
		try {
			bedroom = kidsBedroomRepository.save(bedroom);
			final int id = bedroom.getId();
            bed.getAddons().forEach(a ->{
                uploadAddons(a, id);
            });
            return id;
		} catch (Exception e) {
			logger.error(LOGGER_PREFIX + e.getMessage(), e);
			throw e;
		}
	}
	
	@decorpotTx
	public Integer uploadMasterBedroom(com.decorpot.rest.model.Bedroom bed) {

		MasterBedroom bedroom = new MasterBedroom();
		bedroom.setBasePrice(bed.getBasePrice());
		bedroom.setDescription(bed.getDescription());
		bedroom.setHt(bed.getHt());
		bedroom.setWdth(bed.getWdth());
		bedroom.setImages(String.join(",", bed.getImages()));
		bedroom.setTitle(bed.getTitle());
		bedroom.setThemes(bed.getThemes());
		bedroom.setWardrobeType(bed.getWardrobeType());
		try {
			bedroom = masterBedroomRepository.save(bedroom);
			final int id = bedroom.getId();
            bed.getAddons().forEach(a ->{
                uploadAddons(a, id);
            });
            return id;
		} catch (Exception e) {
			logger.error(LOGGER_PREFIX + e.getMessage(), e);
			throw e;
		}
	}
	
	@decorpotTx
	public Integer uploadGuestBedroom(com.decorpot.rest.model.Bedroom bed) {

		GuestBedroom bedroom = new GuestBedroom();
		bedroom.setBasePrice(bed.getBasePrice());
		bedroom.setDescription(bed.getDescription());
		bedroom.setHt(bed.getHt());
		bedroom.setWdth(bed.getWdth());
		bedroom.setImages(String.join(",", bed.getImages()));
		bedroom.setTitle(bed.getTitle());
		bedroom.setThemes(bed.getThemes());
		bedroom.setWardrobeType(bed.getWardrobeType());
		try {
			bedroom = guestBedroomRepository.save(bedroom);
			final int id = bedroom.getId();
			bed.getAddons().forEach(a ->{
			    uploadAddons(a, id);
			});
			return id;
		} catch (Exception e) {
			logger.error(LOGGER_PREFIX + e.getMessage(), e);
			throw e;
		}
	}
	
	@decorpotTx
	public void uploadAddons(Addon addon, int id) {
	    com.decorpot.datasource.models.Addon addonData = new com.decorpot.datasource.models.Addon();
	    addonData.setName(addon.getName());
	    addonData.setParentId(id);
	    addonData.setPrice(addon.getPrice());
	    addonRepository.save(addonData);
	}

}
