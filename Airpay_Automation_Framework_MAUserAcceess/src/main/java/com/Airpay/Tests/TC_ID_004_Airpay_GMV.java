package com.Airpay.Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.Airpay.BusinessLogic.AirPay_MA_Panel_Select_Merchant_BusinessLogic;
import com.Airpay.InitialSetup.Driver_Setup;
import com.Airpay.PageObject.AirPay_Payment_MA_Panel_PageObject;
import com.Airpay.Reporting.Extent_Reporting;
import com.Airpay.TestData.Excel_Handling;
import com.Airpay.Utilities.Create_TestNGXML;
import com.Airpay.Utilities.Log;

public class TC_ID_004_Airpay_GMV extends Driver_Setup{
	public static WebDriver webDriver = null;
	public static String tcID = null;
	public boolean flag = false;

	//Business Logic Class Object list	
	@Test(priority=1)
	public void setup()
	{
		Log.info("Setup the variable for Test");
		webDriver = driver; 
		tcID = ModuleName;
		Log.info("Setup completed for the variable");
	}
	@Test(priority = 2)
	public boolean TC_TestCaseName() throws Throwable {
		try {						
			Log.info("Script Starts..");
			AirPay_MA_Panel_Select_Merchant_BusinessLogic MA_panel = new AirPay_MA_Panel_Select_Merchant_BusinessLogic(driver, ModuleName);
			Create_TestNGXML test = new Create_TestNGXML();
			MA_panel.MA_Panel_Login();			
			if(Excel_Handling.Get_Data(ModuleName, "ProceedWithNavigation").trim().equalsIgnoreCase("Y"))
			{						
				if(Excel_Handling.Get_Data(ModuleName, "PermissionAccess").trim().equalsIgnoreCase("Y"))
				{			    	
					if(MA_panel.Home_LHS_Dashboard(AirPay_Payment_MA_Panel_PageObject.ReportsNextMenu)==true)
					{
						Extent_Reporting.Log_Pass("Home Menu Is exist", "Passed");
						Extent_Reporting.Log_report_img("Home Menu", "Passed", driver);
					}else{
						Extent_Reporting.Log_Fail("Home menu does not exist", "fail", driver);
					}
					if(MA_panel.Home_LHS_Dashboard(AirPay_Payment_MA_Panel_PageObject.ChangePWDSetting)==true)
					{
						Extent_Reporting.Log_Pass("Change Setting Is exist", "Passed");
						Extent_Reporting.Log_report_img("Change Setting Link", "Passed", webDriver);
						test.NavigationWithTodaysDate("Pass");						
					}else{
						Extent_Reporting.Log_Fail("Change Setting does not exist", "Failed", webDriver);
						test.NavigationWithTodaysDate("Fail");						
					}
				}else{
					Extent_Reporting.Log_Fail("You have given wrong Permission Access for Navigation", "Failed", webDriver);		    	
				}			

			}
			if(Excel_Handling.Get_Data(ModuleName, "ProceedWithURL").trim().equalsIgnoreCase("Y"))
			{				
				if(Excel_Handling.Get_Data(ModuleName, "PermissionAccess").trim().equalsIgnoreCase("Y"))
				{			    	
					if(MA_panel.VerifyPageThroughLink()==true)
					{
						if(driver.findElements(By.xpath(AirPay_Payment_MA_Panel_PageObject.ChangePWDSetting)).size()>0)
						{
							Extent_Reporting.Log_Pass("Change Setting is exist", "Passed");
							Extent_Reporting.Log_report_img("Change Setting", "Passed", webDriver);
							test.URLWithTodaysDate("Pass");						
						}else{
							Extent_Reporting.Log_Fail("Change Setting does not exist", "Failed", webDriver);
							test.URLWithTodaysDate("Fail");						
						}					 
					}else{
						Extent_Reporting.Log_Fail("There is an link issue", "Failed", webDriver);
						test.URLWithTodaysDate("Link issue");						
					}
				}else{
					Extent_Reporting.Log_Fail("You have given wrong Permission Access for URL", "Failed", webDriver);		    						 					    	
					test.URLWithTodaysDate("You have given N status");						

				}		
			}else{
				Extent_Reporting.Log_Fail("You have given wrong Proceedwith url Access for URL", "Failed", webDriver);		    	
			}
		}catch (Exception e) {
			Log.error(e.getMessage());
			System.out.println(e);
			flag = false;
		}
		return true;
	}
	@Test(priority = 3)
	public void PermissionResult() throws Throwable{
		if(TC_TestCaseName()==true){
			System.out.println("Pass");
		}else{
			System.out.println("fail");
		}
	}
	@AfterTest
	public void tearDown()
	{
		if(webDriver != null)
			webDriver.close();
	}
}