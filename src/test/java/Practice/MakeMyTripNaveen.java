package Practice;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MakeMyTripNaveen {
	static WebDriver driver;
	public static int TwoFlights(int a,int b)
	{
		driver.findElement(By.xpath("//div[@class='paneView'][1]//div[@class='listingCardWrap']/div/div["+a+"]//span[@class='customRadioBtn']")).click();
		driver.findElement(By.xpath("//div[@class='paneView'][2]//div[@class='listingCardWrap']/div/div["+b+"]//span[@class='customRadioBtn']")).click();
		int sum = a+b;
		return sum;
	}
public static void main(String[] args) throws InterruptedException {
	WebDriverManager.chromedriver().setup();
	 driver = new ChromeDriver();     
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().deleteAllCookies();
	driver.get("https://www.makemytrip.com/");
	WebElement flight = driver.findElement(By.xpath("//span[@class='chNavText darkGreyText'][normalize-space()='Flights']"));
	Actions a = new Actions(driver);
	a.moveToElement(flight).doubleClick().perform();
	driver.findElement(By.xpath("//ul[@class='userSection pushRight']//li[@data-cy=\"account\"]")).click();
	WebElement roundTrip = driver.findElement(By.xpath("//li[@data-cy='roundTrip']/span[@class='tabsCircle appendRight5']"));
	a.moveToElement(roundTrip).click().perform();
	driver.findElement(By.xpath("//span[contains(text(),'DEPARTURE')]")).click();
	WebElement aug = driver.findElement(By.xpath("//div[contains(text(),'August')]/.."
			+ "/following-sibling::div[@class='DayPicker-Body']//div[@aria-label='Mon Aug 29 2022']"));	
	aug.click();
	driver.findElement(By.xpath("//div[contains(text(),'September')]/.."
			+ "/following-sibling::div[@class='DayPicker-Body']//div[@aria-label=\"Tue Sep 06 2022\"]")).click();
	driver.findElement(By.xpath("//a[contains(text(),'Search')]")).click();
	List<WebElement> elements = driver.findElements(By.xpath("//span[@class='boldFont blackText']"));
	System.out.println(elements.size());
	for(WebElement array:elements)
	{ 
	  System.out.println(array.getText());
	}
	
WebDriverWait wait = new WebDriverWait(driver, 30);
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'OKAY, GOT IT!')]")));
driver.findElement(By.xpath("//button[contains(text(),'OKAY, GOT IT!')]")).click();
Thread.sleep(5000);
	driver.findElement(By.xpath("//span[contains(text(),'Non Stop')]")).click();
	driver.findElement(By.xpath("//p[contains(text(),'Stops From Bengaluru')]/..//span[contains(text(),'1 Stop')]")).click();

	List<WebElement> NoOfDeparture = driver.findElements(By.xpath("//p[contains(text(),'Departure From Bengaluru')]"
			+ "/..//div[@class='appendBottom12 filterTimeSlots ']"));
	System.out.println("Total number of Departure flights = "+NoOfDeparture.size());
	List<WebElement> NoOfArrival = driver.findElements(By.xpath("//p[contains(text(),'Arrival at New Delhi')]"
			+ "/..//div[@class='appendBottom12 filterTimeSlots ']"));
	System.out.println("Total number of Arrival flights = "+NoOfArrival.size());
	driver.findElement(By.xpath("//span[contains(text(),'Non Stop')]")).click();
	driver.findElement(By.xpath("//p[contains(text(),'Stops From Bengaluru')]/..//span[contains(text(),'1 Stop')]")).click();
	driver.findElement(By.xpath("//span[contains(text(),'18:45')]/ancestor::div[@class='timingOption makeFlex spaceBetween textCenter fontSize12']/following-sibling::div/span")).click();
//	int TotalCost = MakeMyTripNaveen.TwoFlights(4,3);
//	System.out.println(TotalCost);
//	
}
}
 