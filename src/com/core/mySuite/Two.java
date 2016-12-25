package com.core.mySuite;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.core.util.TestUtil;



public class Two extends MyInitializations{
	
	@BeforeMethod
	public void verifyExecutionState(Method method){
		if(!TestUtil.verifyTCMode(readExcel, method.getName())){
			AppLogs.debug(method.getName()+" : execution skipped as the runmode is set to no");
			throw new SkipException("Test case execution skipped as the runmode is set to no");
		}
	}

	
	@Test
	public void two(){
		
		System.out.println("Test Case-2");
		String expected_title="abc";
		String actual_title="xyz";
		
		try{
			Assert.assertEquals(expected_title, actual_title);
		}
		catch(Throwable t){
			AppLogs.debug(t);
		}
		
	}
	
	@Test
	public void three(){
		
		System.out.println("Test Case-3");
		String expected_title="abc";
		String actual_title="xyz";
		
		try{
			Assert.assertEquals(expected_title, actual_title);
		}
		catch(Throwable t){
			AppLogs.debug(t);
		}
		
	}
}
