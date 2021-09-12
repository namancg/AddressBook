package com.bridgelabz.addressbook;
import java.util.*;
public class AddressBookDir {
	Scanner sc = new Scanner(System.in);
	Map<String,AddressBook> addressBookDirectory = new HashMap<String,AddressBook>();
	public AddressBook addressBook;


public void operationSystem() {
	
	boolean changes = true;
	do{
		System.out.println("\nWhch operation to perform???");
		System.out.println("1.Add new \n2.Search by City \n3.EditAddress Book\n4.Edit address\n5. display by region\n6.count people\n7.display\nexit");

		switch (sc.nextInt()) {
		case 1:
			addAddressBook();
			break;
		case 2:
			searchByCity();
			break;
		case 3:
			searchByState();
			break;
		case 4:
			editAddress();
			break;
		case 5:
			displayPeopleByRegion(AddressBook.personByCity);
			break;
		case 6:
			System.out.println("Enter \n1.Display By City\n2.Display By State");
			int countChoice = sc.nextInt();
			if(countChoice==1)
				countPeopleByARegion(AddressBook.personByCity);
			else 
				countPeopleByARegion(AddressBook.personByState);
			break;
		case 7:
			displaySystemContents();
		case 8:
			changes =false;
		}

	}while(changes);
}
private void countPeopleByARegion(HashMap<String, ArrayList<PersonContact>> personByState) {
	ArrayList<PersonContact> list;
	for (String region : personByState.keySet()) {
		int count = 0;
		list = personByState.get(region);
		for (PersonContact person : list) {
			count++;
		}
		System.out.println("Number of People residing in " + region+" are: "+count);
	}
	
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
public void searchByCity() {

	System.out.println("Enter the name of the City where the Person resides : ");
	String cityName = sc.next();
	System.out.println("enter the name of the Person : ");
	String personName = sc.next();

	for(AddressBook addressBook : addressBookDirectory.values()) {
		for(PersonContact person : addressBook.getContact()) {
			if(person.getFirstName().equals(personName) && person.getAddress().getCity().equals(cityName)) {
				System.out.println(personName+" Found in Address Book : "+addressBook.getAddressBookname()+" !");
				System.out.println(person);
				return;
			}
		}
	}
	System.out.println("Contact Does Not Exist !!");

}

public void searchByState() {

	System.out.println("Enter the name of the State where the Person resides : ");
	String StateName = sc.next();
	System.out.println("Enter the name of the Person : ");
	String personName = sc.next();

	for(AddressBook addressBook : addressBookDirectory.values()) {
		for(PersonContact person : addressBook.getContact()) {
			if(person.getFirstName().equals(personName) && person.getAddress().getState().equals(StateName)) {
				System.out.println(personName+" Found in Book : "+addressBook.getAddressBookname()+" !");
				System.out.println(person);
				return;
			}
		}
	}
	System.out.println("Contact Does Not Exist !!");

}
public void displayPeopleByRegion(HashMap<String, ArrayList<PersonContact>> listToDisplay) {
	ArrayList<PersonContact> list;
	for (String name : listToDisplay.keySet()) {
		System.out.println("People residing in: " + name);
		list = listToDisplay.get(name);
		for (PersonContact contact : list) {
			System.out.println(contact);
		}
	}

}
}