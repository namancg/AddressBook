package com.bridgelabz.addressbookproblem;

import com.opencsv.bean.CsvBindByName;

public class PersonContact {
	//private String firstName, lastName, email;
	private long number;
	private String address;

	@CsvBindByName(column = "First Name")
	private String firstName;
	
	@CsvBindByName(column = "Last Name")
	private String lastName;
	
	@CsvBindByName(column = "Email")
	private String email;
	
	@CsvBindByName(column = "Phone Number")
	private long phoneNumber;
	
	@CsvBindByName(column = "City")
	private String city;
	
	@CsvBindByName(column = "State")
	private String state;
	
	@CsvBindByName(column = "Zip Code")
	private long zip;
	Place place;
	private Integer contactId;
	PersonContact(Integer contactId,String firstName, String lastName, String city, String address, String state, int zip, int phoneNumber,
			String email ,Integer placeId) {
		this.contactId=contactId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		place=new Place(placeId,city,zip,state);
	}
	public PersonContact() {
		// TODO Auto-generated constructor stub
	}
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public long getPhoneNumber() {
		return number;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setnumber(long number) {
		this.number = number;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

	public String toString() {
		
		return "Person Details:\nFirst Name:  "+firstName+" \nLast Name : "+lastName+"\nPhone Number : "+number+"\nEmail :"+email+"\nAddress : "+address;
	}
	public long getZip() {
		// TODO Auto-generated method stub
		return zip;
	}
	public Place getPlace() {
		return place;
	}
	public void setPlace(Place place) {
		this.place = place;
	}
	public String getCity() {
		return getPlace().getCity();
	}

	public void setState(String state) {
		this.getPlace().setState(state);
	}

	public String getState() {
		return getPlace().getState();
	}

	public void setCity(String city) {
		this.getPlace().setCity(city);
	}

	public int getZipCode() {
		return getPlace().getZip();
	}

	public void setZipCode(int zip) {
		this.getPlace().setZip(zip); 
	}
	public int getContactId() {
		return contactId;
	}
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	
	}


