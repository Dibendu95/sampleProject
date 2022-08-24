package com.SDET38.PriceBook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Vendor {

	public static void main(String[] args) throws IOException {
		//Step 1 : Read common data from Property file
				FileInputStream fis = new FileInputStream(".\\Data\\PricebookCommonData.properties");
				Properties pr = new Properties();
				pr.load(fis);
				String url = pr.getProperty("URL");
				String username = pr.getProperty("USERNAME");
				String password = pr.getProperty("PASSWORD");
				
				//Step 2 : Read test data from excel file
				FileInputStream excel =new FileInputStream(".\\Data\\QuotesToSales.xlsx");
				Workbook wb = WorkbookFactory.create(excel);
				String VendorName = wb.getSheet("Sheet1").getRow(4).getCell(0).getStringCellValue();
				
				
				//Step 3 : Launch the browser
				WebDriverManager.chromedriver().setup();
				WebDriver driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				
				//Step 4: Login to Application
				driver.get(url);
				driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(username);
				driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(username);
				driver.findElement(By.xpath("//input[@id='submitButton']")).click();
				
				//Step 5: Navigate to Vendor module
				driver.findElement(By.xpath("//a[@href='javascript:;' and contains(text(),'More') ]")).click();
				driver.findElement(By.xpath("//a[@href='index.php?module=Vendors&action=index']")).click();
				driver.findElement(By.xpath("//img[@alt='Create Vendor...']")).click();
				
	}

}
