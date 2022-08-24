package vtiger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContacts {
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
		 FileInputStream fileExcel = new FileInputStream(".\\Data\\Contacts Module.xlsx");
		Workbook wb = WorkbookFactory.create(fileExcel);
		String orgName= wb.getSheet("Sheet1").getRow(91).getCell(8).getStringCellValue();
		
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
		
		//Step 5: Navigate to Contacts Link
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
		// Step 6: Create Contact with mandatory info
		driver.findElement(By.name("lastname")).sendKeys(orgName);
		WebElement dp = driver.findElement(By.name("salutationtype"));
		Select dropdown = new Select(dp);
		dropdown.selectByValue("Mr.");
		//Step 7: save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		
		
		
		
	}

}


