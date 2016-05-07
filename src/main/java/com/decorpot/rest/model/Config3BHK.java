package com.decorpot.rest.model;

public class Config3BHK extends ApartmentBaseConfig{

	protected KitchenConfig kitchen;
    protected BedroomBaseConfig masterBedroom;
    protected BedroomBaseConfig kidsBedroom;
    protected BedroomBaseConfig guestBedroom;
    
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
