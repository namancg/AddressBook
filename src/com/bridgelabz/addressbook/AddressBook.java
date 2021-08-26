package com.bridgelabz.addressbook;

import java.util.ArrayList;
import java.util.Iterator;


public class AddressBook implements AddressBookIF{
	
	ArrayList <PersonContact> list = new ArrayList<PersonContact>();
	public void addContact(PersonContact person) {
		list.add(person);
		
	}
	
	@Override
	public void displayContents() {
		
		Iterator<PersonContact> iterator = list.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
	}