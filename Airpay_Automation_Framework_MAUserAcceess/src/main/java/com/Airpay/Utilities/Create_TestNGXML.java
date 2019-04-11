 package com.Airpay.Utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.Airpay.Reporting.Report_Setup;
import com.Airpay.TestData.Excel_Handling;
import com.sun.tracing.dtrace.ModuleName;


public class Create_TestNGXML {	
	private static XSSFWorkbook workbook;
	private static FileInputStream fis = null;
	private static FileOutputStream filwrit = null;

	public  static int  Sheetcount ;
	public static String SheetnameTest ="";
	private static final String TASKLIST = "tasklist";
	private static final String KILL = "taskkill /F /IM ";
	public File f;
	public static int k;
	public static String NavigationWithTodaysDate = "";
	public static String URLWithTodaysDate = "";
	public static String keys;
	Excel_Handling excel = new Excel_Handling();
	public List<XmlInclude> constructIncludes (String... methodNames) {
        List<XmlInclude> includes = new ArrayList<XmlInclude> ();
        for (String eachMethod : methodNames) {
            includes.add (new XmlInclude (eachMethod));
        }
        return includes;
    }
	
	@SuppressWarnings("deprecation")
	@Test     
    public void createXMLfile () throws Exception {
		
		Runtime.getRuntime().exec(Constants.deleteAllTempFileBatchlocation);		
		killProcessRunning("IEDriverServer.exe");
		killProcessRunning("iexplore.exe *32");
		killProcessRunning("iexplore.exe");
		killProcessRunning("ALM-Client.exe");
		killProcessRunning("chromedriver.exe");	
		killProcessRunning("chrome.exe");
		killProcessRunning("scalc.exe");	
    	//calling out the excel datasheet instance to get all the "Y" data for setting up the testngxml
		// Excel sheet 1 st one.........................................	 		
		fis = new FileInputStream(new File(Constants.datasheetPath+"Datasheet.xlsx"));
		workbook = new XSSFWorkbook(fis);	
		Sheetcount= workbook.getNumberOfSheets();
			
		for( k=0;k<Sheetcount;k++)
		{					
			SheetnameTest = workbook.getSheetName(k);
			System.out.println(SheetnameTest);		
			excel.ExcelReader(Constants.datasheetPath+"Datasheet.xlsx", SheetnameTest, Constants.datasheetPath+"Datasheet_Result.xlsx", SheetnameTest);
			try {
				excel.getExcelDataAll(SheetnameTest, "Execute", "Y", "ModuleName");
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date date = new Date();
				String currentdate = sdf.format(date);	
				NavigationWithTodaysDate = "Navigation_Status "+currentdate;
				URLWithTodaysDate = "URL_Status "+currentdate;
				Excel_Handling.addColumn(Constants.datasheetPath+"Datasheet.xlsx",SheetnameTest,NavigationWithTodaysDate);
				Excel_Handling.addColumn(Constants.datasheetPath+"Datasheet.xlsx",SheetnameTest,URLWithTodaysDate);
				if(excel.TestData.isEmpty())
				{
					continue;
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		        @SuppressWarnings({ "rawtypes", "static-access" })
		Map<String, HashMap> map = excel.TestData;              
        for ( String key : map.keySet()){
        	keys = key;
        	System.out.println("out "+keys);
        	//creation of the testng xml based on parameters/data
        	TestNG testNG = new TestNG();
        	XmlSuite suite = new XmlSuite ();
            suite.setName (new Common_Functions_old().GetXMLTagValue(Constants.configPath+"Config.xml", "Regression_Suite_Name"));
	        if(Integer.parseInt(Excel_Handling.Get_Data(key, "Browser_Instance"))>1){
	        	suite.setParallel("tests");
        	 	suite.setThreadCount(Integer.parseInt(Excel_Handling.Get_Data(key, "Browser_Instance")));
	        	for(int i=1;i<=Integer.parseInt(Excel_Handling.Get_Data(key, "Browser_Instance"));i++){
	        		XmlTest test = new XmlTest (suite);        		
	        		test.setName (key+"_Instance_"+i);
	    	        test.setPreserveOrder("false");
	    	        test.addParameter("browserType", Excel_Handling.Get_Data(key, "Browser_Type"));
	    	        test.addParameter("tcID", key);
	    	        test.addParameter("appURL", new Common_Functions_old().GetXMLTagValue(Constants.configPath+"Config.xml", "AppUrl")); 	        
	        		test.addParameter("temp", "temp"+i);	        		
	        		XmlClass testClass = new XmlClass ();
	        		testClass.setName ("com.Airpay.Tests."+Excel_Handling.Get_Data(key, "Class_Name"));	        	
	    	        test.setXmlClasses (Arrays.asList (new XmlClass[] { testClass}));
	        	}        		
        	}else{
        		XmlTest test = new XmlTest (suite);
            	test.setName (key);            	
    	        test.setPreserveOrder ("true");
    	        test.addParameter("browserType", Excel_Handling.Get_Data(key, "Browser_Type"));
    	        test.addParameter("ModuleName", key);
    	        test.addParameter("appURL", new Common_Functions_old().GetXMLTagValue(Constants.configPath+"Config.xml", "AppUrl")); 	        
        		XmlClass testClass = new XmlClass ();
        		testClass.setName ("com.Airpay.Tests."+Excel_Handling.Get_Data(key, "Class_Name"));
    	        test.setXmlClasses (Arrays.asList (new XmlClass[] { testClass}));
        		
        	}
	        List<String> suites = new ArrayList<String>();
	        final File f1 = new File(Create_TestNGXML.class.getProtectionDomain().getCodeSource().getLocation().getPath());
	        f = new File(f1+"\\testNG.xml");
	        f.createNewFile();
	        FileWriter fw = new FileWriter(f.getAbsoluteFile());
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write(suite.toXml());
	        bw.close();
	        
	        suites.add(f.getPath());
	        
	        testNG.setTestSuites(suites);
	        com.Airpay.Reporting.Report_Setup.InitializeReport(key);
	        testNG.run();
        	}
	        Report_Setup.extent.endTest(Report_Setup.test);
    	
	        f.delete();
        } 
		
        Report_Setup.extent.flush();    
	
    }
	
	public void NavigationWithTodaysDate(String Status) {
		try {
			fis = new FileInputStream(new File(Constants.datasheetPath+"Datasheet.xlsx"));
			workbook = new XSSFWorkbook(fis);
			workbook.getName(SheetnameTest);
			System.out.println(SheetnameTest);
			filwrit = new FileOutputStream(new File(Constants.datasheetPath+"Datasheet.xlsx"));		
			Excel_Handling.setCellDataWithCondtion(NavigationWithTodaysDate,"ModuleName",keys, Status);			
			filwrit.close();
			fis.close();			
			excel.close();					
		} catch (FileNotFoundException fnfEx) {
			System.out.println("file is not Found. please check the file name.");
			System.exit(0);
		} catch (IOException ioEx) {
			System.out.println(fis + " is not Found. please check the path.");
		} catch (Exception ex) {
			System.out.println("There is error reading/loading xls file, due to " + ex);
		}
	}
	
	
	public void URLWithTodaysDate(String Status) {
		try {
			fis = new FileInputStream(new File(Constants.datasheetPath+"Datasheet.xlsx"));
			workbook = new XSSFWorkbook(fis);
			workbook.getName(SheetnameTest);
			System.out.println(SheetnameTest);
			
			filwrit = new FileOutputStream(new File(Constants.datasheetPath+"Datasheet.xlsx"));		
			Excel_Handling.setCellDataWithCondtion(URLWithTodaysDate,"ModuleName",keys, Status);
			
			filwrit.close();
			fis.close();			
			excel.close();					
		} catch (FileNotFoundException fnfEx) {
			System.out.println("file is not Found. please check the file name.");
			System.exit(0);
		} catch (IOException ioEx) {
			System.out.println(fis + " is not Found. please check the path.");
		} catch (Exception ex) {
			System.out.println("There is error reading/loading xls file, due to " + ex);
		}
	}
	
	
	
	
	public boolean killProcessRunning(String serviceName) throws Exception {
		boolean flag = false;
		try
		{
			
			Process p = Runtime.getRuntime().exec(TASKLIST);
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.contains(serviceName)) {
					Runtime.getRuntime().exec(KILL+serviceName);
					flag= true;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;
	}



}
