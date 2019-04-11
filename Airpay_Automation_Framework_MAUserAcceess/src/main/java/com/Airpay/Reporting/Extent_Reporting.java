package com.Airpay.Reporting;

import org.openqa.selenium.WebDriver;

import com.Airpay.Utilities.Common_Functions_old;
import com.relevantcodes.extentreports.LogStatus;

public class Extent_Reporting {	
	
	public static void Log_Pass(String stepName, String passMessage){
		
		Report_Setup.test.log(LogStatus.PASS, stepName, passMessage);
        
	}
	
	public static void Log_Fail(String stepName, String failMessage, WebDriver driver) throws InterruptedException{
		
		Report_Setup.test.log(LogStatus.FAIL, stepName, failMessage);
		Thread.sleep(5000);
		String img = Report_Setup.test.addScreenCapture(Common_Functions_old.captureScreenshot(driver,stepName));
		Report_Setup.test.log(LogStatus.INFO, "Image", "Error Snap: " + img);
	}
	
	public static void Log_report_img(String stepName,String string ,WebDriver driver){

        String img = Report_Setup.test.addScreenCapture(Common_Functions_old.captureScreenshot1(driver, stepName));
    

        Report_Setup.test.log(LogStatus.PASS,stepName, string +img);

        

         }
	
	
	
	


}
