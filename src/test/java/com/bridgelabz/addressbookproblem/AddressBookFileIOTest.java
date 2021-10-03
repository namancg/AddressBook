package com.bridgelabz.addressbookproblem;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class AddressBookFileIOTest {
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
		Integer zipCode = 560004;
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
	public void givenAContact_WhenWrittenToFile_ShouldMatchNumberOfEntries() {
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
}
