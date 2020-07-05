package com.Utilities;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Utility extends BaseClass {

	// Code to create screenshot
	public static String screenshotpath;

	public static String captureScreenshotUtility(String methodName) {
		Capabilities cap = ((RemoteWebDriver) getWebDriver()).getCapabilities();
		String browserName = cap.getBrowserName().toLowerCase();
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int sec = cal.get(Calendar.SECOND);
		int min = cal.get(Calendar.MINUTE);
		int date = cal.get(Calendar.DATE);
		int day = cal.get(Calendar.HOUR_OF_DAY);

		File scrFile = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);
		try {
			screenshotpath = System.getProperty("user.dir") + "//Screenshots//" + year + "//" + (month + 1) + "//"
					+ date + "//" + methodName + "_" + year + "_" + date + "_" + (month + 1) + "_" + day + "_" + min
					+ "_" + sec + "_" + browserName + ".png";
			FileUtils.copyFile(scrFile, new File(screenshotpath));
		} catch (IOException e) {
			System.out.println("Exception while taking screenshot " + e.getMessage());
		}

		return screenshotpath;
	}

	// Code to create multiple threads
	static ReadConfig readconfig = new ReadConfig();

	static WebDriver create(String type) throws IllegalAccessException {
		WebDriver driver;

		switch (type) {
		case "Firefox":
			System.setProperty("webdriver.gecko.driver", readconfig.getGeckoPath());
			driver = new FirefoxDriver();
			break;
		case "Chrome":
			System.setProperty("webdriver.chrome.silentOutput", "true");
			System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());

			driver = new ChromeDriver();
			break;
		case "IE":
			System.setProperty("webdriver.ie.driver", readconfig.getIEPath());
			driver = new InternetExplorerDriver();
			break;
		default:
			throw new IllegalAccessException();
		}

		return driver;
	}

	// Code to genrate random string
	public static String randomString() {

		String genratedString = RandomStringUtils.randomAlphabetic(5);
		return genratedString;
	}

	// Code to genrate random number
	public static String randomNumber() {
		String generatedNumber = RandomStringUtils.randomNumeric(4);
		return generatedNumber;
	}

}
