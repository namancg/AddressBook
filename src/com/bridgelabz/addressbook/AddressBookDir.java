package com.bridgelabz.addressbook;
import java.util.*;
public class AddressBookDir {
	private static final int totalAddressBooks = 3;
	Scanner sc = new Scanner(System.in);
	AddressBook[] addressBookDir = new AddressBook[totalAddressBooks];
	int numOfAddressBooks = 0;


public void operationSystem() {
	
	boolean changes = true;
	do{
		System.out.println("\nWhch operation to perform???");
		System.out.println("1.Add new \n2.Display Address book \n3.EditAddress Book\n4.Exit");

		switch (sc.nextInt()) {
		case 1:
			addAddressBook();
			break;
		case 2:
			displaySystemContents();
			break;
		case 3:
			editAddress();
			break;
		case 4:
			changes = false;
		}

	}while(changes);
}
public void addAddressBook() {
	int numOfAddressBooks = 0;
	System.out.println("Enter number of Books ");
	int extraInput= sc.nextInt();
	int Entries = extraInput+numOfAddressBooks;
	String addressBookName = "";
	
	if(numOfAddressBooks == 0) {
		System.out.println("Enter the name of the Book ");
		addressBookName = sc.next();
		AddressBook newAddressBook = new AddressBook();
		newAddressBook.setAddressBookname(addressBookName);
		addressBookDir[numOfAddressBooks] = newAddressBook;
		numOfAddressBooks++;
	}
	
	if(!(Entries > totalAddressBooks))
	 {
		
		boolean bookExists = false;
		for(int i=0; i < numOfAddressBooks ; i++) {
			
			System.out.println("Enter the name of the Book");
			addressBookName = sc.next();
			AddressBook addressBook = addressBookDir[i];
			
			if(addressBookName.equals(addressBook.getAddressBookname())) {
				bookExists = true;
			}
			
		}
		if(bookExists) {
			System.out.println("Change the name");
			return;
		}
		else {
			AddressBook newAddressBook = new AddressBook();
			newAddressBook.setAddressBookname(addressBookName);
			addressBookDir[numOfAddressBooks] = newAddressBook;
			numOfAddressBooks++;
		}
	}
	else {
				System.out.println("No space");
				return;
	}
	
}
public void editAddress() {
	System.out.println("Name of the Address Book ");
	String addressBookName = sc.next();
			
	for(int i = 0; i <totalAddressBooks; i++) {
		
		AddressBook addressBook = addressBookDir[i];
		
		if(addressBookName.equals(addressBook.getAddressBookname())) {
			addressBook.startOperation();
		}
	}
}

public void displaySystemContents() {
	
	System.out.println("Contents are");
	for(int i=0; i < totalAddressBooks ; i++) {
		System.out.println(addressBookDir[i]);
		
	}
}
}