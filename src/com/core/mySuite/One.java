package com.core.mySuite;

import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.core.util.TestUtil;

public class One extends MyInitializations{
	
	@BeforeClass	
	public void verifyExecutionState(){
		
		if(!TestUtil.verifyTCMode(readExcel, this.getClass().getSimpleName())){
			AppLogs.debug(this.getClass().getSimpleName()+" :execution skipped as test execution mode set to No");
			throw new SkipException("execution skipped as test execution mode set to No");
		}
	}
	
	/*
	@Test(dataProvider="getData")
	public void One(String user, String password, String mode){
		
		//System.out.println("User: "+user+" Password: "+password+" Mode: "+mode);
		//System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
		
		launchBrowser();
		enterText("id", "email", "dharmiksameer");
		enterText("id", "pass", "xyz");
		clickButton("id", "u_0_l");
	}
	
	@DataProvider
	public Object[][] getData(){
		
		Object[][] myData=TestUtil.getData(readExcel,this.getClass().getSimpleName());
		return myData;
	}
	
	*/
	
	@Test
	public void fbTest(){
		launchBrowser();
		enterText("id", "email", "dharmiksameer");
		enterText("id", "pass", "xyz");
		clickButton("id", "u_0_l");
		
	}
	
}
