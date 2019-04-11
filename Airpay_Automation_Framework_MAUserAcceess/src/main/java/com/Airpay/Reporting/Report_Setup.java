package com.Airpay.Reporting;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.Airpay.Utilities.Constants;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


public class Report_Setup {
	
	public static ExtentTest test;
	static DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
    static Date date = new Date();

     static String d=dateFormat.format(date);
     static String filename="Summary"+d+".html";
    
	public static ExtentReports extent = new ExtentReports(Constants.reportPath+filename, true);
	
	public static void InitializeReport(String testCaseName){
		Constants.Resultfilename=filename;
		System.out.println(filename);
		 test = extent.startTest(testCaseName, "Execution started for : "+testCaseName);
		  		 
	}
		
	
	
}
