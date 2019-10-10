package testcases;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qtpselenium.driver.DriverScript;
import com.qtpselenium.util.DataUtil;
import com.qtpselenium.util.ExtentManager;
import com.qtpselenium.util.Xls_Reader;

public class AddStockTest {
	String testCase="AddStockTest";
	String path = System.getProperty("user.dir")+"\\src\\test\\resources\\Testcases.xlsx";
	Xls_Reader xls = new Xls_Reader(path);
	ExtentReports rep;
	ExtentTest test;
	DriverScript ds;
	
	@BeforeMethod
	public void init() {
		String repPath=System.getProperty("user.dir")+"\\reports\\";
		rep = ExtentManager.getInstance(repPath);
		test = rep.createTest(testCase);
			
	}
	
	@AfterMethod
	public void quit() {
		rep.flush();
		// mand - close the browser
	//	ds.quit();
	}
	
	@Test(dataProvider="getData")
	public void doLogin(Hashtable<String,String> data) {
		if(
				DataUtil.isSkippable(xls, testCase) || data.get("Runmode").equals("N")) {
			test.log(Status.SKIP, "Skipping the test as Runmmode is N");
			throw new SkipException("Skipping the test as Runmmode is N");
		}
		
		test.log(Status.INFO, "Starting the test");
		ds = new DriverScript(test);
		ds.executeKeywords(testCase, xls, data);
		test.log(Status.PASS, testCase + " Pass ");
		
				
	}
	
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getTestData(xls, testCase);
	}

}
