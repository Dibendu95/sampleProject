package vtiger;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Properties;

public class Practice {

	public static void main(String[] args) throws IOException {
		// step 1: read the data from property file
		
	   FileInputStream fis = new FileInputStream(".\\MyData\\CommonData.properties");
	           Properties prop = new Properties();
	      prop.load(fis);
	      String gh = prop.getProperty("url");
	      String USERNAME = prop.getProperty("username");
	      String PASSWORD = prop.getProperty("password");
	       String LastName = prop.getProperty("lastname");
      FileOutputStream ghi = new  FileOutputStream("JKIO");	       

	}

}
