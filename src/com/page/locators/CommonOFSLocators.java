package com.page.locators;

import org.openqa.selenium.By;

public interface CommonOFSLocators {
	By REGION = By.xpath("//td[contains(text(),'%s')]/following-sibling::td[%s]");
	By REGION_A = By.xpath("//td[contains(text(),'%s')]/following-sibling::td[%s]/a");
	By Order_Number = By.id("ctl00_OFSContent_ID_ORDER_NUMBER");
	By Order_Search = By.id("ctl00_OFSContent_ID_SEARCH");
	By View_Log_History = By.xpath("(//input[@value='View Log History'])[1]");
	By gvExtendedView=By.xpath("//table[@id='ctl00_OFSContent_gvExtendedView']/tbody/tr/td[4]");
//	By gvExtendedView = By.xpath("//table[@id='gvExtendedView']/tbody/tr/td[4]");
	By gvExtendedView_A=By.xpath("//table[@id='ctl00_OFSContent_gvExtendedView']/tbody/tr/td[4]/a");
//	By gvExtendedView_A = By.xpath("//table[@id='gvExtendedView']/tbody/tr/td[4]/a");
	By gvExtendedView_Dynamic=By.xpath("//table[@id='ctl00_OFSContent_gvExtendedView']/tbody/tr/td[4]/a[contains(text(),'%s')]");
//	By gvExtendedView_Dynamic = By.xpath("//table[@id='gvExtendedView']/tbody/tr/td[4]/a[contains(text(),'%s')]");
	By NavigatetoLegacy = By.xpath("//a[contains(text(),'Navigate to Legacy LogHistory')]");
	By XML_STATUS_GPT = By.xpath(
			"(//div[@id='Inxml']//td[5][contains(text(),'GPT')]/preceding-sibling::td[2][contains(text(),'STATUS')]/preceding-sibling::td/a)[1]");
	By Incoming_XML = By.xpath("//a[contains(text(),'Incoming XML')]");
	By Select_page_count = By.xpath("//div[@id='Inxml']//select[@class='page-count']");
	By BOSSREGION = By.xpath("//table//tr[%s]/td[%s]/a[contains(text(),'URL')]");
}
