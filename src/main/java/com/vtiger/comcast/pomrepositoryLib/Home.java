package com.vtiger.comcast.pomrepositoryLib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home {
	WebDriver driver;
	
	public Home(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Organizations")
	private WebElement organizationLnk;
	
	@FindBy(linkText = "Products")
	private WebElement productLnk;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG]")
	private WebElement administratorImg;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement SignOutImg;
	
	@FindBy(xpath = "//a[contains(text(),'Sign Out')]")
	private WebElement SignOutLnk;
	
	public WebElement getOrganizationLnk()
	{
		return organizationLnk;
	}
	
	public WebElement getProductLnk()
	{
		return productLnk;
	}
	
	public WebElement getAdministratorLnk()
	{
		return administratorImg;
	}
	
	public WebElement getSignOutLnk()
	{
		return SignOutLnk;
	}
	
	public void Logout() {
		SignOutImg.click();
		SignOutLnk.click();
	}
public void SignOut() {
		
	SignOutImg.click();
	}
	
	
	
	
}
