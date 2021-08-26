package com.bridgelabz.addressbook;

public class PersonContact {
	private String firstName, lastName, email;
	private long number;
	private Address address;
	
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
		
		return "Person Details:\n First Name is:  "+firstName+" \n Last Name is: "+lastName+"\n Phone Number is: "+number+"\n Email is :"+email+"\n Address is: "+address;
	}

}
