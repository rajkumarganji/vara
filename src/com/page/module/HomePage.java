package com.page.module;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.common.usermodel.Hyperlink;
import org.apache.poi.hssf.record.RightMarginRecord;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.log4testng.Logger;

import com.datamanager.ExcelManagerFillo;
import com.page.locators.CommonOFSLocators;
import com.selenium.Dynamic;
import com.utilities.UtilityMethods;

public class HomePage extends CommonFunctionalities implements CommonOFSLocators {

	private WebDriver driver;
	Logger log = Logger.getLogger(getClass());
	OFSHomePage OFSHomePage;
	UtilityMethods um = new UtilityMethods();

	public HomePage(WebDriver driver) {
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

	public Map<String, String> selectRegionENV(String path, String sheetName) {
		Map<String, String> map = null;
		try {
			ExcelManagerFillo EM = new ExcelManagerFillo(path);
			List<String> headers = EM.readMetaDataFromXlxFile(path, sheetName);
			map = getDataFromShet(headers, path, sheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
//
//	public Object SelectURL(String region, String source, String dest, String srcOrdernumber, String dstOrdernumber,
//			String file, String DestinationSheet) throws Exception {
//		String k = null;
//		String l = null;
//		if (!source.equalsIgnoreCase("prod"))
//			k = source.substring(2);
//		if (!dest.equalsIgnoreCase("prod"))
//			l = dest.substring(2);
//		if (dest.equalsIgnoreCase("prod"))
//			l = "5";
//		if (source.equalsIgnoreCase("prod")) {
//			k = "5";
//		}
//		waitForPageToLoad();
//		By by = Dynamic.getNewLocator(REGION, region, k);
//		safeActionsClick(by, LONGWAIT);
//		searchOrder(srcOrdernumber, 1);
//		switchToWindow(0);
//		waitForPageToLoad();
//		By by1 = Dynamic.getNewLocator(REGION, region, l);
//		waitForSecs(5);
//		safeActionsClick(by1, LONGWAIT);
//		searchOrder(dstOrdernumber, 3);
//		waitForPageToLoad();
//		switchToWindow(2);
//		waitForPageToLoad();
//		if(isElementPresent(NavigatetoLegacy))
//		safeActionsClick(NavigatetoLegacy, LONGWAIT);
//		waitForPageToLoad();
//		List<WebElement> ele1 = LocatorWebElements(gvExtendedView_A);
//		Set<String> m1 = getTestFromListWebelements1(ele1);
//		switchToWindow(4);
//		waitForPageToLoad();
//		if(isElementPresent(NavigatetoLegacy))
//		safeActionsClick(NavigatetoLegacy, LONGWAIT);
//		waitForPageToLoad();
//		List<WebElement> ele2 = LocatorWebElements(gvExtendedView);
//		Set<String> m21 = getTestFromListWebelements1(ele2);
//		Set<String> set1 = new HashSet<String>(m21);
//		List<String> m2 = new ArrayList<>(set1);
//		List<WebElement> ele4 = LocatorWebElements(gvExtendedView_A);
//		Set<String> m31 = getTestFromListWebelements(ele4);
//		Set<String> set = new HashSet<String>(m31);
//		List<String> m3 = new ArrayList<>(set);
////		List<String> ele5 = um.compareTowLists(m1, m2);
////		m3.removeAll(ele5);
//		String all[] = new String[m3.size()];
//		int i = 0;
//		for (String string : m3) {
//			all[i] = string;
//			i++;
//		}
//		ExcelManagerFillo EM = new ExcelManagerFillo(file);
////		EM.writeToExel(file,DestinationSheet, m3,0,false);
//		System.out.println("====" + m3.size() + "====");
//		List<String> fina = new ArrayList<String>();
//		for (String string : m3) {
//			By by11 = Dynamic.getNewLocator(gvExtendedView_Dynamic, string.substring(0, 25));
//			fina.add(getAttributeValue(by11, "href", LONGWAIT));
//		}
//		EM.writeToExel(file, DestinationSheet, m3, 0, fina, 1);
//		return new OFSHomePage(driver);
//	}

	
	public Object SelectURL(String region, String source, String dest, String srcOrdernumber, String dstOrdernumber,
			String file, String DestinationSheet) throws Exception {
		String k = null;
		String l = null;
		if (!source.equalsIgnoreCase("prod"))
			k = source.substring(2);
		if (!dest.equalsIgnoreCase("prod"))
			l = dest.substring(2);
		if (dest.equalsIgnoreCase("prod"))
			l = "5";
		if (source.equalsIgnoreCase("prod")) {
			k = "5";
		}
		waitForPageToLoad();
		By by = Dynamic.getNewLocator(REGION, region, k);
		safeActionsClick(by, LONGWAIT);
		searchOrder(srcOrdernumber, 1);
		switchToWindow(0);
		waitForPageToLoad();
		By by1 = Dynamic.getNewLocator(REGION, region, l);
		waitForSecs(5);
		safeActionsClick(by1, LONGWAIT);
		searchOrder(dstOrdernumber, 3);
		waitForPageToLoad();
		switchToWindow(2);
		waitForPageToLoad();
		if(isElementPresent(NavigatetoLegacy))
		safeActionsClick(NavigatetoLegacy, LONGWAIT);
		waitForPageToLoad();
		List<WebElement> ele1 = LocatorWebElements(gvExtendedView);
		List<String> m1 = getTestFromListWebelements(ele1);
		switchToWindow(4);
		waitForPageToLoad();
		if(isElementPresent(NavigatetoLegacy))
		safeActionsClick(NavigatetoLegacy, LONGWAIT);
		waitForPageToLoad();
		List<WebElement> ele2 = LocatorWebElements(gvExtendedView);
		List<String> m21 = getTestFromListWebelements(ele2);
		Set<String> set1 = new HashSet<String>(m21);
		List<String> m2 = new ArrayList<>(set1);
		List<WebElement> ele4 = LocatorWebElements(gvExtendedView_A);
		List<String> m31 = getTestFromListWebelements(ele4);
		Set<String> set = new HashSet<String>(m31);
		List<String> m3 = new ArrayList<>(set);
		List<String> ele5 = um.compareTowLists(m1, m2);
		m3.removeAll(ele5);
		String all[] = new String[m3.size()];
		int i = 0;
		for (String string : m3) {
			all[i] = string;
			i++;
		}
		ExcelManagerFillo EM = new ExcelManagerFillo(file);
//		EM.writeToExel(file,DestinationSheet, m3,0,false);
		System.out.println("====" + m3.size() + "====");
		List<String> fina = new ArrayList<String>();
		for (String string : m3) {
			By by11 = Dynamic.getNewLocator(gvExtendedView_Dynamic, string.substring(0, 25));
			fina.add(getAttributeValue(by11, "href", LONGWAIT));
		}
		EM.writeToExel(file, DestinationSheet, m3, 0, fina, 1);
		return new OFSHomePage(driver);
	}

	public Object SelectURL(String region, String env, String Ordernumber) {
		String k = null;
		String l = null;
		if (!env.equalsIgnoreCase("prod"))
			k = env.substring(2);
		if (env.equalsIgnoreCase("prod"))
			l = "5";
		waitForPageToLoad();
		By by = Dynamic.getNewLocator(REGION, region, k);
		safeActionsClick(by, LONGWAIT);
		searchOrder(Ordernumber, 1);
		switchToWindow(2);
		safeClick(Incoming_XML, LONGWAIT);
		waitForSecs(10);
		safeSelectOptionInDropDownByValue(Select_page_count, "All", LONGWAIT);
		waitForSecs(10);
		safeClick(XML_STATUS_GPT, LONGWAIT);
		switchToWindow(3);
		return new OFSHomePage(driver);
	}

	public Object SelectBOSSURL(String region, String env) throws Exception {
		String k = null;
		String l = null;
		if (region.equalsIgnoreCase("DAO"))
			k = "8";
		else if (region.equalsIgnoreCase("EMEA"))
			k = "9";
		else if (region.equalsIgnoreCase("APJ"))
			k = "10";
		if (env.equalsIgnoreCase("GE1"))
			l = "6";
		else if (env.equalsIgnoreCase("GE2"))
			l = "7";
		else if (env.equalsIgnoreCase("GE3"))
			l = "8";
		else if (env.equalsIgnoreCase("GE4"))
			l = "9";
		By by = Dynamic.getNewLocator(BOSSREGION, l, k);
		navigateToURLandRetrivePageLoadTime(safeGetAttribute(by, "href", LONGWAIT), LONGWAIT);
//		safeActionsClick(by, LONGWAIT);
//		switchToWindow(1);
		waitForPageToLoad();
		return new BOSSHomePage(driver);
	}

	public Set<String> getTestFromListWebelements1(List<WebElement> lsit) {
		Set<String> name = new HashSet<String>();
		System.out.println(lsit.size());
		String m = null;
		String[] kk = null;
		for (WebElement webElement : lsit) {
			m = webElement.getText();
			if (m.contains(")")) {
				kk = m.split("\\)");
				name.add(kk[1]);
			} else {
				name.add(webElement.getText());
			}
		}
		return name;
	}
	public List<String> getTestFromListWebelements(List<WebElement> lsit) {
		List<String> name = new ArrayList<String>();
		System.out.println(lsit.size());
		for (WebElement webElement : lsit) {
			name.add(webElement.getText());
		}
		return name;
	}
	public void searchOrder(String ordernumber, int window) {
		switchToWindow(window);
		safeType(Order_Number, ordernumber, LONGWAIT);
		safeActionsClick(Order_Search, LONGWAIT);
		waitForPageToLoad();
		safeClick(View_Log_History, LONGWAIT);
	}

	public Object selectUrl1(String region, String env) throws Exception {
		waitForPageToLoad();
		if (env.equalsIgnoreCase("GE1")) {
			NavigateToURL(REGION_A, region, "1");
		} else if (env.equalsIgnoreCase("GE2")) {
			NavigateToURL(REGION_A, region, "2");
		} else if (env.equalsIgnoreCase("GE3")) {
			NavigateToURL(REGION_A, region, "3");
		} else if (env.equalsIgnoreCase("GE4")) {
			NavigateToURL(REGION_A, region, "4");
		} else if (env.equalsIgnoreCase("PROD")) {
			NavigateToURL(REGION_A, region, "5");
		}
		return new OFSHomePage(driver);
	}

	public Object SelectURL(String region, String env) throws Exception {
		waitForPageToLoad();
		if (env.equalsIgnoreCase("GE1")) {
			By by = Dynamic.getNewLocator(REGION, region, "1");
//			navigateToURLandRetrivePageLoadTime(
//					safeGetAttribute(Dynamic.getNewLocator(REGION_A, region, "1"), "href", LONGWAIT), VERYLONGWAIT);
//			Actions action = new Actions(driver);
//			action.keyDown(Keys.CONTROL);
			waitForSecs(5);
			safeActionsClick(by, LONGWAIT);
//			action.keyUp(Keys.CONTROL);
//			action.build().perform();
		} else if (env.equalsIgnoreCase("GE2")) {
			By by = Dynamic.getNewLocator(REGION, region, "2");
//			navigateToURLandRetrivePageLoadTime(
//					safeGetAttribute(Dynamic.getNewLocator(REGION_A, region, "1"), "href", LONGWAIT), VERYLONGWAIT);
//			Actions action = new Actions(driver);
//			action.keyDown(Keys.CONTROL);
			waitForSecs(5);
			safeActionsClick(by, LONGWAIT);
//			action.keyUp(Keys.CONTROL);
//			action.build().perform();
		} else if (env.equalsIgnoreCase("GE3")) {
			By by = Dynamic.getNewLocator(REGION, region, "3");
//			navigateToURLandRetrivePageLoadTime(
//					safeGetAttribute(Dynamic.getNewLocator(REGION_A, region, "1"), "href", LONGWAIT), VERYLONGWAIT);
//			Actions action = new Actions(driver);
//			action.keyDown(Keys.CONTROL);
			waitForSecs(5);
			safeActionsClick(by, LONGWAIT);
//			action.keyUp(Keys.CONTROL);
//			action.build().perform();
		} else if (env.equalsIgnoreCase("GE4")) {
			By by = Dynamic.getNewLocator(REGION, region, "4");
//			navigateToURLandRetrivePageLoadTime(
//					safeGetAttribute(Dynamic.getNewLocator(REGION_A, region, "1"), "href", LONGWAIT), VERYLONGWAIT);
//			Actions action = new Actions(driver);
//			action.keyDown(Keys.CONTROL);
			waitForSecs(5);
			safeActionsClick(by, LONGWAIT);
//			action.keyUp(Keys.CONTROL);
//			action.build().perform();
		} else if (env.equalsIgnoreCase("PROD")) {
			By by = Dynamic.getNewLocator(REGION, region, "5");
//			navigateToURLandRetrivePageLoadTime(
//					safeGetAttribute(Dynamic.getNewLocator(REGION_A, region, "1"), "href", LONGWAIT), VERYLONGWAIT);
//			Actions action = new Actions(driver);
//			action.keyDown(Keys.CONTROL);
			waitForSecs(5);
			safeActionsClick(by, LONGWAIT);
//			action.keyUp(Keys.CONTROL);
//			action.build().perform();
		}
		waitForPageToLoad();
		return new OFSHomePage(driver);
	}

	public enum HomeReturnPage {
		OFSHomePage
	}

}
