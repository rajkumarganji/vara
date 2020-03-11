package com.page.module;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.log4testng.Logger;

import com.datamanager.ConfigManager;
import com.page.locators.OFSOrderPageLocators;
import com.selenium.Dynamic;

public class OFSOrderPage extends CommonFunctionalities implements OFSOrderPageLocators {

	ConfigManager appData = new ConfigManager("Sys");

	private WebDriver driver;
	Logger log = Logger.getLogger(getClass());

	public OFSOrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void PurdgeOrder(String Purge) {
		waitForPageToLoad();
		if (!isElementPresent(Work_Order_SENT_state)) {
			if (Purge.equals("Purge&Reporcess")) {
				safeClick(Purge_Prod_Orders, LONGWAIT);
				acceptAlert();
			}
			waitForPageToLoad();
			waitForSecs(3);
			safeClick(Reprocess_Message, LONGWAIT);
			waitForPageToLoad();
			waitForSecs(4);
		}
	}

	public void BomResponcXML() {
		safeClick(BOMRESPONSE, LONGWAIT);
		waitForSecs(3);
		waitForPageToLoad();
	}

	public void GetTags(String Order) {
		By COMP_ITEMby = Dynamic.getNewLocator(COMP_ITEM, "<LATE_REV>X");
		By PARENT_ITEMby = Dynamic.getNewLocator(PARENT_ITEM, "<LATE_REV>X");
		System.out.println("----------" + Order + "----------");
		List<WebElement> list = LocatorWebElements(COMP_ITEMby);
		List<WebElement> list1 = LocatorWebElements(PARENT_ITEMby);
		System.out.println(list.size() + "===" + list1.size());
		for (int index = 0; index < list.size(); index++) {
			String PARENT_ITEMString = safeGetText(list1.get(index), LONGWAIT);
			String COMP_ITEMString = safeGetText(list.get(index), LONGWAIT);
			String[] m = PARENT_ITEMString.split("PARENT_ITEM>");
			String mm[] = m[1].split("<");
			String[] n = COMP_ITEMString.split("COMP_ITEM>");
			String nn[] = n[1].split("<");
			System.out.println(mm[0] + "==>" + nn[0]);
		}
	}
}
