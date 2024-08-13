package com.ninza.hrm.api.genericUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ExcelFileUtility {

	public String getDataFromExcelFile(String sheetName,int rowNum, int celNum) throws Throwable {
		
		FileInputStream fis=new FileInputStream("./APITestCaseDocument/ExcelData.xlsx");
		Workbook Wb = WorkbookFactory.create(fis);
		String data= Wb.getSheet(sheetName).getRow(rowNum).getCell(celNum).getStringCellValue();
		Wb.close();
		return data;
		
	}
	
	public int getRowCount(String sheetName) throws Throwable {
		
		FileInputStream fis=new FileInputStream("./APITestCaseDocument/ExcelData.xlsx");
		Workbook Wb = WorkbookFactory.create(fis);
		int rowCount =Wb.getSheet(sheetName).getLastRowNum();
		Wb.close();
		return rowCount;
	}
	
    public void setDataIntoExcel(String sheetName, int rowNum, int celNum, String data) throws Throwable  {
    	FileInputStream fis=new FileInputStream("./APITestCaseDocument/ExcelData.xlsx");
		Workbook Wb = WorkbookFactory.create(fis);
		Wb.getSheet(sheetName).getRow(rowNum).createCell(celNum).setCellValue(data);
		
		FileOutputStream fos = new FileOutputStream("./APITestCaseDocument/ExcelData.xlsx");
		Wb.write(fos);
		Wb.close();
    }
}
