package com.testscripts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.utilities.JDBCConn;

public class SampleDB {
	public static void main(String[] args) throws SQLException {

		try {
			String dbURL = "jdbc:oracle:thin:@//MGGC2DE1DBSCN.US.DELL.COM:1521/df11s_default.sit.amer.dell.com";
			
			String strUserID = "SIT_BROWSER";
			String strPassword = "browser_s1t";
			Connection myConnection = DriverManager.getConnection(dbURL, strUserID, strPassword);

			Statement sqlStatement = myConnection.createStatement();
			String readRecordSQL = "select * from UFD_BASE.SALES_ORDER Where SALES_ORDER_ID='383440819'";
			ResultSet myResultSet = sqlStatement.executeQuery(readRecordSQL);
			while (myResultSet.next()) {
				System.out.println("Record values: " + myResultSet.getString("WORK_ORDER_NO"));
			}
			myResultSet.close();
			myConnection.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
