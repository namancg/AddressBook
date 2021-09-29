package com.bridgelabz.addressbookproblem;

import java.sql.Date;
import java.time.LocalDate;
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
		long phoneNumber = 953816996;
		String city = "Bangalore";
		String state = "Karnataka";
		long zipCode = 560004;
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setEmail(email);
		person.setnumber(phoneNumber);
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
		Assert.assertEquals(6, addressFileIO.countEntries("BookNo1.txt"));
		
	}
	
	@Test
	public void givenFile_WhenRead_ShouldReturnNumberOfEntries() {
		
		AddressBookFileIO addressFileIO = new AddressBookFileIO();
		List<String> entries = addressFileIO.readDataFromFile("BookNo1.txt");
		long countEntries = entries.size();
		Assert.assertEquals(6, countEntries);
	}
	@Test
	public void givenAddressBookName_CheckIfAllContactsAreFetched(){
		AddressBookDir addressBook=new AddressBookDir();
		int AddressBookSize=addressBook.readData("ADDRESS_BOOK_1");
		Assert.assertEquals(4,	AddressBookSize);
	}
	@Test
	public void insertContactToGivenAddressBook_checkIfInserted() {
		AddressBookDir addressBook=new AddressBookDir();
		int AddressBookSize=addressBook.readData("ADDRESS_BOOK_2");
		Assert.assertEquals(3,	AddressBookSize);
		System.out.println(AddressBookSize);
		PersonContact contact= new PersonContact(999, "LEWIS", "HAMILTON", "CHARLESTOWN", "ENGLAND", "UNITED KINGDOM",0101, 99292939,"LEWIS@gmail.com", 99);
		addressBook.writeAddressBookDB(contact,"ADDRESS_BOOK_2");
		int updatedSize=addressBook.readData("ADDRESS_BOOK_2");
		System.out.println(updatedSize);
		Assert.assertEquals(AddressBookSize,updatedSize);
		
	}
	@Test
	public void givenDateRange_WhenCorrect_RetrieveAllContactsAdded() {
		AddressBookDir addressBookImpl = new AddressBookDir();
		LocalDate startDate = LocalDate.of(2020, 4, 19);
		LocalDate endDate = LocalDate.of(2020, 6, 19);
		List<PersonContact> contacts = addressBookImpl.readConatctsAddedInRange(Date.valueOf(startDate), Date.valueOf(endDate));
		System.out.println(contacts.size());
	}
	
	
}
