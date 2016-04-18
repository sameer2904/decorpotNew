package com.decorpot.rest.model;

import java.util.List;

public class Kitchen {
	
	private String title;
	private String description;
	private Integer basePrice;
	private Integer ht;
	private Integer wdth;
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
	public Integer getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(Integer basePrice) {
		this.basePrice = basePrice;
	}
	public Integer getHt() {
		return ht;
	}
	public void setHt(Integer ht) {
		this.ht = ht;
	}
	public Integer getWdth() {
		return wdth;
	}
	public void setWdth(Integer wdth) {
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
