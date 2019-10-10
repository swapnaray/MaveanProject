package com.qtpselenium.keywords;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qtpselenium.util.ExtentManager;

public class GenericKeywords {
	WebDriver driver;
	Properties prop;
	ExtentTest test;
	
	public void openBrowser(String browserName) {
		
		test.log(Status.INFO,"Opening Browser " + browserName);
		//test.log(Status.INFO, "Opening Browser");
		if(prop.getProperty("gridRun").equals("N")) {
			if(browserName.equals("Mozilla"))
				driver = new FirefoxDriver();
			else if(browserName.equals("Chrome"))
				driver = new ChromeDriver();
			else if(browserName.equals("Edge"))
				driver = new EdgeDriver();
		}else {// run on grid
			// grid code
						// Desired capabilities and RemoteWebDriver
						
						DesiredCapabilities cap=null;
						if(browserName.equals("Mozilla")){
							cap = DesiredCapabilities.firefox();
							cap.setBrowserName("firefox");
							cap.setJavascriptEnabled(true);
							cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
							
						}else if(browserName.equals("Chrome")){
							 cap = DesiredCapabilities.chrome();
							 cap.setBrowserName("chrome");
							 cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
						}
						
						try {
							driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		
		}
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		
	}
	
	public void navigate(String urlKey) {
		test.log(Status.INFO,"Navigating to URL "+urlKey);
		driver.get(prop.getProperty(urlKey));
	}
	
	public void click(String objectKey) {
		test.log(Status.INFO,"Clicking on " + objectKey);
		getObject(objectKey).click();
	}
	
	public void clear(String objectKey) {
		test.log(Status.INFO,"Clearing " + objectKey);
		getObject(objectKey).clear();
	}
	
	public void type(String objectKey, String data) {
		test.log(Status.INFO,"Typing in "+ objectKey +" . Data "+data );
		getObject(objectKey).sendKeys(data);
	}
	
	public void quit() {
		if(driver!=null)
			driver.quit();
	}
	
	/****************************************************************************/
	// common function - to extract object
	public WebElement getObject(String objectKey) {
		WebElement e  = null;
		try {
			if(objectKey.endsWith("_xpath"))
				e = driver.findElement(By.xpath(prop.getProperty(objectKey)));
			else if(objectKey.endsWith("_id"))
				e = driver.findElement(By.id(prop.getProperty(objectKey)));
			else if(objectKey.endsWith("_css"))
				e = driver.findElement(By.cssSelector(prop.getProperty(objectKey)));
		}catch(Exception ex) {
			reportFailure("Could not extract Object "+objectKey+" Failure - "+ex.getMessage());
		}
		return e;
	}
	
	// true - present
	// false - not present
	public boolean isElementPresent(String objectKey) {
			List<WebElement> e=null;
			if(objectKey.endsWith("_xpath"))
				e = driver.findElements(By.xpath(prop.getProperty(objectKey)));
			else if(objectKey.endsWith("_id"))
				e = driver.findElements(By.id(prop.getProperty(objectKey)));
			else if(objectKey.endsWith("_css"))
				e = driver.findElements(By.cssSelector(prop.getProperty(objectKey)));
			
			if(e.size()==0)
				return false;
			else
				return true;
	}
	
	
	public void reportFailure(String failureMsg) {
		// fail in extent reports
		test.log(Status.FAIL, failureMsg);
		// add screenshot
		takeScreenShot();
		// fail in testNG
		Assert.fail(failureMsg);
	}
	
	public void takeScreenShot(){
		// fileName of the screenshot
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_")+".png";
		// take screenshot
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			// get the dynamic folder name
			FileUtils.copyFile(srcFile, new File(ExtentManager.screenshotFolderPath+screenshotFile));
			//put screenshot file in reports
			test.log(Status.INFO,"Screenshot-> "+ test.addScreenCaptureFromPath(ExtentManager.screenshotFolderPath+screenshotFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
