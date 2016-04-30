package com.decorpot.datasource.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="CONFIG_3BHK")
public class Config3BHK {

    @Id
    @Column(name = "PLAN_NAME")
    private String planName;
    
    @Column(name = "KITCHEN_CONFIG")
    private String kitchenConfig;
    
    @Column(name = "MASTERBEDROOM_CONFIG")
    private String masterBedroomConfig;
    
    @Column(name = "KIDSBEDROOM_CONFIG")
    private String kidsBedroomConfig;
    
    @Column(name = "GUESTBEDROOM_CONFIG")
    private String guestBedroomConfig;
    
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

    public String getKidsBedroomConfig() {
        return kidsBedroomConfig;
    }

    public void setKidsBedroomConfig(String kidsBedroomConfig) {
        this.kidsBedroomConfig = kidsBedroomConfig;
    }

    public String getGuestBedroomConfig() {
        return guestBedroomConfig;
    }

    public void setGuestBedroomConfig(String guestBedroomConfig) {
        this.guestBedroomConfig = guestBedroomConfig;
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

    public String getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }
    
    
}