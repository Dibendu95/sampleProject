package com.vtiger.comcast.pomrepositoryLib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpprtunityInfo {
	
	public OpprtunityInfo(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
  
	@FindBy(xpath="//span[contains(text(),'Mr. Majunath -  Opportunity Information')]")
	private WebElement SuccessfulMessage;

	public  WebElement getSuccessfulMessage() {
		return SuccessfulMessage;
	}
}
