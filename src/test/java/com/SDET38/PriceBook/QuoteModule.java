package com.SDET38.PriceBook;
    import java.io.FileInputStream;
	import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
	import java.util.Set;
	import java.util.concurrent.TimeUnit;

	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.ss.usermodel.WorkbookFactory;
	import org.openqa.selenium.Alert;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;

import com.crm.vtiger.genericutility.ExcelUtility;
import com.crm.vtiger.genericutility.FileUtility;
import com.crm.vtiger.genericutility.JavaUtility;
import com.crm.vtiger.genericutility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

	public class QuoteModule {
		public static void main(String[] args) throws Throwable {
			//Create an object of Utility
			ExcelUtility eLib = new ExcelUtility();
			FileUtility fLib = new FileUtility();
			JavaUtility jLib = new JavaUtility();
			WebDriverUtility wLib = new WebDriverUtility();
			
			//get Random Number
			 int intRanNum = jLib.getRanDomNumber();
			 
			//Step 1 : Read common data from Property file
			String URL = fLib.getPropertyKeyValue("url");
			String USERNAME = fLib.getPropertyKeyValue("username");
			String PASSWORD = fLib.getPropertyKeyValue("password");
			String BROWSER = fLib.getPropertyKeyValue("browser");
			
			//Step 2 : Read test data from excel file
			String subject =  eLib.getExcelData("Sheet1",1,0);
			String Billing_Address =  eLib.getExcelData("Sheet1",1,1);
			String Shipping_Address =  eLib.getExcelData("Sheet1",1,2);
			String Qty =  eLib.getExcelData("Sheet1",1,3);
			
			
			//Step 3 : Launch the browser
			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			wLib.waitForElementInDOM(driver);
			
			
			
			//Step 4: Login to Application
			driver.get(URL);
			driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(USERNAME);
			driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(PASSWORD);
			driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		
			//Step 5 : Navigate to Quotes module
			driver.findElement(By.xpath("//a[@href='javascript:;' and contains(text(),'More') ]")).click();
			driver.findElement(By.xpath("//a[@id='more' and contains(text(),'Quotes')]")).click();
			driver.findElement(By.xpath("//img[@alt='Create Quote...']")).click();
			
			//Step 6 : Create Quotes module with mandatory fields
			driver.findElement(By.xpath("//input[@name='subject']")).sendKeys(subject);
			driver.findElement(By.xpath("//textarea[@name='bill_street']")).sendKeys(Billing_Address);
			driver.findElement(By.xpath("//img[contains(@onclick,'return') ]")).click();
		    wLib.switchToWindow(driver, "module=Accounts");
			driver.findElement(By.xpath("//a[@href='javascript:window.close();'][contains(text(),'Qspider1')]")).click(); 
			wLib.swithToAlertWindowAndCancel(driver);
			
			wLib.switchToWindow(driver, "Quotes");
			driver.findElement(By.xpath("//textarea[@class='detailedViewTextBox'][@name='ship_street']")).sendKeys(Shipping_Address);
			driver.findElement(By.xpath("//img[@title='Products']")).click();
			wLib.switchToWindow(driver, "module=Products");
			
			driver.findElement(By.xpath("//a[@id='popup_product_14']")).click();
			wLib.switchToWindow(driver, "Quotes");
			driver.findElement(By.xpath("//input[@id='qty1']")).sendKeys(Qty);
			
			//Step 7: Save
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			
			
			
			// Step 8: Navigate to Sales Order module
			driver.findElement(By.xpath("//a[@href='javascript:;' and contains(text(),'More') ]")).click();
			driver.findElement(By.xpath("//a[@id='more' ] [@name='Sales Order']")).click();
			driver.findElement(By.xpath("//img[@alt='Create Sales Order...']")).click();
			driver.findElement(By.xpath("//img[@onclick='selectQuote()']")).click();
			wLib.switchToWindow(driver, "Quotes");
			driver.findElement(By.xpath("//a[contains(text(),'bulk order')]")).click();
			wLib.switchToWindow(driver, "SalesOrder");
			driver.findElement(By.xpath("//textarea[@name='bill_street']")).sendKeys(Billing_Address);
			driver.findElement(By.xpath("//textarea[@name='ship_street']")).sendKeys(Shipping_Address);
			
			
			// Step 9: Verification
			WebElement bulky = driver.findElement(By.xpath("//input[@value='bulk order']"));
			String expValue = "bulk order";
			String actualValue = bulky.getAttribute("value");
			
			if(actualValue.contains(expValue))
		    {
			System.out.println("Test Pass");
			}
			else
			{
				System.out.println("Test Fail");
			}
			
			//Step 10 : Logout from application
			driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
			driver.findElement(By.xpath("//a[@href='index.php?module=Users&action=Logout']")).click();
		  driver.quit();
		  
		}}
			
		

		
			



