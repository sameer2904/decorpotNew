package com.decorpot.rest.model;

public class Config2BHK extends ApartmentBaseConfig{

	protected KitchenConfig kitchen;
    protected BedroomBaseConfig masterBedroom;
    private BedroomBaseConfig otherBedroom;

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

	public BedroomBaseConfig getOtherBedroom() {
        return otherBedroom;
    }

    public void setOtherBedroom(BedroomBaseConfig otherBedroom) {
        this.otherBedroom = otherBedroom;
    }

}
