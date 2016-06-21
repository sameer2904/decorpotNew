package com.decorpot.rest.model;

public class SpaceId {

	private String space;
	private int id;
	
	public SpaceId(String space, int id) {
		this.space = space;
		this.id = id;
	}
	
	public String getSpace() {
		return space;
	}
	public void setSpace(String space) {
		this.space = space;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
