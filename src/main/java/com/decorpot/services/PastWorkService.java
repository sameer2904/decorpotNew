package com.decorpot.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.decorpot.datasource.repository.PastWorkRepository;
import com.decorpot.rest.model.PastWork;
import com.decorpot.rest.model.PastWorkBackend;
import com.decorpot.rest.model.PastWorkImageUrls;
import com.decorpot.utils.DecorpotConstants;
import com.decorpot.utils.ImageProcessorService;

@Service
public class PastWorkService {

	@Autowired
	private PastWorkRepository pastWorkRepository;

	@Autowired
	private ImageProcessorService imageProcessorService;

	public void uploadPastWork(PastWork pastWork) throws Exception {

		com.decorpot.datasource.models.PastWork work = pastWorkRepository
				.findByApartmentName(pastWork.getApartmentName());
		if (work == null) {
			work = new com.decorpot.datasource.models.PastWork();
			work.setApartmentName(pastWork.getApartmentName());
			work.setImages(String.join(",", pastWork.getImages()));
			work.setMainImage(pastWork.getMainImage());
			work.setActive(true);
			pastWorkRepository.save(work);
		} else {
			throw new Exception("apartment already exist");
		}

	}

	public PastWork getPastWorkById(Integer id) {

		String folderLocation = DecorpotConstants.BUCKET_LOCATION + DecorpotConstants.PAST_WORK_IMAGE_LOCATION;
		com.decorpot.datasource.models.PastWork work = pastWorkRepository.findById(id);
		PastWork pastWork = new PastWork();
		if (work != null) {
			pastWork.setId(work.getId());
			pastWork.setApartmentName(work.getApartmentName());
			List<PastWorkImageUrls> imageUrls = new ArrayList<>();
			for (String image : work.getImages().split(",")) {
				PastWorkImageUrls pastWorkImageUrls = new PastWorkImageUrls();
				pastWorkImageUrls.setUrl(folderLocation + DecorpotConstants.pastWorkImageSizes.get(0) + image);
				pastWorkImageUrls.setThumbnail(folderLocation + DecorpotConstants.pastWorkImageSizes.get(1) + image);
				imageUrls.add(pastWorkImageUrls);
			}
			pastWork.setImageUrls(imageUrls);
		}
		return pastWork;
	}

	public List<PastWork> getAllPastWork() {

		String folderLocation = DecorpotConstants.BUCKET_LOCATION + DecorpotConstants.PAST_WORK_IMAGE_LOCATION;
		List<com.decorpot.datasource.models.PastWork> works = pastWorkRepository.findByActiveTrueOrderByDateDesc();
		List<PastWork> pastWorks = new ArrayList<>();
		if (!CollectionUtils.isEmpty(works)) {
			works.forEach(w -> {
				PastWork pastWork = new PastWork();
				pastWork.setId(w.getId());
				pastWork.setApartmentName(w.getApartmentName());
				pastWork.setMainImage(folderLocation + DecorpotConstants.pastWorkImageSizes.get(1) + w.getMainImage());
				pastWork.setMainImageHd(
						folderLocation + DecorpotConstants.pastWorkImageSizes.get(0) + w.getMainImage());
				pastWorks.add(pastWork);
			});
		}
		return pastWorks;
	}

	public void backendPastworkUpload(PastWorkBackend backend) throws Exception {

		com.decorpot.datasource.models.PastWork work = pastWorkRepository
				.findByApartmentName(backend.getApartmentName());
		List<String> imgFiles = new ArrayList<>();
		if (work == null) {
			work = new com.decorpot.datasource.models.PastWork();
			work.setApartmentName(backend.getApartmentName());

			File folder = new File(backend.getFolderPath());
			File[] files = folder.listFiles();

			if (files == null) {
				throw new Exception("folder not found");
			}

			for (File f : files) {
				imgFiles.add(f.getName());
			}

			work.setImages(String.join(",", imgFiles));
			work.setMainImage(backend.getMainImage());
			work.setActive(true);
			pastWorkRepository.save(work);
			
			for(File f : files) {
				imageProcessorService.uploadPastWorkImage(f);
			}
		} else {
			throw new Exception("apartment already exist");
		}
	}

}
