package com.decorpot.datasource.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LIVING_DINING")
public class LivingAndDining {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "TITLE")
	private String title;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "BASE_PRICE")
	private double basePrice;
	
	@Column(name = "HEIGHT")
	private double ht;
	
	@Column(name = "WIDTH")
	private double wdth;
	
	@Column(name = "IMAGES")
	private String images;
	
	@Column(name= "THEMES")
	private String themes;
	
	@Column(name = "CREATED_DATE", columnDefinition = "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private Date date = new java.sql.Date((new java.util.Date()).getTime());

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getThemes() {
		return themes;
	}

	public void setThemes(String themes) {
		this.themes = themes;
	}

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

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

}