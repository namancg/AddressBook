package com.bridgelabz.addressbook;
import java.util.*;
public class AddressBookDir {
	private static final int totalAddressBooks = 3;
	Scanner sc = new Scanner(System.in);
	Map<String,AddressBook> addressBookDirectory = new HashMap<String,AddressBook>();
	public AddressBook addressBook;


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
		System.out.println("Enter the name of the Book ");
		String addressBookName = sc.next();
		addressBookName = sc.next();
		if(addressBookDirectory.containsKey(addressBookName)) {
			System.out.println("Book Name Is Present");
			return;
		}
		AddressBook newAddressBook = new AddressBook();
		newAddressBook.setAddressBookname(addressBookName);
		addressBookDirectory.put(addressBookName, newAddressBook);
	}
public void editAddress() {
	System.out.println("Name of the Address Book ");
	String addressBookName = sc.next();
			
	if(addressBookDirectory.containsKey(addressBookName)) {
		addressBook = addressBookDirectory.get(addressBookName);
		addressBook.startOperation();
	}
	else {
		System.out.println("Book Does Not Exist");
	}
}

public void displaySystemContents() {
	
	System.out.println("Contents are");
	for (String eachBookName : addressBookDirectory.keySet()) {
		
		System.out.println(eachBookName);
}
}
}