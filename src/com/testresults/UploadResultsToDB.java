package com.testresults;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

//import org.json.simple.JSONObject;
import org.testng.log4testng.Logger;

import com.datamanager.ConfigManager;
import com.utilities.JDBCConn;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Recordset;;

public class UploadResultsToDB {
	public static void main(String[] i) {

		uploadResultsToD3API();

	}

	public static void uploadResultsToDB() {
		Logger log = Logger.getLogger(UploadResultsToDB.class);
		ConfigManager sys = new ConfigManager();
		ResultsToLocalExcel resultToExcel = new ResultsToLocalExcel(
				System.getProperty("user.dir") + "/Data/TestAutomationResults.xlsx");
		Connection connection = resultToExcel.connection;
		String tableName = sys.getProperty("TABLENAME");
		String latestSheet = sys.getProperty("TestResult.LATEST.Sheet");
		try {
			Recordset recordset = connection.executeQuery("Select * from " + latestSheet);
			ArrayList<String> columns = recordset.getFieldNames();
			HashMap<String, String> columnsAndValues = new HashMap<>();
			while (recordset.next()) {
				for (int c = 0; c < columns.size(); c++) {
					columnsAndValues.put(recordset.getField(c).name(),
							recordset.getField(recordset.getField(c).name()));
				}
				String[] colDetails = resultToExcel.generateColumnsString(columnsAndValues);
				JDBCConn.insert("INSERT INTO " + tableName + " (" + colDetails[0] + ") VALUES(" + colDetails[1] + ")");
			}
			log.info("Writing the details is completed");
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

	public static void uploadResultsToD3API() {
		Logger log = Logger.getLogger(UploadResultsToDB.class);
		ConfigManager sys = new ConfigManager();
		ResultsToLocalExcel resultToExcel = new ResultsToLocalExcel(
				System.getProperty("user.dir") + "/Data/TestAutomationResults.xlsx");
		Connection connection = resultToExcel.connection;
		String tableName = sys.getProperty("TABLENAME");
		String latestSheet = sys.getProperty("TestResult.LATEST.Sheet");
//		JSONArray ja = new JSONArray();
		try {
			Recordset recordset = connection.executeQuery("Select * from " + latestSheet);
			ArrayList<String> columns = recordset.getFieldNames();
			HashMap<String, String> columnsAndValues = new HashMap<>();
			while (recordset.next()) {
//				JSONObject jo = new JSONObject();
				for (int c = 0; c < columns.size(); c++) {
					columnsAndValues.put(recordset.getField(c).name(),
							recordset.getField(recordset.getField(c).name()));
					String propertyKey = recordset.getField(c).name().toLowerCase();
					String propertyKey_split[] = propertyKey.split("_");
					String d = propertyKey_split[0];
					for (int i = 1; i < propertyKey_split.length; i++) {
						String s1 = propertyKey_split[i].substring(0, 1).toUpperCase();
						// System.out.println(s1);
						d = d + s1 + propertyKey_split[i].substring(1);
						// System.out.println(d);
					}
//					jo.addProperty(d, recordset.getField(recordset.getField(c).name()));
				}
//				ja.add(jo);
				// String[] colDetails = resultToExcel.generateColumnsString(columnsAndValues);

				// JDBCConn.insert("INSERT INTO "+tableName+" ("+colDetails[0]+")
				// VALUES("+colDetails[1]+")");
			}
//			System.out.println(ja);
			log.info("Writing the details is completed");
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

	/*
	 * public static String post(String strBody) throws ClientProtocolException,
	 * IOException { ConfigManager sys = new ConfigManager(); // String strURL =
	 * "http://192.168.70.132:8180/d3Admin/api/results/add"; String strURL =
	 * sys.getProperty("D3.API.URL"); // String strURL =
	 * "http://localhost:8080/d3Admin/api/results/add"; String strContentType =
	 * "application/json"; HttpClient client = HttpClientBuilder.create().build();
	 * HttpPost post = new HttpPost(strURL); post.addHeader("Content-Type",
	 * strContentType); post.setEntity(new StringEntity(strBody)); HttpResponse
	 * response = client.execute(post); int responseCode =
	 * response.getStatusLine().getStatusCode(); return getResponseBody(response); }
	 * 
	 *//**
		 * 
		 * To get response body
		 * 
		 * @param HttpResponse response object
		 * @return String Response body
		 *//*
			 * private static String getResponseBody(HttpResponse response) { try {
			 * InputStreamReader in = new
			 * InputStreamReader(response.getEntity().getContent()); BufferedReader br = new
			 * BufferedReader(in); String responseLine; String responseBody = ""; while
			 * ((responseLine = br.readLine()) != null) { responseBody = responseBody +
			 * responseLine; } if (responseBody.length() > 0) {
			 * 
			 * // log.info("<pre> Response : "+responseBody+"</pre>"); return responseBody;
			 * } else { // log.info("null Response"); return null; } } catch (Exception e) {
			 * return null; }
			 * 
			 * }
			 */

}
