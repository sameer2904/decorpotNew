package com.decorpot.rest.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class MultipleImages {

	private List<MultipartFile> crunchifyFiles;

	public List<MultipartFile> getFiles() {
		return crunchifyFiles;
	}

	public void setFiles(List<MultipartFile> files) {
		this.crunchifyFiles = files;
	}
}
