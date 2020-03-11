package com.page.module;

import org.openqa.selenium.WebDriver;
import org.testng.log4testng.Logger;

import com.datamanager.ConfigManager;
import com.page.locators.ODTLocators;
import com.utilities.UtilityMethods;

public class ODTPage extends CommonFunctionalities implements ODTLocators {

	private WebDriver driver;
	Logger log = Logger.getLogger(getClass());

	public ODTPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void launchURL(String url) {
		try {
			navigateToURLandRetrivePageLoadTime(url, VERYLONGWAIT);
			waitForPageToLoad();
		} catch (Exception e) {
			log.info("Page navigation is failed due to" + UtilityMethods.getStackTrace());
			e.printStackTrace();
		}
	}

	public void GenerateXml(String region, String env, String order) throws Exception {
		safeSelectOptionInDropDownByValue(ddl_Region, region, LONGWAIT);
		safeSelectOptionInDropDownByValue(ddl_Environment, env, LONGWAIT);
		safeSelectOptionInDropDownByValue(ddl_MessageType, "WO ACK", LONGWAIT);
		safeType(txt_OrderNumber, order, LONGWAIT);
		safeSelectOptionInDropDownByValue(ddl_ResType, "Accept", LONGWAIT);
		safeClick(btn_generate, LONGWAIT);
		waitForPageToLoad();
		waitForSecs(4);
		safeClick(btn_pushtoMQ, LONGWAIT);
	}

	public void executeQuery(String order) {
		String CHANNEL_STATUS_CODE = "select CHANNEL_STATUS_CODE from ufd_base.work_order where sales_order_ref in (select sales_order_ref from ufd_base.sales_order where sales_order_id='"
				+ order + "')";
		String WO_TYPE = "select WO_TYPE from ufd_base.work_order where sales_order_ref in (select sales_order_ref from ufd_base.sales_order where sales_order_id='"
				+ order + "')";
//		if (WO_TYPE.equalsIgnoreCase("MAKE")&&CHANNEL_STATUS_CODE.equals(arg0)) {
			
//		}
	}

}
