package com.testscripts;

import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.BaseSetup;
import com.data.testdata.HomeData;
import com.datamanager.ConfigManager;
import com.datamanager.ExcelManagerFillo;
import com.page.module.BOSSHomePage;
import com.page.module.HomePage;
import com.page.module.OFSHomePage;

public class OFSUpdateUserAccess extends BaseSetup {
	ConfigManager sys;
	HomeData homeData;
	HomePage homePage;
	OFSHomePage OFSHomePage;
	BOSSHomePage BOSSHomePage;
	ExcelManagerFillo ExcelManagerFillo = new ExcelManagerFillo("Data\\OrderDownload.xlsx");
	ExcelManagerFillo ExcelManagerFillo1 = new ExcelManagerFillo("Data\\OrderDownload.xlsx");

	@BeforeMethod(alwaysRun = true)
	public void BaseClassSetUp() {
		sys = new ConfigManager();
		homePage = new HomePage(getDriver());
		homeData = new HomeData();
		OFSHomePage = new OFSHomePage(getDriver());
	}

	@DataProvider(name = "UserAccess")
	public Object[][] TestData(ITestContext context) throws Exception {
		Object[][] data = ExcelManagerFillo.getExcelSheetData("OFSUpdateUserAccess");
		return data;
	}

	@DataProvider(name = "UserAccess1")
	public Object[][] TestData1(ITestContext context) throws Exception {
		Object[][] data = ExcelManagerFillo1.getExcelSheetData("BOSSUpdateUserAccess");
		return data;
	}

	@Test(dataProvider = "UserAccess")
	public void UserAccessCreation(String Region, String Env, String EmailAddress, String WindowsAuthenticationID,
			String Manager, String UserType) throws Exception {
		homePage.launchURL(homeData.OFS_URL);
		OFSHomePage.clickUserAccess1();
		OFSHomePage.AddNewUser(EmailAddress, Manager, WindowsAuthenticationID, UserType);
	}

	@Test(dataProvider = "UserAccess1")
	public void UserAccess1(String Region, String Env, String EmailAddress, String WindowsAuthenticationID,
			String Manager, String UserType, String Months) throws Exception {
		homePage.launchURL(homeData.BOSS_URL);
		BOSSHomePage = (com.page.module.BOSSHomePage) homePage.SelectBOSSURL(Region, Env);
		BOSSHomePage.clickUserAdminitstration();
		BOSSHomePage.checkUsers(EmailAddress, WindowsAuthenticationID);
		BOSSHomePage.SelectRolese(UserType, EmailAddress, Months, WindowsAuthenticationID);
	}

}
