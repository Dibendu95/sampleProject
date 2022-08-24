package Practice;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.crm.vtiger.genericutility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MakeMyTripCalendar {
	public static void main(String[] args) throws InterruptedException {
		WebDriverUtility wLib = new WebDriverUtility();
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		wLib.waitForElementInDOM(driver);
		driver.get("https://www.makemytrip.com/");
		driver.findElement(By.xpath("//input[@id='fromCity'][@value='Delhi']")).click();
		driver.findElement(By.xpath("//p[contains(text(),'Mumbai, India')]")).click();
		WebElement departure = driver.findElement(By.xpath("//span[@class='lbl_input latoBold appendBottom10'][contains(text(),'DEPARTURE')]"));
		departure.click();
		wLib.waitForElement(driver, departure);
		//Thread.sleep(5000);
		departure.click();
		driver.findElement(By.xpath("//div[contains(text(),'August 2022')]/parent::div/following-sibling::div[@class='DayPicker-Body']/descendant::div[@class='DayPicker-Day'][@aria-label='Fri Aug 12 2022']")).click();
	
	}

}
