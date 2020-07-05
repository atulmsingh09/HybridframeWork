package com.Utilities;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	static ReadConfig readconfig = new ReadConfig();

	public String baseUrl = readconfig.getApplicationURL();
	public String userID = readconfig.getUserID();
	public String password = readconfig.getPassword();
	public static Logger logger;
	// public static WebDriver driver;
	public static ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();

	@Parameters("browser")
	@BeforeClass
	public void setup(String browser) throws IllegalAccessException {
		logger = Logger.getLogger("ebanking");
		PropertyConfigurator.configure("log4j.properties");

		if (browser.equals("chrome")) {
			// System.setProperty("webdriver.chrome.silentOutput", "true");
			// System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());

			logger.info("Open chrome Browser");
			new Utility();
			WebDriver driver = Utility.create("Chrome");
			setWebdriver(driver);

			logger.info("Maximize browser");
			getWebDriver().manage().window().maximize();
		} else if (browser.equals("firefox")) {
			// System.setProperty("webdriver.gecko.driver", readconfig.getGeckoPath());
			logger.info("Open Firefox Browser");
			new Utility();
			WebDriver driver = Utility.create("Firefox");
			setWebdriver(driver);
			logger.info("Maximize browser");
			getWebDriver().manage().window().maximize();
		}

		else if (browser.equals("ie")) {
			// System.setProperty("webdriver.ie.driver", readconfig.getIEPath());
			logger.info("Open InternetExplorer Browser");

			new Utility();
			WebDriver driver = Utility.create("IE");
			setWebdriver(driver);

			logger.info("Maximize browser");
			getWebDriver().manage().window().maximize();
		}

		logger.info("Open banking url");
		getWebDriver().get(baseUrl);

	}

	public void setWebdriver(WebDriver driver) {
		dr.set(driver);
	}

	public static WebDriver getWebDriver() {
		return dr.get();
	}

	// @AfterMethod()
	public void captureScreenshot(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			// Utility.captureScreenshotUtility(result.getName().toString());
		}

	}

	@AfterClass
	public void tearUp() {
		getWebDriver().quit();

	}

	/*
	 * static WebDriver create(String type) throws IllegalAccessException {
	 * WebDriver driver;
	 * 
	 * switch (type) { case "Firefox": System.setProperty("webdriver.gecko.driver",
	 * readconfig.getGeckoPath()); driver = new FirefoxDriver(); break; case
	 * "Chrome": System.setProperty("webdriver.chrome.silentOutput", "true");
	 * System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
	 * 
	 * driver = new ChromeDriver(); break; case "IE":
	 * System.setProperty("webdriver.ie.driver", readconfig.getIEPath()); driver =
	 * new InternetExplorerDriver(); break; default: throw new
	 * IllegalAccessException(); }
	 * 
	 * return driver; }
	 */

}
