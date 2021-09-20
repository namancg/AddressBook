package com.bridgelabz.addressbookproblem;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import com.bridgelabz.addressbookproblem.AddressBook.IOService;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;

public interface AddressBookIF {
	public void startOperation();
	public void createContact(Scanner sc);
	void addContact(String firstName, PersonContact person);
	public void displayContents();
	public void editPerson();
	public void deletePerson();
	void writeToAddressBookFile(IOService ioService);
	List<String> readDataFromFile(IOService fileIo);
	public void writeDataToCSV() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException;
	public void readDataFromCSV() throws IOException, CsvValidationException;
	
}
