package com.bridgelabz.addressbookproblem;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.bridgelabz.addressbookproblem.AddressBook.IOService;
import com.google.gson.Gson;
public class AddressBookDir {
	Scanner sc = new Scanner(System.in);
	Map<String,AddressBook> addressBookDirectory = new HashMap<String,AddressBook>();
	public Map<String, PersonContact> contactList = new HashMap<String,PersonContact>();
	public AddressBook addressBook;


public void operationSystem() throws IOException {
	
	boolean changes = true;
	do{
		System.out.println("\nWhch operation to perform???");
		System.out.println("1.Add new \n2.Edit Address book \n3.Search person by a region\n4.View people by region\n5. display by region\n6.count people\n7.exit");

		switch (sc.nextInt()) {
		case 1:
			addAddressBook();
			break;
		case 3:
			System.out.println("1.Search By City\n2.Search By State");
			int Choice = sc.nextInt();
			if(Choice==1)
				searchByCity();
			else 
				searchByState();
			break;
		case 2:
			editAddress();
			break;
		case 4:
			System.out.println("1.Display By City\n2.Display By State");
			int Choice1 = sc.nextInt();
			if(Choice1==1)
				displayPeopleByRegion(AddressBook.personByCity);
			else 
				displayPeopleByRegion(AddressBook.personByState);
			break;
		case 5:
			System.out.println("1.Display By City\n2.Display By State");
			int countChoice = sc.nextInt();
			if(countChoice==1)
				countPeopleByARegion(AddressBook.personByCity);
			else 
				countPeopleByARegion(AddressBook.personByState);
			break;
		case 6:
			System.out.println("Enter \n1.Display By City\n2.Display By State");
			int countChoice1 = sc.nextInt();
			if(countChoice1==1)
				countPeopleByRegion(AddressBook.personByCity);
			else 
				countPeopleByRegion(AddressBook.personByState);
			break;
		case 7:
		changes =false;
		}

	}while(changes);
}
private void countPeopleByARegion(HashMap<String, ArrayList<PersonContact>> personByState) {
	ArrayList<PersonContact> list;
	System.out.println("THE NAME OF THE REGION");
	String regionName = sc.next();
	for(String region : personByState.keySet())
	{
		if(regionName .equals(region)) {
		int count = 0;
		list = personByState.get(region);
		for (PersonContact person : list) {
			count++;
		}
		System.out.println("Number of People residing in " + region+" are: "+count);
	}
	}
	System.out.print("NO CONTATCS FROM THE GIVEN REGION");
	
}
public void addAddressBook() {
		System.out.println("Enter the name of the Book ");
		String addressBookName = sc.next();
		if(addressBookDirectory.containsKey(addressBookName)) {
			System.out.println("Book Name Already Exists");
			return;
		}
		AddressBook addressBook = new AddressBook();
		addressBook.setAddressBookname(addressBookName);
		addressBookDirectory.put(addressBookName, addressBook);
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

	System.out.println("Enter the name of City where the Person resides : ");
	String cityName = sc.next();
	System.out.println("enter the name of the Person : ");
	String personName = sc.next();

	for(AddressBook addressBook : addressBookDirectory.values()) {
		ArrayList<PersonContact> contactList = addressBook.getContact();
		contactList.stream()
			.filter(person -> person.getFirstName().equals(personName) && person.getCity().equals(cityName))
			.forEach(person -> System.out.println(person));
		
	}		

}

public void searchByState() {

	System.out.println("Enter the name if state where the person stays : ");
	String stateName = sc.next();
	System.out.println("Enter the name of the Person : ");
	String personName = sc.next();

	for(AddressBook addressBook : addressBookDirectory.values()) {
		ArrayList<PersonContact> contactList = ((AddressBook) addressBook).getContact();
		contactList.stream()
			 .filter(person -> person.getFirstName().equals(personName) && person.getState().equals(stateName))
			.forEach(person -> System.out.println(person));
		
	}


	System.out.println("Contact Does Not Exist !!");

}
public void displayPeopleByRegion(HashMap<String, ArrayList<PersonContact>> listToDisplay) {
	System.out.println("Enter the name of the region :");
	String regionName = sc.next();
	listToDisplay.values().stream()
		.map(region -> region.stream()
		.filter(person -> person.getState().equals(regionName) || person.getCity().equals(regionName)))
		.forEach(person -> person.forEach(personDetails -> System.out.println(personDetails)));

}
public void countPeopleByRegion(HashMap<String, ArrayList<PersonContact>> listToDisplay) {

	System.out.println("Enter the name of the region :");
	String regionName = sc.next();
	long countPeople = listToDisplay.values().stream()
			.map(region -> region.stream()
				.filter(person -> person.getState().equals(regionName) || person.getCity().equals(regionName)))
				.count();
				
	System.out.println("No of People residing in " + regionName+" are: "+countPeople);
	
}
public void readDataFromJson()
{
	System.out.println("{");
	for(AddressBook addressBook : addressBookDirectory.values()) {
		System.out.println(addressBook.getAddressBookname()+": [\n");
		try {
			addressBook.readDataFromJson();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("]\n");
		
	}
	System.out.println("}");
	
}
public void writeDataToJson() throws IOException 
{
	String fileName = "./Contacts.json";
	Path filePath = Paths.get(fileName);
	Gson gson = new Gson();
	String json = gson.toJson(contactList.values());
	FileWriter writer = new FileWriter(String.valueOf(filePath));
	writer.write(json);
	writer.close();

}
public int readData(String addressBookName) {
	List<PersonContact> contacts=AddressBookDBService.getDBInstance().readContacts(addressBookName);
	return contacts.size();
}
public void writeAddressBookDB(PersonContact contact, String addressBookName) {
	AddressBookDBService.getDBInstance().writeAddressBookDB(contact,addressBookName);
	
}


}