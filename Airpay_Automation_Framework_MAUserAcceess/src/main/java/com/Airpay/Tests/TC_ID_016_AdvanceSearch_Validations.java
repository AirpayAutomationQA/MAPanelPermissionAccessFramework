package com.Airpay.Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.Airpay.BusinessLogic.AirPay_MA_Panel_Select_Merchant_BusinessLogic;
import com.Airpay.InitialSetup.Driver_Setup;
import com.Airpay.Utilities.Log;

public class TC_ID_016_AdvanceSearch_Validations extends Driver_Setup{
	//public static WebDriver webDriver = null;
	public static String tcID = null;
	public boolean flag = false;
	
	//Business Logic Class Object list	
	/*@Test(priority=1)
	public void setup()
	{
		Log.info("Setup the variable for Test");
		webDriver = driver; 
		tcID = ModuleName;
		Log.info("Setup completed for the variable");
	}*/
	@Test(priority = 1)
	public boolean TC_TestCaseName() throws Throwable 
	{
		try {
			AirPay_MA_Panel_Select_Merchant_BusinessLogic MA_panel = new AirPay_MA_Panel_Select_Merchant_BusinessLogic(driver, ModuleName);
			MA_panel.AdvanceSearchVerification();
			MA_panel.AdvanceSearchDropbox();
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
	
}