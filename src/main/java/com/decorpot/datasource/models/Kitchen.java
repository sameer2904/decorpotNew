package com.decorpot.datasource.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "KITCHEN")
public class Kitchen {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "TITLE")
	private String title;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "BASE_PRICE")
	private Float basePrice;
	
	@Column(name = "HEIGHT")
	private Float ht;
	
	@Column(name = "WIDTH")
	private Float wdth;
	
	@Column(name = "IMAGES")
	private String images;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "Kitchen [id=" + id + ", title=" + title + ", description="
				+ description + ", basePrice=" + basePrice + ", ht=" + ht
				+ ", wdth=" + wdth + ", images=" + images + "]";
	}
	
}
