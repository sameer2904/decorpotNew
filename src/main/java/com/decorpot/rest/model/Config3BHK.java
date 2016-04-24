package com.decorpot.rest.model;

public class Config3BHK extends ApartmentBaseConfig{

    protected BedroomBaseConfig kidsBedroom;
    protected BedroomBaseConfig guestBedroom;
    
    public BedroomBaseConfig getKidsBedroom() {
        return kidsBedroom;
    }
    public void setKidsBedroom(BedroomBaseConfig kidsBedroom) {
        this.kidsBedroom = kidsBedroom;
    }
    public BedroomBaseConfig getGuestBedroom() {
        return guestBedroom;
    }
    public void setGuestBedroom(BedroomBaseConfig guestBedroom) {
        this.guestBedroom = guestBedroom;
    }
    
    
    
}
