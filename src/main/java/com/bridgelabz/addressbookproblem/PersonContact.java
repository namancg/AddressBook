package com.bridgelabz.addressbookproblem;

import com.opencsv.bean.CsvBindByName;

public class PersonContact {
	//private String firstName, lastName, email;
	private long number;
	private Address address;

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
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public long getnumber() {
		return number;
	}
	
	public String getEmail() {
		return email;
	}
	
	public Address getAddress() {
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
	
	public void setAddress(Address address) {
		this.address = address;
	}

	public String toString() {
		
		return "Person Details:\nFirst Name:  "+firstName+" \nLast Name : "+lastName+"\nPhone Number : "+number+"\nEmail :"+email+"\nAddress : "+address;
	}

		
	}


