package vtiger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon 
{
	
	public static void main(String[] args) throws IOException {
	 ChromeDriver driver= null;
            Scanner sc = new Scanner(System.in);
            String product =sc.next();
            WebDriverManager.chromedriver().setup();
		    driver = new ChromeDriver();
		    driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.get("https://www.amazon.in/");
			driver.findElement(By.id("twotabsearchtextbox")).sendKeys("mobile");
			driver.findElement(By.id("nav-search-submit-button")).click();
			
			driver.findElement(By.xpath("//li[@id='p_89/"+product+"']//i[@class='a-icon a-icon-checkbox']")).click();
			
	}

}
