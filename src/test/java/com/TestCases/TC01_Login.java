package com.TestCases;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.PageObjects.LoginPageObjects;
import com.Utilities.BaseClass;

public class TC01_Login extends BaseClass {

	List<String> messages = new ArrayList<String>();
	String dispMessage = "";
	LoginPageObjects lp;

	//groups={"c1.verifyConfig"}
	@Test(groups = {"TC01.skip"})
	public void loginTest() {

		lp = new LoginPageObjects(getWebDriver());

		logger.info("Enter User ID");
		lp.setUserID(userID);

		logger.info("Enter Password");
		lp.setPassword(password);

		logger.info("Click on Login button");
		lp.clickLoginButton();

		logger.info("Verify Page Title");
		logger.info(((RemoteWebDriver) getWebDriver()).getCapabilities().getBrowserName().toUpperCase());
		if (!getWebDriver().getTitle().equals("Guru99 Bank Manager HomePage")) {

			logger.info("User is not able to signIn due to some issue " + getWebDriver().getCurrentUrl());
			messages.add(
					"User is not able to signIn due to some issue " + " url of page " + getWebDriver().getCurrentUrl());
		}

		if (messages.size() > 0)

		{
			for (String msg : messages) {
				dispMessage = dispMessage + msg + "\n";
			}
		}
		AssertJUnit.assertTrue(dispMessage, dispMessage == "");

	}

}
