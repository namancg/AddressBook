package com.bridgelabz.addressbookproblem;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import com.bridgelabz.addressbookproblem.InputOutputEnumService.IOService;

public class AddressBookDatabaseIOTest {

	static AddressBook addressBook = new AddressBook();

	@Test
	public void givenState_WhenMatches_ShouldReturnDetails() {

		String state = "UTTRAKHAND";
		AddressBookManager addressBook = new AddressBookManager();
		List<Contact> contactList = addressBook.getEmployeeDetailsBasedOnState(IOService.DB_IO, state);
		Assert.assertEquals(2, contactList.size());
	}

	@Test
	public void givenStartDateRange_WhenMatchesUsingPreparedStatement_ShouldReturnDetails() {

		String startDate = "2018-01-01";
		String endDate = "2021-01-01";
		AddressBookManager addressBook = new AddressBookManager();
		List<Contact> contactData = addressBook.getContactsBasedOnStartDateUsingPreparedStatement(IOService.DB_IO,
				startDate, endDate);
		Assert.assertEquals(4, contactData.size());
	}

	@Test
	public void givenNewName_WhenAdded_ShouldSyncWithUpdatedDB() {
		AddressBookManager addressBook = new AddressBookManager();
		addressBook.addContactToUpdatedDatabse("LEWIS", "HAMILTON", "9988998877", "lewis@gmail.com", "2021-02-12");
		addressBook.readData(IOService.DB_IO);
		boolean result = addressBook.checkContactsSyncWithDB("LEWIS");
		Assert.assertEquals(result, result);

	}
}
