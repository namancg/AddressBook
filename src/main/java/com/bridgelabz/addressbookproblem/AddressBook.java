package com.bridgelabz.addressbookproblem;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;
import com.bridgelabz.addressbookproblem.InputOutputEnumService.IOService;

public class AddressBook implements AddressBookIF {

	public String addressBookname;
	Scanner sc = new Scanner(System.in);
	public Map<String, PersonContact> contactList = new HashMap<String, PersonContact>();
	public static HashMap<String, ArrayList<PersonContact>> personByCity = new HashMap<String, ArrayList<PersonContact>>();
	public static HashMap<String, ArrayList<PersonContact>> personByState = new HashMap<String, ArrayList<PersonContact>>();

	public String getAddressBookname() {
		return addressBookname;
	}

	public void setAddressBookname(String addressBookname) {
		this.addressBookname = addressBookname;
		startOperation();
	}

	@Override
	public void startOperation() {
		boolean changes = true;
		do {

			System.out.println("Choose");
			System.out.println(
					"1.Adding details to Address Book \n 2.Edit Existing Details \n 3.Display Address\n 4.Delete PersonContact \n5Sort \n6.Write to address book file \n7.read the data from file \n8.Write data to csv \n9.read data from csv \n10.Write to json\n11.Read to json\nExit");

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
				int sortChoice = sc.nextInt();
				sortAddressBook(sortChoice);
				break;
			case 6:
				writeToAddressBookFile(IOService.CONSOLE_IO);
				break;
			case 7:
				readDataFromFile(IOService.FILE_IO);
				break;
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
				break;
			case 9:
				try {
					readDataFromCSV();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			case 10:
				try {
					writeDataToJson();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}
				break;
			case 11:
				try {
					readDataFromJson();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 12:
				changes = false;
			}

		} while (changes);
	}

	@Override
	public void addContact(String firstName, PersonContact person) {

		contactList.put(firstName.toLowerCase(), person);

	}

	public void createContact(Scanner sc) {
		PersonContact person = new PersonContact();
		System.out.println("First Name: ");
		String firstName = sc.next();
		contactList.entrySet().stream().forEach(entry -> {
			if (entry.getKey().equals(firstName.toLowerCase())) {
				System.out.println("Contact Already Exists");
				boolean isPresent = true;
				return;
			}
		});

		System.out.println("Last Name: ");
		String lastName = sc.next();

		System.out.println("Phone Number: ");
		String phoneNumber = sc.next();

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
		person.setPhoneNumber(phoneNumber);
		person.setEmail(email);
		person.setCity(city);
		person.setState(state);
		person.setZipCode(zipCode);
		addPersonToCity(person);
		addPersonToState(person);
		contactList.put(firstName, person);
	}

	public void editPerson() {
		PersonContact person = new PersonContact();

		System.out.println("Enter the first name:");
		String firstName = sc.next();

		if (contactList.containsKey(firstName)) {
			person = contactList.get(firstName);
			String address = person.getAddress();
			System.out.println("Choose the attribute you want to change:");
			System.out.println("1.Last Name\n2.Phone Number\n3.Email\n4.City\n5.State\n6.ZipCode");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter the correct Last Name :");
				String lastName = sc.next();
				person.setLastName(lastName);
				break;
			case 2:
				System.out.println("Enter the correct Phone Number :");
				String phoneNumber = sc.next();
				person.setPhoneNumber(phoneNumber);
				break;
			case 3:
				System.out.println("Enter the correct Email Address :");
				String email = sc.next();
				person.setEmail(email);
				break;
			case 4:
				System.out.println("Enter the correct City :");
				String city = sc.next();
				person.setCity(city);
				break;
			case 5:
				System.out.println("Enter the correct State :");
				String state = sc.next();
				person.setState(state);
				break;
			case 6:
				System.out.println("Enter the correct ZipCode :");
				int zip = sc.nextInt();
				person.setZipCode(zip);
				break;
			}

		}
	}

	public void addPersonToCity(PersonContact contact) {
		if (personByCity.containsKey(contact.getCity())) {
			personByCity.get(contact.getCity()).add(contact);
		} else {
			ArrayList<PersonContact> cityList = new ArrayList<PersonContact>();
			cityList.add(contact);
			personByCity.put(contact.getCity(), cityList);
		}
	}

	public void addPersonToState(PersonContact contact) {
		if (personByState.containsKey(contact.getState())) {
			personByState.get(contact.getState()).add(contact);
		} else {
			ArrayList<PersonContact> stateList = new ArrayList<PersonContact>();
			stateList.add(contact);
			personByState.put(contact.getState(), stateList);
		}
	}

	public void deletePerson() {

		System.out.println("Enter the name of the person to be deleted from address book");
		String firstName = sc.next();
		if (contactList.containsKey(firstName)) {
			contactList.remove(firstName);
			System.out.println("Successfully Deleted");
		} else {
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
		List<PersonContact> sortedContactList;
		switch (sortChoice) {

		case 0:
			sortedContactList = contactList.values().stream().sorted(
					(firstperson, secondperson) -> firstperson.getFirstName().compareTo(secondperson.getFirstName()))
					.collect(Collectors.toList());
			printSortedList(sortedContactList);
			break;

		case 1:
			sortedContactList = contactList.values().stream()
					.sorted((firstperson, secondperson) -> firstperson.getCity().compareTo(secondperson.getCity()))
					.collect(Collectors.toList());
			printSortedList(sortedContactList);
			break;

		case 2:
			sortedContactList = contactList.values().stream()
					.sorted((firstperson, secondperson) -> firstperson.getState().compareTo(secondperson.getState()))
					.collect(Collectors.toList());
			printSortedList(sortedContactList);
			break;

		case 3:
			sortedContactList = contactList.values().stream().sorted((firstperson, secondperson) -> Long
					.valueOf(firstperson.getZip()).compareTo(Long.valueOf(secondperson.getZip())))
					.collect(Collectors.toList());
			printSortedList(sortedContactList);
			break;
		}

	}

	private void printSortedList(List<PersonContact> sortedContactList) {
		System.out.println("Sorted Address Book " + this.getAddressBookname() + "");
		Iterator<PersonContact> iterator = sortedContactList.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
			System.out.println();
		}

	}

	public void writeToAddressBookFile(IOService ioService) {
		if (ioService.equals(IOService.CONSOLE_IO))
			displayContents();

		else if (ioService.equals(IOService.FILE_IO)) {
			String bookName = this.getAddressBookname();
			String fileName = bookName + ".txt";
			new AddressBookFileIO().writeToAddressBookFile(fileName, contactList);
		}
	}

	public void printData(IOService fileIo) {
		String bookName = this.getAddressBookname();
		String fileName = bookName + ".txt";
		if (fileIo.equals(IOService.FILE_IO))
			new AddressBookFileIO().printData(fileName);
	}

	public long countEntries(IOService fileIo) {

		String bookName = this.getAddressBookname();
		String fileName = bookName + ".txt";
		if (fileIo.equals(IOService.FILE_IO))
			return new AddressBookFileIO().countEntries(fileName);

		return 0;
	}

	public List<String> readDataFromFile(IOService fileIo) {

		List<String> employeePayrollFromFile = new ArrayList<String>();
		if (fileIo.equals(IOService.FILE_IO)) {
			System.out.println("Employee Details from payroll-file.txt");
			String bookName = this.getAddressBookname();
			String fileName = bookName + ".txt";
			employeePayrollFromFile = new AddressBookFileIO().readDataFromFile(fileName);

		}
		return employeePayrollFromFile;
	}

	public void readDataFromCSV() throws IOException {
		String fileName = "Contacts.csv";
		try (Reader reader = Files.newBufferedReader(Paths.get("./" + fileName));
				CSVReader csvReader = new CSVReader(reader);) {
			List<String[]> recordsList = csvReader.readAll();
			try {
				recordsList = csvReader.readAll();
				for (String[] record : recordsList) {
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

	public void writeDataToCSV() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {

		String fileName = "Contacts.csv";
		try (Writer writer = Files.newBufferedWriter(Paths.get("./" + fileName))) {
			StatefulBeanToCsvBuilder<PersonContact> builder = new StatefulBeanToCsvBuilder<>(writer);
			StatefulBeanToCsv<PersonContact> beanWriter = builder.build();
			ArrayList<PersonContact> listOfContacts = contactList.values().stream()
					.collect(Collectors.toCollection(ArrayList::new));
			beanWriter.write(listOfContacts);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void writeDataToJson() throws IOException {

		String fileName = "./Contacts.json";
		Path filePath = Paths.get(fileName);
		Gson gson = new Gson();
		String json = gson.toJson(contactList.values());
		FileWriter writer = new FileWriter(String.valueOf(filePath));
		writer.write(json);
		writer.close();

	}

	@Override
	public void readDataFromJson() throws IOException {

		ArrayList<PersonContact> contactList;
		String fileName = "./Contacts.json";
		Path filePath = Paths.get(fileName);

		try (Reader reader = Files.newBufferedReader(filePath)) {
			Gson gson = new Gson();
			contactList = new ArrayList<>(Arrays.asList(gson.fromJson(reader, PersonContact[].class)));
			for (PersonContact contact : contactList) {
				System.out.println("{");
				System.out.println("Firstname : " + contact.getFirstName());
				System.out.println("Lastname : " + contact.getLastName());
				System.out.println("City : " + contact.getCity());
				System.out.println("State : " + contact.getState());
				System.out.println("Zip Code : " + contact.getZip());
				System.out.println("Phone number : " + contact.getPhoneNumber());
				System.out.println("Email : " + contact.getEmail());
				System.out.println("}\n");
			}
		}
	}
}
