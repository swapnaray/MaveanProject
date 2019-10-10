package com.qtpselenium.keywords;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

public class ValidationKeywords extends GenericKeywords{

	public void verifyTitle(String titleKey) {
		test.log(Status.INFO,"Verifying title "+ titleKey);
		String expectedTitle = prop.getProperty(titleKey);
		String actualTitle =driver.getTitle();
		if(!actualTitle.equals(expectedTitle)) {
			//report failure
		}
	}
	
	public void verifyElementPresence(String objectKey) {
		test.log(Status.INFO,"Verifying presence of "+ objectKey);
		if(!isElementPresent(objectKey)) {
			// report failure
		}
	}
	
	
	
}
