package com.SDET38.PriceBook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.vtiger.genericutility.ExcelUtility;
import com.crm.vtiger.genericutility.FileUtility;
import com.crm.vtiger.genericutility.JavaUtility;
import com.crm.vtiger.genericutility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

    public class Organisation {
	public static void main(String[] args) throws Throwable {
			//Create Object of Utilities file
		    ExcelUtility eLib = new ExcelUtility();
		    FileUtility fLib = new FileUtility();
		    JavaUtility jLib = new JavaUtility();
		    WebDriverUtility wLib = new WebDriverUtility();
		    
			//Step 1 : Read common data from Property file
		    String URL = fLib.getPropertyKeyValue("url");
		    String USERNAME = fLib.getPropertyKeyValue("username");
		    String PASSWORD = fLib.getPropertyKeyValue("password");
		    
					
			//Step 2 : Read test data from excel file
			String subject = eLib.getExcelData("Sheet1", 1, 0);
			String Billing_Address = eLib.getExcelData("Sheet1", 1, 1);
			String Shipping_Addresst = eLib.getExcelData("Sheet1", 1, 2);
			String Qty = eLib.getExcelData("Sheet1", 1, 3);
			
			
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
		
			//Step 5 : Navigate to Organisation module
			driver.findElement(By.xpath("//a[@href='index.php?module=Accounts&action=index'][contains(text(),'Organizations')]")).click();
			driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
			Random ran = new Random();
			int ranNumber = ran.nextInt();
			driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys("Bajaj"+ranNumber);
			
			driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();
			String parent1 = driver.getWindowHandle();	
			Set<String> child = driver.getWindowHandles();
			for(String popup:child)
			{
				driver.switchTo().window(popup);
			}
			driver.findElement(By.xpath("//a[contains(text(),'uber')]")).click();
			Alert a1 = driver.switchTo().alert();
			a1.dismiss();
			driver.switchTo().window(parent1);
			driver.findElement(By.xpath("//input[@id='phone']")).sendKeys("8929764534");
			driver.findElement(By.xpath("//input[@name='notify_owner']")).click();
			WebElement select1 = driver.findElement(By.xpath("//select[@name='industry']"));
			Select s = new Select(select1);
			s.selectByValue("Electronics");
			WebElement select2 = driver.findElement(By.xpath("//select[@name='accounttype']"));
			Select s2 =new Select(select2);
			s2.selectByValue("Partner");
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			driver.navigate().refresh();//TO AVOID STALE ELEMENT REFERENCE EXCEPTION
			
			//Naviagte to Quotes module
			driver.findElement(By.xpath("//a[contains(text(),'More') and @href='javascript:;']")).click();
			driver.findElement(By.xpath("//a[@id='more' and @href='index.php?module=Quotes&action=index']")).click();
			driver.findElement(By.xpath("//img[@alt='Create Quote...']")).click();
			driver.findElement(By.xpath("//input[@name='subject']")).sendKeys("1234");
			
			
			
			
			/*
			//Step 6 : Create Quotes module with mandatory fields
			driver.findElement(By.xpath("//input[@name='subject']")).sendKeys(subject);
			driver.findElement(By.xpath("//textarea[@name='bill_street']")).sendKeys(Billing_Address);
			driver.findElement(By.xpath("//img[contains(@onclick,'return') ]")).click();
			String parent = driver.getWindowHandle();
			Set<String> product = driver.getWindowHandles();
			for(String id:product)
			{
				driver.switchTo().window(id);
			}
			driver.findElement(By.xpath("//a[@id='1']")).click();
			Alert a = driver.switchTo().alert();
			a.dismiss();
			driver.switchTo().window(parent);
			driver.findElement(By.xpath("//textarea[@name='ship_street']")).sendKeys(Shipping_Address);
			driver.findElement(By.xpath("//img[@title='Products']")).click();
			Set<String> item = driver.getWindowHandles();
			
			for(String tool:item)
			{
				driver.switchTo().window(tool);
			}
			driver.findElement(By.xpath("//a[@id='popup_product_14']")).click();
			driver.switchTo().window(parent);
			driver.findElement(By.xpath("//input[@id='qty1']")).sendKeys(Qty);
			
			//Step 7: Save
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			WebElement title = driver.findElement(By.xpath("//span[contains(text(),'bulk order -  Quote Information')]"));
			
			
			// Step 8: Navigate to Sales Order module
			driver.findElement(By.xpath("//a[@href='javascript:;' and contains(text(),'More') ]")).click();
			driver.findElement(By.xpath("//a[@id='more' ] [@name='Sales Order']")).click();
			driver.findElement(By.xpath("//img[@alt='Create Sales Order...']")).click();
			driver.findElement(By.xpath("//img[@onclick='selectQuote()']")).click();
			String parent2 = driver.getWindowHandle();
			Set<String> sales = driver.getWindowHandles();
			for(String popup:sales)
			{
				driver.switchTo().window(popup);
			}
			driver.findElement(By.xpath("//a[contains(text(),'bulk order')]")).click();
			driver.switchTo().window(parent2);
			
			// Step 9: Verification
			WebElement bulky = driver.findElement(By.xpath("//input[@value='bulk order']"));
			String expValue = "bulk order";
			String actualValue = bulky.getAttribute("value");
			System.out.println(bulky.getAttribute("value"));
			
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
		*/
			
		}
	}


