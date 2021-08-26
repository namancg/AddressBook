package com.bridgelabz.addressbook;

public class Address {
	private String city, state;
	private int zip;
	
	public String getCity() {
		return city;
	}
	
	public String getState() {
		return state;
	}
	
	public long getZip() {
		return zip;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public void setZip(int zipCode) {
		this.zip = zipCode;
	}
	
	public String toString() {
		
		return "State - "+state+"\nCity - "+city+"\nZip Code - "+zip;
}
}