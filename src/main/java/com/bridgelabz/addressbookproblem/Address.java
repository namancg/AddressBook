package com.bridgelabz.addressbookproblem;

public class Address {
	private String city, state;
	private long zip;
	
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
	
	public void setZip(long zipCode) {
		this.zip = zipCode;
	}
	
	public String toString() {
		
		return "State - "+state+"\nCity - "+city+"\nZip Code - "+zip;
	}

}
