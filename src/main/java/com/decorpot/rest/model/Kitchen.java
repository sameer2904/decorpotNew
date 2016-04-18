package com.decorpot.rest.model;

import java.util.List;

public class Kitchen {
	
	private String title;
	private String description;
	private Float basePrice;
	private Float ht;
	private Float wdth;
	private List<String> images;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Float getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(Float basePrice) {
		this.basePrice = basePrice;
	}
	public Float getHt() {
		return ht;
	}
	public void setHt(Float ht) {
		this.ht = ht;
	}
	public Float getWdth() {
		return wdth;
	}
	public void setWdth(Float wdth) {
		this.wdth = wdth;
	}
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
	@Override
	public String toString() {
		return "Kitchen [title=" + title + ", description=" + description
				+ ", basePrice=" + basePrice + ", ht=" + ht + ", wdth=" + wdth
				+ ", images=" + images + "]";
	}
	
}