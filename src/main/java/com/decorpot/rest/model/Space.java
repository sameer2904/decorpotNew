package com.decorpot.rest.model;

import java.util.List;

public abstract class Space {


	private int id;
	private String title;
	private String description;
	private double basePrice;
	private double ht;
	private double wdth;
	private List<String> themes;
	private List<Addon> addons;
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
	public double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	public double getHt() {
		return ht;
	}
	public void setHt(double ht) {
		this.ht = ht;
	}
	public double getWdth() {
		return wdth;
	}
	public void setWdth(double wdth) {
		this.wdth = wdth;
	}
	
	public List<String> getThemes() {
		return themes;
	}
	public void setThemes(List<String> themes) {
		this.themes = themes;
	}
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
	@Override
	public String toString() {
		return "Space [title=" + title + ", description=" + description
				+ ", basePrice=" + basePrice + ", ht=" + ht + ", wdth=" + wdth
				+ ", themes=" + themes 
				+ ", images=" + images + "]";
	}
    public List<Addon> getAddons() {
        return addons;
    }
    public void setAddons(List<Addon> addons) {
        this.addons = addons;
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
