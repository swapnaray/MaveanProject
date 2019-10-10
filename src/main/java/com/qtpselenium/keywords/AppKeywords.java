package com.qtpselenium.keywords;

import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class AppKeywords extends ValidationKeywords{

	public AppKeywords(ExtentTest test) {
		// init the properites file
		prop = new Properties();
		String filePath=System.getProperty("user.dir")+"//src//test//resources//project.properties";
		try {
			FileInputStream fs = new FileInputStream(filePath);
			prop.load(fs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.test=test;
	}
	
	public void validateLogin(String expectedResult) {
		test.log(Status.INFO,"Validating login");
		String actualResult="";
		if(isElementPresent("signoutLink_xpath"))
			actualResult="Login_Success";
		else
			actualResult="Login_Failure";
		
		if(!expectedResult.equals(actualResult)) {
			reportFailure("Got actual result as "+actualResult);
		}
	}
	
	public void defaultLogin() {
		test.log(Status.INFO,"Default Login");
		type("username_xpath",prop.getProperty("default_login_username"));
		click("username_submit_id");
		type("password_css",prop.getProperty("default_login_password"));
		click("password_submit_xpath");

	}
	
	public void selectDate(String purchaseDate) {
		test.log(Status.INFO,"Selecting Date " +purchaseDate );
		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date currDate = new Date();
			Date dateToBeSelected = sd.parse(purchaseDate);
			
			sd = new SimpleDateFormat("d");
			String day = sd.format(dateToBeSelected);
			System.out.println(day);

			sd = new SimpleDateFormat("MMMM");
			String month = sd.format(dateToBeSelected);
			System.out.println(month);

			sd = new SimpleDateFormat("yyyy");
			String year = sd.format(dateToBeSelected);
			System.out.println(year);
			
			
			String monthYearDisplayed = getObject("monthyear_xpath").getText();
			String monthYearToBeSelected = month +" "+year;
			
			while(!monthYearDisplayed.equals(monthYearToBeSelected)) {
				if(dateToBeSelected.compareTo(currDate) == 1) {
					getObject("forwardIcon_xpath").click();
				}else if(dateToBeSelected.compareTo(currDate) == -1) {
					getObject("backIcon_xpath").click();
				}
				
				monthYearDisplayed = getObject("monthyear_xpath").getText();
				
			}
			// selet the day
			driver.findElement(By.xpath("//td[text()='"+day+"']")).click();
			
			
			
			
			
		} catch (Exception e) {
			reportFailure(e.getMessage());
		
		}

	}
	
	public void selectCompany(String companyName) {
		type("addstockname_id", companyName);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getObject("addstockname_id").sendKeys(Keys.ENTER);
		
	}
	
}
