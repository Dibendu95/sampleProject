package com.crm.vtiger.genericutility;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.vtiger.comcast.pomrepositoryLib.Home;
import com.vtiger.comcast.pomrepositoryLib.Login;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public WebDriver driver;
	
	
	public JavaUtility jLib = new JavaUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
	public FileUtility fLib = new FileUtility();
	public ExcelUtility eLib = new ExcelUtility();
	
	@BeforeSuite(groups ="smokeTest")
	public void configBS() {
	System.out.println("========================connect to DB========================");
	}
	
	
	@BeforeClass
	public void configBC() throws Throwable {
	
	}
	
	
	@BeforeMethod(groups ="smokeTest")
	public void configBM() throws Throwable {
	String URL = fLib.getPropertyKeyValue("url");
	String USERNAME = fLib.getPropertyKeyValue("username");
	String PASSWORD = fLib.getPropertyKeyValue("password");
	String BROWSER = fLib.getPropertyKeyValue("browser");
	WebDriverManager.chromedriver().setup();
	if(BROWSER.equals("chrome")) {
		driver = new ChromeDriver();
		}else if(BROWSER.equals("firefox")) {
		driver = new FirefoxDriver();
		}else if(BROWSER.equals("ie")) {
		driver = new InternetExplorerDriver();
		}else {
		driver = new ChromeDriver();
		}
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get(URL);
	wLib.waitForElementInDOM(driver);
	Login loginPage = new Login(driver);
	loginPage.loginToApp(USERNAME, PASSWORD);
	
	}
	
	@AfterMethod(groups ="smokeTest")
	public void configAM() {
	 /*step 6 : logout*/
	Home homePage = new Home(driver);
	 homePage.Logout();
	}
	
	@AfterClass
	public void configAC() {
	System.out.println("=============Close the Browser=======");
	driver.quit();
	}
	
	@AfterSuite(groups ="smokeTest")
	public void configAS() {
	System.out.println("========================close DB========================");
	}

 
}
