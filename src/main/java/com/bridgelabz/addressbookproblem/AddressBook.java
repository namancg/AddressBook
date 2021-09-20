package com.bridgelabz.addressbookproblem;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;

public class AddressBook implements AddressBookIF{

	public String addressBookname;
	Scanner sc = new Scanner(System.in);
	public Map<String, PersonContact> contactList = new HashMap<String,PersonContact>();
	public static HashMap<String, ArrayList<PersonContact>> personByCity  = new HashMap<String, ArrayList<PersonContact>>();
	public static HashMap<String, ArrayList<PersonContact>>personByState = new HashMap<String, ArrayList<PersonContact>>();
	public enum IOService {
		FILE_IO, CONSOLE_IO
	}
	public String getAddressBookname() {
		return addressBookname;
	}
	public void setAddressBookname(String addressBookname)
	{
		this.addressBookname= addressBookname;
		startOperation();
	}
	@Override
	public void startOperation() {
		boolean changes = true;
		do{
			
			System.out.println("Choose");
			System.out.println("1.Adding details to Address Book \n 2.Edit Existing Details \n 3.Display Address\n 4.Delete PersonContact \n5Sort \n6.Write to address book file \n7.read the data from file \n8.Write data to csv \n9.read data from csv \n10.Exit Address book System");

			switch (sc.nextInt()) {
			case 1:
				createContact(sc);
				break;
			case 2:
				editPerson();
				break;
			case 3:
				displayContents();
				break;
			case 4:
				deletePerson();
				break;
			case 5:
				System.out.println("Enter 1 for NAME \n2 for CITY \n3 for STATE and \n4 for ZIPCODE ");
				int sortChoice=sc.nextInt();
				sortAddressBook(sortChoice);
			case 6:
				writeToAddressBookFile(IOService.CONSOLE_IO);
			case 7:
				readDataFromFile(IOService.FILE_IO);
			case 8:
				try {
					writeDataToCSV();
				} catch (CsvRequiredFieldEmptyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CsvDataTypeMismatchException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			case 9:
				try {
					readDataFromCSV();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			case 10:
				changes = false;
			}

		}while(changes);
	}
	
	@Override
	public void addContact(String firstName, PersonContact person) 
	{
		
		contactList.put(firstName.toLowerCase(), person);

	}
	
	public void createContact(Scanner sc)
	{
		PersonContact person = new PersonContact();
		Address address = new Address();
		
		
		System.out.println("First Name: ");
		String firstName = sc.next();
		contactList.entrySet().stream().forEach(entry -> {
			if(entry.getKey().equals(firstName.toLowerCase())) {
				System.out.println("Contact Already Exists");
				boolean	isPresent = true;
				return;
			}
		});
		
		System.out.println("Last Name: ");
		String lastName = sc.next();
		
		System.out.println("Phone Number: ");
		long phoneNumber = sc.nextLong();
		
		System.out.println("Email: ");
		String email = sc.next();
		
		System.out.println("City: ");
		String city = sc.next();
		
		System.out.println("State: ");
		String state = sc.next();
		
		System.out.println("Zip Code: ");
		int zipCode = sc.nextInt();
		
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setnumber(phoneNumber);
		person.setEmail(email);
		address.setCity(city);
		address.setState(state);
		address.setZip(zipCode);
		person.setAddress(address);
		addPersonToCity(person);
		addPersonToState(person);
		contactList.put(firstName, person);
	}
	
		
	
	public void editPerson() {
		PersonContact person = new PersonContact();

		System.out.println("Enter the first name:");
		String firstName = sc.next();
		
		if(contactList.containsKey(firstName)) {
			person = contactList.get(firstName);
			Address address = person.getAddress();
			System.out.println("Choose the attribute you want to change:");
			System.out.println("1.Last Name\n2.Phone Number\n3.Email\n4.City\n5.State\n6.ZipCode");
			int choice = sc.nextInt();
				switch(choice) {
				case 1: 
					System.out.println("Enter the correct Last Name :");
					String lastName = sc.next();
					person.setLastName(lastName);
					break;
				case 2: 
					System.out.println("Enter the correct Phone Number :");
					long phoneNumber = sc.nextLong();
					person.setnumber(phoneNumber);
					break;
				case 3: 
					System.out.println("Enter the correct Email Address :");
					String email = sc.next();
					person.setEmail(email);
					break;
				case 4:
					System.out.println("Enter the correct City :");
					String city = sc.next();
					address.setCity(city);
					break;
				case 5:
					System.out.println("Enter the correct State :");
					String state = sc.next();
					address.setState(state);
					break;
				case 6:
					System.out.println("Enter the correct ZipCode :");
					int zip = sc.nextInt();
					address.setZip(zip);
					break;
				}
				
			}
		}
	
		
	public void addPersonToCity(PersonContact contact) {
		if (personByCity.containsKey(contact.getAddress().getCity())) {
			personByCity.get(contact.getAddress().getCity()).add(contact);
		}
		else {
			ArrayList<PersonContact> cityList = new ArrayList<PersonContact>();
			cityList.add(contact);
			personByCity.put(contact.getAddress().getCity(), cityList);
		}
	}

	public void addPersonToState(PersonContact contact) {
		if (personByState.containsKey(contact.getAddress().getState())) {			
			personByState.get(contact.getAddress().getState()).add(contact);
		}
		else {
			ArrayList<PersonContact> stateList = new ArrayList<PersonContact>();
			stateList.add(contact);
			personByState.put(contact.getAddress().getState(), stateList);
		}
	}
	
	public void deletePerson() {
		
		System.out.println("Enter the name of the person to be deleted from address book");
		String firstName = sc.next();
		if(contactList.containsKey(firstName)) {
			contactList.remove(firstName);
			System.out.println("Successfully Deleted");
		}
		else {
			System.out.println("Contact Not Found!");
		}
}

	
	public void displayContents() {
		
		for (String contact : contactList.keySet()) {
			PersonContact person = contactList.get(contact);
			System.out.println(person);
		}
		
	}
	public ArrayList<PersonContact> getContact() {
		return new ArrayList<PersonContact>(contactList.values());
	}
	public void sortAddressBook(int sortChoice) {
		List<PersonContact> sortedContactList ;
		switch(sortChoice) 
				{
			
		case 0: sortedContactList = contactList.values().stream()
				.sorted((firstperson, secondperson) -> firstperson.getFirstName().compareTo(secondperson.getFirstName()))
				.collect(Collectors.toList());
				printSortedList(sortedContactList);
				break;
			
		case 1: sortedContactList = contactList.values().stream()
				.sorted((firstperson, secondperson) -> firstperson.getAddress().getCity().compareTo(secondperson.getAddress().getCity()))
				.collect(Collectors.toList());
				printSortedList(sortedContactList);
				break;
			
		case 2: sortedContactList = contactList.values().stream()
				.sorted((firstperson, secondperson) -> firstperson.getAddress().getState().compareTo(secondperson.getAddress().getState()))
				.collect(Collectors.toList());
				printSortedList(sortedContactList);
				break;
			
		case 3: sortedContactList = contactList.values().stream()
				.sorted((firstperson, secondperson) -> Long.valueOf(firstperson.getAddress().getZip()).compareTo(Long.valueOf(secondperson.getAddress().getZip())))
				.collect(Collectors.toList());
				printSortedList(sortedContactList);
				break;
	}
			
}
	private void printSortedList(List<PersonContact> sortedContactList) 
	{
		System.out.println("Sorted Address Book "+this.getAddressBookname()+"");
		Iterator<PersonContact> iterator = sortedContactList.iterator();
		while (iterator.hasNext()) {
		System.out.println(iterator.next());
		System.out.println();
	}
	
	}
	@Override
	public void writeToAddressBookFile(IOService ioService) {
		if(ioService.equals(IOService.CONSOLE_IO))
			displayContents();
		
		else if(ioService.equals(IOService.FILE_IO)) {
			String bookName = this.getAddressBookname();
			String fileName = bookName+".txt";
			new AddressBookFileIO().writeToAddressBookFile(fileName, contactList);
		}
	}
	
	public void printData(IOService fileIo) {
		String bookName = this.getAddressBookname();
		String fileName = bookName+".txt";
		if(fileIo.equals(IOService.FILE_IO)) new AddressBookFileIO().printData(fileName);
	}


	public long countEntries(IOService fileIo) {
		
		String bookName = this.getAddressBookname();
		String fileName = bookName+".txt";
		if(fileIo.equals(IOService.FILE_IO)) 
			return new AddressBookFileIO().countEntries(fileName);
		
		return 0;
	}
	@Override
	public List<String> readDataFromFile(IOService fileIo) {
		
		List<String> employeePayrollFromFile = new ArrayList<String>();
		if(fileIo.equals(IOService.FILE_IO)) {
			System.out.println("Employee Details from payroll-file.txt");
			String bookName = this.getAddressBookname();
			String fileName = bookName+".txt";
			employeePayrollFromFile = new AddressBookFileIO().readDataFromFile(fileName);
			
		}
		return employeePayrollFromFile;
	}
	 public void readDataFromCSV() throws IOException
	 {
	    	String fileName ="Contacts.csv";
	        try (Reader reader = Files.newBufferedReader(Paths.get("./"+fileName));
	             CSVReader csvReader = new CSVReader(reader);){
	        	List<String[]> recordsList= csvReader.readAll();
				try {
					recordsList = csvReader.readAll();
	            for(String[] record : recordsList) {
	                System.out.println("FIRST NAME = " + record[0]);
	                System.out.println("LAST NAME = " + record[1]);
	                System.out.println("PHONE NUMBER = " + record[2]);
	                System.out.println("EMAIL = " + record[3]);
	                System.out.println("STATE = " + record[4]);
	                System.out.println("ZIP CODE = " + record[5]);
	                System.out.println("CITY = " + record[6]);
	                System.out.println("\n \n");
	            }
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CsvException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	    }
	        } catch (CsvException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	 }
	 
	 public void writeDataToCSV() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException 
	 {
			
			String fileName = "Contacts.csv";
	        try (Writer writer = Files.newBufferedWriter(Paths.get("./"+fileName))) {
	            StatefulBeanToCsvBuilder<PersonContact> builder = new StatefulBeanToCsvBuilder<>(writer);
	            StatefulBeanToCsv<PersonContact> beanWriter = builder.build();
	            ArrayList<PersonContact> listOfContacts= contactList.values().stream().collect(Collectors.toCollection(ArrayList::new));
	            beanWriter.write(listOfContacts);
	            writer.close();
	        } 
	        catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}
