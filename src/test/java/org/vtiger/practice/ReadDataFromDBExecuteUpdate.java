package org.vtiger.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class ReadDataFromDBExecuteUpdate {

	public static void main(String[] args) throws SQLException {
		// Create object for driver
		Driver driver = new Driver();
		
		//step2: register the driver
		DriverManager.registerDriver(driver);
		
		//step3: establish the connection
		   Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB1", "root","root");
		    
		//step4: create statement
		   Statement state = con.createStatement();
		   
		//step5: execute the query
		   String query = "insert into student values(4,'Shivmogga');";
		   int result = state.executeUpdate(query);
		   System.out.println(result);
		   
		//step6: validate
		   if(result==1)
		   {
			   System.out.println("data added successfully");
		   }
		   
		   else
		   {
			   System.out.println("data not added");
		   }
		   
		   //step7:close connection
		   con.close();
		

	}

}
