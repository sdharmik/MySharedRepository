package com.core.util;

import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class TestUtil {
	
	
	public static boolean verifyTCMode(ReadExcel excelObj, String testCaseName){
		
		boolean testCaseMode=false;
		
		
		for(int i=1;i<excelObj.getRowCount("Sheet1");i++){
			
			if(excelObj.getCellData("Sheet1","ID",i).toLowerCase().matches(testCaseName.toLowerCase())){
				
				if(excelObj.getCellData("Sheet1", "Mode",i).toLowerCase().matches("y"))
					testCaseMode=true;
				
				else
					testCaseMode=false;
				
			}
		}
		System.out.println("Test Case mode "+testCaseMode);
		return testCaseMode;	
		
	}
	
	public static Object[][] getData(ReadExcel obj, String testCase){
		if(!obj.isSheetExist(testCase)){
			int rows=obj.getRowCount(testCase);
			System.out.println("Total rows"+rows);
			return new Object[1][0];
		}
		
		int rows=obj.getRowCount(testCase);
		int cols=obj.getColumnCount(testCase);
		
		Object[][] myData= new Object[rows-1][cols-2];
		for(int i=1;i<rows;i++){
			for(int j=0;j<cols-2;j++){
				myData[i-1][j]=obj.getCellData(testCase,j,i);
			}
		}
		
		return myData;
	}

	/*
	public static void main(String[] args) throws BiffException, IOException{
		
		TestUtil tu = new TestUtil();
		ReadExcel re = new ReadExcel("D:\\Selenium\\WebDriver\\MyFramework\\src\\com\\core\\data\\ExcelTestcaseData.xls");
		boolean tcMode=tu.verifyTCMode(re,"tc-004");
		System.out.println("Test Case Mode: "+tcMode);
	}
	
	*/
}
