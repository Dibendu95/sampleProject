package org.vtiger.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.xdevapi.Statement;

public class ReadDataFromDataBaseExecuteQuery {

	public static void main(String[] args) throws SQLException {
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		Connection con = DriverManager.getConnection("Jdbc:mysql://localhost:3306/student","root","root");
	
		String query = "select* from student; ";
		String expData = "Shivmogga";
	
		

	}

}
