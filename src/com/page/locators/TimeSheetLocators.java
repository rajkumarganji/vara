package com.page.locators;

import org.openqa.selenium.By;

public interface TimeSheetLocators {

	By Current_Sprint = By.xpath("//ul[@id='tfs_tnul0']//div[contains(text(),'Current')]/../following-sibling::ul");
	By Collapse_all = By.xpath("//table[@id='taskboard-table-header']//div[contains(text(),'Collapse all')]");
	By Expand_all = By.xpath("//table[@id='taskboard-table-header']//div[contains(text(),'Expand all')]");
//	By Dynamic_Name = By.xpath("//div[@class='container witExtra']/div//span[contains(text(),'Rajapanthula, Keshav')]");
	//div[@class='container witExtra']/div//span[contains(text(),'Rajapanthula, Keshav')]/../../../../../..
//	By Dynamic_Name = By.xpath("//div[@class='container witExtra']/div//span[contains(text(),'Rajapanthula, Keshav')]/../../../../../../div/div[@class='title ellipsis']");
	//tr[@id='taskboard-row-47']/td[3]//span[contains(text(),'Polneni, Devendar')]/../../../../../../div/div[@class='title ellipsis']
	By Dynamic_Name = By.xpath("//tr[@id='taskboard-row-47']/td[3]//span[contains(text(),'%s')]/../../../../../../div/div[@class='title ellipsis']");
	By Original_Estimate = By.xpath("//input[@aria-label='Original Estimate']");
	By Completed_Work = By.xpath("//input[@aria-label='Completed Work']");
	By Remaining_Work = By.xpath("//input[@aria-label='Remaining Work']");
	By Save_Close = By.xpath("//span[contains(text(),'Save & Close')]");
//	By State = By.xpath("(//div[@class='combo input-text-box list drop']/div/input)[1]");
	By Application = By.xpath("(//div[@class='combo input-text-box list drop']/div/input)[2]");
	By Task_Type = By.xpath("(//div[@class='combo input-text-box list drop']/div/input)[3]");
	By Dynamic_Menu_Selector= By.xpath("//ul[@role='menubar']//span[contains(text(),'%s')]");
	By Task_Dropdown= By.xpath("//li[contains(@id,'new-work-item')]/span[contains(text(),'Task')]");
	By new_Dropdown= By.xpath("//li[@command='new']");

	By Description=By.xpath("//body[@aria-label='Description:']");

	By Name_mail=By.xpath("(//span[@class='identity-picker-resolved-name text-cursor'])[2]");
	By Name_mail1=By.xpath("(//input[@aria-label='Search users'])[2]");
	By Search_Name_mail=By.xpath("//span[contains(text(),'Search')]");
	By Name_mail_ExactName=By.xpath("(//div[@class='item-text-container']//span[contains(text(),'%s')])[2]");
	By ExactName=By.xpath("//div[@class='subtitle large']/span");

	By Enter_Title_Name= By.xpath("(//input[@placeholder='Enter Title'])[2]");
	By Dynamic_Estimate= By.xpath("(//label[contains(text(),'%s')]/../following-sibling::div//input)[2]");//Original Estimate:,Completed Work:,Remaining Work:
	By Application_input=By.xpath("(//input[@aria-label='Application'])[2]");
	By StrrtDate=By.xpath("(//div[@class='section2 form-section section']/div[2]//div[@class='workitemcontrol work-item-control initialized']//input)[3]");
	By EndDate=By.xpath("(//div[@class='section2 form-section section']/div[2]//div[@class='workitemcontrol work-item-control initialized']//input)[4]");
	By StrrtDate1=By.xpath("(//div[@class='section2 form-section section']/div[2]//div[@class='workitemcontrol work-item-control initialized']//input)");
	By EndDate1=By.xpath("(//div[@class='section2 form-section section']/div[2]//div[@class='workitemcontrol work-item-control initialized']//input)[2]");
	By State=By.xpath("(//div[@class='workitemcontrol work-item-control initialized state-coloring-enabled']//input)[2]");
	By Save1=By.xpath("//li[@command='save-work-item']/span[contains(text(),'Save')]");
	
//	By Name_mail=By.xpath("(//span[@class='identity-picker-resolved-name text-cursor'])");
//	By State=By.xpath("//div[@class='workitemcontrol work-item-control initialized state-coloring-enabled']//input");
//	By Enter_Title_Name= By.xpath("//div[@class='combo input-text-box list text invalid emptyBorder']/div/input[@placeholder='Enter Title']");
//	By Dynamic_Estimate= By.xpath("//label[contains(text(),'%s')]/../following-sibling::div/div/div/input");//Original Estimate:,Completed Work:,Remaining Work:
//	By Application_input= By.xpath("//label[contains(text(),'Application')]/../following-sibling::div/div/div/input");
//	By StrrtDate=By.xpath("(//div[@class='section2 form-section section']/div[2]//div[@class='workitemcontrol work-item-control initialized']//input)[1]");
//	By EndDate=By.xpath("(//div[@class='section2 form-section section']/div[2]//div[@class='workitemcontrol work-item-control initialized']//input)[2]");
	By Save=By.xpath("(//li[@command='save-work-item']/span[contains(text(),'Save')])[2]");
	
	By iframe=By.xpath("(//div[@class='richeditor-container propagate-keydown-event']//iframe[@role='presentation'])[4]");
	By iframe1=By.xpath("(//div[@class='richeditor-container propagate-keydown-event']//iframe[@role='presentation'])[2]");
	
	By Links=By.xpath("(//li[@aria-label='Links'])[2]");
	By Add_Links=By.xpath("//span[contains(text(),'Add link')]");
	By Existing_item=By.xpath("//span[contains(text(),'Existing item')]");
	By Ok_Button=By.xpath("//button[@id='ok']");
	By Existing_item_Story=By.xpath("//input[@class='textbox link-dialog-title-textbox witIds-cell']");
	By Dynamic_Story=By.xpath("//span[contains(text(),'%s')]");
	By Story_Close=By.xpath("//button[@title='Close']");
	By Get_Story_Number=By.xpath("//div[contains(@class,'workitem-header-bar')]//a");
}
