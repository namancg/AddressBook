package com.bridgelabz.addressbookproblem;

public class Address {
	private Integer placeId;
	private String city;
	private Integer zip;
	private String state;

	public Address(Integer placeId, String city, Integer zip, String state) {
		this.placeId = placeId;
		this.city = city;
		this.zip = zip;
		this.state = state;
	}

	public Address(String city, Integer zip, String state) {
		this.city = city;
		this.zip = zip;
		this.state = state;
	}

	public Address() {
		// TODO Auto-generated constructor stub
	}

	public Integer getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Integer placeId) {
		this.placeId = placeId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getZip() {
		return zip;
	}

	public void setZip(Integer zip) {
		this.zip = zip;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String toString() {

		return "State :" + state + "\nCity - " + city + "\nZip Code - " + zip;
	}

}
