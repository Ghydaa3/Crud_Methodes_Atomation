package MYProj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mysql.cj.xdevapi.Statement;

public class sqlMethodes {
	Connection con = null;
	java.sql.Statement stm = null;
	ResultSet rs = null;

	@BeforeTest
	public void preTest() throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "Ghydaa7", "ghydaa24399G");
	}

	@Test()
	public void createMethod() throws SQLException {
		stm = con.createStatement();

		String myQuery = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber, creditLimit)\r\n"
				+ "VALUES (778, 'Ghydaa', 'Abu Zainh', 'Amm', '078527', 'Yajoz', NULL, 'Amman', NULL, 333, 'Jordan', NULL, 100000)";

		int rowEffected = stm.executeUpdate(myQuery);
		if (rowEffected > 0) {
			System.out.println("Query is done");
		} else {
			System.out.println("Query is falid");
		}

	}

	@Test()
	public void updateMethod() throws SQLException {
		stm = con.createStatement();
		String myQuery = "update customers set customerName='Ghydaa g' where customerNumber=778";
		int rowEffected = stm.executeUpdate(myQuery);

		Assert.assertEquals(rowEffected > 0, true, "sorry the update has problem");

	}
	
	
	@Test()
	public void readMethod() throws SQLException {
		stm=con.createStatement();
		String myQuery=" select * from customers where customerNumber=77";
		rs=stm.executeQuery(myQuery);
		
		while(rs.next()) {
			String thecustomerName=rs.getString("customerNumber");
			String customercity=rs.getString("city");
			String customerLastName=rs.getString("contactLastName");
			System.out.println(thecustomerName);
			System.out.println(customercity);
			System.out.println(customerLastName);
			
			Assert.assertEquals(customercity, "Amman");
			
		}
		
		
		
	}
	
	@Test()
	public void DeleteQuery() throws SQLException {
		stm = con.createStatement();

		String myQuery = "delete from customers where customerName='Schuyler Imports' and customerNumber = 303";

		int rowEffected = stm.executeUpdate(myQuery);

		Assert.assertEquals(rowEffected > 0, true, "sorry the delete has a problem");

	}

	@AfterTest
	public void postTest() {

	}

}
