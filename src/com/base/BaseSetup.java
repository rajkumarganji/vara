package com.base;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.log4testng.Logger;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.datamanager.ConfigManager;
import com.testresults.UploadResultsToDB;
import com.utilities.ReportSetup;
import com.utilities.TimeOuts;
import com.utilities.UtilityMethods;

public class BaseSetup extends Thread implements TimeOuts  {
	WebDriver driver;
	private Logger log = Logger.getLogger(BaseSetup.class);
	ConfigManager sys = new ConfigManager();
//	BrowserProvider browserProvider = new BrowserProvider();
	private boolean isReportFolderCreated = true;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	@BeforeSuite
	public void beforeSuite() throws Exception {
		UtilityMethods.disableWarning();
		if (isReportFolderCreated) {
			UtilityMethods.clearTasks();
			ReportSetup.createFolderStructure();
//			resultToExcel.moveLatestToOld(oldSheet, latestSheet);
			isReportFolderCreated = false;
		}
		log.info("<h2>--------------------SuiteRunner Log-------------------------<h2>");
	}

//	@Parameters({ "OS", "browser" })
//	@BeforeClass
//	public void startReport(String OS, String browser) {
//		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Automation Reports/testReport.html");
//
//		// initialize ExtentReports and attach the HtmlReporter
//		extent = new ExtentReports();
//		extent.attachReporter(htmlReporter);
//
//		// To add system or environment info by using the setSystemInfo method.
//		extent.setSystemInfo("OS", OS);
//		extent.setSystemInfo("Browser", browser);
//
//		// configuration items to change the look and feel
//		// add content, manage tests etc
////		htmlReporter.config().setChartVisibilityOnOpen(true);
//		htmlReporter.config().setDocumentTitle("Extent Report Demo");
//		htmlReporter.config().setReportName("Test Report");
////		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
//		htmlReporter.config().setTheme(Theme.STANDARD);
//		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
//
//	}

	@BeforeMethod(alwaysRun = true)
	public void initializeBaseSetup(ITestContext context) {
//		initiateDriver("firefox", "68", "windows", "10");
//		initiateDriver("chrome", "64", "windows", "10");
		initiateDriver("ie", "11", "windows", "10");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void   initiateDriver(String browserType, String browserVersion, String OSName, @Optional String OSVersion) {

		log.info("Browser name present in config file :" + browserType);

		log.info("-----------------STARTED RUNNING SELENIUM TESTS ON LOCAL MACHINE------------------");
		setDriver(browserType);
	}

	private void setDriver(String browserName) {
//		driver = browserProvider.getBrowserInstance(browserName).init();
	}

	/**
	 * This method since added in "AfterClass" group and when this class is
	 * inherited from a TestSuite class, it will be called automatically
	 * 
	 * @throws Exception
	 */
	@AfterMethod(alwaysRun = true)
	public void getResult(ITestResult result) {
//		if (result.getStatus() == ITestResult.FAILURE) {
//			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));
//			test.fail(result.getThrowable());
//		} else if (result.getStatus() == ITestResult.SUCCESS) {
//			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));
//		} else {
//			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));
//			test.skip(result.getThrowable());
//		}
		if (driver != null) {
			driver.quit();
		}
	}

//	@AfterTest
//	public void tearDown() {
//		// to write or update test information to reporter
//		extent.flush();
//	}

	/**
	 * 
	 * This method adds Log file link to ReportNG report
	 * 
	 * @throws Exception
	 */
	@AfterSuite
	public void AddLogFileToReport() throws Exception {
		log.info("after suite");
		String sSeperator = UtilityMethods.getFileSeperator();
		String logFilePath = ".." + sSeperator + "Log.log";
		Reporter.log("<br>");
		Reporter.log("<a class=\"cbutton\" href=\"" + logFilePath + "\">Click to Open Log File</a>");
		String PageLoadTimeSummaryFilePath = ".." + sSeperator + "PageLoadTime_Summary.html";
		File f = new File(PageLoadTimeSummaryFilePath);
		if (f.exists()) {
			Reporter.log("<br>");
			Reporter.log("<a class=\"cbutton\" href=\"" + PageLoadTimeSummaryFilePath
					+ "\">Click to Open PageLoad Time Summary File</a>");
		}
		try {
			if (sys.getProperty("InsertResultsIntoDB").equalsIgnoreCase("true")) {
//				UploadResultsToDB.uploadResultsToDB();
			} else {
				log.info("Data base results not configure to upload");
			}
		} catch (Exception e) {
			log.info("Failed to upload results");
		}
	}

}
