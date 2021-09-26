package com.bridgelabz.addressbookproblem;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDBService
{
	public List<PersonContact> readData() 
	{
		String sql = "select * from contact_details c inner join addressBook_contact ac on ac.contact_id=c.contact_id";
		List<PersonContact> contactList = new ArrayList<>();
		try {
			Connection connection = this.getConnection();
			Statement statement = (Statement) connection.createStatement();
			ResultSet result = ((java.sql.Statement) statement).executeQuery(sql);
			while(result.next()) {	
				PersonContact person  = new PersonContact();
				person.setFirstName(result.getString("first_name"));
				person.setLastName(result.getString("last_name"));
				person.setnumber(result.getString("phone_number"));
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
}
