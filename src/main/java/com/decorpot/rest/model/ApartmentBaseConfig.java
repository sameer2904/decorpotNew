package com.decorpot.rest.model;

public class ApartmentBaseConfig {
	
    protected String apartmentName;
    protected String planName;
    protected String apartmentType;
    protected String floorPlan;
    protected int planId;
    
    public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}
	public String getApartmentName() {
        return apartmentName;
    }
    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }
    public String getApartmentType() {
        return apartmentType;
    }
    public String getPlanName() {
        return planName;
    }
    public void setPlanName(String planName) {
        this.planName = planName;
    }
    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }
   
    public String getFloorPlan() {
        return floorPlan;
    }
    public void setFloorPlan(String floorPlan) {
        this.floorPlan = floorPlan;
    }
	
}
