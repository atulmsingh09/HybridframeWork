package com.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.PageObjects.AddCustomerPageObjects;
import com.PageObjects.LoginPageObjects;
import com.Utilities.BaseClass;
import com.Utilities.Utility;

public class TC03_AddCustomer extends BaseClass {

	@Test
	public void addNewCustomer() throws InterruptedException {
		LoginPageObjects lpo = new LoginPageObjects(getWebDriver());
		
		
		lpo.setUserID(userID);
		logger.info("User ID entered");
		lpo.setPassword(password);
		logger.info("Password entered");
		lpo.clickLoginButton();
		logger.info("Login button clicked");

		Thread.sleep(3000);

		AddCustomerPageObjects cpo = new AddCustomerPageObjects(getWebDriver());
		cpo.clickAddNewCustomer();
		logger.info("Add New Customer link clicked");
		cpo.custName("Kevin");
		logger.info("Customer Name entered");
		cpo.custgender("male");
		logger.info("Gender selected");
		cpo.custdob("01", "01", "1990");
		logger.info("Birthdate entered");
		Thread.sleep(3000);
		cpo.custaddress("INDIA");
		logger.info("Address entered");
		cpo.custcity("AHMEDABAD");
		logger.info("City entered");
		cpo.custstate("GUJARAT");
		logger.info("State entered");
		cpo.custpinno("123456");
		logger.info("Pincoed entered");
		cpo.custtelephoneno("9876543210");
		logger.info("Phone number entered");
		String email = Utility.randomString() + "@yopmail.com";
		cpo.custemailid(email);
		logger.info("Email ID entered");
		cpo.custpassword("password");
		logger.info("Password entered");
		cpo.custsubmit();
		logger.info("Customer detail submitted");
		Thread.sleep(3000);

		boolean res = getWebDriver().getPageSource().contains("Customer Registered Successfully!!!");
		if (res == true) {
			logger.info("Test Case passed");
			Assert.assertTrue(true);
		} else {
			logger.info("Test Case failed");
			Assert.assertTrue(false);
		}

	}

}
