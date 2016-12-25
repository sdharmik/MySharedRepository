package com.core.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.core.util.ReadExcel;

import jxl.read.biff.BiffException;

public class GlobalFunctions {

	public static Properties p;
	public FileInputStream fis;
	public WebDriver driver;
	public static ReadExcel readExcel;
	public static boolean isInitialized;
	public static Logger AppLogs;
	
	public void initialize() throws Exception{
		
		if(!isInitialized){
			
			//logs
			PropertyConfigurator.configure("log4j.properties");
			System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
			AppLogs=Logger.getLogger("devpinoyLogger");
			
			//properties file
			AppLogs.debug("Loading properties file");
			p=new Properties();
			System.out.println(System.getProperty("user.dir"));
			fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\core\\properties\\Config.properties");
			p.load(fis);
			AppLogs.debug("Properties file loaded successfully");
			
			//excel file
			AppLogs.debug("Loading Excel file");
			readExcel = new ReadExcel(System.getProperty("user.dir")+"\\src\\com\\core\\data\\ExcelTestcaseData.xls");
			AppLogs.debug("Excel file loaded successfully");
			isInitialized=true;
		}
		
	}
//open corresponding browser
	
	public void launchBrowser(){
		
		if(p.getProperty("browserName").toLowerCase().matches("firefox")){
			driver = new FirefoxDriver();
			driver.get(p.getProperty("url"));
		}
		else if(p.getProperty("browserName").toLowerCase().matches("iexplorer")){
			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\lib\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			driver.get(p.getProperty("url"));
		}
		else if(p.getProperty("browserName").toLowerCase().matches("chrome")){
			driver = new ChromeDriver();
			driver.get(p.getProperty("url"));
		}
		
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	}

public void closeBrowser(){
	driver.close();
}

	public void enterText(String locType, String locValue, String data){
		
		By locator1=getLocator(locType,locValue);
		
		try{
			AppLogs.debug("Entering text in text box");
			driver.findElement(locator1).sendKeys(data);
			AppLogs.debug("Text entered successfully");
		}
		catch(Exception e){
			AppLogs.debug(e);
			Assert.fail();
		}
	}
	
	public String getText(String locType,String locValue){
		By locator2=getLocator(locType,locValue);
		String text=null;
		try{
			AppLogs.debug("Retrieving text");
		text=driver.findElement(locator2).getText();
		AppLogs.debug("Text retrieved successfully");
		
		}
		catch(Exception e){
			AppLogs.debug(e);
		}
		return text;
	}
	
	public void verifyText(String locType,String locValue,String expected){
		try{
		String actual=getText(locType,locValue);
		AppLogs.debug("Verifying text");
		Assert.assertEquals(actual, expected);
		AppLogs.debug("Text verified successfully");
		}
		catch(Exception e){
			AppLogs.debug("Text not matched");
			Assert.fail();
		}
	}
	
	public void clickButton(String locType, String locValue){
		By locator2=getLocator(locType,locValue);
		
		try{
			AppLogs.debug("Clicking Button");
			//if(driver.findElement(locator2).isEnabled())
			
			driver.findElement(locator2).click();		
			AppLogs.debug("Button clicked successfully");
			
			/*if(driver.findElement(locator2).isSelected()){
				System.out.println("Button selected successfully");
			}
			*/
		}
		catch(Exception e){
			AppLogs.debug(e);
			//Assert.fail();
		}
	}
	
	public void windowHandle(String locType, String locValue){
				
		System.out.println("Parent window Title is: "+driver.getTitle());
		clickButton(locType,locValue);
		String pwh=null;
		String cwh=null;
		pwh=driver.getWindowHandle();
		Set<String> winElements=driver.getWindowHandles();	
		for(String win: winElements){
			if(win!=pwh){
				cwh=win;				
			}
		}
		driver.switchTo().window(cwh);
		System.out.println("Child Window Title is: "+driver.getTitle());
	}
	
	public boolean isSelectedTest(String locType, String locValue){
	
		By locator4=getLocator(locType, locValue);
		if(driver.findElement(locator4).isSelected()){
			System.out.println("Checkbox is selected");
			return true;
		}
		else
			return false;
	}
	
	public void dropDown(String locType, String locValue, int index){
		
		By locator3=getLocator(locType,locValue);
		WebElement dropdown=driver.findElement(locator3);
		Select sel = new Select(dropdown);
		sel.selectByIndex(index);
	}
	
	public void alertHandle(String locType, String locValue){
		By locator4=getLocator(locType,locValue);
		driver.findElement(locator4).click();
		Alert alert1 = driver.switchTo().alert();
		String message=alert1.getText();
		System.out.println("Alert Message: "+message);
		alert1.accept();
	}

	public By getLocator(String locType, String locValue){

		By locator=null;
		switch(locType){

		case "id":
				locator=By.id(locValue);
				break;
		case "name":
				locator=By.name(locValue);
				break;
		case "className":
				locator=By.className(locValue);
				break;
		case "xpath":
				locator=By.xpath(locValue);
				break;
		case "cssSelector" :
				locator=By.cssSelector(locValue);
				break;
		case "linkText" :
				locator=By.linkText(locValue);
				break;
		case "partialLinktext":
				locator= By.partialLinkText(locValue);
				break;
		}	
		return locator;
	}

	/*
	public static void main(String[] args) throws IOException{

	
		GlobalFunctions gf = new GlobalFunctions();
		String p = gf.readProperty();
		gf.openBrowser(p);
		gf.enterText("id","email","sdharmik@gmail.com");
		//gf.enterText("id", "pass", "test");
		//gf.clickButton("linkText","Train Berth Availability");
		//gf.alertHandle("name","submit2");
		//gf.clickButton("id","u_0_l");
		
		//gf.windowHandle("linkText", "How Do I");
		

	}
	*/
}
