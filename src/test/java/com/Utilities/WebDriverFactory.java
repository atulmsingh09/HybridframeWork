package com.Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebDriverFactory {
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

}
