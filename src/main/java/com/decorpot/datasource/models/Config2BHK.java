package com.decorpot.datasource.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="CONFIG_2BHK")
public class Config2BHK {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @Column(name = "PLAN_NAME")
    private String planName;
    
    @Column(name = "KITCHEN_CONFIG")
    private String kitchenConfig;
    
    @Column(name = "MASTERBEDROOM_CONFIG")
    private String masterBedroomConfig;
    
    @Column(name = "OTHERBEDROOM_CONFIG")
    private String otherBedroomConfig;
    
    @Column(name = "FLOOR_PLAN")
    private String floorPlan;
    
    @Column(name= "APARTMENT_ID")
    private Integer apartmentId;
    
    @Column(name= "APARTMENT_TYPE")
    private String apartmentType;
    
    @Column(name = "CREATED_DATE", columnDefinition = "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private Date date;
    
    @Column(name = "ACTIVE")
    @Type(type = "yes_no")
    private boolean active;

    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getKitchenConfig() {
        return kitchenConfig;
    }

    public void setKitchenConfig(String kitchenConfig) {
        this.kitchenConfig = kitchenConfig;
    }

    public String getMasterBedroomConfig() {
        return masterBedroomConfig;
    }

    public void setMasterBedroomConfig(String masterBedroomConfig) {
        this.masterBedroomConfig = masterBedroomConfig;
    }

    public String getOtherBedroomConfig() {
        return otherBedroomConfig;
    }

    public void setOtherBedroomConfig(String otherBedroomConfig) {
        this.otherBedroomConfig = otherBedroomConfig;
    }

    public String getFloorPlan() {
        return floorPlan;
    }

    public void setFloorPlan(String floorPlan) {
        this.floorPlan = floorPlan;
    }

    public Integer getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Integer apartmentId) {
        this.apartmentId = apartmentId;
    }

}
