package com.learneAutomation.selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFunctions {
	FileInputStream fis;
	XSSFWorkbook wb;
	XSSFSheet sheet;
	File fl;
	

	public ExcelFunctions (String excelPath) 
	{
	   try {
		   
		fl=new File(excelPath);
		fis=new FileInputStream(fl);
		wb = new XSSFWorkbook(fis);
	   }
	   
	   catch(Exception e) {
		   System.out.println(e.getMessage());
	   }
	}

    public String getdata(int index,int row,int column)
    {
    	
    		    sheet=wb.getSheetAt(index);
    			String data=sheet.getRow(row).getCell(column).getStringCellValue();
				return data;  	
    	
    } 
    
    public void putdata(int index,int row,int column,String strText) throws IOException
         {
    	     sheet=wb.getSheetAt(index);
    	     sheet.getRow(row).createCell(column).setCellValue(strText);
    	      
	 		FileOutputStream Fout= new FileOutputStream(fl);
		    wb.write(Fout);
    	    wb.close();
			}

}
