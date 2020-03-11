package com.datamanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.log4testng.Logger;
import org.w3c.dom.ls.LSInput;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

/**
 * This class implements IExcelManager
 */
public class ExcelManagerFillo implements IExcelManager {
	Fillo fillo;
	public Connection con;
	String filepath;
	public Recordset recordset;
	private Logger log = Logger.getLogger(ExcelManagerFillo.class);
	ExcelManagerFillo emf;
	XSSFWorkbook workbook = null;
	FileInputStream file = null;
	File fileFile = null;
	XSSFSheet sheet = null;

	/**
	 * Initiating the Class members using parameterized constructor.
	 * 
	 * @param filepath - Pass Excel file location
	 */

	public ExcelManagerFillo(String filepath) {
		this.filepath = filepath;
		fillo = new Fillo();
		try {
			con = fillo.getConnection(filepath);
		} catch (FilloException e) {
			log.error("File " + filepath + "doesn't exist...Please check the filepath \n");
			Assert.fail("File " + filepath + "doesn't exist...Please check the filepath \n" + e.getCause());
		}
	}

	public void ConnectionClose() throws Exception {

		if (fileFile.canWrite()) {
			file.close();
			workbook.close();
		}
		if (recordset == null)
			con.close();
		else {
			recordset.close();
			con.close();
		}

	}

	/**
	 * Purpose - To get entire ColumnData from excel sheet
	 * 
	 * @param sheetName    - Pass the name of excel sheet
	 * @param columnIndex- Pass the column Index which we want to retrieve
	 * @return - returns the specified columnData as List<String>.
	 * 
	 */

	@Override
	public List<String> getExcelColumnData(String sheetName, int columnIndex) {
		List<String> columnData = new ArrayList<String>();
		try {
			String query = "select * from " + sheetName;
			recordset = con.executeQuery(query);
			if (columnIndex < 0 || columnIndex >= getColumnCount(sheetName)) {
				log.error("Column index '" + columnIndex + "' is not valid...Please check column index \n");
				Assert.fail("Column index '" + columnIndex + "' is not valid...Please check column index");
			}
			while (recordset.next()) {
				columnData.add(recordset.getField(columnIndex).value());
			}
		} catch (FilloException e) {
			log.error("Provided sheetName '" + sheetName + "' does not exist...Please check sheet name \n");
			Assert.fail("Provided sheetName '" + sheetName + "' does not exist...Please check sheet name");
		} finally {
			if (recordset == null)
				con.close();
			else {
				recordset.close();
				con.close();
			}
		}
		return columnData;
	}

	public int getColumnCount(String FileName, String testCaseSheetName) throws Exception {
		int colCount = 0;
		try {
			fileFile = new File(FileName);
			file = new FileInputStream(fileFile);
			workbook = new XSSFWorkbook(file);
			sheet = workbook.getSheet(testCaseSheetName);
			XSSFRow row = null;

			// Making the object of excel row
			row = sheet.getRow(0);
			colCount = row.getLastCellNum();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Provided sheetName '" + testCaseSheetName + "' does not exist...Please check sheet name \n");
			Assert.fail("Provided sheetName '" + testCaseSheetName + "' does not exist...Please check sheet name");
		}
		return colCount;
	}

	public int getRowCount(String FileName, String testCaseSheetName) throws Exception {
		int rowCount = 0;
		try {
			fileFile = new File(FileName);
			file = new FileInputStream(fileFile);
			workbook = new XSSFWorkbook(file);
			sheet = workbook.getSheet(testCaseSheetName);
			XSSFRow row = null;

			// Making the object of excel row
			row = sheet.getRow(0);
			rowCount = sheet.getLastRowNum() + 1;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Provided sheetName '" + testCaseSheetName + "' does not exist...Please check sheet name \n");
			Assert.fail("Provided sheetName '" + testCaseSheetName + "' does not exist...Please check sheet name");
		}
		return rowCount;
	}

	public List<String> readMetaDataFromXlxFile(String FileName, String testCaseSheetName) throws Exception {
		List<String> list = new ArrayList<String>();
		try {
			fileFile = new File(FileName);
			file = new FileInputStream(fileFile);
			workbook = new XSSFWorkbook(file);
			sheet = workbook.getSheet(testCaseSheetName);
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				int rowIndex = row.getRowNum();
				if (rowIndex >= 1) {
					continue;
				}
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					String value = getValue(cell);
					list.add(value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Provided sheetName '" + testCaseSheetName + "' does not exist...Please check sheet name \n");
			Assert.fail("Provided sheetName '" + testCaseSheetName + "' does not exist...Please check sheet name");
		}
		return list;
	}

	static DataFormatter fmt = new DataFormatter();

	private static String getValue(Cell cell) {
		switch (cell.getCellType()) {
		case BLANK:
			return null;
		case BOOLEAN:
			return "CELL_TYPE_BOOLEAN";
		case ERROR:
			return "CELL_TYPE_ERROR";
		case FORMULA:
			return "CELL_TYPE_FORMULA";
		case NUMERIC:
			return fmt.formatCellValue(cell);
		case STRING:
			return cell.getStringCellValue();
		default:
			return "none";

		}
	}

	/**
	 * Purpose - To get entire ColumnData from excel sheet
	 * 
	 * @param sheetName-  Pass the name of excel sheet
	 * @param columnName- Pass the column name which we want to retrieve
	 * @return - returns the specified columnData as List<String>.
	 * 
	 */
	@Override
	public List<String> getExcelColumnData(String sheetName, String columnName) {
		List<String> columnData = new ArrayList<String>();
		try {
			String strQuery = "Select " + columnName.trim() + " from " + sheetName.trim();
			recordset = con.executeQuery(strQuery);
//			if((columnName==""||columnName==null)||!recordset.getFieldNames().contains(columnName)){
//		    	log.error("Column name provided as '"+ columnName +"' does not exist in the sheet \n");
//		    	Assert.fail("Column name provided as '"+ columnName +"' does not exist in the sheet \n");
//		    }
			while (recordset.next()) {
				columnData.add(recordset.getField(columnName));
			}
		} catch (FilloException e) {
			log.error("Provided sheetName '" + sheetName + "' does not exist...Please check sheet name \n");
			Assert.fail("Provided sheetName '" + sheetName + "' does not exist...Please check sheet name \n"
					+ e.getCause());
		}

		finally {
//			if (recordset == null)
//				con.close();
//			else {
//				recordset.close();
//				con.close();
//			}
		}

		return columnData;
	}

	/**
	 * Purpose - To get cell data from Excel sheet by passing row and column
	 * positions
	 * 
	 * @param sheetName       - Pass name of the sheet
	 * @param columnPosition- position of column[index starts from 0]
	 * @param rowPosition-    position of row[index starts from zero]
	 * @return - value of the specified cell as String
	 */

	@Override
	public String getExcelCellData(String sheetName, int columnIndex, int rowIndex) {

		String cellValue = null;
		try {
			String query = "select * from " + sheetName;
			recordset = con.executeQuery(query);
			int rowCount = getRowCount(sheetName);
			if ((columnIndex < 0 || columnIndex >= getColumnCount(sheetName))
					|| (rowIndex < 0 || rowIndex >= rowCount)) {
				log.error("Index is not valid...Please check row and column Index \n");
				Assert.fail("Index is not valid...Please check row and column Index \n");
			}
			for (int row = 0; row < rowCount; row++)
				if (recordset.next() && row == rowIndex) {
					cellValue = recordset.getField(columnIndex).value();
					break;
				}
		} catch (FilloException e) {
			log.error("Provided sheetName '" + sheetName + "' does not exist...Please check sheet name \n");
			Assert.fail("Provided sheetName '" + sheetName + "' does not exist...Please check sheet name \n"
					+ e.getCause());
		} finally {
			if (recordset == null)
				con.close();
			else {
				recordset.close();
				con.close();
			}
		}
		return cellValue;
	}

	/**
	 * Purpose - To get cell data from Excel sheet by passing column name and row
	 * position
	 * 
	 * @param sheetName-   name of the sheet
	 * @param columnName-  name of the column you want to retrieve
	 * @param rowPosition- position of row
	 * @return - value of specified cell as String
	 */

	@Override
	public String getExcelCellData(String sheetName, String columnName, int rowIndex) {
		String cellValue = "";
		try {
			String query = "select * from " + sheetName;
			recordset = con.executeQuery(query);
			int rowCount = getRowCount(sheetName);
			if ((columnName == "" || columnName == null) || !recordset.getFieldNames().contains(columnName)) {
				log.error("Column name provided as '" + columnName + "' does not exist in the sheet \n");
				Assert.fail("Column name provided as '" + columnName + "' does not exist in the sheet \n");
			}
			if (rowIndex < 0 || rowIndex >= rowCount) {
				log.error("Row index '" + rowIndex + "' is not valid...Please check row index \n");
				Assert.fail("Row index '" + rowIndex + "' is not valid...Please check row index");
			}
			for (int row = 0; row < rowCount; row++)
				if (recordset.next() && row == rowIndex) {
					cellValue = recordset.getField(columnName);
					break;
				}
		} catch (FilloException e) {
			log.error("Provided sheetName '" + sheetName + "' does not exist...Please check sheet name \n");
			Assert.fail("Provided sheetName '" + sheetName + "' does not exist...Please check sheet name \n"
					+ e.getMessage());
		}
//		finally {
//			if (recordset == null)
//				con.close();
//			else {
//				recordset.close();
//				con.close();
//			}
//		}
		return cellValue;
	}

	/**
	 * /** Purpose - To get cell data from Excel sheet by passing column name and
	 * row position
	 * 
	 * @param sheetName-   name of the sheet
	 * @param columnName-  name of the column you want to retrieve
	 * @param rowPosition- position of row
	 * @return - value of specified cell as String
	 */

	public String getExcelCellData(String sheetName, String columnName) {
		String cellValue = "";
		try {
			String query = "select " + columnName.trim() + " from " + sheetName.trim();
			recordset = con.executeQuery(query);
//			if ((columnName == "" || columnName == null) || !recordset.getFieldNames().contains(columnName)) {
//				log.error("Column name provided as '" + columnName + "' does not exist in the sheet \n");
//				Assert.fail("Column name provided as '" + columnName + "' does not exist in the sheet \n");
//			}
			if (recordset.next()) {
				cellValue = recordset.getField(columnName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Provided sheetName '" + sheetName + "' does not exist...Please check sheet name \n");
			Assert.fail("Provided sheetName '" + sheetName + "' does not exist...Please check sheet name \n"
					+ e.getMessage());
		} /*
			 * finally { if (recordset == null) con.close(); else { recordset.close();
			 * con.close(); } }
			 */
		return cellValue;
	}

	/**
	 * Purpose - To get sheetData
	 * 
	 * @param sheetName- pass sheet name
	 * @return - returns sheet data as two dimensional array as two dimensional
	 *         String array
	 */

	@Override
	public String[][] getExcelSheetData(String sheetName) {
		String[][] sheetData = null;
		try {
			String query = "select * from " + sheetName;
			recordset = con.executeQuery(query);
			int rowCount = getRowCount(sheetName);
			int colCount = getColumnCount(sheetName);
			sheetData = new String[rowCount][colCount];
			for (int cols = 0; cols < colCount; cols++) {
				for (int row = 0; row < rowCount; row++)
					if (recordset.next()) {
						if (row == 0)
							recordset.moveFirst();
						sheetData[row][cols] = recordset.getField(cols).value();
					}
				recordset.moveFirst();
			}
		} catch (FilloException e) {
			log.error("Provided sheetName '" + sheetName + "' does not exist...Please check sheet name \n");
			Assert.fail("Provided sheetName '" + sheetName + "' does not exist...Please check sheet name \n"
					+ e.getMessage());
		} finally {
			if (recordset == null)
				con.close();
			else {
				recordset.close();
				con.close();
			}
		}
		return sheetData;
	}

	/**
	 * Purpose - To add new record to the existing excel file
	 * 
	 * @param sheetName         - Pass Excel sheet name
	 * @param arrayOfDataForRow - Pass values for new record in the form of an array
	 */

	@Override
	public void addExcelRowData(String sheetName, String[] rowData) {
		try {
			System.out.println("File Path is===" + filepath);
			recordset = con.executeQuery("select * from " + sheetName);
			if (rowData.length <= getColumnCount(sheetName)) {
				String strQuery = "INSERT INTO " + sheetName + "("
						+ getQueryStringOfColumnnames(recordset.getFieldNames()) + ")" + " VALUES("
						+ getQueryStringOfValues(rowData) + ")";
				if (con.executeUpdate(strQuery) == 0)
					log.info("The Insertion is sucessfully Completed");
				else
					log.info("Unable to insert row in" + sheetName);
			} else {
				log.error("Index outof range...Please check array size \n");
				Assert.fail("Index out of range...Please check array size \n");
			}
		} catch (FilloException e) {
			log.error("Unable to insert row in '" + sheetName + "' Please check the sheet name \n");
			Assert.fail("Unable to insert row in '" + sheetName + "' Please check the sheet name \n" + e.getMessage());
		} catch (NullPointerException e) {
			log.error("String[] rowData is null or empty...Please provide proper values for an array \n");
			Assert.fail(
					"String[] rowData is null or empty...Please provide proper values for an array \n" + e.getCause());
		} finally {
			if (recordset == null)
				con.close();
			else {
				recordset.close();
				con.close();
			}
		}
	}

	@Override
	public void addExcelRowData(String sheetName, List<String> rowData) {
		try {
			System.out.println("File Path is===" + filepath);
			recordset = con.executeQuery("select * from " + sheetName);
			if (rowData.size() <= getColumnCount(sheetName)) {
				String strQuery = "INSERT INTO " + sheetName + "("
						+ getQueryStringOfColumnnames(recordset.getFieldNames()) + ")" + " VALUES("
						+ getQueryStringOfValues(rowData) + ")";
				if (con.executeUpdate(strQuery) == 0)
					log.info("The Insertion is sucessfully Completed");
				else
					log.info("Unable to insert row in" + sheetName);
			} else {
				log.error("Index outof range...Please check array size \n");
				Assert.fail("Index out of range...Please check array size \n");
			}
		} catch (FilloException e) {
			log.error("Unable to insert row in '" + sheetName + "' Please check the sheet name \n");
			Assert.fail("Unable to insert row in '" + sheetName + "' Please check the sheet name \n" + e.getMessage());
		} catch (NullPointerException e) {
			log.error("String[] rowData is null or empty...Please provide proper values for an array \n");
			Assert.fail(
					"String[] rowData is null or empty...Please provide proper values for an array \n" + e.getCause());
		} finally {
			if (recordset == null)
				con.close();
			else {
				recordset.close();
				con.close();
			}
		}
	}

	public void writeToExel(String FILE_NAME, String sheetName, List<String> data, int Cellnumber, List<String> data1,
			int Cellnumber1) {
		try {
			InputStream inp = new FileInputStream(FILE_NAME);
			Workbook wb = WorkbookFactory.create(inp);
			Sheet sheet = wb.getSheet(sheetName);
			int num = sheet.getPhysicalNumberOfRows();
			Row row = sheet.createRow(++num);
			Cell cell = row.createCell(++num);
//			for (int i = 0; i < data.size(); i++) {
////				if (Cellnumber1==1) {
////					Hyperlink href = wb.getCreationHelper().createHyperlink(HyperlinkType.URL);
////					href.setAddress(data.get(i));
////					sheet.createRow(i).createCell(Cellnumber1).setHyperlink(href);
////				} else
//					sheet.createRow(i).createCell(Cellnumber).setCellValue(data.get(i));
//			}
			for (int i = 0; i < data1.size(); i++) {
				Hyperlink href = wb.getCreationHelper().createHyperlink(HyperlinkType.URL);
				href.setAddress(data1.get(i));
//					sheet.createRow(i).createCell(Cellnumber).setCellValue(data.get(i));
				sheet.createRow(i).createCell(Cellnumber1).setCellValue(data.get(i));
			}
			FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
			wb.write(fileOut);
			inp.close();
			wb.close();
		} catch (Exception e) {
			log.error("Unable to insert row in '" + sheetName + "' Please check the sheet name \n");
			Assert.fail("Unable to insert row in '" + sheetName + "' Please check the sheet name \n" + e.getMessage());
		}
	}

	public void writeToExel(String FILE_NAME, String sheetName, List<String> data, int Cellnumber1) {
		try {
			InputStream inp = new FileInputStream(FILE_NAME);
			Workbook wb = WorkbookFactory.create(inp);
			Sheet sheet = wb.getSheet(sheetName);
			int num = sheet.getPhysicalNumberOfRows();
			Row row = sheet.createRow(++num);
			Cell cell = row.createCell(++num);
//			for (int i = 0; i < data.size(); i++) {
////				if (Cellnumber1==1) {
////					Hyperlink href = wb.getCreationHelper().createHyperlink(HyperlinkType.URL);
////					href.setAddress(data.get(i));
////					sheet.createRow(i).createCell(Cellnumber1).setHyperlink(href);
////				} else
//					sheet.createRow(i).createCell(Cellnumber).setCellValue(data.get(i));
//			}

//					sheet.createRow(i).createCell(Cellnumber).setCellValue(data.get(i));
			for (int i = 0; i < data.size(); i++) {
				sheet.createRow(i + 1).createCell(Cellnumber1).setCellValue(data.get(i));
			}
			FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
			wb.write(fileOut);
			inp.close();
			wb.close();
		} catch (Exception e) {
			log.error("Unable to insert row in '" + sheetName + "' Please check the sheet name \n");
			Assert.fail("Unable to insert row in '" + sheetName + "' Please check the sheet name \n" + e.getMessage());
		}
	}

	/**
	 * purpose - To form a query String for column names
	 * 
	 * @param colname- list of column names
	 */
	private String getQueryStringOfColumnnames(List<String> colname) {
		String colNames = "";
		int size = colname.size();
		for (int i = 0; i < size; i++)
			if (i < size - 1)
				colNames = colNames + colname.get(i) + ",";
			else
				colNames = colNames + colname.get(i);
		return colNames;
	}

	/**
	 * Purpose - To form a query string for values
	 * 
	 * @param rowData-array of values we add to the excel sheet
	 */

	private String getQueryStringOfValues(String[] rowData) {
		String values = "";
		int size = rowData.length;
		for (int i = 0; i < size; i++)
			if (i < size - 1)
				values = values + "'" + rowData[i] + "'" + ",";
			else
				values = values + "'" + rowData[i] + "'";
		return values;
	}

	private String getQueryStringOfValues(List<String> rowData) {
		String values = "";
		int size = rowData.size();
		for (int i = 0; i < size; i++)
			if (i < size - 1)
				values = values + "'" + rowData.get(i) + "'" + ",";
			else
				values = values + "'" + rowData.get(i) + "'";
		return values;
	}

	/**
	 * Purpose - To get row count
	 * 
	 * @param query- name of the sheet
	 * @return- row count as integer
	 */
	@Override
	public int getRowCount(String sheetName) {
		int rowCount = 0;
		try {
			String query = "select * from " + sheetName;
			recordset = con.executeQuery(query);
			rowCount = recordset.getCount();
		} catch (FilloException e) {
			log.error("Provided sheetName '" + sheetName + "' does not exist...Please check sheet name \n");
			Assert.fail("Provided sheetName '" + sheetName + "' does not exist...Please check sheet name \n"
					+ e.getMessage());
		}
		return rowCount;
	}

	/**
	 * Purpose - To get column count
	 * 
	 * @param query- name of the sheet
	 * @return - column count as integer
	 */
	@Override
	public int getColumnCount(String sheetName) {
		int columnCount = 0;
		try {
			String query = "select * from " + sheetName;
			recordset = con.executeQuery(query);
			columnCount = recordset.getFieldNames().size();

		} catch (FilloException e) {
			log.error("Provided sheetName '" + sheetName + "' does not exist...Please check sheet name \n");
			Assert.fail("Provided sheetName '" + sheetName + "' does not exist...Please check sheet name \n"
					+ e.getMessage());
		}
		return columnCount;
	}

	/**
	 * Method returns only the column names from the give Excel Sheet
	 * 
	 * @param sheetName
	 * @return
	 */
	public String[] getColumnNames(String sheetName) {
		String colNames[] = null;
		try {
			String query = "select * from " + sheetName;
			recordset = con.executeQuery(query);
			colNames = new String[recordset.getFieldNames().size()];
			int i = 0;
			for (String s : recordset.getFieldNames()) {
				colNames[i] = s;
				i++;
			}
			if (recordset.getCount() <= 0) {
				log.error("Sheet provided as '" + sheetName + "' does not have any rows/columns \n");
				Assert.fail("Sheet provided as '\"+ sheetName +\"' does not have any rows/columns  \n");
			}

		} catch (FilloException e) {
			log.error("Provided sheetName '" + sheetName + "' does not exist...Please check sheet name \n");
			Assert.fail("Provided sheetName '" + sheetName + "' does not exist...Please check sheet name \n"
					+ e.getMessage());
		} finally {
			if (recordset == null)
				con.close();
			else {
				recordset.close();
				con.close();
			}
		}
		return colNames;
	}
}
