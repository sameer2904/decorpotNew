package com.decorpot.rest.model;


public class Bedroom extends Space{
	
	private String wardrobeType;
	
	public String getWardrobeType() {
		return wardrobeType;
	}
	public void setWardrobeType(String wardrobeType) {
		this.wardrobeType = wardrobeType;
	}
    @Override
    public String toString() {
        return "Bedroom [wardrobeType=" + wardrobeType + "]";
    }
	
	
}
