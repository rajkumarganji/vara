package com.testresults;

import java.util.HashMap;

import org.testng.log4testng.Logger;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;

public class AppCreatedDataToExcel {

	Fillo fillo;
	public Connection connection;
	String path;
	Logger log = Logger.getLogger(AppCreatedDataToExcel.class);

	public AppCreatedDataToExcel(String path1) {
		this.path = path1;
		fillo = new Fillo();

		try {
			connection = fillo.getConnection(path);
		} catch (FilloException e) {
			log.info("Connection has some problem");
			e.printStackTrace();
		}
	}

	public void insertresultRow(String sheet, HashMap<String, String> columnsAndValues) {
		try {
			log.info("Connection success. The file name: " + path);
			String[] rowDetails = generateColumnsString(columnsAndValues);
			String strQuery = "INSERT INTO " + sheet + "(" + rowDetails[0] + ") VALUES(" + rowDetails[1] + ")";
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
	 * Update the coloumn details...
	 */
	public void updateColoumnValue(String sheet, String colomnName, String value) {
		try {
			log.info("Connection success. The file name: " + path);
			// String [] rowDetails = generateColumnsString(columnsAndValues);
			String strQuery = "UPDATE " + sheet + " SET " + colomnName + " = '" + value + "'";
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
}
