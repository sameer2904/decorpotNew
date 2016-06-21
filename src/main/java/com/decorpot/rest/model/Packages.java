package com.decorpot.rest.model;

import java.util.List;

public class Packages {
	
	private String apartmentName;
	private String apartmentType;
	private double basePrice;
	private String image;
	private List<SpaceId> spaceIds;
	
	public String getApartmentName() {
		return apartmentName;
	}
	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}
	public String getApartmentType() {
		return apartmentType;
	}
	public void setApartmentType(String apartmentType) {
		this.apartmentType = apartmentType;
	}
	public double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public List<SpaceId> getSpaceIds() {
		return spaceIds;
	}
	public void setSpaceIds(List<SpaceId> spaceIds) {
		this.spaceIds = spaceIds;
	}
	
	
}
