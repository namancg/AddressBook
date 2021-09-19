package com.bridgelabz.addressbook;

import java.util.List;

public interface AddressBookIF {
	public void startOperation();
	public void addContact();
	public void displayContents();
	public void editPerson();
	public void deletePerson();
	public void writeToAddressBookFile();
	public List<String> readDataFromFile();
	
}
