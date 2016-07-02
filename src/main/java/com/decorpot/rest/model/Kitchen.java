package com.decorpot.rest.model;


public class Kitchen extends Space{
	
	
	private String kitchenType;
	private String basePriceDesc = "Base Price includes Kitchen Fixed Furnitures";	
	public Kitchen() {
		super();
	}
	
	public String getKitchenType() {
		return kitchenType;
	}
	public void setKitchenType(String kitchenType) {
		this.kitchenType = kitchenType;
	}
	@Override
	public String toString() {
		return "Kitchen [kitchenType=" + kitchenType + "]";
	}

	public String getBasePriceDesc() {
		return basePriceDesc;
	}

	public void setBasePriceDesc(String basePriceDesc) {
		this.basePriceDesc = basePriceDesc;
	}
	
}
