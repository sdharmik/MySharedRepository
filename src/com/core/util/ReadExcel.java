package com.core.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {
	
	public FileInputStream fis;
	public Workbook wb;
	public Sheet sh;
	String data;
	boolean isSheetPresent;
	
	
	public ReadExcel(String path) throws BiffException, IOException{
		fis = new FileInputStream(path);
		wb=Workbook.getWorkbook(fis);
		
	}
	
	
	public int getRowCount(String sheetName){
	
			sh=wb.getSheet(sheetName);
		int row= sh.getRows();
		return row;
	}
	
	
	public int getColumnCount(String sheetName){
		sh=wb.getSheet(sheetName);
		int cols=sh.getColumns();
		return cols;
	}
	
	/*
	public String[][] getCellData(Sheet s,int rows,int cols) throws BiffException, IOException{
		
		String[][] data = new String[rows][cols];
		for(int i=0;i<rows;i++)
		{		
			for(int j=0;j<cols;j++)
			{
				data[i][j]=s.getCell(j, i).getContents();
			}
		}
		return data;		
	}
	
	*/
	
	public String getCellData(String sheetName, int colNum, int rowNum){
		
		sh=wb.getSheet(sheetName);
		return sh.getCell(colNum, rowNum).getContents();
	}
	
	
	public String getCellData(String sheetName, String colName, int rowNum){
		
		
		sh=wb.getSheet(sheetName);
		int colNum=0;
		int totalCols=sh.getColumns();
		
		for(int i=0; i<totalCols;i++){
			
		String col=sh.getCell(i, 0).getContents();
			
			if(col.equals(colName))
			
			{
				
				colNum = i;
			}
			
		}
		return sh.getCell(colNum,rowNum).getContents();

	}
	
	public boolean isSheetExist(String sheetName){
		Sheet[] sheets = wb.getSheets();
		
		for(int i=0;i<sheets.length;i++){
			if(sheets[i].getName().matches(sheetName))
			isSheetPresent=true;
		}
		return isSheetPresent;
	}
	

	/*
	public static void main(String[] args) throws BiffException, IOException
	
	{
		ReadExcel re = new ReadExcel("D:\\Selenium\\WebDriver\\MyFramework\\src\\com\\core\\data\\ExcelTestcaseData.xls");
		int totalRows=re.getRowCount("Sheet1");
		int totalColumns=re.getColumnCount("Sheet1");
		String cellData1=re.getCellData("Sheet1", 2, 3);
		String cellData2=re.getCellData("Sheet1", "Mode", 2);
		System.out.println("Total Rows "+totalRows);
		System.out.println("Total Columns "+totalColumns);
		System.out.println("Cell Data for first method "+cellData1);
		System.out.println("Cell Data for second method "+cellData2);
	
	
	}
	*/
}
	
			

