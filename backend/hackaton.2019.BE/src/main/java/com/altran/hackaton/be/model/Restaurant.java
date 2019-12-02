package com.altran.hackaton.be.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RESTAURANTS")
public class Restaurant {

	@Id
	@Column(name = "RESTAURANT_ID")	
	private String identifier;
	
	@Column(name = "NAME")
	private String name;

	@Column(name = "LATITUDE")
	private Double latitude;
	
	@Column(name = "LONGITUDE")
	private Double longitude;
	
	@Column(name = "ADDRESS")
	private String address;
	
	public String getIdentifier() {
		return identifier;
	}
	
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Location location() {		
		return new Location(latitude, longitude);
	}
	
}
