package com.bridgelabz.addressbook;
import java.util.*;
public class AddressBookMain {

	public static void main(String[] args) {
		PersonContact person = new PersonContact();
		Address address = new Address();
		Scanner sc = new Scanner(System.in);
		
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
		
		sc.close();
		
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setnumber(phoneNumber);
		person.setEmail(email);
		address.setCity(city);
		address.setState(state);
		person.setAddress(address);
		
		AddressBook addressBook = new AddressBook();
		addressBook.addContact(person);

	}

}
