package com.qtpselenium.driver;

import java.util.Hashtable;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qtpselenium.keywords.AppKeywords;
import com.qtpselenium.util.Xls_Reader;

public class DriverScript {

	ExtentTest test;
	AppKeywords app;
	public DriverScript(ExtentTest test) {
		this.test=test;
	}
	
	public void executeKeywords(String testCase, Xls_Reader xls,Hashtable<String,String> testData) {
		System.out.println(System.getProperty("user.dir"));
	    app = new AppKeywords(test);
		
		
		
		//String testCase="LoginTest";
		//String path = System.getProperty("user.dir")+"\\src\\test\\resources\\Testcases.xlsx";
		//Xls_Reader xls = new Xls_Reader(path);
		int rows = xls.getRowCount("Keywords");
		System.out.println(rows);
		
		for(int rNum=2;rNum<=rows;rNum++) {
			String tcid = xls.getCellData("Keywords", "TCID", rNum);
			if(tcid.equals(testCase)) {
				String keyword = xls.getCellData("Keywords", "Keyword", rNum);
				String objectKey = xls.getCellData("Keywords", "Object", rNum);
				String key = xls.getCellData("Keywords", "Data", rNum);
				String data = testData.get(key);
				
				
				if(keyword.equals("openBrowser"))
					app.openBrowser(data);
				else if(keyword.equals("navigate"))
					app.navigate(objectKey);
				else if(keyword.equals("click"))
					app.click(objectKey);
				else if(keyword.equals("type"))
					app.type(objectKey,data);
				else if(keyword.equals("verifyTitle"))
					app.verifyTitle(objectKey);
				else if(keyword.equals("verifyElementPresence"))
					app.verifyElementPresence(objectKey);
				else if(keyword.equals("validateLogin"))
					app.validateLogin(data);
				else if(keyword.equals("defaultLogin"))
					app.defaultLogin();
				else if(keyword.equals("clear"))
					app.clear(objectKey);
				else if(keyword.equals("selectDate"))
					app.selectDate(data);
				else if(keyword.equals("selectCompany"))
					app.selectCompany(data);
				
			//	test.log(Status.INFO, tcid +" --- "+ keyword+" -- "+objectKey +" --- "+data);
			}
			
		}
		
		
		

	}
	
	public void quit() {
		if(app!=null)
			app.quit();
	}

}
