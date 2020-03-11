package com.page.locators;

import org.openqa.selenium.By;

public interface OFSHomeLocators {
	By REGION = By.xpath("//td[contains(text(),'%s')]/following-sibling::td[%s]");
//	By REGION_A=By.xpath("//td[contains(text(),'%s')]/following-sibling::td[%s]/a");
	By ADMIN_MAIN = By.xpath("(//a[contains(text(),'Admin')])[1]");
	By USER_MAIN = By.xpath("//a[contains(@href,'UserManager.aspx')]");
	By USER_MAIN_A = By.xpath("//div[contains(@onclick,'UserManager.aspx')]/..");
	By Add_New_User = By.id("ctl00_OFSContent_btnAdd");
	By USER_search = By.id("ctl00_OFSContent_lbUsers");
	By Deactivate_User = By.id("ctl00_OFSContent_btnDelete");

	By USER_search_Dynamic = By.xpath("//select[@id='ctl00_OFSContent_lbUsers']/option[contains(text(),'%s')]");
	By USER_search_Dynamic1 = By.xpath("(//select[@id='ctl00_OFSContent_lbUsers']/option[contains(text(),'%s')])[1]");
	By Last_Name = By.xpath("//input[@id='ctl00_OFSContent_txtLastName']");
	By First_Name = By.xpath("//input[@id='ctl00_OFSContent_txtFirstName']");
	By Email = By.xpath("//input[@id='ctl00_OFSContent_txtEmail']");
	By Auth_Id = By.xpath("//input[@id='ctl00_OFSContent_txtNTAuthId']");
	By SelectRole = By.xpath("//input[@id='ctl00_OFSContent_btnSelectRole']");
	By CreateUser = By.id("ctl00_OFSContent_btnCreate");
	By UpdateUser = By.id("ctl00_OFSContent_btnUpdate");
	By Cancel = By.xpath("//input[@id='ctl00_OFSContent_btnCancel']");
	By Select_Maneger = By.xpath("//select[@id='ctl00_OFSContent_ddManager']");
	By Role = By.xpath("//select[@id='ctl00_OFSContent_lbRoles']");
	By Role_Name_select = By.xpath("//select[@id='ctl00_OFSContent_lbRoles']");
	By Role_Name_select_Dynamic = By.xpath("//select[@id='ctl00_OFSContent_lbRoles']/option[contains(text(),'%s')]");
	By Role_Name_All = By.xpath("//select[@id='ctl00_OFSContent_lbRoles']/option");
	By Add_Role_Touser = By.id("ctl00_OFSContent_btnSelectRole");
	By Romove_Role_Touser = By.id("ctl00_OFSContent_btnRemoveRole");
	By tagValue = By.xpath("//span[contains(text(),'%s')]/..");
	
	By Search_Order_input = By.xpath("//input[contains(@id,'ID_ORDER_NUMBER')]");
	By Search_Order_Button = By.xpath("//input[contains(@id,'ID_SEARCH')]");
	By Search_Order_Select_Order = By.xpath("//select[contains(@id,'Content_ListBox')]/option[@selected]");
	By Search_Order_View_Log_History = By.xpath("(//input[@value='View Log History'])[1]");
	By oldLogHistory = By.xpath("//a[@id='oldLogHistory']");

}
