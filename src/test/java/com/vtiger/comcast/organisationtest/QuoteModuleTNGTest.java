package com.vtiger.comcast.organisationtest;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.testng.annotations.Test;
import com.crm.vtiger.genericutility.BaseClass;
import com.vtiger.comcast.pomrepositoryLib.QuotesBusinessPage;
public class QuoteModuleTNGTest extends BaseClass{
	
	@Test(groups ="smokeTest")
	public void quote() throws Throwable {
		String subject =  eLib.getExcelData("Pricebook",7,0);
		String Billing_Address =  eLib.getExcelData("Pricebook",7,1);
		String Shipping_Address =  eLib.getExcelData("Pricebook",7,2);
		String Qty =  eLib.getExcelData("Pricebook",7,3);
			// navigate to Quotes Page
			QuotesBusinessPage Qbp = new QuotesBusinessPage(driver);
			Qbp.getMore().click();
			Qbp.getQuotesLnk().click();
			Qbp.CreateQuote().click();
			
			// Create Quotes module with mandatory fields
			Qbp.subject().sendKeys(subject);
			Qbp.ProductLookupIcon().click();
			Qbp.SwitchToProductsThenQuotes();
			Qbp.Qty().sendKeys(Qty);
			Qbp.Return().click();
			Qbp.SwitchToOrganisationWindow();
			Qbp.AlertPop();
			driver.navigate().refresh();
			wLib.switchToWindow(driver, "http://rmgtestingserver:8888/index.php?module=Quotes&action=EditView&return_action=DetailView&parenttab=Sales");
			 
			Qbp.BillingAddress().sendKeys(Billing_Address);
			Qbp.ShippingAddress().sendKeys(Shipping_Address);
					
			//Step 7: Save
			Qbp.SaveBtn();
						
			//Navigate to Sales Order module
			Qbp.SalesOrderMore().click();
			Qbp.SalesOrderLink().click();
			Qbp.CreateSalesOrder().click();
			Qbp.SelectQuoteImg().click();
			Qbp.SwitchToQuotesWindow();
			Qbp.BulkOrder();
			Qbp.SwitchToSalesOrderWindow();
			Qbp.OrgImgClick();
			Qbp.SwitchToOrganisationWindow();
			Qbp.AlertPop();
			driver.navigate().refresh();
			Qbp.SwitchToSalesOrderWindow();
			Qbp.SalesBillingAddress().sendKeys(Billing_Address);
			Qbp.SalesShippingAddress().sendKeys(Shipping_Address);
			Qbp.subject().sendKeys(subject);
			Qbp.SaveBtn();
			
			
			//Verification
			//Assert.assertTrue(false);
		
		
		}

		

}
