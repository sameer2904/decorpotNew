package com.decorpot.rest.model;


public class Bedroom extends Space{
	
	private String wardrobeType;
	private String basePriceDesc = "Base Price included wardrobe ";
	
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
	public String getBasePriceDesc() {
		return basePriceDesc;
	}
	public void setBasePriceDesc(String basePriceDesc) {
		this.basePriceDesc = basePriceDesc;
	}
}
