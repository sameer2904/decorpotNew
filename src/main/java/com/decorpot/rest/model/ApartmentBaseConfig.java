package com.decorpot.rest.model;

public class ApartmentBaseConfig {
    protected String apartmentName;
    protected String planName;
    protected String apartmentType;
    protected String floorPlan;
    protected KitchenConfig kitchen;
    protected BedroomBaseConfig masterBedroom;
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
   
    
    public KitchenConfig getKitchen() {
        return kitchen;
    }
    public void setKitchen(KitchenConfig kitchen) {
        this.kitchen = kitchen;
    }
    public BedroomBaseConfig getMasterBedroom() {
        return masterBedroom;
    }
    public void setMasterBedroom(BedroomBaseConfig masterBedroom) {
        this.masterBedroom = masterBedroom;
    }
    public String getFloorPlan() {
        return floorPlan;
    }
    public void setFloorPlan(String floorPlan) {
        this.floorPlan = floorPlan;
    }
    @Override
    public String toString() {
        return "ApartmentBaseConfig [apartmentName=" + apartmentName + ", planName=" + planName + ", apartmentType="
                + apartmentType + ", floorPlan=" + floorPlan + ", kitchen=" + kitchen + ", masterBedroom="
                + masterBedroom + "]";
    }
    
    
}
