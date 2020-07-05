package com.Utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportTestListener extends BaseClass implements ITestListener {
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;

	@Override
	public void onTestStart(ITestResult result) {
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int date = cal.get(Calendar.DATE);
		String reportPath = System.getProperty("user.dir") + "//htmlReport//" + year + "//" + (month + 1) + "//" + date
				+ "//";
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
		String repName = "Test-Report-" + timeStamp + ".html";
		htmlReporter = new ExtentHtmlReporter(reportPath + repName);// specify
																	// location
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
		htmlReporter.config().setTheme(Theme.DARK);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		logger = extent.createTest(result.getName()); // create new entry in th report
		logger.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN)); // send the passed
	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			logger = extent.createTest(result.getName()); // create new entry in th report
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED)); // send the passed
																									// information
			logger.addScreenCaptureFromPath(Utility.captureScreenshotUtility(result.getName()));
			logger.log(Status.FAIL, result.getName() + " FAIL with error " + result.getThrowable());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger = extent.createTest(result.getName()); // create new entry in th report
		logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.ORANGE));
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}
}
