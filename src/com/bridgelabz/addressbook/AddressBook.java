package com.bridgelabz.addressbook;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
public class AddressBook{

Scanner sc = new Scanner(System.in);
ArrayList <PersonContact> contactList = new ArrayList<PersonContact>();

public void startOperation() {
	boolean changes = true;
	do{

		System.out.println("Choose the operation you want to perform");
		System.out.println("1.Adding details to Address Book \n 2.Edit Existing Details \n 3.Display Address\n 4.Delete PersonContact \n5.Exit Address book System");

		switch (sc.nextInt()) {
		case 1:
			addContact();
			break;
		case 2:
			editPerson();
			break;
		case 3:
			displayContents();
			break;
		case 4:
			changes = false;
		}

	}while(changes);
}

public void addContact() {
	
	PersonContact person = new PersonContact();
	Address address = new Address();
	
	
	System.out.println("First Name: ");
	String firstName = sc.next();
	
	System.out.println("Last Name: ");
	String lastName = sc.next();
	
	System.out.println("Phone Number: ");
	long phoneNumber = sc.nextLong();
	
	System.out.println("Email: ");
	String email = sc.next();
	
	System.out.println("City: ");
	String city = sc.next();
	
	System.out.println("State: ");
	String state = sc.next();
	
	System.out.println("Zip Code: ");
	int zipCode = sc.nextInt();
	
	person.setFirstName(firstName);
	person.setLastName(lastName);
	person.setnumber(phoneNumber);
	person.setEmail(email);
	address.setCity(city);
	address.setState(state);
	address.setZip(zipCode);
	person.setAddress(address);
	contactList.add(person);
	
}

public void editPerson() {
	
	System.out.println("Enter the first name:");
	String firstName = sc.next();
	Iterator<PersonContact> iterator = contactList.listIterator();
	
	while(iterator.hasNext()) {
		
		PersonContact person = iterator.next();
		
		if(firstName.equals(person.getFirstName()) ) {
			
			Address address = person.getAddress();
			System.out.println("\nChoose the attribute you want to change:");
			System.out.println("1.Last Name\n2.Phone Number\n3.Email\n4.City\n5.State\n6.ZipCode");
			int choice = sc.nextInt();
			
			switch(choice) {
			case 1: 
				System.out.println("Enter the correct Last Name :");
				String lastName = sc.next();
				person.setLastName(lastName);
				break;
			case 2: 
				System.out.println("Enter the proper Phone Number :");
				long phoneNumber = sc.nextLong();
				person.setnumber(phoneNumber);
				break;
			case 3: 
				System.out.println("Enter the proper Email Address :");
				String email = sc.next();
				person.setEmail(email);
				break;
			case 4:
				System.out.println("Enter the proper City :");
				String city = sc.next();
				address.setCity(city);
				break;
			case 5:
				System.out.println("Enter the proper State :");
				String state = sc.next();
				address.setState(state);
				break;
			case 6:
				System.out.println("Enter the proper ZipCode :");
				int zip = sc.nextInt();
				address.setZip(zip);
				break;
			}
			
		}
	}
	
}


public void displayContents() {
	
	Iterator <PersonContact> iterator = contactList.iterator();
	while(iterator.hasNext()) {
		System.out.println(iterator.next());
	}
	
}

	}