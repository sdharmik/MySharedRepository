package com.core.mySuite;

import org.testng.annotations.BeforeSuite;

import com.core.common.GlobalFunctions;

public class MyInitializations extends GlobalFunctions{
	
	@BeforeSuite
	public void Initializations() throws Exception{
		initialize();
	}

}
