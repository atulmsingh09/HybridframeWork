package com.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObjects {

	WebDriver ldriver;

	public LoginPageObjects(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(css = "input[type=text][name='uid']")
	WebElement txt_userID;

	@FindBy(css = "input[type=password][name='password']")
	WebElement txt_Password;

	@FindBy(css = "input[type=submit][name='btnLogin']")
	WebElement btn_Login;

	@FindBy(css = "a[href='Logout.php']")
	WebElement lnk_Logout;

	public void setUserID(String userID) {
		txt_userID.clear();
		txt_userID.sendKeys(userID);
	}

	public void setPassword(String password) {
		txt_Password.clear();
		txt_Password.sendKeys(password);
	}

	public void clickLoginButton() {
		btn_Login.click();
	}

	public void clickLogoutLink() {
		lnk_Logout.click();
	}

}
