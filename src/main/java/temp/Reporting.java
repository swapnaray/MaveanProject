package temp;

import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qtpselenium.util.ExtentManager;

public class Reporting {

	public static void main(String[] args) {
		Date d = new Date();
		System.out.println(d.toString().replaceAll(":", "-"));
		
		
		
		
		String repPath=System.getProperty("user.dir")+"\\reports\\";
		ExtentReports rep = ExtentManager.getInstance(repPath);
		ExtentTest test = rep.createTest("SampleTest");
		test.log(Status.INFO, "Starting the test");
		test.log(Status.INFO, "Navigiating to site");
		test.log(Status.INFO, "Logging in");
		test.log(Status.PASS, "Login success");
		rep.flush();

	}

}
