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
import com.vtiger.comcast.pomrepositoryLib.CreateNewOrganization;
import com.vtiger.comcast.pomrepositoryLib.Home;
import com.vtiger.comcast.pomrepositoryLib.Login;
import com.vtiger.comcast.pomrepositoryLib.OpprtunityInfo;
import com.vtiger.comcast.pomrepositoryLib.OrganizationInfo;
import com.vtiger.comcast.pomrepositoryLib.Organizations;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrgTest { 
	//organisation should be added in oppurtunities,quotes,sales order
	public static void main(String[] args) throws Throwable {
		/*Create Objects*/
		WebDriverUtility wLib = new WebDriverUtility();
		FileUtility fLib = new FileUtility();
		JavaUtility jLib = new JavaUtility();
		ExcelUtility eLib =new ExcelUtility();
		
		//read common data
		String BROWSER = fLib.getPropertyKeyValue("browser");
		String URL = fLib.getPropertyKeyValue("url");
		String USERNAME = fLib.getPropertyKeyValue("username");
		String PASSWORD = fLib.getPropertyKeyValue("password");
		
		//read test data
		String orgName = eLib.getExcelData("Pricebook", 5, 1) + "_"+jLib.getRanDomNumber();
		String opportName = "Mr. Majunath";
		
		//Step 1 Launch the browser
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		//step 2 Login to App
		driver.get(URL);
		Login lp = new Login(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		//step 3 navigate to Org Page
		Home hp = new Home(driver);
		hp.getOrganizationLnk().click();
		
		//step 4 navigate to create Org Page
		Organizations op = new Organizations(driver); 
		op.getCreateOrgImg().click();
		
		//step 5 create org
		CreateNewOrganization cnop = new CreateNewOrganization(driver);
		cnop.createOrg(orgName);
		
		//step 6 verify
		OrganizationInfo oinfo = new OrganizationInfo(driver);
		wLib.waitForElement(driver, oinfo.getSuccessfulMsg());
		String actMsg = oinfo.getSuccessfulMsg().getText();
		
		if(actMsg.contains(orgName))
		{
			System.out.println("test pass");
		}
		else
		{
			System.out.println("test fail");
		}
		
		//step 7 Navigate to Opportunity module
		driver.findElement(By.xpath("//a[@href='index.php?module=Potentials&action=index']")).click();
		  //clicking on + image
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		//fill opportunity text field
		driver.findElement(By.name("potentialname")).sendKeys("Mr. Majunath");
		//click on Related to + button
		driver.findElement(By.xpath("//input[@id='related_to_display']/following-sibling::img")).click();
		//switch window
		wLib.switchToWindow(driver,"module=Accounts");
		driver.findElement(By.xpath("//a [@href='javascript:window.close();'][contains(text(),'myntra703')]")).click();
		wLib.switchToWindow(driver,"module=Potentials");
		driver.findElement(By.xpath("//div[@id='basicTab']//input[@title='Save [Alt+S]'][1]")).click();
		
		//VERIFY
		OpprtunityInfo oppo = new OpprtunityInfo(driver);
		String ActMsg = oppo.getSuccessfulMessage().getText();
		System.out.println(ActMsg);
		if(ActMsg.contains(opportName))
		{
			System.out.println("Opportunity created successfully");
		}
		else
		{
			System.out.println("Opportunity not created");
		}
		
		//navigating to quotes to check if organisation has been created
		driver.findElement(By.xpath("//a[contains(text(),'More')][@href='javascript:;'])")).click();
		driver.findElement(By.xpath("//a[@name='Quotes']")).click();
		//click on + button
		driver.findElement(By.xpath("//img[@alt='Create Quote...']")).click();
		//fill Subject field
		driver.findElement(By.xpath("//input[@name='subject']")).sendKeys("HCL");
		//click on organisation img
		driver.findElement(By.xpath("//input[@name='subject']/../following-sibling::td[@width='30%']//img")).click();
		
		/*
		//step 7: logout
		hp.SignOut();
		
		//step 8: close the browser
		driver.close();*/
	}

}
