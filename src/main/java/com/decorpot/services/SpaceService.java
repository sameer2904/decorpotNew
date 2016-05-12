package com.decorpot.services;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.util.CollectionUtils;
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
import com.decorpot.utils.DataCache;
import com.decorpot.utils.DecorpotConstants;
import com.decorpot.utils.ImageProcessorService;
import com.decorpot.utils.decorpotTx;

@Service
@SuppressWarnings({ "unchecked", "static-access" })
public class SpaceService {

	private static final Logger logger = LoggerFactory.getLogger(SpaceService.class);
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
		kitchen.setThemes(String.join(",", kit.getThemes()));
		kitchen.setKitchenType(kit.getKitchenType());
		try {
			kitchen = kitchenRepository.save(kitchen);
			final int id = kitchen.getId();
			kit.getAddons().forEach(a -> {
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
		bedroom.setThemes(String.join(",", bed.getThemes()));
		bedroom.setWardrobeType(bed.getWardrobeType());
		try {
			bedroom = kidsBedroomRepository.save(bedroom);
			final int id = bedroom.getId();
			bed.getAddons().forEach(a -> {
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
		bedroom.setThemes(String.join(",", bed.getThemes()));
		bedroom.setWardrobeType(bed.getWardrobeType());
		try {
			bedroom = masterBedroomRepository.save(bedroom);
			final int id = bedroom.getId();
			bed.getAddons().forEach(a -> {
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
		bedroom.setThemes(String.join(",", bed.getThemes()));
		bedroom.setWardrobeType(bed.getWardrobeType());
		try {
			bedroom = guestBedroomRepository.save(bedroom);
			final int id = bedroom.getId();
			bed.getAddons().forEach(a -> {
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

	@decorpotTx
	public List<com.decorpot.rest.model.Kitchen> getAllKitchens() {

		long start = System.currentTimeMillis();
		List<com.decorpot.rest.model.Kitchen> kitchens = new ArrayList<>();
		if (DataCache.getInstance().get(DecorpotConstants.KITCHEN + DecorpotConstants.ALL) != null) {
			kitchens = (List<com.decorpot.rest.model.Kitchen>) DataCache.getInstance()
					.get(DecorpotConstants.KITCHEN + DecorpotConstants.ALL);
		} else {

			List<Kitchen> list = kitchenRepository.findAll();
			if (!CollectionUtils.isNullOrEmpty(list)) {
				for (Kitchen k : list) {
					kitchens.add(kitchenRepoToRestModelConverter(k));
				}
			}
			DataCache.getInstance().put(DecorpotConstants.KITCHEN + DecorpotConstants.ALL, kitchens);

		}
		long end = System.currentTimeMillis();
		System.out.println("time take -> " + (end-start));
		return kitchens;
	}

	@decorpotTx
	public List<com.decorpot.rest.model.Kitchen> getAllKitchensByType(String type) {
		List<com.decorpot.rest.model.Kitchen> kitchens = new ArrayList<>();
		if (DataCache.getInstance().get(DecorpotConstants.KITCHEN + type) != null) {
			kitchens = (List<com.decorpot.rest.model.Kitchen>) DataCache.getInstance()
					.get(DecorpotConstants.KITCHEN + type);
		} else if (DataCache.getInstance().get(DecorpotConstants.KITCHEN + DecorpotConstants.ALL) != null) {
			kitchens = ((List<com.decorpot.rest.model.Kitchen>) DataCache.getInstance()
					.get(DecorpotConstants.KITCHEN + type)).parallelStream().filter(k -> k.getKitchenType() == type)
							.collect(Collectors.toList());
			DataCache.getInstance().put(DecorpotConstants.KITCHEN + type, kitchens);
		} else {
			List<Kitchen> list = kitchenRepository.findByKitchenType(type);
			if (!CollectionUtils.isNullOrEmpty(list)) {
				kitchens = list.parallelStream().map(k -> kitchenRepoToRestModelConverter(k))
						.collect(Collectors.toList());
			}

			DataCache.getInstance().put(DecorpotConstants.KITCHEN + type, kitchens);
		}
		return kitchens;
	}

	@decorpotTx
	public com.decorpot.rest.model.Kitchen getKitchensById(int id) {
		com.decorpot.rest.model.Kitchen kitchen = null;
		if (DataCache.getInstance().get(DecorpotConstants.KITCHEN + id) != null) {
			kitchen = (com.decorpot.rest.model.Kitchen) DataCache.getInstance().get(DecorpotConstants.KITCHEN + id);
		} else if (DataCache.getInstance().get(DecorpotConstants.KITCHEN + DecorpotConstants.ALL) != null) {
			List<com.decorpot.rest.model.Kitchen> kitchens = (List<com.decorpot.rest.model.Kitchen>) DataCache
					.getInstance().get(DecorpotConstants.KITCHEN + DecorpotConstants.ALL);
			kitchens.parallelStream().filter(k -> k.getId() == id).findAny().orElse(null);
			if (kitchen != null) {
				List<com.decorpot.datasource.models.Addon> addons = addonRepository.findByParentId(kitchen.getId());
				kitchen.setAddons(
						addons.parallelStream().map(a -> addonRepoToRestConverter(a)).collect(Collectors.toList()));
			}
			DataCache.getInstance().put(DecorpotConstants.KITCHEN + id, kitchen);
		} else {
			Kitchen kitchen2 = kitchenRepository.findOne(id);
			kitchen = kitchenRepoToRestModelConverter(kitchen2);
			List<com.decorpot.datasource.models.Addon> addons = addonRepository.findByParentId(kitchen.getId());
			kitchen.setAddons(
					addons.parallelStream().map(a -> addonRepoToRestConverter(a)).collect(Collectors.toList()));

		}
		return kitchen;
	}

	private com.decorpot.rest.model.Kitchen kitchenRepoToRestModelConverter(Kitchen k) {
		com.decorpot.rest.model.Kitchen kitchen = new com.decorpot.rest.model.Kitchen();
		kitchen.setBasePrice(k.getBasePrice());
		kitchen.setDescription(k.getDescription());
		kitchen.setImages(Arrays.asList(k.getImages().split(",")));
		kitchen.setThemes(Arrays.asList(k.getThemes().split(",")));
		kitchen.setKitchenType(k.getKitchenType());
		kitchen.setTitle(k.getTitle());
		kitchen.setId(k.getId());
		return kitchen;
	}

	private Addon addonRepoToRestConverter(com.decorpot.datasource.models.Addon a) {

		Addon addon = new Addon();
		addon.setName(a.getName());
		addon.setPrice(a.getPrice());
		addon.setId(a.getId());
		return addon;
	}

	// private String spaceImageUrlMapper(List<String> images){
	// return images.parallelStream().map(i -> DecorpotConstants.BUCKET_LOCATION
	// + DecorpotConstants.SPACE_IMAGE_LOCATION)
	// }

}
