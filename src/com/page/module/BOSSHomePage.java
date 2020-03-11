package com.page.module;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.log4testng.Logger;

import com.datamanager.ConfigManager;
import com.page.locators.BOSSHomeLocators;
import com.selenium.Dynamic;

public class BOSSHomePage extends CommonFunctionalities implements BOSSHomeLocators {

	ConfigManager appData = new ConfigManager("Sys");

	private WebDriver driver;
	Logger log = Logger.getLogger(getClass());

	public BOSSHomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void clickUserAdminitstration() throws Exception {
		mouseHover(Admin_Menu, LONGWAIT);
		safeJavaScriptClick(User_Administration, LONGWAIT);
	}

	public void checkUsers(String EmailAddress, String WindowsAuthenticationID) throws Exception {
		String[] email = EmailAddress.split("@");
		String Name = email[0];
		String[] Fname = Name.split("_");
		String FirstName = Fname[0];
		String LastName = Fname[1];
		String CompleateName = WindowsAuthenticationID + "\\" + Name;
//		List<WebElement> ele = LocatorWebElements(All_Availale_Users);

//		List<String> names = new ArrayList<String>();
//		for (WebElement ele1 : ele) {
//			names.add(safeGetText(ele1, VERYSHORTWAIT));
//		}
//		if (names.contains(CompleateName)) {
//			safeJavaScriptClick(Dynamic.getNewLocator(Availale_Users_DYNAMIC, CompleateName), LONGWAIT);
//			waitForPageToLoad();
//			waitForSecs(3);
//		}
		if (!isElementPresent(Dynamic.getNewLocator(Availale_Users_DYNAMIC, CompleateName.toUpperCase()))) {
			safeType(Add_User_input, CompleateName, LONGWAIT);
			safeClick(Add_User_Button, LONGWAIT);
			waitForPageToLoad();
			waitForSecs(5);
		}
	}

	public void SelectRolese(String UserType, String EmailAddress, String Months, String WindowsAuthenticationID) {
		String[] email = EmailAddress.split("@");
		String Name = email[0];
		String CompleateName = WindowsAuthenticationID + "\\" + Name;
		safeClick(Dynamic.getNewLocator(Dyanamic_UserSelect, CompleateName.toUpperCase()), LONGWAIT);
		waitForPageToLoad();
		waitForSecs(3);
		List<WebElement> ele = LocatorWebElements(View_All_Roles);
//		safeSelectListBox(locator, sOptionToSelect, optionWaitTime);
		for (WebElement ele1 : ele) {
			safeClick(ele1, LONGWAIT);
		}
		if (UserType.equals("ReadOnly")) {
//			safeClick(View_Roles_ADMIN, LONGWAIT);
			safeClick(View_Roles_ADMINISTRATORS, LONGWAIT);
		}
		safeClick(Dynamic.getNewLocator(Dynamic_Months, Months), LONGWAIT);
		safeClick(Save_Role, LONGWAIT);
		waitForSecs(4);
	}
}
