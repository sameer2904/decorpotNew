package com.decorpot.rest.model;

public class BedroomBaseConfig extends Dimension{

    private String wardrobeType;

    public String getWardrobeType() {
        return wardrobeType;
    }

    public void setWardrobeType(String wardrobeType) {
        this.wardrobeType = wardrobeType;
    }

    @Override
    public String toString() {
        return "BedroomBaseConfig [wardrobeType=" + wardrobeType + "]";
    }
}
