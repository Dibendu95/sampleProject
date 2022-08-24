package com.vtiger.comcast.organisationtest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.crm.vtiger.genericutility.ExcelUtility;
import com.crm.vtiger.genericutility.FileUtility;
import com.crm.vtiger.genericutility.JavaUtility;
import com.crm.vtiger.genericutility.WebDriverUtility;
import com.vtiger.comcast.pomrepositoryLib.Login;
import com.vtiger.comcast.pomrepositoryLib.QuotesBusinessPage;


import io.github.bonigarcia.wdm.WebDriverManager;

public class QuoteModuleTest {
		
	public static void main(String[] args) throws Throwable {
		//Create Objects
		WebDriverUtility wLib = new WebDriverUtility();
		FileUtility fLib = new FileUtility();
		JavaUtility jLib = new JavaUtility();
		ExcelUtility eLib =new ExcelUtility();
		
		//Read common data	
		String URL = fLib.getPropertyKeyValue("url");
		String USERNAME = fLib.getPropertyKeyValue("username");
		String PASSWORD = fLib.getPropertyKeyValue("password");
		
		//Read test data
		String subject =  eLib.getExcelData("Pricebook",7,0);
		String Billing_Address =  eLib.getExcelData("Pricebook",7,1);
		String Shipping_Address =  eLib.getExcelData("Pricebook",7,2);
		String Qty =  eLib.getExcelData("Pricebook",7,3);
		
		//Step 1 Launch the browser
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		//step 2 Login to App
		driver.get(URL);
		Login lp = new Login(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		//step 3 navigate to Quotes Page
		QuotesBusinessPage Qbp = new QuotesBusinessPage(driver);
		Qbp.getMore().click();
		Qbp.getQuotesLnk().click();
		Qbp.CreateQuote().click();
		
		//Step 4 : Create Quotes module with mandatory fields
		Qbp.subject().sendKeys(subject);
		Qbp.ProductLookupIcon().click();
		Qbp.SwitchToProductsThenQuotes();
		Qbp.Qty().sendKeys(Qty);
		Qbp.Return().click();
		Qbp.SwitchToOrganisationWindow();
		Qbp.AlertPop();
		driver.navigate().refresh();
		wLib.switchToWindow(driver, "http://rmgtestingserver:8888/index.php?module=Quotes&action=EditView&return_action=DetailView&parenttab=Sales");
		 
		Qbp.BillingAddress().sendKeys(Billing_Address);
		Qbp.ShippingAddress().sendKeys(Shipping_Address);
				
		//Step 7: Save
		Qbp.SaveBtn();
					
		// Step 8: Navigate to Sales Order module
		Qbp.SalesOrderMore().click();
		Qbp.SalesOrderLink().click();
		Qbp.CreateSalesOrder().click();
		Qbp.SelectQuoteImg().click();
		Qbp.SwitchToQuotesWindow();
		Qbp.BulkOrder();
		Qbp.SwitchToSalesOrderWindow();
		Qbp.OrgImgClick();
		Qbp.SwitchToOrganisationWindow();
		Qbp.AlertPop();
		driver.navigate().refresh();
		Qbp.SwitchToSalesOrderWindow();
		Qbp.SalesBillingAddress().sendKeys(Billing_Address);
		Qbp.SalesShippingAddress().sendKeys(Shipping_Address);
		Qbp.subject().sendKeys(subject);
		Qbp.SaveBtn();
		
		// Step 9: Verification
		//Qbp.Verification();
		WebElement actResult = driver.findElement(By.xpath("//span[contains(text(),'bulk order -  Sales Order Information')]"));
		 String ACTUALValue =actResult.getText();
		 String EXPECTEDvalue="bulk order";
		 if(ACTUALValue.contains(EXPECTEDvalue))
		 {
			 System.out.println("test pass");
		 }
		 else
		 {
			 System.out.println("test fail");
		 }
	/*
		//Step 10 : Logout from application
		Qbp.SignOut();
		Qbp.SignOutLnk().click();
	    driver.quit();
	    */
	}

	
	}


