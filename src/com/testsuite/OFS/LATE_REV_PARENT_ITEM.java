package com.testsuite.OFS;

import java.util.ArrayList;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.BaseSetup;
import com.data.testdata.HomeData;
import com.datamanager.ConfigManager;
import com.datamanager.ExcelManagerFillo;
import com.page.module.CommonFunctionalities;
import com.page.module.HomePage;
import com.page.module.OFSHomePage;
import com.page.module.OFSOrderPage;

public class LATE_REV_PARENT_ITEM extends BaseSetup {
	ConfigManager sys;
	HomeData homeData;
	HomePage homePage;
	OFSHomePage OFSHomePage;
	OFSOrderPage OFSOrderPage;
	ExcelManagerFillo ExcelManagerFillo = new ExcelManagerFillo("Data\\OrderDownload.xlsx");

	@BeforeMethod(alwaysRun = true)
	public void BaseClassSetUp() {
		getDriver().manage().deleteAllCookies();
		sys = new ConfigManager();
		homePage = new HomePage(getDriver());
		homeData = new HomeData();
		OFSHomePage = new OFSHomePage(getDriver());
		OFSOrderPage = new OFSOrderPage(getDriver());
	}

	@Test(dataProvider = "LATE_REV_PARENT_ITEM")
	public void UserAccessCreation(String Region, String Env, String Order, String tag) throws Exception {
		homePage.launchURL(homeData.OFS_URL);
		OFSHomePage = (com.page.module.OFSHomePage) homePage.selectUrl1(Region, Env);
		OFSOrderPage = OFSHomePage.SearchOrder(Order);
		OFSOrderPage.BomResponcXML();
		String url = OFSHomePage.getCurrentURL();
		String[] url1 = url.split("://");
		System.out.println(url1[1]);
//		url = url + "view-source:";
		OFSHomePage.navigateToURLandRetrivePageLoadTime("view-source:" + url1[1], LONGWAIT);
		OFSOrderPage.GetTags(Order);

	}

	@DataProvider(name = "LATE_REV_PARENT_ITEM")
	public Object[][] TT_L1AllActivity(ITestContext context) {
		Object[][] products = ExcelManagerFillo.getExcelSheetData("LATE_REV_PARENT_ITEM");
		return products;
	}

}
