package com.page.module;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.log4testng.Logger;

import com.datamanager.ConfigManager;
import com.datamanager.ExcelManagerFillo;
import com.page.locators.BOSSHomeLocators;
import com.page.locators.ComparingDBLocators;
import com.selenium.Dynamic;

public class ComparingDBPage2 extends CommonFunctionalities implements ComparingDBLocators {
	ConfigManager sys;
	ResultSet rs = null;
	String url = "jdbc:oracle:thin:@//";
	String DestinationSheet = "comparisionResults";
	Connection conn = null;
	String PARTNUM = null;
	ComparingDBPage2 comparingDBPage = null;
	Statement stmt = null;

	private WebDriver driver;
	Logger log = Logger.getLogger(getClass());

	public ComparingDBPage2(WebDriver driver) {
		super(driver);
		sys = new ConfigManager();
		this.driver = driver;
	}

	public void fillTheForm(String reagion, String env, String Application, String[] list, String CCN, String Order) {
	
			safeSelectOptionInDropDownByValue(ddlApplication, Application, SHORTWAIT);
			safeSelectOptionInDropDownByValue(ddlRegions, reagion, LONGWAIT);
			safeSelectOptionInDropDownByValue(ddlSourceEnv, "PROD", LONGWAIT);
			safeSelectOptionInDropDownByValue(ddlDestinationEnv, env, LONGWAIT);

			for (int i = 0; i < list.length; i++) {
				if (i != list.length - 1)
					safeType(txtItem, list[i] + ",", SHORTWAIT);
				else
					safeType(txtItem, list[i], SHORTWAIT);
			}
			safeSelectOptionInDropDownByVisibleText(ddlCCN, CCN, SHORTWAIT);
			safeClick(btnDataRefresh, SHORTWAIT);
	}

	public void getDetailsConnect(String reagion, String env, String order) {
		String Server = sys.getProperty(reagion + env + "_Server");
		String Host = sys.getProperty(reagion + env + "_Host");
		String driver = sys.getProperty("JDBCDRIVER");
		String User = sys.getProperty("DBUSERNAME");
		String Pwd = sys.getProperty("PASSWORD");
		url = url + Server + ":1521/";
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + Host, User, Pwd);
			System.out.println("Connected");
			stmt = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void colseConection(ExcelManagerFillo ExcelManagerFillo1) throws Exception {
		if (conn != null)
			conn.close();
		ExcelManagerFillo1.ConnectionClose();
		if (rs != null)
			rs.close();
		if (stmt != null)
			stmt.close();
	}

	public void colseConection() throws Exception {
		if (conn != null)
			conn.close();
		if (rs != null)
			rs.close();
		if (stmt != null)
			stmt.close();
	}

	public ArrayList<String> getCCNdetails(String order) {
		ArrayList<String> ACCN = new ArrayList<String>();
		String SQl12 = "select distinct CCN from (select OC.ORDER_MOD_REF,OC.PART_NUM,OA.MOD_PART_NUM,OA.LINE_SKU_REF, OC.SALES_ORDER_ID,LS.CCN from UFD_BASE.ORDER_COMPONENT OC left join UFD_BASE.ORDER_assembly OA on OC.ORDER_MOD_REF = OA.ORDER_MOD_REF left join ufd_base.LINE_SKU LS on LS.LINE_SKU_REF=OA.LINE_SKU_REF where OC.SALES_ORDER_ID in('"+ order + "'))";
		try {
			ResultSet rssd = stmt.executeQuery(SQl12);
			while (rssd.next()) {
				ACCN.add(rssd.getString("CCN"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ACCN;
	}

	public List<String> connectDBPullData(String reagion, String env, String order, String ExcelManagerFillo) {

		getDetailsConnect(reagion, env, order);
		List<String> finalAvailable = new ArrayList<String>();
		List<String> finalNotAvailable = new ArrayList<String>();
		ArrayList<String> ACCN = new ArrayList<String>();
		try {
			String sql = "select OC.ORDER_MOD_REF,OC.PART_NUM,OA.MOD_PART_NUM,OA.LINE_SKU_REF, OC.SALES_ORDER_ID,LS.CCN from UFD_BASE.ORDER_COMPONENT OC left join UFD_BASE.ORDER_assembly OA on OC.ORDER_MOD_REF = OA.ORDER_MOD_REF left join ufd_base.LINE_SKU LS on LS.LINE_SKU_REF=OA.LINE_SKU_REF where OC.SALES_ORDER_ID in('"+ order + "')";
			ACCN = getCCNdetails(order);
			for (String k : ACCN) {
				String sqlCCN = "select OC.ORDER_MOD_REF,OC.PART_NUM,OA.MOD_PART_NUM,OA.LINE_SKU_REF, OC.SALES_ORDER_ID,LS.CCN from UFD_BASE.ORDER_COMPONENT OC left join UFD_BASE.ORDER_assembly OA on OC.ORDER_MOD_REF = OA.ORDER_MOD_REF left join ufd_base.LINE_SKU LS on LS.LINE_SKU_REF=OA.LINE_SKU_REF where OC.SALES_ORDER_ID in('"+ order + "') and OC.CCN in('" + k + "')";

				rs = stmt.executeQuery(sqlCCN);
				finalAvailable.add(k);
				ArrayList<String> APART_NUM = new ArrayList<String>();
				while (rs.next()) {
//			String ORDER_MOD_REF = rs.getString("ORDER_MOD_REF");
					String PART_NUM = rs.getString("PART_NUM");
//			String MOD_PART_NUM = rs.getString("MOD_PART_NUM");
//			String LINE_SKU_REF = rs.getString("LINE_SKU_REF");
//			String SALES_ORDER_ID = rs.getString("SALES_ORDER_ID");
					String CCN = rs.getString("CCN");
					APART_NUM.add(PART_NUM);
				}
				System.out.println("Array list prepared and size is : " + APART_NUM.size());
				for (String part : APART_NUM) {
//			String partss[]=part.split("_CCN_");
					String sql2 = "select COUNT(*) as count from UFD_BASE.PART_MASTER where Part_num='" + part + "'";
					String sql23 = "select distinct CCN from UFD_BASE.PART_MASTER where Part_num='" + part + "'";
					ResultSet ras = stmt.executeQuery(sql23);
					ResultSet ras1 = stmt.executeQuery(sql2);
					try {
						while (ras1.next()) {
							if(ras1.getInt("count")!=0) {
						
						while (ras.next()) {
							System.out.print(" Part number is: " + part);
							System.out.println(" and Count is : " + ras.getString("CCN"));
							if (!ras.getString("CCN").isEmpty())
								finalAvailable.add(part + ":" + ras.getString("CCN"));
							else
								finalNotAvailable.add(part + ":" + ras.getString("CCN"));
						
						}
							}
						}
					} catch (Exception e) {

						System.out.println("======" + part);
					}
				}
			}
//	for (String string : finalAvailable) {
//		
//	}
			System.out.println("Total not available parts means 0 parts--->" + finalNotAvailable);
			System.out.println("Total available parts means 1 or more parts--->" + finalAvailable);
//		ExcelManagerFillo ExcelManagerFillo11=new ExcelManagerFillo(ExcelManagerFillo);
//		ExcelManagerFillo11.writeToExel(ExcelManagerFillo, DestinationSheet,finalAvailable,0);
//		ExcelManagerFillo11.writeToExel(ExcelManagerFillo, DestinationSheet,ACCN,3);
//		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalNotAvailable;
	}

}
