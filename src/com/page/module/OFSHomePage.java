package com.page.module;

import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import com.datamanager.ConfigManager;
import com.page.locators.OFSHomeLocators;
import com.selenium.Dynamic;

public class OFSHomePage extends CommonFunctionalities implements OFSHomeLocators {

	ConfigManager appData = new ConfigManager("Sys");
	OFSOrderPage OFsOrderPage;

	private WebDriver driver;
	Logger log = Logger.getLogger(getClass());

	public OFSHomePage(WebDriver driver) {
		super(driver);
		OFsOrderPage = new OFSOrderPage(driver);
		this.driver = driver;
	}

	public void clickUserAccess1() throws Exception {
		mouseHover(ADMIN_MAIN, LONGWAIT);
		safeJavaScriptClick(USER_MAIN, LONGWAIT);
//		NavigateToURL(USER_MAIN_A, null, null);
	}

	public OFSOrderPage SearchOrder(String Order) throws Exception {
		waitForPageToLoad();
		safeType(Search_Order_input, Order, LONGWAIT);
		safeClick(Search_Order_Button, LONGWAIT);
//		safeClick(Search_Order_Select_Order, LONGWAIT);
		safeClick(Search_Order_View_Log_History, LONGWAIT);
		waitForPageToLoad();
		switchToWindow(1);
		waitForPageToLoad();
		if (isElementPresent(oldLogHistory)) {
			waitForSecs(2);
			safeActionsClick(oldLogHistory, LONGWAIT);
		}
		return OFsOrderPage;
	}

	public void clickUserAccess() throws Exception {
		waitForPageToLoad();
		waitForSecs(5);
		switchToWindow(1);
		waitForSecs(5);
		mouseHover(ADMIN_MAIN, LONGWAIT);
		safeClick(USER_MAIN, LONGWAIT);
//		mouseHoverAndSelectOption(ADMIN_MAIN, USER_MAIN, LONGWAIT);
		waitForPageToLoad();
	}

	public void AddNewUser(String EmailAddress, String Manager, String WindowsAuthenticationID, String UserType)
			throws Exception {
		String[] email = EmailAddress.split("@");
		String Name = email[0];
		String[] Fname = Name.split("_");
		String FirstName = Fname[0];
		String LastName = Fname[1];
//		WindowsAuthenticationID = WindowsAuthenticationID + "\\" + Name;
		String Search_name = LastName + ", " + FirstName;
		int i = getLocatorCount(Dynamic.getNewLocator(USER_search_Dynamic, Search_name));
		if (i >= 1) {
			String k = safeGetText(Dynamic.getNewLocator(USER_search_Dynamic1, Search_name), LONGWAIT);
			safeSelectOptionInDropDownByVisibleText(USER_search, k, LONGWAIT);
			waitForPageToLoad(40);
			waitUntilClickable(Deactivate_User, VERYLONGWAIT);
//			safeJavaScriptDblClick(Dynamic.getNewLocator(USER_search_Dynamic1, k), LONGWAIT);
//			((JavascriptExecutor) driver).executeScript("arguments[0].doubleClick();",
//					driver.findElement(Dynamic.getNewLocator(USER_search_Dynamic1, k)));
//			((JavascriptExecutor) driver).executeScript("var evt = document.createEvent('MouseEvents');"
//					+ "evt.initMouseEvent('dblclick',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"
//					+ "arguments[0].dispatchEvent(evt);",
//					driver.findElement(Dynamic.getNewLocator(USER_search_Dynamic1, k)));
//			Actions action = new Actions(driver);
//			action.moveToElement(driver.findElement(Dynamic.getNewLocator(USER_search_Dynamic1, k))).doubleClick()
//					.build().perform();
////			mouseHover(Dynamic.getNewLocator(USER_search_Dynamic1, Search_name), LONGWAIT);
//			safeJavaScriptClick(Dynamic.getNewLocator(USER_search_Dynamic1, Search_name), LONGWAIT);
		}
//		safeSelectOptionInDropDownByVisibleText(USER_search,Search_name,LONGWAIT);
		else {
			safeJavaScriptClick(Add_New_User, LONGWAIT);
		}
		waitForPageToLoad();
		safeJavaScriptType(First_Name, FirstName, LONGWAIT);
		safeJavaScriptType(Last_Name, LastName, LONGWAIT);
		safeJavaScriptType(Email, EmailAddress, LONGWAIT);
		safeJavaScriptType(Auth_Id, WindowsAuthenticationID, LONGWAIT);
		safeType(Auth_Id, "\\" + Name, LONGWAIT);
		safeSelectOptionInDropDownByVisibleText(Select_Maneger, Manager, LONGWAIT);
		selectRoles(UserType);
//		
//		safeJavaScriptClick(Add_Role_Touser, LONGWAIT);
//		waitForPageToLoad();
//		Actions action = new Actions(driver);
//		action.keyDown(Keys.ENTER);
//		action.build().perform();
		if (i >= 1)
			driver.findElement(UpdateUser).sendKeys(Keys.ENTER);
		else
			driver.findElement(CreateUser).sendKeys(Keys.ENTER);
//		safeActionsClick(CreateUser, LONGWAIT);
		waitForPageToLoad();
		waitForSecs(10);
		waitForPageToLoad();
	}

	// Order Lookup,Purge Access,Reassign,Reprocess Access,User Hold,Log
	// Messages,MES Engineering HOLD
	private void selectRoles(String UserType) {
//		Actions action = new Actions(driver);
//		action.keyDown(Keys.CONTROL);
		List<WebElement> list = LocatorWebElements(Role_Name_All);
		int i = 0;
		String[] a1 = new String[list.size()];
		for (WebElement el : list) {
			a1[i] = safeGetText(el, VERYSHORTWAIT);
			i++;
		}
		List<String> kk = new ArrayList<String>();
//		String[] kk = new String[a1.length];
		String[] a = new String[] { "Order Lookup", "Purge Access", "Reassign", "Reprocess Access", "User Hold",
				"Log Messages", "MES Engineering HOLD", "XML", "View log histroy", "MESHold", "Order Lookup Ext",
				"Hold Release", "Purge&Reprocess User" };
		for (int ii = 0; ii < a.length; ii++) {
			for (int j = 0; j < a1.length; j++) {
				if (a[ii].equalsIgnoreCase(a1[j])) {
					System.out.println(a[ii]);
					kk.add(a[ii]);
				}
			}
		}
		if (UserType.equalsIgnoreCase("ReadOnly")) {
			for (String string : kk) {
				By by = Dynamic.getNewLocator(Role_Name_select_Dynamic, string);
//				safeSelectOptionInDropDown(Role_Name_select, string, LONGWAIT);
//				safeClick(by, LONGWAIT);
				if (isElementPresent(by)) {
					safeSelectOptionInDropDownByVisibleText(Role_Name_select, string, LONGWAIT);
					safeJavaScriptClick(Add_Role_Touser, LONGWAIT);
				}
				waitForPageToLoad();
			}
		} else {
			for (String string : a1) {
				By by = Dynamic.getNewLocator(Role_Name_select_Dynamic, string);
				if (isElementPresent(by)) {
					safeSelectOptionInDropDownByVisibleText(Role_Name_select, string, VERYSHORTWAIT);
					safeJavaScriptClick(Add_Role_Touser, VERYSHORTWAIT);
				}
				waitForPageToLoad();
			}
		}
//		action.keyUp(Keys.CONTROL);
//		action.build().perform();
	}

	public void compare(String first_name, String email, String region, String address_Line_1, String state_desc,
			String phone_number1, String phone_number2String) {
		String name = safeGetText(Dynamic.getNewLocator(tagValue, "ContactName"), LONGWAIT);
		String mail = safeGetText(Dynamic.getNewLocator(tagValue, "ContactEmail"), LONGWAIT);
		String phone = safeGetText(Dynamic.getNewLocator(tagValue, "ContactPhone"), LONGWAIT);
		String state = safeGetText(Dynamic.getNewLocator(tagValue, "State"), LONGWAIT);
		String add = safeGetText(Dynamic.getNewLocator(tagValue, "Line1"), LONGWAIT);
		if (name.contains(first_name) && mail.contains(email) && state.contains(state_desc)
				&& add.contains(address_Line_1)) {
			log.info("Succes fully verified");
			System.out.println("Succes fully verified");
		} else {
			log.info("Not Equel not");
			Assert.fail("Elements  And Tages  values are not equal");
		}
	}

}
