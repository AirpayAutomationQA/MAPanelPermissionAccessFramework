package com.Airpay.TestData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.aspose.cells.Workbook;

public class Excel_Permission_Access {

	private static XSSFWorkbook workbook;
	private static XSSFWorkbook workbook2;
	private static XSSFSheet sheet;
	@SuppressWarnings("unused")
	private static XSSFSheet sheet2;
	private static XSSFCell cell;	
	private static XSSFRow row;
	private static FileInputStream fis = null;
	private static FileInputStream fis2 = null;
	public static FileOutputStream fileOut = null;
	@SuppressWarnings("rawtypes")
	public static HashMap <String, HashMap> TestData;
	public static String fileFullPath;
	public static String fileFullPath2;
	public static String srcSheetName;
	public static String resultPath="";
	public static String resultSheetName="";
	public static String resultSheetName2="";

	public void ExcelReader(String fileName, String sheetname,String ResultPath,String ResultName) {
		try {
			fis = new FileInputStream(new File(fileName));
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetname);
			srcSheetName=sheetname;
			fileFullPath=fileName;
			resultPath=ResultPath;
			resultSheetName=ResultName;
			createcopy();
			fis.close();
			
			fis2 = new FileInputStream(new File(resultPath));
			workbook2 = new XSSFWorkbook(fis2);
			sheet2 = workbook2.getSheet(resultSheetName2);
			fileFullPath2=resultPath;
			srcSheetName=resultSheetName2;
			
			
		} catch (FileNotFoundException fnfEx) {
			System.out.println(fileName + " is not Found. please check the file name.");
			System.exit(0);
		} catch (IOException ioEx) {
			System.out.println(fis + " is not Found. please check the path.");
		} catch (Exception ex) {
			System.out.println("There is error reading/loading xls file, due to " + ex);
		}
	}
	
	public void createcopy() throws Exception
	{     
		File excel = new File(resultPath);
		if(!excel.exists()) {
		    try {
		    	
		    	@SuppressWarnings("resource")
				XSSFWorkbook workbook = new XSSFWorkbook(); 
			     
			      FileOutputStream out = new FileOutputStream(new File(resultPath));
			      workbook.createSheet(resultSheetName);
			      workbook.write(out);
			      out.close();		       
		    } 
		    catch(IOException e) {
		        System.out.println("Failed to create new file, \n" + e.getMessage()); //Log framework would be much better instead of system print outs
		}	
		}
		System.out.println(srcSheetName);    
		Workbook excelWorkbook1 = new Workbook(fileFullPath);
		Workbook excelWorkbook2 = new Workbook(resultPath);
		//excelWorkbook2.getWorksheets().add();
		excelWorkbook2.getWorksheets().get(0).copy(excelWorkbook1.getWorksheets().get(srcSheetName));
		excelWorkbook2.save(resultPath);
		FileInputStream fis2 = new FileInputStream(new File(resultPath));
		@SuppressWarnings("resource")
		XSSFWorkbook workbook2 = new XSSFWorkbook(fis2);
		workbook2.removeSheetAt(1);
		FileOutputStream fileOut2 = new FileOutputStream(resultPath);
		workbook2.write(fileOut2);
		fileOut2.close();
	}
	
}