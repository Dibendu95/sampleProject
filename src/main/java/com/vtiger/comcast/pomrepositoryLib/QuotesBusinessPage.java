package com.vtiger.comcast.pomrepositoryLib;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.vtiger.genericutility.WebDriverUtility;

public class QuotesBusinessPage {
	 WebDriver driver;

	 WebDriverUtility wLib = new WebDriverUtility();
	public QuotesBusinessPage(WebDriver driver) 
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

	
	
	@FindBy(xpath = "//a[@name='Quotes']")
	private WebElement QuotesLnk;	
	
	public WebElement getQuotesLnk()
	{
		return QuotesLnk;
	}

	
	
    @FindBy(xpath="//img[@alt='Create Quote...']")
    private WebElement CreateQuote;
	
    public WebElement CreateQuote()
	{
		return CreateQuote;
	}

    
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement SignOutImg;
	
    public void SignOut()
    {
		SignOutImg.click();
	}

    
    
	@FindBy(xpath = "//a[@href='index.php?module=Users&action=Logout']")
	private WebElement SignOutLnk;
	
	public WebElement SignOutLnk()
	{
		return SignOutLnk;
	}

	
	
	@FindBy(xpath = "//input[@name='subject']")
	private WebElement subject;
	
	public WebElement subject()
	{
		return subject;
	}

	
	
	@FindBy(xpath = "//textarea[@name='bill_street']")
	private WebElement BillingAddress;
	
	public WebElement BillingAddress()
	{
		return BillingAddress;
	}
	
	
	@FindBy(xpath = "//img[contains(@onclick,'return') ]")
	private WebElement Return;
	
	public WebElement Return()
	{
		return Return;
	}
	
	
	
	@FindBy(xpath = "//textarea[@class='detailedViewTextBox'][@name='ship_street']")
	private WebElement ShippingAddress;
	
	public WebElement ShippingAddress()
	{
		return ShippingAddress;
	}

	
	
	@FindBy(xpath = "//img[@title='Products']")
	private WebElement ProductLookupIcon;
	
	public WebElement ProductLookupIcon()
	{
		return ProductLookupIcon;
	}

	
	
	@FindBy(xpath = "//a[@id='popup_product_14']")
	private WebElement productPop;
	
	public WebElement productPop()
	{
		return productPop;
	}

	
	
	@FindBy(xpath = "//input[@id='qty1']")
	private WebElement Qty;
	
	public WebElement Qty()
	{
		return Qty;
	}

	
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement SaveBtn;
	
	public WebElement SaveBtn()
	{ 
		SaveBtn.click();
		return SaveBtn;
	}

	
	
	@FindBy(xpath="//a[@href='javascript:;' and contains(text(),'More') ]")
	private WebElement SalesOrderMore;
	
	public WebElement SalesOrderMore()
	{ 
		return SalesOrderMore;
	}
	

	
	@FindBy(xpath="//a[@id='more' ] [@name='Sales Order']")
	private WebElement SalesOrderLink;
	
	public WebElement SalesOrderLink()
	{
		return SalesOrderLink;
	}

	
	
	@FindBy(xpath="//img[@alt='Create Sales Order...']")
	private WebElement CreateSalesOrder;
	
	public WebElement CreateSalesOrder()
	{
		return CreateSalesOrder;
	}
	
	
	
	@FindBy(xpath="//img[@onclick='selectQuote()']")
	private WebElement SelectQuoteImg;
	
	public WebElement SelectQuoteImg()
	{
		return SelectQuoteImg;
	}
	

	
	@FindBy(xpath="//textarea[@name='bill_street']")
	private WebElement SalesBillingAddress;
	
	public WebElement SalesBillingAddress()
	{
		return SalesBillingAddress;
	}
	
	
	
	@FindBy(xpath="//textarea[@name='ship_street']")
	private WebElement SalesShippingAddress;
	
	public WebElement SalesShippingAddress()
	{
		return SalesShippingAddress;
	}
	
	

	@FindBy(xpath="//input[@value='bulk order']")
	private WebElement bulky;
	
	public WebElement Verification() 
	{
		String expValue = "bulk order";
		String actualValue = bulky.getAttribute("value");
		return bulky;
	}
	
	@FindBy(xpath="//a[contains(text(),'Adobe Quotes')]")
	private WebElement bulkorder;
	
	public WebElement BulkOrder()
	{
		bulkorder.click();
		return bulkorder;
	}
	
	
	@FindBy(xpath="//input[@name='subject']")
	private WebElement Subject;
	
	public WebElement Subject()
	{
		bulkorder.click();
		return bulkorder;
	}
	
	@FindBy(xpath="//input[@name='account_name']//following-sibling::img")
	private WebElement OrgImgClick;
	
	public WebElement OrgImgClick()
	{
		OrgImgClick.click();
		return OrgImgClick;
	}
	
	
	public void SwitchToSalesOrderWindow()
	{
		wLib.switchToWindow(driver, "http://rmgtestingserver:8888/index.php?module=SalesOrder&action=EditView&return_action=DetailView&parenttab=Sales");
	}
	
	public void SwitchToQuotesWindow()
	{
		wLib.switchToWindow(driver, "module=Quotes");
	}
	
	
	public void SwitchToOrganisationWindow()
	{ 
	  wLib.switchToWindow(driver, "module=Accounts");
	  driver.findElement(By.xpath("//a[contains(text(),'Adobe Factory')]")).click();
	 
	}
	
	public void SwitchToAccountsThenQuotes()
	{
	  wLib.switchToWindow(driver, "module=Accounts");
	  driver.findElement(By.xpath("//a[@href='javascript:window.close();'][contains(text(),'Qspider1')]")).click(); 
	  wLib.swithToAlertWindowAndCancel(driver);			
	  wLib.switchToWindow(driver, "Quotes");
	}
	
	
	public void SwitchToProductsThenQuotes()
	{
      wLib.switchToWindow(driver, "module=Products");	
	  driver.findElement(By.xpath("//a[@id='popup_product_1300']")).click();
	  wLib.switchToWindow(driver, "Quotes");
	}
	
	
	public void AlertPop()
	{
		driver.switchTo().alert().accept();	
	}
	
	
	
	
	
}
