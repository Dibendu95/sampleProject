package Practice;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Flipkart {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.get("https://www.flipkart.com/");
		driver.findElement(By.xpath("//input[@type='text'][@autocomplete='off'][@class='_2IX_2- VJZDxU']")).sendKeys("8920886952");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("prodigy1995");
		driver.findElement(By.xpath("//button[@class='_2KpZ6l _2HKlqd _3AWRsL']")).click();
		driver.findElement(By.name("q")).click();
	
		driver.findElement(By.xpath("//input[@title='Search for products, brands and more']")).click();
		driver.findElement(By.xpath("//li[@class='Y5N33s']//div[@class='lrtEPN'][contains(text(),'mobiles')]")).click();
		
   
	}

}
