package com.decorpot.rest.model;

public class Dimension {
    private float length;
    private float width;
    
    public float getLength() {
        return length;
    }
    public void setLength(float length) {
        this.length = length;
    }
    public float getWidth() {
        return width;
    }
    public void setWidth(float width) {
        this.width = width;
    }
    @Override
    public String toString() {
        return "Dimension [length=" + length + ", width=" + width + "]";
    }

}
