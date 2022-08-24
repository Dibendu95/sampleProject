package com.SDET38.PriceBook;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.crm.vtiger.genericutility.ExcelUtility;
import com.crm.vtiger.genericutility.FileUtility;
import com.crm.vtiger.genericutility.JavaUtility;
import com.crm.vtiger.genericutility.WebDriverUtility;


import io.github.bonigarcia.wdm.WebDriverManager;



public class CreatePriceBookWithMandatoryFields { //DONE

	public static void main(String[] args) throws Throwable {
		
		//Create object of Utility
		WebDriverUtility wLib = new WebDriverUtility();
		FileUtility fLib = new FileUtility();
		JavaUtility jLib = new JavaUtility();
		ExcelUtility eLib =new ExcelUtility();
		
		
		 
		 //get Random Number
		 int intRanNum = jLib.getRanDomNumber();
		
		//Step1: Read all the common data from Property file
		 String URL = fLib.getPropertyKeyValue("url");
		 String BROWSER = fLib.getPropertyKeyValue("browser");
		 String USERNAME = fLib.getPropertyKeyValue("username");
		 String PASSWORD = fLib.getPropertyKeyValue("password");
		 
		//Step2: Read the test data from excel sheet
		String orgName =  eLib.getExcelData("Pricebook",1,2);
		String expectedData = eLib.getExcelData("Pricebook",1,3);
		
		
		//Step 3: Launch the browser
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
				
		//Step4 :Login to Application
		wLib.waitForElementInDOM(driver); //implicitly wait
		driver.get(URL);
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(USERNAME);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		
		//Step5 :Navigate to Pricebook link 
		driver.findElement(By.xpath("//a[@href='javascript:;' and contains(text(),'More') ]")).click();
		driver.findElement(By.xpath("//a[@id='more' and contains(text(),'Price Books')]")).click();
		driver.findElement(By.xpath("//img[@title='Create Price Book...']")).click();
		
		//Step6 :Create Pricebook with mandatory info
		driver.findElement(By.xpath("//input[@name='bookname']")).sendKeys(orgName);
		
		//Step7 :Save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step8 :Verification
		WebElement data = driver.findElement(By.xpath(" //b[contains(text(),'Price Book Information:')]"));
		String actualData = data.getText();
		System.out.println(actualData);
	    String expeData =" Price Book Information:";
	    if(expeData.contains(actualData))
	    {
	    	System.out.println("Test Pass");
	    }
	    
	    else
	    {
	    	System.out.println("Test Fail");
	    }
		
		
		//Step8 :Logout from app
		driver.findElement(By.xpath("//td[@class='tabSelected']//a[@href='index.php?module=PriceBooks&action=index']")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
		driver.findElement(By.xpath("//a[@href='index.php?module=Users&action=Logout']")).click();
		
	}

}
