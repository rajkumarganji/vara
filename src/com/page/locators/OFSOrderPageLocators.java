package com.page.locators;

import org.openqa.selenium.By;

public interface OFSOrderPageLocators {
	By Purge_Prod_Orders = By.xpath("//input[@value='Purge Prod Orders']");
	By Reprocess_Message = By.xpath(
			"//table[contains(@id,'IncomingXml')]//td[contains(text(),'SALESORDER')]/..//input[contains(@title,'Reprocess Message')]");

	By Work_Order_SENT_state = By.xpath("//table[contains(@id,'WorkOrderInfo')]//td[contains(text(),'SENT')]");

	By BOMRESPONSE = By.xpath("((//table[@id='gvOutgoingXml']//td[contains(text(),'BOMRESPONSE')])[1]/../td)[1]");
	By COMP_ITEM = By.xpath(
			"//td[contains(text(),'%s')]/../preceding-sibling::*[1][self::tr]/preceding-sibling::*[1][self::tr]");

	By PARENT_ITEM = By.xpath(
			"//td[contains(text(),'%s')]/../preceding-sibling::*[1][self::tr]/preceding-sibling::*[1][self::tr]/preceding-sibling::*[1][self::tr]");

}
