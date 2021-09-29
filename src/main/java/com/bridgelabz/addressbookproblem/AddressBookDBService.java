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

public class AddressBookDBService
{
	private static AddressBookDBService addressBookDBService;
	private java.sql.PreparedStatement readContactPreparedStatement;
	 private PreparedStatement contactAddedGivenRangeStatement;
	 private PreparedStatement contactsInGivenCityOrStateStatement;
	private AddressBookDBService() {
		
	}
	public static AddressBookDBService getDBInstance() {
		addressBookDBService = new AddressBookDBService();
		return addressBookDBService;
	}
	public Connection getConnection() throws SQLException
	{
		String jdbcURL = "jdbc:mysql://localhost:3306/addressBook_service?userSSL=false";
		String userName = "root";
		String password = "naman1999";
		Connection connection;
		System.out.println("Connecting to database:"+jdbcURL);
		connection =  (Connection) DriverManager.getConnection(jdbcURL,userName,password);
		System.out.println("Connection is successful"+connection);
		return connection;
	
	}
	public List<PersonContact> readContacts(String addressBookName){
		ResultSet resultSet;
		if(readContactPreparedStatement==null)
			this.preparedStatementToReadContacts();
		try {
			readContactPreparedStatement.setString(1, addressBookName);
			resultSet=readContactPreparedStatement.executeQuery();
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
		
		return getContactList(resultSet);
	}
	public List<PersonContact> getContactList(ResultSet resultSet){
		List<PersonContact> contactsList=new ArrayList<PersonContact>();
		try {
			while(resultSet.next()) {
				contactsList.add(new PersonContact(null, resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("city"), resultSet.getString("address"), resultSet.getString("state"), resultSet.getInt("zip"),new Long(resultSet.getLong("phone_number")).intValue(), resultSet.getString("email"), null));
			}
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
		return contactsList;

	}
	public void writeAddressBookDB(PersonContact contact,String addressBookName) {
		String insertQueryString="INSERT INTO contact_details values("+contact.getContactId()+",\""+contact.getFirstName()+"\",\""+contact.getLastName()+"\",\""+contact.getAddress()+"\",\""+contact.getEmail()+"\","+contact.getPlace().getPlaceId()+",\""+contact.getPhoneNumber()+"\")";
		String insertPlaceQueryString="INSERT into place values("+contact.getPlace().getPlaceId()+",\""+contact.getCity()+"\",\""+contact.getZipCode()+"\",\""+contact.getState()+"\")";
		String insertIntermediateTableString="insert into addressBook_contact values((select book_id from address_book where book_name=\""+addressBookName+"\"),(select contact_id from contact_details where first_name=\""+contact.getFirstName()+"\"))";
		try {
			Connection connection=this.getConnection();
			System.out.println("connected");
			Statement statement=connection.createStatement();
			statement.executeUpdate(insertPlaceQueryString);
			statement.executeUpdate(insertQueryString);
			statement.executeUpdate(insertIntermediateTableString);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void preparedStatementToReadContacts() {
	    try {
	        Connection connection = this.getConnection();
	    	String sql="SELECT first_name,last_name,city,address,state,zip,phone_number,email from address_book a,contact_details c,addressBook_contact ac,place p  where p.place_id=c.place_id and a.book_id=ac.book_id and c.contact_id=ac.contact_id and book_name=?";
	       readContactPreparedStatement=connection.prepareStatement(sql);
	    } catch (Exception e) {
	        throw new DBException(e.getMessage());
	    }
	}
	public List<PersonContact> readConatctsAddedInRange(Date startDate, Date endDate) {
        if (contactAddedGivenRangeStatement == null) {
            this.preparedStatementToretriveContactsInRange();
        }
        try {
        	contactAddedGivenRangeStatement.setDate(1, startDate);
        	contactAddedGivenRangeStatement.setDate(2, endDate);
            ResultSet resultSet = contactAddedGivenRangeStatement.executeQuery();
            return this.getContactList(resultSet);
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }
		private void preparedStatementToretriveContactsInRange() {
        try {
            Connection connection = this.getConnection();
            String query = "SELECT * from contact_details where date_added between ? and ?";
            contactAddedGivenRangeStatement = connection.prepareStatement(query);
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }
		public List<PersonContact> readContactsInGivenCityOrState(String city, String state) {
			if (contactsInGivenCityOrStateStatement == null) {
				this.preparedStatementToretriveContactsInGivenCityOrState();
			}
			try {
				contactsInGivenCityOrStateStatement.setString(1, city);
				contactsInGivenCityOrStateStatement.setString(2, state);
				ResultSet resultSet = contactsInGivenCityOrStateStatement.executeQuery();
				return this.getContactList(resultSet);
			} catch (Exception e) {
				throw new DBException(e.getMessage());
			}
		}
		private void preparedStatementToretriveContactsInGivenCityOrState() {
			try {
				Connection connection = this.getConnection();
				String query = "SELECT * from contact_details c ,place p where c.place_id = p.place_id and city =? or state=?";
				contactsInGivenCityOrStateStatement = connection.prepareStatement(query);
			} catch (Exception e) {
				throw new DBException(e.getMessage());
			}
		}

}