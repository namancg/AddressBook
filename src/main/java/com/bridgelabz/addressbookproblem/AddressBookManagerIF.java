package com.bridgelabz.addressbookproblem;

import java.io.IOException;
import java.util.*;

import com.bridgelabz.addressbookproblem.InputOutputEnumService.IOService;

public interface AddressBookManagerIF {
	public void operationSystem();

	public void addAddressBook();

	public void displaySystemContents();

	public void editAddress();

	public void searchByCity();

	public void searchByState();

	public void displayPeopleByRegion(HashMap<String, ArrayList<PersonContact>> listToDisplay);

	public void countPeopleByRegion(HashMap<String, ArrayList<PersonContact>> listToDisplay);

	public void readDataFromJson();

	public void writeDataToJson() throws IOException;

	public List<Contact> readAddressBookData(IOService dbIo);

	public List<Contact> readAddressBookContactData(IOService dbIo);

	public List<Contact> getPersonDetailsBasedOnName(IOService dbIo, String name);

	public int updateContact(String firstName, String lastName, String phoneNumber, String email);

	public Contact getContactData(String name);

	public boolean checkContactsSyncWithDB(String name);

	public void addContactToAddress(String firstName, String lastName, String phoneNumber, String email);

	public List<Contact> getContactsBasedOnStartDateUsingPreparedStatement(IOService ioService, String startDate,
			String endDate);

	public List<Contact> readContactDataDB(IOService dbIo, String city, String state);

	public void addContactToUpdatedDatabse(String firstName, String lastName, String phoneNumber, String email,
			String added);

	public List<Contact> getEmployeeDetailsBasedOnCity(IOService ioService, String city);

	public List<Contact> getEmployeeDetailsBasedOnState(IOService ioService, String state);

	public int readData(IOService type);

}
