package com.vtiger.comcast.pomrepositoryLib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PricebookBusinessPage {
	
	WebDriver driver;
	
	PricebookBusinessPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@href='javascript:;'][contains(text(),'More')]")
	private WebElement More;
	
	public WebElement getMore()
	{
		return More;
	}
	
	
	
	@FindBy(xpath = "//a[@href='index.php?module=PriceBooks&action=index']")
	private WebElement PricebookLnk;	
	
	public WebElement PricebookLnk()
	{
		return PricebookLnk;
	}	
	
	
	
	@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement CreatePricebookImg;
	
	public WebElement CreatePricebookImg()
	{
		return CreatePricebookImg;
	}
	
	
	
	@FindBy(xpath="//input[@name='bookname']")
	private WebElement bookname;
	
	public WebElement bookname()
	{
		return bookname;
	}
	
	
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement SaveBtn;
	
	public WebElement SaveBtn()
	{
		return SaveBtn;
	}
	
	
	public void Verification(String actualData, String expeData)
	{
		WebElement data = driver.findElement(By.xpath(" //b[contains(text(),'Price Book Information:')]"));
		 actualData = data.getText();
		System.out.println(actualData);
	    expeData =" Price Book Information:";
	    if(expeData.contains(actualData))
	    {
	    	System.out.println("Test Pass");
	    }
	    
	    else
	    {
	    	System.out.println("Test Fail");
	    }
	}
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement SignOutImg;
	
	public WebElement SignOutImg()
	{
		return SignOutImg;
	}
	
	   /*
			//Step8 :Logout from app
			driver.findElement(By.xpath("//td[@class='tabSelected']//a[@href='index.php?module=PriceBooks&action=index']")).click();
			driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
			driver.findElement(By.xpath("//a[@href='index.php?module=Users&action=Logout']")).click();
			
		}*/

}
