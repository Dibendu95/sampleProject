package com.vtiger.comcast.organisationtest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.crm.vtiger.genericutility.ExcelUtility;
import com.crm.vtiger.genericutility.FileUtility;
import com.crm.vtiger.genericutility.JavaUtility;
import com.crm.vtiger.genericutility.WebDriverUtility;
import com.vtiger.comcast.pomrepositoryLib.Login;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VendorTest {
  //create,update and delete vendor 
	public static void main(String[] args) throws Throwable {
		WebDriverUtility wLib = new WebDriverUtility();
		JavaUtility jLib = new JavaUtility();
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		
		//Read common Data
		String URL = fLib.getPropertyKeyValue("url");
		String USERNAME = fLib.getPropertyKeyValue("username");
		String PASSWORD = fLib.getPropertyKeyValue("password");
		
		//Read Test Data
		String x = eLib.getExcelData("Pricebook",7,0);
		String y = eLib.getExcelData("Pricebook",7,0);
		String z = eLib.getExcelData("Pricebook",7,0);
		
		//Launch the browser
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		//Login to application
		Login log = new Login(driver);
		log.loginToApp(USERNAME, PASSWORD);
		
		
		
		
	}
}
