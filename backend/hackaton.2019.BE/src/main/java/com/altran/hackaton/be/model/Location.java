package com.altran.hackaton.be.model;

public class Location {

	private double x;
	private double y;
	
	Location(double x, double y) {		
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double distance(Location location) {
		// TODO Auto-generated method stub
		return -1;
	}

	public static Location of(double x, double y) {		
		return new Location(x, y);
	}	
}
