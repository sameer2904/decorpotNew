package com.decorpot.rest.model;

public class Config2BHK extends ApartmentBaseConfig{

    
    private BedroomBaseConfig otherBedroom;

    public BedroomBaseConfig getOtherBedroom() {
        return otherBedroom;
    }

    public void setOtherBedroom(BedroomBaseConfig otherBedroom) {
        this.otherBedroom = otherBedroom;
    }

    @Override
    public String toString() {
        return "Config2BHK [otherBedroom=" + otherBedroom + ", apartmentName=" + apartmentName + ", apartmentType="
                + apartmentType + ", floorPlan=" + floorPlan + ", kitchen=" + kitchen + ", masterBedroom="
                + masterBedroom + "]";
    }

   
}
