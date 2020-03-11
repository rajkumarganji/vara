package com.page.module;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.log4testng.Logger;

import com.datamanager.ConfigManager;
import com.page.locators.BOSSHomeLocators;
import com.page.locators.TimeSheetLocators;
import com.selenium.Dynamic;

public class TimeSheetHomePage extends CommonFunctionalities implements TimeSheetLocators {

	ConfigManager appData = new ConfigManager("Sys");

	private WebDriver driver;
	Logger log = Logger.getLogger(getClass());

	public TimeSheetHomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void clickCurrentSprint() throws Exception {
		safeClick(Current_Sprint, LONGWAIT);
	}

	public void clickCollapse_all() throws Exception {
		safeClick(Collapse_all, LONGWAIT);
		waitForSecs(3);
		safeClick(Expand_all, LONGWAIT);
	}

	public void clickSprintLink(String name) throws Exception {
		By by = Dynamic.getNewLocator(Dynamic_Name, name);
		safeClick(by, LONGWAIT);
	}

	public void clickQueiresDynamic(String name) throws Exception {
		By by = Dynamic.getNewLocator(Dynamic_Menu_Selector, name);
		safeClick(by, LONGWAIT);
	}

//	public void clickNew_DropDown() throws Exception {
//		safeClick(new_Dropdown, LONGWAIT);
//	}
//
	public void EnterApplication_Tab(String app, String by) throws Exception {
		safeClearAndType(by, app, SHORTWAIT);
		driver.findElement(By.xpath(by)).sendKeys(Keys.TAB);
	}

	public void EnterTitle(String title, By by) {
		safeType(by, title, SHORTWAIT);
	}

	public void EnterTitle2(String title, String by) {
		safeType(by, title, SHORTWAIT);
	}

	public void ClickAction(By by) {
		safeClick(by, SHORTWAIT);
	}

	public void ClickAction(String by) {
		safeClick(by, SHORTWAIT);
	}

	public void waitForSecs(int sec) {
		log.info("Waiting for " + sec + " seconds.");
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			log.error("Unable to wait for given " + sec + " seconds.");
			e.printStackTrace();
		}
	}

	public void ClickSearch_Name(String name) {
//		if (!name.equalsIgnoreCase("Rajapanthula, Keshav")) {
		safeClick("(//span[@class='identity-picker-resolved-name text-cursor'])[2]", SHORTWAIT);
		waitForSecs(1);
		safeType("(//input[@aria-label='Search users'])[2]", name, SHORTWAIT);
		safeClick(Search_Name_mail, SHORTWAIT);
		safeClick(ExactName, SHORTWAIT);
		waitForSecs(2);
//		By by=Dynamic.getNewLocator(Name_mail_ExactName, name);
//		TimeSheetHomePage.ClickAction(by);
//		driver.findElement(by).sendKeys(Keys.ENTER);
//		}
	}

	public void EnterDesc(String desc, String st, String en) {
		if (isElementPresent(iframe))
			selectFrame(iframe, SHORTWAIT);
		else
			selectFrame(iframe1, SHORTWAIT);
		safeType(Description, desc, SHORTWAIT);
		defaultFrame();
	}

	public void EstimatedTimeEntry(String original, String completed, String remaing) {
		By ori = Dynamic.getNewLocator(Dynamic_Estimate, "Original Estimate:");
		By cmp = Dynamic.getNewLocator(Dynamic_Estimate, "Completed Work:");
		By remai = Dynamic.getNewLocator(Dynamic_Estimate, "Remaining Work:");
		String oris = ori.toString();
		String cmps = cmp.toString();
		String remais = remai.toString();
		String oris1 = oris.substring(10, oris.length() - 4) + ")[2]";
		String cmps1 = cmps.substring(10, cmps.length() - 4) + ")[2]";
		String remais1 = remais.substring(10, remais.length() - 4) + ")[2]";
		waitForSecs(2);
		safeType(oris1, original, SHORTWAIT);
		safeType(cmps1, completed, SHORTWAIT);
		safeType(remais1, remaing, SHORTWAIT);
	}

	public String getStoryNumber(String story) {
		By by = Dynamic.getNewLocator(Dynamic_Story, story);
		safeClick(by, LONGWAIT);
		String string = safeGetText(Get_Story_Number, LONGWAIT);
		safeClick(Story_Close, LONGWAIT);
		String[] abc = string.split(" ");
		String finl = abc[1];
		return finl;
	}
}
