package com.decorpot.rest.model;


public class Kitchen extends Space{
	
	
	private String kitchenType;
	
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
	
}
