package com.page.locators;

import org.openqa.selenium.By;

public interface BOSSHomeLocators {

	By Admin_Menu = By.xpath("//table[@id='ctl00_TopTitleMenu_TitleMenu']//td/a[contains(text(),'Admin')]");
	By User_Administration = By.xpath("//td/a[contains(text(),'User Administration')]");
	By All_Availale_Users = By.xpath("//select[@id='ctl00_MainContent_lstUsers']/option");
	By Availale_Users_DYNAMIC = By.xpath("//select[@id='ctl00_MainContent_lstUsers']/option[contains(text(),'%s')]");
	By Add_User_input = By.xpath("//input[@id='ctl00_MainContent_txtUser']");
	By Add_User_Button = By.xpath("//input[@id='ctl00_MainContent_btnAddUser']");
	By View_All_Roles = By.xpath("//select[@id='ctl00_MainContent_lstRoles']/option[not (contains(@selected,'selected'))]");
	By View_All_Roles_Select = By.xpath("//select[@id='ctl00_MainContent_lstRoles']");
	By Save_Role = By.xpath("//input[@id='ctl00_MainContent_btnAddRolesToUser']");
	By Dyanamic_UserSelect = By.xpath("//select[@id='ctl00_MainContent_lstUsers']/option[contains(text(),'%s')]");
	By View_Roles_ADMIN = By.xpath("//select[@id='ctl00_MainContent_lstRoles']/option[not (contains(@selected,'selected')) and contains(text(),'ADMIN')]");
	By View_Roles_ADMINISTRATORS = By.xpath("//select[@id='ctl00_MainContent_lstRoles']/option[not (contains(@selected,'selected')) and contains(text(),'ADMINISTRATORS')]");
	By Dynamic_Months = By.xpath("//label[contains(text(),'%s')]/../input");

}
