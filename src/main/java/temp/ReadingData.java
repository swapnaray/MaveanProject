package temp;

import java.util.Hashtable;

import com.qtpselenium.util.Xls_Reader;

public class ReadingData {

	public static void main(String[] args) {
		
		
		Hashtable<String,String> table = new Hashtable<String,String>();
		table.put("Browser", "Mozilla");
		table.put("key", "value");
		
		
		
		String sheetName="LoginTest";
		String path = System.getProperty("user.dir")+"\\src\\test\\resources\\Testcases.xlsx";
		Xls_Reader xls = new Xls_Reader(path);
		int rows = xls.getRowCount(sheetName);
		int cols = xls.getColumnCount(sheetName);
		
		System.out.println("Rows " + rows);
		System.out.println("Cols "+ cols);
		Object[][] data = new Object[rows-1][1];
		int rowNumber = 0;
		Hashtable<String,String> dataTable = null;
		for(int rNum=2;rNum<=rows;rNum++) {
			dataTable = new Hashtable<String,String>();
			
			for(int cNum=0;cNum<cols;cNum++) {
				String cellData = xls.getCellData(sheetName, cNum, rNum);
				String colName = xls.getCellData(sheetName, cNum, 1);
				dataTable.put(colName, cellData);
				System.out.println(colName+" --- "+cellData);
				// 00 01  02 03
				// 10 11  12 13
			}
			// dataTable is full with row data
			data[rowNumber][0] = dataTable;
			rowNumber++;
		}
		

	}

}
