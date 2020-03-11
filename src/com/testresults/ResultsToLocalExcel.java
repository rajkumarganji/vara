package com.testresults;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.log4testng.Logger;

import com.datamanager.ConfigManager;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class ResultsToLocalExcel {

	Fillo fillo;
	public Connection connection;
	String path;
	Logger log = Logger.getLogger(ResultsToLocalExcel.class);

	public ResultsToLocalExcel(String path1) {
		this.path = path1;
		fillo = new Fillo();

		try {
			connection = fillo.getConnection(path);
		} catch (FilloException e) {
			log.info("Connection has some problem");
			e.printStackTrace();
		}
	}

	public static void main(String d[]) {
		ConfigManager sys = new ConfigManager();
		HashMap<String, String> columnsAndValues = new HashMap<>();
		columnsAndValues.put("RUN_ID", "runID");
		columnsAndValues.put("PRODUCT_NAME", "productName");
		columnsAndValues.put("MODULE_NAME", "moduleName");
		columnsAndValues.put("TESTCASE_TITLE", "TestCaseTitle");
		columnsAndValues.put("TEST_STATUS", "Status");
		columnsAndValues.put("TEST_DATA", "testData");
		columnsAndValues.put("FAIL_STACKTRACE", "FailStackTrace");
		columnsAndValues.put("TEST_BROWSER", sys.getProperty("CurrentlyRunningBrowserName"));
		columnsAndValues.put("TEST_MACHINE", System.getenv("COMPUTERNAME") + "\\\\" + System.getProperty("user.name"));
		columnsAndValues.put("IMAGE_LINK", "..\\\\Screenshots\\\\Screenshot969885647.png");
		columnsAndValues.put("VIDEO_LINK", "..\\\\Videos\\\\VEL_SDUENT_TC002_sample.avi");
		columnsAndValues.put("TEST_DEVICE", "Windows 10 10.0 amd4");
		columnsAndValues.put("TEST_OS", "Windows 10 10.0 amd4");
		columnsAndValues.put("TEST_DATE", "DATE_VALUE");
		columnsAndValues.put("TEST_START_TIME", "2017-08-09 18:24:09.829");
		columnsAndValues.put("TEST_END_TIME", "2017-08-09 18:24:09.829");
		columnsAndValues.put("PROJECT_ID", "10005");
		columnsAndValues.put("TEST_SUITE", "testSuite");
		columnsAndValues.put("RUN_BY", "user");
		columnsAndValues.put("ERROR_MESSAGE", "errorMsg");
		columnsAndValues.put("EXECUTION_MODE", "executionMode");
		columnsAndValues.put("FAIL_TYPE", "failType");
		String path1 = sys.getProperty("TestResultExcelFile");
		ResultsToLocalExcel rs = new ResultsToLocalExcel(path1);
		rs.insertresultRow("LATEST", columnsAndValues);
	}

	public void insertresultRow(String sheet, HashMap<String, String> columnsAndValues) {
		try {
			log.info("Connection success. The file name: " + path);
			String[] rowDetails = generateColumnsString(columnsAndValues);
			String strQuery = "INSERT INTO " + sheet + "(" + rowDetails[0] + ") VALUES(" + rowDetails[1] + ")";
			/*
			 * String g = "INSERT INTO LATEST" +
			 * "(MODULE_NAME,FAIL_STACKTRACE,VIDEO_LINK,ERROR_MESSAGE,TEST_MACHINE,TEST_START_TIME,TEST_STATUS,TEST_SUITE,TEST_DATE,FAIL_TYPE,TEST_DATA,RUN_BY,PRODUCT_NAME,RUN_ID,TEST_OS,IMAGE_LINK,TEST_DEVICE,EXECUTION_MODE,TEST_BROWSER,TEST_END_TIME,TESTCASE_TITLE,PROJECT_ID) VALUES"
			 * +
			 * "('SDUENT','java.lang.AssertionError: sample fail','..\\Videos\\VEL_SDUENT_TC002_sample.avi','sample fail','LT112\\Sathish','2017-08-10 12:13:51.778','FAILED','Regression','2017-08-10','Assertion','','Test','VEL','SATHISH_21547','Windows 10 10.0 amd64','..\\Screenshots\\Screenshot29754309.png','Windows','linear','chrome','2017-08-10 12:13:51.910','VEL_SDUENT_TC002_sample','10005')"
			 * ;
			 */
			log.info("Executing the Query " + strQuery);
			connection.executeUpdate(strQuery);
			log.info("Row/Rows inserted successfully");
			connection.close();
		} catch (FilloException e) {
			log.info("Fillo has some issue");
			e.printStackTrace();
		} catch (Exception e) {
			log.info("some Exception occured while writing the data");
			e.printStackTrace();
		} finally {
			if (!(connection == null)) {
				log.info("Connection not closed. Hence retry to close");
				connection.close();
				log.info("Connection closed");
			}
		}
	}

	/**
	 * Create the coloumn values from a HashMap
	 * 
	 * @param columnsAndValues
	 * @return
	 */
	public String[] generateColumnsString(HashMap<String, String> columnsAndValues) {
		String columnString = "";
		String valuesString = "";
		int i = 1;
		log.info("The dynamic columns and values string generation is started...");
		for (String key : columnsAndValues.keySet()) {
			// System.out.println( key );
			columnString = columnString + key;
			valuesString = valuesString + "'" + columnsAndValues.get(key) + "'";
			if (!(i == columnsAndValues.size())) {
				columnString += ",";
				valuesString += ",";
			}
			i++;
		}
		String[] dd = { columnString, valuesString };
		return dd;
	}

	/**
	 * Move Latest To old
	 * 
	 * @throws FilloException
	 */
	public void moveLatestToOld(String oldSheet, String latestSheet) {
		log.info("Moving Latest results to Old Reports sheet");
		try {
			Recordset recordset = connection.executeQuery("Select * from " + latestSheet);
			ArrayList<String> columns = recordset.getFieldNames();
			HashMap<String, String> columnsAndValues = new HashMap<>();
			while (recordset.next()) {
				for (int c = 0; c < columns.size(); c++) {
					columnsAndValues.put(recordset.getField(c).name(),
							recordset.getField(recordset.getField(c).name()));
				}
				String[] colDetails = generateColumnsString(columnsAndValues);
				// System.out.println("INSERT INTO "+oldSheet+"("+colDetails[0]+")
				// VALUES("+colDetails[1]+")");
				connection.executeUpdate(
						"INSERT INTO " + oldSheet + "(" + colDetails[0] + ") VALUES(" + colDetails[1] + ")");
			}
			log.info("Writing the details is completed");
			log.info("Deleting rows from Lates sheet");
			connection.executeUpdate("DELETE from " + latestSheet);
			connection.close();
		} catch (FilloException e) {
			log.info("Fillo has some issue");
			e.printStackTrace();
		} catch (Exception e) {
			log.info("some Exception occured while writing the data");
			e.printStackTrace();
		} finally {
			if (!(connection == null)) {
				log.info("Connection not closed. Hence retry to close");
				connection.close();
				log.info("Connection closed");
			}
		}

	}
}
