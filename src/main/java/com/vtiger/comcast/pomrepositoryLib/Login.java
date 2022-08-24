package com.vtiger.comcast.pomrepositoryLib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login { //Rule 1: Create separate Java class for every page in application

	    public Login(WebDriver driver)
	    { 
		 //Rule 2: Execute all the elements & initialize the elements PageFactory.initElemnts [initialization] 
		 PageFactory.initElements (driver, this);
	    }


		 @FindBy(name = "user_name")//Rule 3: Identify all the elements using @findBy & findbys, findAll (Declaration)
         private WebElement userNameEdt;



		@FindBy(name = "user_password")
        private WebElement userPasswordEdt;


		@FindBy(id="submitButton") 
		private WebElement loginBtn;
			
		//Rule 4: Declare all the elements as private & provide getters method, business method for (Utilization)
		 public WebElement getUserNameEdt() {	
			 return userNameEdt;
         }
		

		public WebElement getUserPasswordEdt() 
		{
		return userPasswordEdt;
		} 
		
		
		public WebElement getLoginBtn()
		{ 
			return loginBtn;
		}

		public void loginToApp (String userName, String password )
		{
		userNameEdt.sendKeys(userName);
		userPasswordEdt.sendKeys(password);
		loginBtn.click();
       }
		
		}
