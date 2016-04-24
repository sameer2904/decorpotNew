package com.decorpot.rest.model;

public class KitchenConfig extends Dimension{

    private String kitchenType;

    public String getKitchenType() {
        return kitchenType;
    }

    public void setKitchenType(String kitchenType) {
        this.kitchenType = kitchenType;
    }

    @Override
    public String toString() {
        return "KitchenConfig [kitchenType=" + kitchenType + "]";
    }
    
}
