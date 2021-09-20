package com.bridgelabz.addressbookproblem;
import java.util.List;
import java.util.Scanner;
import com.bridgelabz.addressbookproblem.AddressBook.IOService;

public interface AddressBookIF {
	public void startOperation();
	public void createContact(Scanner sc);
	void addContact(String firstName, PersonContact person);
	public void displayContents();
	public void editPerson();
	public void deletePerson();
	void writeToAddressBookFile(IOService ioService);
	List<String> readDataFromFile(IOService fileIo);
	
}
