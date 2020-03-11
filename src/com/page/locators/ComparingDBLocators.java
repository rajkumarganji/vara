package com.page.locators;

import org.openqa.selenium.By;

public interface ComparingDBLocators {

	By ddl_Region = By.id("ddlRegion");
	By ddl_Environment = By.id("ddlEnvironment");
	By ddl_MessageType = By.id("ddlMessageType");
	By txt_OrderNumber = By.id("txtOrderNumber");
	By ddl_ResType = By.id("ddlResType");
	By btn_generate = By.id("btn-generate");
	By btn_pushtoMQ = By.id("btn-pushtoMQ");

	By ddlApplication = By.id("ddlApplication");
	By ddlRegions = By.id("ddlRegions");
//	By ddlSourceEnv = By.id("ddlSourceEnv");
	By ddlSourceEnv = By.xpath("//select[@id='ddlSourceEnv']");
	By ddlDestinationEnv = By.id("ddlDestinationEnv");
	By ddlCCN = By.id("ddlCCN");
	By txtItem = By.xpath("//textarea[@id='txtItem']");
	By btnDataRefresh = By.id("btnDataRefresh");
	By btnReset = By.id("btnReset");

}
