package com.decorpot.services;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.decorpot.datasource.models.Kitchen;
import com.decorpot.datasource.repository.KitchenRepository;
import com.decorpot.utils.ImageProcessorService;
import com.decorpot.utils.decorpotTx;

@Service
public class SpaceUploaderService {

	private static final Logger logger = LoggerFactory
			.getLogger(SpaceUploaderService.class);
	private static final String LOGGER_PREFIX = "[SpaceUploaderService] ";

	@Autowired
	private KitchenRepository kitchenRepository;

	@Autowired
	private ImageProcessorService imageProcessorService;

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
		try {
			kitchen = kitchenRepository.save(kitchen);
			return kitchen.getId();
		} catch (Exception e) {
			logger.error(LOGGER_PREFIX + e.getMessage(), e);
			throw e;
		}
	}

}
