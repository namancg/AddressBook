package com.bridgelabz.addressbook;
import java.util.*;


public class AddressBook implements AddressBookIF{

	public String addressBookname;
	Scanner sc = new Scanner(System.in);
	Map<String, PersonContact> contactList = new HashMap<String,PersonContact>();
	public String getAddressBookname() {
		return addressBookname;
	}
	public void setAddressBookname(String addressBookname)
	{
		this.addressBookname= addressBookname;
	}
	@Override
	public void startOperation() {
		boolean changes = true;
		do{
			
			System.out.println("Choose");
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
				deletePerson();
				break;
			case 5:
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
		contactList.put(firstName, person);
	}
	
		
	
	public void editPerson() {
		PersonContact person = new PersonContact();

		System.out.println("Enter the first name:");
		String firstName = sc.next();
		
		if(contactList.containsKey(firstName)) {
			person = contactList.get(firstName);
			Address address = person.getAddress();
			System.out.println("Choose the attribute you want to change:");
			System.out.println("1.Last Name\n2.Phone Number\n3.Email\n4.City\n5.State\n6.ZipCode");
			int choice = sc.nextInt();
				switch(choice) {
				case 1: 
					System.out.println("Enter the correct Last Name :");
					String lastName = sc.next();
					person.setLastName(lastName);
					break;
				case 2: 
					System.out.println("Enter the correct Phone Number :");
					long phoneNumber = sc.nextLong();
					person.setnumber(phoneNumber);
					break;
				case 3: 
					System.out.println("Enter the correct Email Address :");
					String email = sc.next();
					person.setEmail(email);
					break;
				case 4:
					System.out.println("Enter the correct City :");
					String city = sc.next();
					address.setCity(city);
					break;
				case 5:
					System.out.println("Enter the correct State :");
					String state = sc.next();
					address.setState(state);
					break;
				case 6:
					System.out.println("Enter the correct ZipCode :");
					int zip = sc.nextInt();
					address.setZip(zip);
					break;
				}
				
			}
		}
	
		
	
	
	public void deletePerson() {
		
		System.out.println("Enter the name of the person to be deleted from address book");
		String firstName = sc.next();
		if(contactList.containsKey(firstName)) {
			contactList.remove(firstName);
			System.out.println("Successfully Deleted");
		}
		else {
			System.out.println("Contact Not Found!");
		}
}

	
	public void displayContents() {
		
		for (String contact : contactList.keySet()) {
			PersonContact person = contactList.get(contact);
			System.out.println(person);
		}
		
	}
	public ArrayList<PersonContact> getContact() {
		return new ArrayList<PersonContact>(contactList.values());
	}
	
	
}
