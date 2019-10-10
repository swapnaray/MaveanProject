package com.qtpselenium.util;

import java.util.Hashtable;

public class DataUtil {
	
	public static Object[][] getTestData(Xls_Reader xls, String testCase){
		
		int rows = xls.getRowCount(testCase);
		int cols = xls.getColumnCount(testCase);
		
		Object[][] data = new Object[rows-1][1];
		int rowNumber = 0;
		Hashtable<String,String> dataTable = null;
		for(int rNum=2;rNum<=rows;rNum++) {
			dataTable = new Hashtable<String,String>();
			
			for(int cNum=0;cNum<cols;cNum++) {
				String cellData = xls.getCellData(testCase, cNum, rNum);
				String colName = xls.getCellData(testCase, cNum, 1);
				dataTable.put(colName, cellData);
				System.out.println(colName+" --- "+cellData);
				// 00 01  02 03
				// 10 11  12 13
			}
			// dataTable is full with row data
			data[rowNumber][0] = dataTable;
			rowNumber++;
		}
		
		return data;

	}
// true - N
// false - Y
	public static boolean isSkippable(Xls_Reader xls, String testName) {
		String sheetName="Testcases";
		int rows = xls.getRowCount(sheetName);
		
		for(int rNum=2;rNum<=rows;rNum++) {
			String tName = xls.getCellData(sheetName, "TCID", rNum);
			if(tName.equals(testName)) {
				String runmode = xls.getCellData(sheetName, "Runmode", rNum);
				if(runmode.equals("N"))
					return true;
				else
					return false;
			}
		}
		return false;// default
	}
	
	
	
	
	
}
