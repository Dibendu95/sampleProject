package com.vtiger.comcast.organisationtest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.crm.vtiger.genericutility.BaseClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class seleLondonChallengeTest  {
	
	//edited
	@Test
    public void test() {
	WebDriverManager.chromedriver().setup();
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.get("https://www.zoopla.co.uk/");
	driver.findElement(By.xpath("//input[@class='c-voGFy']")).sendKeys("London");
	driver.findElement(By.xpath("//div[contains(text(),'Search')]")).click();
	List<WebElement> element1 = driver.findElements(By.xpath("//*[@id='main-content']//div[@class='css-10j5eug e1sqvn2f12']/a/p[2]"));
	
for(WebElement array:element1) {
	
	System.out.println(array.getText());//edited
}

    }

 }

