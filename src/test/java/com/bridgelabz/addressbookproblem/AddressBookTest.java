package com.bridgelabz.addressbookproblem;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.bridgelabz.addressbookproblem.AddressBook.IOService;

public class AddressBookTest {

static AddressBook addressBook = new AddressBook();
	@Test
	public void givenDetails_ShouldAddToContactList() {
		
		PersonContact person = new PersonContact();
		Address address = new Address();
		
		String firstName = "NAMAN";
		String lastName = "CHANDRA";
		String email = "ncg@gmail.com";
		String phoneNumber = "953816996";
		String city = "Bangalore";
		String state = "Karnataka";
		long zipCode = 560004;
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setEmail(email);
		person.setPhoneNumber(phoneNumber);
		address.setCity(city);
		address.setState(state);
		address.setZip(zipCode);
		addressBook.addContact(firstName, person);
		
	}
	@Test
	public void givenAContact_WhenWrittenToFile_ShouldMatchNumberOfEntries()
	{
		AddressBookFileIO addressFileIO = new AddressBookFileIO();
		addressFileIO.writeToAddressBookFile("BookNo1.txt", addressBook.contactList);
		addressFileIO.printData("BookNo1.txt");
		Assert.assertEquals(7, addressFileIO.countEntries("BookNo1.txt"));
	}
	
	@Test
	public void givenFile_WhenRead_ShouldReturnNumberOfEntries() {
		
		AddressBookFileIO addressFileIO = new AddressBookFileIO();
		List<String> entries = addressFileIO.readDataFromFile("BookNo1.txt");
		long countEntries = entries.size();
		Assert.assertEquals(7, countEntries);
	}
	public void givenAdressBookDB_WhenRetrived_ShouldatchAddressBookCount() 
	{
		AddressBookDir addressBook = new AddressBookDir();
		long count = addressBook.readData(IOService.DB_IO);
		Assert.assertEquals(19, count);
	}
	@Test
	public void givenCity_WhenMatches_ShouldReturnDetails() {
		
		AddressBookDir addressBook= new AddressBookDir();
		List<Contact> contactList = addressBook.getEmployeeDetailsBasedOnCity(IOService.DB_IO,"MUMBAI");
		Assert.assertEquals(1, contactList.size());
	}
	@Test
	public void givenListOfContact_WhenInserted_ShouldMatchContactEntries() 
	{
		AddressBookDir addressBook = new AddressBookDir();
		addressBook.addContactToAddress("MAX","V","9535082363","max@gmail.com");
		boolean result = addressBook.checkContactsSyncWithDB("MAX");
		Assert.assertEquals(result,result);
	}
	@Test
	public void givenState_WhenMatches_ShouldReturnDetails() {
		
		//String state = "MAHARASHTRA";
		AddressBookDir addressBook= new AddressBookDir();
		List<Contact> contactList = addressBook.getEmployeeDetailsBasedOnState(IOService.DB_IO,"MAHARASHTRA");
		Assert.assertEquals(2, contactList.size());
	}
	@Test
	public void givenStartDateRange_WhenMatchesUsingPreparedStatement_ShouldReturnDetails() {
		
		String startDate = "2018-01-01";
		String endDate = "2021-01-01";
		AddressBookDir addressBook = new AddressBookDir();
		List<Contact> contactData = addressBook.getContactsBasedOnStartDateUsingPreparedStatement(IOService.DB_IO, startDate, endDate);
		Assert.assertEquals(4, contactData.size());
	}
	@Test
	public void givenNewName_WhenAdded_ShouldSyncWithUpdatedDB() {
		AddressBookDir addressBook = new AddressBookDir();
		addressBook.addContactToUpdatedDatabse("LEWIS", "HAMILTON", "9988998877", "lewis@gmail.com", "2021-02-12");
		addressBook.readData(IOService.DB_IO);
		boolean result = addressBook.checkContactsSyncWithDB("LEWIS");
		Assert.assertEquals(result,result);
		
	}
}
