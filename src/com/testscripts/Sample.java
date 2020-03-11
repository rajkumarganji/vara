package com.testscripts;

import java.io.FileOutputStream;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;
import org.testng.reporters.jq.Main;

public class Sample {
	static WebDriver driver;

	@Test

//		System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir") + "/Drivers/geckodriver.exe");
//		driver = new FirefoxDriver();

//		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Drivers/chromedriver.exe");
//		driver = new ChromeDriver();

//		System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/Drivers/IEDriverServer.exe");
//		driver = new InternetExplorerDriver();

//		driver.get("https://github.com/mozilla/geckodriver/releases");
//		driver.findElement(By.xpath(""));
//		public static void main(String[] args) throws Exception {
//		 final Workbook workbook = WorkbookFactory.create("");
//		    final Sheet sheet = workbook.getSheetAt(1);
//		    final Row row = sheet.getRow(1);
//		    final Cell cell = row.getCell(1);
//
//		    cell.setCellValue("");
//		    final Hyperlink href = workbook.getCreationHelper().createHyperlink(HyperlinkType.URL);
//		    href.setAddress("");
//		    cell.setHyperlink(href);
//
//		    ByteArrayOutputStream out = new ByteArrayOutputStream();
//		    workbook.write(out);
//		    FileOutputStream fout = new FileOutputStream("");
//		    fout.write(out.toByteArray());
//		    fout.close();
//		   }
	public static void main(String... ar) {
		System.out.println("\\");
	}
}
