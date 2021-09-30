package com.bridgelabz.addressbookproblem;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDBService {
	private static AddressBookDBService addressBookDBService;
	private PreparedStatement addressDataStatement;
	private static List<PersonContact> addressList;

	AddressBookDBService() {

	}

	public List<PersonContact> readData() {
		String sql = "SELECT * FROM contacts";
		List<PersonContact> contactList = new ArrayList<>();
		try {
			Connection connection = this.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				PersonContact person = new PersonContact();
				person.setFirstName(result.getString("firstName"));
				person.setLastName(result.getString("lastName"));
				person.setPhoneNumber(result.getString("phoneNumber"));
				person.setEmail(result.getString("email"));
				contactList.add(person);
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contactList;
	}

	public static AddressBookDBService getDBInstance() {
		addressBookDBService = new AddressBookDBService();
		return addressBookDBService;
	}

	public Connection getConnection() throws SQLException {
		String jdbcURL = "jdbc:mysql://localhost:3306/addressBook_service?userSSL=false";
		String userName = "root";
		String password = "naman1999";
		Connection connection;
		System.out.println("Connecting to database:" + jdbcURL);
		connection = (Connection) DriverManager.getConnection(jdbcURL, userName, password);
		System.out.println("Connection is successful" + connection);
		return connection;

	}
	public List<Contact> readContactData() {
		String sql = "SELECT * FROM contacts";
		List<Contact> contactList = new ArrayList<>();
		try {
			Connection connection = this.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				Contact person  = new Contact();
				person.setFirstName(result.getString("firstName"));
				person.setLastName(result.getString("lastName"));
				person.setPhoneNumber(result.getString("phoneNumber"));
				person.setEmail(result.getString("email"));
				contactList.add(person);
			}
			connection.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return contactList;
	}

	public Contact addContactToAddress(String firstName, String lastName, String phoneNumber, String email) {
		int contactID = -1;
		Contact contactData = null;
		String sql = String.format(
				"INSERT INTO contacts(firstname,lastname,phoneNumber,email) VALUES('%s','%s','%s','%s')", firstName,
				lastName, phoneNumber, email);
		try {
			Connection connection = this.getConnection();
			Statement statement = connection.createStatement();
			int result = statement.executeUpdate(sql, statement.RETURN_GENERATED_KEYS);
			if (result == 1) {
				ResultSet resultSet = statement.getGeneratedKeys();
				if (resultSet.next())
					contactID = resultSet.getInt(1);
			}
			connection.close();
			contactData = new Contact(firstName, lastName, phoneNumber, email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(contactData);
		return contactData;
	}

	public List<Contact> readContactDetails(String firstName, String lastName, String phoneNumber, String email) {

		String sqlStatement = "SELECT * FROM contacts JOIN address ON contacts.address_id = address.address_id;";
		List<Contact> contactsList = new ArrayList<>();

		try (Connection connection = getConnection();) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlStatement);	
			contactsList = getContactList(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contactsList;
	}

	public List<Contact> readContactAddressData(String city, String state) {
		String sqlStatement = String.format(
				"SELECT count(c.contact_id) from contacts c , address a WHERE c.contact_id =a.contact_id  and a.city = '%s' and a.state ='%s'",
				city, state);
		List<Contact> contactList = new ArrayList<>();

		try (Connection connection = getConnection();) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlStatement);
			contactList = this.getContactList(resultSet);
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return contactList;
	}

	public List<Contact> getContactList(ResultSet resultSet) {
		List<Contact> contactList = new ArrayList<>();
		try {
			while (resultSet.next()) {
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String phoneNumber = resultSet.getString("phoneNumber");
				String email = resultSet.getString("email");
				contactList.add(new Contact(firstName, lastName, phoneNumber, email));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contactList;
	}
	public List<Contact> getContactData(String name) {
		List<Contact> contactList = null;
		if(this.addressDataStatement == null) {
			this.prepareStatementForContactData();
		}
		try {
			addressDataStatement.setString(1, name);
			ResultSet resultSet = addressDataStatement.executeQuery();
			contactList = this.getAddressContactData(resultSet);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return contactList;
	}
	public List<Contact> getAddressContactData(ResultSet resultSet) {
		List<Contact> contactList = new ArrayList<>();
		try {
			while(resultSet.next()) {
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String phoneNumber = resultSet.getString("phoneNumber");
				String email = resultSet.getString("email");
				contactList.add(new Contact(firstName, lastName, phoneNumber,email));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return contactList;
		
	}
	

	public Contact addNewContactToContacts(String firstName, String lastName, String phoneNumber, String email,
			String added) {

		Connection connection = null;
		Contact contactPerson = null;

		try {
			connection = this.getConnection();
			connection.setAutoCommit(false);
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		try (Statement statement = connection.createStatement()) {

			String sql = String.format(
					"INSERT INTO contacts (firstName, lastName, phoneNumber, email, added) VALUES ('%s', '%s', '%s', '%s', '%s');",
					firstName, lastName, phoneNumber, email, added);
			statement.executeUpdate(sql);
			contactPerson = new Contact(firstName, lastName, email, phoneNumber);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contactPerson;
	}

	public List<Contact> getContactsBasedOnStartDateUsingPreparedStatement(String startDate, String endDate) {

		String sqlStatement = String.format("SELECT * FROM contacts WHERE date_added BETWEEN '%s' AND '%s';", startDate,
				endDate);
		List<Contact> contactList = new ArrayList<>();

		try (Connection connection = getConnection();) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlStatement);
			contactList = this.getContactList(resultSet);
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return contactList;
	}

	public List<Contact> getContactDetailsBasedOnCityUsingStatement(String city) {

		String sqlStatement = String.format(
				"SELECT * FROM address where city='%s';",city );
		List<Contact> contactList = new ArrayList<>();

		try (Connection connection = getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlStatement);
			contactList = getContactDetails(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contactList;
	}

	public List<Contact> getContactDetailsBasedOnStateUsingStatement(String state) {

		String sqlStatement = String.format(
				"SELECT * FROM address WHERE state = '%s';", state);
		List<Contact> contactList = new ArrayList<>();

		try (Connection connection = getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlStatement);
			contactList = getContactDetails(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contactList;
	}
	public List<PersonContact> getPersonDetailsBasedOnNameUsingStatement(String name) {
		String sqlStatement = String.format("SELECT * FROM contacts WHERE firstName  = '%s';",name);
		List<PersonContact> addressList = new ArrayList<>();
				
		try (Connection connection = this.getConnection();){
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlStatement);
			addressList = this.getAddressData(resultSet);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return addressList;
	}
	public int updateContact(String firstName, String lastName, String phoneNumber, String email) 
	{
		String sqlString = String.format("update contacts set lastName ='%s',phoneNumber ='%s',email='%s' where firstName= '%s';",lastName,phoneNumber,email,firstName);
		try(Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			return statement.executeUpdate(sqlString);
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}return 0;
	}
	private List<PersonContact> getAddressData(ResultSet resultSet) {
		
		addressList = new ArrayList<>();
			
			try {
				while(resultSet.next()) {
					String firstName = resultSet.getString("firstname");
					String lastName = resultSet.getString("lastName");
					String address = resultSet.getString("address");
					String city  = resultSet.getString("city");
					String state   = resultSet.getString("state");
					String phoneNumber   = resultSet.getString("phoneNumber");
					int pinCode   = resultSet.getInt("pinCode");
					String email   = resultSet.getString("email");
					addressList.add(new PersonContact(firstName,lastName, address, city,state,pinCode,phoneNumber,email));
					
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			return addressList;
			
		}

	private List<Contact> getContactDetails(ResultSet resultSet) {

		List<Contact> contactList = new ArrayList<>();

		try {
			while (resultSet.next()) {
				int contactId = resultSet.getInt("contact_id");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String phoneNumber = resultSet.getString("phoneNumber");
				String email = resultSet.getString("email");
				String added = resultSet.getString("added");
				contactList.add(new Contact(contactId, firstName, lastName, phoneNumber, email, added));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contactList;

	}

	private void prepareStatementForAddressData() {

		try {
			Connection connection = this.getConnection();
			String sqlStatement = "SELECT * FROM address_Book WHERE name = ?;";
			addressDataStatement = connection.prepareStatement(sqlStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void prepareStatementForContactData() {

		try {
			Connection connection = this.getConnection();
			String sqlStatement = "SELECT * FROM contacts WHERE firstName = ?;";
			addressDataStatement = connection.prepareStatement(sqlStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}