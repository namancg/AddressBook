package com.bridgelabz.addressbookproblem;
import java.util.*;
public interface AddressBookDirIF {
	public void operationSystem();
	public void addAddressBook();
	public void displaySystemContents();
	public void editAddress();
	public void searchByCity();
	public void searchByState();
	public void displayPeopleByRegion(HashMap<String, ArrayList<PersonContact>> listToDisplay);
	public void countPeopleByRegion(HashMap<String, ArrayList<PersonContact>> listToDisplay);
}
