        package com.vtiger.comcast.organisationtest;
		import org.testng.annotations.Test;
		import com.crm.vtiger.genericutility.*;
import com.vtiger.comcast.pomrepositoryLib.CreateNewOrganization;
import com.vtiger.comcast.pomrepositoryLib.Home;
import com.vtiger.comcast.pomrepositoryLib.OrganizationInfo;
import com.vtiger.comcast.pomrepositoryLib.Organizations;

import junit.framework.Assert;
		
		public class CreateOrganisationTest extends BaseClass{
			//organisation should be added in oppurtunities,quotes,sales order
		@Test(groups ="smokeTest")
		public void createOrgTest() throws Throwable {
		int randomInt = jLib.getRanDomNumber();
		/*test script Data*/
		String orgName = eLib.getExcelData("Pricebook", 5, 1) + randomInt;
		
		 /*step 2 : navigate to organization*/
		 Home homePage = new Home(driver);
		 homePage.getOrganizationLnk().click();

		 /*step 3 : navigate to "create new organization"page by click on "+" image */
		 Organizations orgPage = new Organizations(driver);
		 orgPage.getCreateOrgImg().click();

		 /*step 4 : create organization*/
		 CreateNewOrganization cno = new CreateNewOrganization(driver);
		 cno.createOrg(orgName);

		 /*step 5 : verify the successful msg with org name*/
		 OrganizationInfo orginfoPage = new OrganizationInfo(driver);
		 String actSuccesfullMg = orginfoPage.getSuccessfulMsg().getText();
		 Assert.assertTrue(actSuccesfullMg.contains(orgName));
		 System.out.println(orgName + "==>created successfully");
		}
		
		}


