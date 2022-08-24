package vtiger;

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
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganisation {

	public static void main(String[] args) throws IOException {
		
	WebDriver driver = null;
		//Step 1 : read all the necessary common data 
		FileInputStream fis = new FileInputStream(".\\Data\\commonData.properties");
		 Properties prop = new Properties();
		 prop.load(fis);
		 String URL = prop.getProperty("url");
		 String BROWSER = prop.getProperty("browser");
		 String USERNAME = prop.getProperty("username");
		 String PASSWORD = prop.getProperty("password");
		 
		 //Step 2: read the test data from excel sheet
		 FileInputStream fileExcel = new FileInputStream(".\\Data\\Campaign Module.xlsx");
		Workbook wb = WorkbookFactory.create(fileExcel);
		String orgName= wb.getSheet("Sheet11").getRow(1).getCell(2).getStringCellValue();
		
		//Step 3: launch the browser ---- Run time polymorphism
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver();
		}
		
		else if(BROWSER.equalsIgnoreCase("FIREFOX"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
		else
		{
			System.out.println("invalid browser name");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(URL);
		
		
		//Step 4: login to app
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Step 5: Navigate to organization Link
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		// Step 6: Create Organization with mandatory info
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		
		//Step 7: save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		
		
		
		
	}

}
