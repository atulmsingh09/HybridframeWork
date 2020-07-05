package com.Utilities;

//Listener class used to generate Extent reports

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting1 extends TestListenerAdapter {
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;

	Calendar cal = new GregorianCalendar();
	int month = cal.get(Calendar.MONTH);
	int year = cal.get(Calendar.YEAR);
	int sec = cal.get(Calendar.SECOND);
	int min = cal.get(Calendar.MINUTE);
	int date = cal.get(Calendar.DATE);
	int day = cal.get(Calendar.HOUR_OF_DAY);

	public void onStart(ITestContext testContext) {
		String reportPath = System.getProperty("user.dir") + "//htmlReport//" + year + "//" + (month + 1) + "//" + date
				+ "//";
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
		String repName = "Test-Report-" + timeStamp + ".html";

//		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "//htmlReport" + repName);// specify
		htmlReporter = new ExtentHtmlReporter(reportPath + repName);																									// location
																											// of the
																											// report
		htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");

		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("user", "Atul");

		htmlReporter.config().setDocumentTitle("InetBanking Test Project"); // Tile of report
		htmlReporter.config().setReportName("Functional Test Automation Report"); // name of the report
		// htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); //
		// location of the chart
		htmlReporter.config().setTheme(Theme.DARK);
	}

	public void onTestSuccess(ITestResult tr) {
		logger = extent.createTest(tr.getName()); // create new entry in th report
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN)); // send the passed
	}

	public void onTestFailure(ITestResult tr) {
		logger = extent.createTest(tr.getName()); // create new entry in th report
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED)); // send the passed information
		logger.log(Status.FAIL, tr.getName() + " FAIL with error " + tr.getThrowable()); // to the report with GREEN
																							// color highlighted

		// screenshotpath = System.getProperty("user.dir") + "//Screenshots//" + year +
		// "//" + (month + 1) + "//"+ date + "//" + methodName + "_" + year + "_" + date
		// + "_" + (month + 1) + "_" + day + "_" + min+ "_" + sec + "_" + browserName +
		// ".jpeg";

		// String screenshotPath = System.getProperty("user.dir") + "//Screenshots//" +
		// tr.getName() + ".png";

		// String screenshotpath1 = Utility.screenshotpath;

		new BaseClass();
		// File f = new File(screenshotPath);
		File scrFile = ((TakesScreenshot) BaseClass.getWebDriver()).getScreenshotAs(OutputType.FILE);
		String screenshotPath = System.getProperty("user.dir") + "//Screenshots//" + year + "//" + (month + 1) + "//"
				+ date + "//" + tr.getName() + "_" + year + "_" + date + "_" + (month + 1) + "_" + day + "_" + min + "_"
				+ sec + "_" + ".png";
		try {
			FileUtils.copyFile(scrFile, new File(screenshotPath));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (scrFile.exists()) {
			try {
				logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void onTestSkipped(ITestResult tr) {
		logger = extent.createTest(tr.getName()); // create new entry in th report
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
	}
}
