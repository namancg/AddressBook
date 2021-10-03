package com.bridgelabz.addressbookproblem;

import java.util.HashMap;
import com.opencsv.bean.CsvBindByName;

public class PersonContact {
	// private String firstName, lastName, email;
	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@CsvBindByName
	private int contactId;
	@CsvBindByName
	private String firstName;
	@CsvBindByName
	private String lastName;
	@CsvBindByName
	private String address;
	@CsvBindByName
	private String city;
	@CsvBindByName
	private String state;
	@CsvBindByName
	private int zip;
	@CsvBindByName
	private String phoneNumber;
	@CsvBindByName
	private String email;
	Address Address;

	PersonContact(String firstName, String lastName, String city, String address, String state, int zip,
			String phoneNumber, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		Address = new Address(city, zip, state);
	}

	public void setAddress(Address Address) {
		this.Address = Address;
	}

	PersonContact(Integer contactId, String firstName, String lastName, String city, String address, String state,
			int zip, String phoneNumber, String email, Integer AddressId) {
		this.contactId = contactId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		Address = new Address(AddressId, city, zip, state);
	}

	public PersonContact() {
		// TODO Auto-generated constructor stub
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getZipCode() {
		return zip;
	}

	public void setZipCode(int zip) {
		this.zip = zip;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailId() {
		return email;
	}

	public void setEmailId(String email) {
		this.email = email;
	}

	@Override
	public boolean equals(Object anotherObject) {
		@SuppressWarnings("unchecked")
		HashMap<String, PersonContact> addressBook = (HashMap<String, PersonContact>) anotherObject;
		if (addressBook.keySet().stream().anyMatch(s -> (s.equals(firstName)))) {
			return true;
		}
		return false;

	}

	@Override
	public String toString() {
		return "First Name: " + getFirstName() + "\n" + "Last Name: " + getLastName() + "\n" + "city :" + getCity()
				+ "\n" + "Address: " + getAddress() + "\n" + "state: " + getState() + "\n" + "Phone Number: "
				+ getPhoneNumber() + "\n" + "Email: " + getEmailId();
	}

}
