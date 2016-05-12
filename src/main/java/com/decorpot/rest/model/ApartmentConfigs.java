package com.decorpot.rest.model;

import java.util.List;

public class ApartmentConfigs {

	private int id;
	private String apartmentName;
	private String image;
	private String address;
	private List<ApartmentBaseConfig> apartmentBaseConfigs;
	
	public String getApartmentName() {
		return apartmentName;
	}
	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<ApartmentBaseConfig> getApartmentBaseConfigs() {
		return apartmentBaseConfigs;
	}
	public void setApartmentBaseConfigs(List<ApartmentBaseConfig> apartmentBaseConfigs) {
		this.apartmentBaseConfigs = apartmentBaseConfigs;
	}
	
}
