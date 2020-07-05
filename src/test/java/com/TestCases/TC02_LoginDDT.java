package com.TestCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.PageObjects.LoginPageObjects;
import com.Utilities.BaseClass;
import com.Utilities.ExcelUtils;

public class TC02_LoginDDT extends BaseClass {

	List<String> messages = new ArrayList<String>();
	String dispMessage = "";
	LoginPageObjects lp;

	@Test(dataProvider = "LoginData", dependsOnGroups = "TC01.skip")
	public void LoginDDTt(String username, String password) throws InterruptedException {
		System.out.println("aaaaaaaaaa");
		lp = new LoginPageObjects(getWebDriver());
		logger.info("Enter User ID");
		lp.setUserID(username);

		logger.info("Enter Password");
		lp.setPassword(password);

		logger.info("Click on Login button");
		lp.clickLoginButton();

		Thread.sleep(3000);

		if (isAlertPresent() == true) {
			getWebDriver().switchTo().alert().accept();
			getWebDriver().switchTo().defaultContent();

			logger.warn("Login Failed");
			Assert.assertTrue(false);
		} else {
			logger.info("Login passed");
			Assert.assertTrue(true);
			lp.clickLogoutLink();
			Thread.sleep(3000);

			getWebDriver().switchTo().alert().accept();// close logout alert
			getWebDriver().switchTo().defaultContent();
		}

		/*
		 * logger.info("Verify Page Title"); logger.info(((RemoteWebDriver)
		 * getWebDriver()).getCapabilities().getBrowserName().toUpperCase()); if
		 * (!getWebDriver().getTitle().equals("Guru99 Bank Manager HomePage")) {
		 * 
		 * logger.info("User is not able to signIn due to some issue " +
		 * getWebDriver().getCurrentUrl()); messages.add(
		 * "User is not able to signIn due to some issue " + " url of page " +
		 * getWebDriver().getCurrentUrl()); }
		 */
		if (messages.size() > 0)

		{
			for (String msg : messages) {
				dispMessage = dispMessage + msg + "\n";
			}
		}
		AssertJUnit.assertTrue(dispMessage, dispMessage == "");

	}

	public boolean isAlertPresent() { // user defind function to check alert present or not
		try {
			getWebDriver().switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	//@Test(dataProvider = "RegistrationDetail")
	public void test(String FirstName, String LastName, String ContactNumber, String address) {
		System.out.println("FirstName: " + FirstName);
		System.out.println("Last Name" + LastName);
		System.out.println("Contact Number " + ContactNumber);
		System.out.println("Address " + address);
	}

	@DataProvider(name = "LoginData")
	String[][] getData() throws IOException {
		String path = System.getProperty("user.dir") + "\\src\\test\\java\\com\\testData\\LoginData.xlsx";
		int rowNum = ExcelUtils.getRowCount(path, "Sheet1");
		int colcount = ExcelUtils.getCellCount(path, "Sheet1", 1);

		String logindata[][] = new String[rowNum][colcount];

		for (int i = 1; i <= rowNum; i++) {
			for (int j = 0; j < colcount; j++) {
				logindata[i - 1][j] = ExcelUtils.getCellData(path, "Sheet1", i, j);

			}
		}

		return logindata;
	}

	/* @DataProvider(name = "RegistrationDetail") // Use this approach to check any feature with multiple data in single
												// run
	String[][] getRegistrationData() throws IOException {
		String path = System.getProperty("user.dir") + "\\src\\test\\java\\com\\testData\\LoginData.xlsx";
		int rowNum = ExcelUtils.getRowCount(path, "Sheet2");
		int colcount = ExcelUtils.getCellCount(path, "Sheet2", 1);

		String RegistrationData[][] = new String[rowNum][colcount];

		for (int i = 1; i <= rowNum; i++) {
			for (int j = 0; j < colcount; j++) {
				RegistrationData[i - 1][j] = ExcelUtils.getCellData(path, "Sheet2", i, j);

			}
		}

		return RegistrationData;
	} */

}
