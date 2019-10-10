package temp;

import com.qtpselenium.util.Xls_Reader;

public class Runmode {

	public static void main(String[] args) {
		String testName="LoginTest";
		String sheetName="Testcases";
		String path = System.getProperty("user.dir")+"\\src\\test\\resources\\Testcases.xlsx";
		Xls_Reader xls = new Xls_Reader(path);
		int rows = xls.getRowCount(sheetName);
		
		for(int rNum=2;rNum<=rows;rNum++) {
			String tName = xls.getCellData(sheetName, "TCID", rNum);
			if(tName.equals(testName)) {
				String runmode = xls.getCellData(sheetName, "Runmode", rNum);
				System.out.println(runmode);
			}
		}
		

	}

}
