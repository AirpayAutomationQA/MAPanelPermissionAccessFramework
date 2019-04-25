package com.Airpay.BusinessLogic;



import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.Airpay.PageObject.AirPay_Payment_MA_Panel_PageObject;

import com.Airpay.Reporting.Extent_Reporting;
import com.Airpay.TestData.Excel_Handling;
import com.Airpay.Utilities.Create_TestNGXML;
import com.Airpay.Utilities.ElementAction;
import com.Airpay.Utilities.Log;


public class AirPay_MA_Panel_Select_Merchant_BusinessLogic extends AirPay_Payment_MA_Panel_PageObject {

	public WebDriver driver;
	public String ModuleName = "";
	public static String MA_URL = null;
	public static String FieldName = null;

	Create_TestNGXML test = new 	Create_TestNGXML();
	ElementAction Assert = new ElementAction();
	public AirPay_MA_Panel_Select_Merchant_BusinessLogic(WebDriver driver, String ModuleName)
	{
		Log.info("AirPay_MA_Panel_Select_Merchant_BusinessLogic");

		this.driver = driver;
		this.ModuleName=ModuleName
;
	}
	/**
	 * @author sakole
	 * @throws Exception 
	 */
	public void MA_Panel_Login() throws Exception{
		try{
			MA_URL = Excel_Handling.Get_Data(ModuleName, "RootURL").trim();
			driver.navigate().to(MA_URL);
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.MA_UserID, "USER ID")){
				Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.MA_UserID, Excel_Handling.Get_Data(ModuleName, "MA_UserID").trim(), "MA Panel USer ID");
				Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.MA_PWD, Excel_Handling.Get_Data(ModuleName, "MA_Password").trim(), "");
				Extent_Reporting.Log_report_img("Login Details entered", "Passed", driver);
				Assert.clickButton(driver, AirPay_Payment_MA_Panel_PageObject.MA_SubmitBtn, "Sign In button");
				Assert.waitForPageToLoad(driver);
				Thread.sleep(10000);
			}else{

				Extent_Reporting.Log_Fail("MA Panel User field does not exist", "Failed", driver);
			}			
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("MA Panel User field does not exist", "Failed", driver);
			throw new Exception("MA panel page issue");
		}
	}
	public static String MA_ActualUrl = "";
	public static String MA_Expected_URL ="";
	public static String  URLConcat = "";
	public boolean VerifyPageThroughLink() throws Exception{
		try{
			MA_ActualUrl = Excel_Handling.Get_Data(ModuleName, "RootURL").trim();
			String Link = Excel_Handling.Get_Data(ModuleName, "Link").trim();
			URLConcat = MA_ActualUrl.concat(Link);
			driver.navigate().to(URLConcat);
			Assert.waitForPageToLoad(driver);
			Thread.sleep(7000);
			MA_ActualUrl = driver.getCurrentUrl().trim();
			System.out.println("Actual URL: "+URLConcat);			
			if(MA_ActualUrl.equalsIgnoreCase(URLConcat))
			{
				Extent_Reporting.Log_report_img("Menu redirected as expected", "Passed", driver);
				System.out.println("Links got matched as expected");				
			}else{
				Extent_Reporting.Log_Fail("Passed URL is: "+URLConcat, "Actual URL is: "+MA_ActualUrl, driver);			
				return false;
			}			
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("URL Not Matched for "+ModuleName, "Failed", driver);
			throw new Exception("MA panel page issue");
		
		}
		return true;
	}

	public boolean NavigationPageVerify() throws Exception{
		try{
			Thread.sleep(15000);
			MA_ActualUrl = Excel_Handling.Get_Data(ModuleName, "RootURL").trim();
			String Link = Excel_Handling.Get_Data(ModuleName, "Link").trim();
			URLConcat = MA_ActualUrl.concat(Link);
			Assert.waitForPageToLoad(driver);		
			MA_ActualUrl = driver.getCurrentUrl().trim();
			System.out.println("Actual URL: "+URLConcat);
			if(MA_ActualUrl.equalsIgnoreCase(URLConcat))
			{
				Extent_Reporting.Log_report_img("Menu redirected as expected", "Passed", driver);
			}else{
				Extent_Reporting.Log_Fail("Passed URL is: "+URLConcat, "Actual URL is: "+MA_ActualUrl, driver);			
				return false;
			}			
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("URL Not Matched for "+ModuleName, "Failed", driver);
			throw new Exception("MA panel page issue");
		
		}
		return true;
	}
	
	public static String MerchantName =null;
	public void Select_Merchant() throws Exception{
		try{			
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.MM_SelectMerchantSymbol, "Select Merchant ID")){			
				MerchantName = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_SelectMerchantSymbol)).getAttribute("title").trim();
				if(MerchantName.equalsIgnoreCase("Select Merchant")){
					Extent_Reporting.Log_report_img("Select Merchant", "Passed", driver);
					Assert.clickButton(driver, AirPay_Payment_MA_Panel_PageObject.MM_SelectMerchantSymbol , "Select Merchant");									
					Assert.waitForPageToLoad(driver);
					Assert.WaitUntilExist(driver, AirPay_Payment_MA_Panel_PageObject.MM_Merchant_Search);
				}else{
					Extent_Reporting.Log_Pass("Merchant already selected", "Passed");
					Extent_Reporting.Log_report_img("Merchant selected as: "+MerchantName, "Passed", driver);
				}				
			}else{

				Extent_Reporting.Log_Fail("MA Panel User field does not exist", "Failed", driver);
			}			
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("MA Panel User field does not exist", "Failed", driver);
			throw new Exception("MA panel page issue");
		}
	}


	public void Filter_Merchant() throws Throwable{
		try{
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.MM_Merchant_Search, "Merchant Search"))
			{
				Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.MM_Merchant_Search, Excel_Handling.Get_Data(ModuleName, "Merchant_ID").trim(), "Merchant ID");
				Assert.clickButton(driver, AirPay_Payment_MA_Panel_PageObject.MM_Merchant_Search, "Search Entered");
				Thread.sleep(10000);
				Extent_Reporting.Log_report_img("Merchant ID Entered", "Passed", driver);
				Assert.Javascriptexecutor_forClick(driver, AirPay_Payment_MA_Panel_PageObject.MM_Merchant_Search, "Merchant ID ");
				Thread.sleep(10000);
				Assert.waitForPageToLoad(driver);
				Assert.WaitUntilExist(driver, AirPay_Payment_MA_Panel_PageObject.MM_Merchant_Select);	
			}else{
				Extent_Reporting.Log_Fail("Merchant ID search field does not exost", "Failed", driver);
			}			
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Merchant ID search field does not exost", "Failed", driver);
			throw new Exception("Serch filter does not exist");
		}
	}


	public void Choose_Merchant() throws Exception{
		try{
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.MM_Merchant_Select, "Merchant Select")){	
				MerchantName=driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_Merchant_IDName)).getText().trim();
				Assert.Clickbtn(driver, AirPay_Payment_MA_Panel_PageObject.MM_Merchant_SelectBtn+"["+1+"]", "Select Merchant");
				Assert.waitForPageToLoad(driver);
				Thread.sleep(20000);
				String SelectedMerchant = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_SelectMerchantSymbol)).getText().trim();
				if(MerchantName.equalsIgnoreCase(SelectedMerchant))
				{				
					Extent_Reporting.Log_Pass("Merchant selected as :"+SelectedMerchant, "Expected Merchant AS:"+MerchantName);
					Extent_Reporting.Log_report_img("Merchant selected", "Passed", driver);

				}else{
					Extent_Reporting.Log_Fail("Selected Merchant Name is: "+SelectedMerchant, "Updated Merchant name is:"+MerchantName, driver);
				}				
			}else{
				Extent_Reporting.Log_Fail("Merchant ID search field does not exost", "Failed", driver);
			}				
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Merchant ID search field does not exost", "Failed", driver);
			throw new Exception("Serch filter does not exist");

		}
	}


	public  boolean Home_LHS_Dashboard(String MenuOption ) throws Throwable{
		try{
			String FieldName = "";
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, MenuOption,  "Main Menu"))
			{		
				FieldName = driver.findElement(By.xpath(MenuOption)).getText().trim();
				System.out.println("FieldName:"+FieldName );
				Assert.Javascriptexecutor_forClick(driver, MenuOption, FieldName+" Respective Menu");
				Assert.waitForPageToLoad(driver);
				Extent_Reporting.Log_report_img(FieldName+" Respective link or button", "Passed", driver);			
			}else{
				Extent_Reporting.Log_Fail(FieldName + "Respective menu does not exist", "Failed", driver);
				return false;
			}				
		}catch(Exception t){
			return false;
		}
		return true;
	}

	public static String ReportsMenu = "";
	public static String sheetName = Create_TestNGXML.SheetnameTest;
		
	public boolean Reports_TabVerify() throws Throwable{
	try{			
					switch(sheetName)
					{
						case "Admin": 						         
							         if(Assert.isElementDisplayed(driver, "//*[text()='"+FieldName+"']", FieldName+ " Menu"))
							         {							            						            	  
							                 	Assert.Javascriptexecutor_forClick(driver, "//*[text()='"+FieldName+"']", FieldName+"Menu");
							                 	VerifyPageThroughLink();							            	  							            	  
							              }else
							              {													            	  
							            	  Extent_Reporting.Log_Fail(FieldName+" Menu Not exist", "Fail", driver);
							              }							              
							             break;							  													
						case  "Brand":							         							
										 chkstatusReport();					
										 break;
							             
						case  "Merchant":						
										 chkstatusReport();					
										 break;
							    
							    
						case  "Partner":
										chkstatusReport();					
										break;
								
						case  "Airpay OPS":
										chkstatusReport();					
										break;
								
						case  "Airpay User":
										chkstatusReport();					
										break;
								
						case  "Tech User":
										chkstatusReport();					
										break;
								
						case  "Airpay Payments":
										chkstatusReport();					
										break;
					
						default:
							             Extent_Reporting.Log_Fail("UserId not valid", "Fail", driver);
									
					}			
		}catch(Exception t){
			return false;
		}
		return true;
	}
	
	public boolean chkstatusReport() throws Throwable{
	try{
		FieldName = Excel_Handling.Get_Data(ModuleName, "Fields").trim();
		 if(Excel_Handling.Get_Data(ModuleName, "PermissionAccess").trim().equalsIgnoreCase("Y"))
		 {
			 System.out.println(FieldName);
				 if(Assert.isElementDisplayed(driver, "//*[text()='"+FieldName+"']", FieldName+ "  Menu")){	
			       	  Assert.Javascriptexecutor_forClick(driver, "//*[text()='"+FieldName+"']", FieldName+"Menu");
		       	       if(VerifyPageThroughLink()==true){
		 		       	  test.NavigationWithTodaysDate("Pass");		
		       	       }else{
			 		       	  test.NavigationWithTodaysDate("Fail");		
		       	       }
		         }else{     	 
		        	 Extent_Reporting.Log_Fail(FieldName+" Menu Not exist", "Fail", driver);
			       	 test.NavigationWithTodaysDate("fail");		

		         }	
		 }else
		 {				 
			 	MA_ActualUrl = Excel_Handling.Get_Data(ModuleName, "RootURL").trim();
				String Link = Excel_Handling.Get_Data(ModuleName, "Link").trim();
				URLConcat = MA_ActualUrl.concat(Link);
				driver.navigate().to(URLConcat);
				Assert.waitForPageToLoad(driver);
				Thread.sleep(5000);
				MA_ActualUrl = driver.getCurrentUrl().trim();
				System.out.println("Actual URL: "+URLConcat);
				if(!MA_ActualUrl.equalsIgnoreCase(URLConcat))
				{
					Extent_Reporting.Log_report_img("URL are different", "As expected", driver);
					System.out.println("Links didn't matched got matched as expected");	
			       	test.NavigationWithTodaysDate("Pass");		

				}else{
					Extent_Reporting.Log_Fail("Passed URL is: "+URLConcat, "Actual URL is: "+MA_ActualUrl, driver);
			       	test.NavigationWithTodaysDate("fail");		
					return false;
				}		 
		 }
		}catch(Exception t){
			t.printStackTrace();
	       	test.NavigationWithTodaysDate("fail");		
			throw new Exception("Serch filter does not exist");
		}
		return true;	
	}
	
	public boolean URLDashboard() throws Throwable{
		try{
			//FieldName = Excel_Handling.Get_Data(ModuleName, "Fields").trim();
			 if(Excel_Handling.Get_Data(ModuleName, "PermissionAccess").trim().equalsIgnoreCase("N"))
			 { 
				    MA_ActualUrl = Excel_Handling.Get_Data(ModuleName, "RootURL").trim();
					String Link = Excel_Handling.Get_Data(ModuleName, "Link").trim();
					URLConcat = MA_ActualUrl.concat(Link);
					driver.navigate().to(URLConcat);
					Assert.waitForPageToLoad(driver);
					Thread.sleep(5000);
					MA_ActualUrl = driver.getCurrentUrl().trim();
					System.out.println("Actual URL: "+URLConcat);
					if(!MA_ActualUrl.equalsIgnoreCase(URLConcat))
					{
						Extent_Reporting.Log_report_img("URL are different", "As expected", driver);
						System.out.println("Links didn't matched got matched as expected");	
				       	test.URLWithTodaysDate("Pass");		

					}else{
						Extent_Reporting.Log_Fail("Passed URL is: "+URLConcat, "Actual URL is: "+MA_ActualUrl, driver);
				       	test.URLWithTodaysDate("fail");		
						return false;
					}		  
			 }else
			 {				 
					test.URLWithTodaysDate("I think, You have given the Wrong permission Access");
			 }
			}catch(Exception t){
				t.printStackTrace();
		       	test.NavigationWithTodaysDate("fail");		
				throw new Exception("Serch filter does not exist");
			}
			return true;	
		}
	
	
	public boolean NavigationDashboard() throws Throwable{
		try{
			//FieldName = Excel_Handling.Get_Data(ModuleName, "Fields").trim();
			 if(Excel_Handling.Get_Data(ModuleName, "PermissionAccess").trim().equalsIgnoreCase("N"))
			 { 
				    MA_ActualUrl = Excel_Handling.Get_Data(ModuleName, "RootURL").trim();
					String Link = Excel_Handling.Get_Data(ModuleName, "Link").trim();
					URLConcat = MA_ActualUrl.concat(Link);
					driver.navigate().to(URLConcat);
					Assert.waitForPageToLoad(driver);
					Thread.sleep(5000);
					MA_ActualUrl = driver.getCurrentUrl().trim();
					System.out.println("Actual URL: "+URLConcat);
					if(!MA_ActualUrl.equalsIgnoreCase(URLConcat))
					{
						Extent_Reporting.Log_report_img("URL are different", "As expected", driver);
						System.out.println("Links didn't matched got matched as expected");	
				       	test.NavigationWithTodaysDate("Pass");		

					}else{
						Extent_Reporting.Log_Fail("Passed URL is: "+URLConcat, "Actual URL is: "+MA_ActualUrl, driver);
				       	test.NavigationWithTodaysDate("fail");		
						
					}		  
			 }else
			 {				 
					test.URLWithTodaysDate("I think, You have given the Wrong permission Access");
			 }
			}catch(Exception t){
				t.printStackTrace();
		       	test.NavigationWithTodaysDate("fail");		
				throw new Exception("Serch filter does not exist");
			}
			return true;	
		}
	
	public boolean chkstatusReportForURL() throws Throwable{
		
		 if(Excel_Handling.Get_Data(ModuleName, "PermissionAccess").trim().equalsIgnoreCase("Y"))
		 {
			 VerifyPageThroughLink();
			 test.URLWithTodaysDate("Pass");
		 }else
		 {				 
			 	MA_ActualUrl = Excel_Handling.Get_Data(ModuleName, "RootURL").trim();
				String Link = Excel_Handling.Get_Data(ModuleName, "Link").trim();
				URLConcat = MA_ActualUrl.concat(Link);
				driver.navigate().to(URLConcat);
				Assert.waitForPageToLoad(driver);
				Thread.sleep(5000);
				MA_ActualUrl = driver.getCurrentUrl().trim();
				System.out.println("Actual URL: "+URLConcat);
				if(!MA_ActualUrl.equalsIgnoreCase(URLConcat))
				{
					Extent_Reporting.Log_report_img("URL are different", "As expected", driver);
					System.out.println("Links didn't matched got matched as expected");
					 test.URLWithTodaysDate("Pass");

				}else{
					Extent_Reporting.Log_Fail("Passed URL is: "+URLConcat, "Actual URL is: "+MA_ActualUrl, driver);
					 test.URLWithTodaysDate("Fail");
					return false;
				}		 
		 }
		return true;	
	}
	
	public void CLickbtn(String MenuOption, String Option ) throws Throwable{
		try{
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, MenuOption, "RespectiveLHS Menu"))
			{	
				Assert.Javascriptexecutor_forClick(driver, MenuOption, "Respective Menu");
				Assert.waitForPageToLoad(driver);
				Extent_Reporting.Log_report_img(Option, "Passed", driver);			
			}else{
				Extent_Reporting.Log_Fail(Option+"vdoes not exost", "Failed", driver);
			}				
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail(Option+"vdoes not exost", "Failed", driver);
			throw new Exception("Serch filter does not exist");

		}
	}


	public void AdvancesearchDropDown() throws Throwable{
		try{
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.MA_FilterCardTypeDropDown, "Select card Type"))
			{	
				Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, AirPay_Payment_MA_Panel_PageObject.MA_FilterCardTypeDropDown, Excel_Handling.Get_Data(ModuleName, "").trim(), "card Type Drop down");
				Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, AirPay_Payment_MA_Panel_PageObject.MA_FilterChannelDropDown, Excel_Handling.Get_Data(ModuleName, "").trim(), "Channel Drop down");
				Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, AirPay_Payment_MA_Panel_PageObject.MA_FilterMCCDropDown, Excel_Handling.Get_Data(ModuleName, "").trim(), "Select MCC drop down");
				Assert.waitForPageToLoad(driver);
				Extent_Reporting.Log_report_img("Respective details filled", "Passed", driver);			
			}else{
				Extent_Reporting.Log_Fail("Drop down data issue might be", "Failed", driver);
			}				
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Drop down data issue might be", "Failed", driver);
			throw new Exception("Serch filter does not exist");

		}
	}


	public void VerifyText(String Xpath, String TextVerify) throws Exception{
		try{
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, Xpath, "Respective Text Xpath"))
			{	
				String TextVal = driver.findElement(By.xpath(Xpath)).getText().trim();
				System.out.println(TextVal);
				if(TextVal.equalsIgnoreCase(TextVerify)){
					Extent_Reporting.Log_Pass("Field name is: "+TextVal, "Expected as: "+TextVerify);
					Extent_Reporting.Log_report_img("Respective Link or field is exist", "As expected", driver);			

				}else{
					Extent_Reporting.Log_Fail("Respective text Does not exist as "+TextVal , "Failed", driver);
				}			
			}else{

				Extent_Reporting.Log_Fail("Respective text Does not exist" , "Failed", driver);
			}				
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective menu does not exost", "Failed", driver);
			throw new Exception("Serch filter does not exist");

		}
	}


	public void GetTextButNotCompare(String Xpath) throws Exception{
		try{
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, Xpath, "Respective Text Xpath"))
			{	
				TextVal = driver.findElement(By.xpath(Xpath)).getText().trim();
				System.out.println(TextVal);		
			}else{

				Extent_Reporting.Log_Fail("Respective text Does not exist" , "Failed", driver);
			}				
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective menu does not exost", "Failed", driver);
			throw new Exception("Serch filter does not exist");

		}
	}


	public void VerifyTextWithPassingData(String Xpath, String TextVerify) throws Exception{
		try{
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, Xpath, "Respective Text Xpath"))
			{


				String TextVal = driver.findElement(By.xpath(Xpath)).getText().trim();
				System.out.println(TextVal);
				if(TextVal.equalsIgnoreCase(TextVerify)){
					Extent_Reporting.Log_Pass("Field name is: "+TextVal, "Expected as: "+TextVerify);
					Extent_Reporting.Log_report_img("Respective Link or field is exist", "As expected", driver);			

				}else{
					Extent_Reporting.Log_Fail("Respective text Does not exist as "+TextVal , "Failed", driver);
				}			
			}else{

				Extent_Reporting.Log_Fail("Respective text Does not exist" , "Failed", driver);
			}				
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective menu does not exost", "Failed", driver);
			throw new Exception("Serch filter does not exist");

		}
	}

	public void NewRuleAddDetails() throws Exception{
		try{
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.TitleInput, "Respective Text Xpath"))
			{									
				Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.TitleInput, Excel_Handling.Get_Data(ModuleName, "RuleTitleName").trim(), "Title name");
				Assert.Clickbtn(driver, AirPay_Payment_MA_Panel_PageObject.MCC_FieldClick, "MCCField box is");
				Thread.sleep(1000);
				Assert.Clickbtn(driver, AirPay_Payment_MA_Panel_PageObject.MCC_FirstOptionClick, "MCC Firstoption");
				Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, AirPay_Payment_MA_Panel_PageObject.ChannelName, Excel_Handling.Get_Data(ModuleName, "ChannelName"), "Channel name");
				//BankNameEnableDisable();
				//Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, AirPay_Payment_MA_Panel_PageObject.ChannelName, "netbnk", "Channel name");
				Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, AirPay_Payment_MA_Panel_PageObject.CardBankName, Excel_Handling.Get_Data(ModuleName, "BankName_MAPanel"), "Channel name");
				Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, AirPay_Payment_MA_Panel_PageObject.AmountSelectBox, "Greater than", "Amount Greater Than");				
				Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.AmountEditBox, Excel_Handling.Get_Data(ModuleName, "MaxAmtVal"), "Title Name");
				Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.PTCEditBox, Excel_Handling.Get_Data(ModuleName, "PTC_Vlaue"), "PTC value");
				Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.MSFEditBox, Excel_Handling.Get_Data(ModuleName, "MSF_Value"), "MSF Value");						
			}else{

				Extent_Reporting.Log_Fail("Rule Title Name should be want unique" , "Failed", driver);
			}				
		}catch(Exception t){
			Extent_Reporting.Log_Fail("Rule Title Name should be want unique", "Failed", driver);
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective menu does not exost", "Failed", driver);
			throw new Exception("Serch filter does not exist");

		}
	}


	public void NewRuleAddMerchantDetails() throws Exception{
		try{
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.TitleInput, "Respective Text Xpath"))
			{									
				Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.TitleInput, Excel_Handling.Get_Data(ModuleName, "RuleTitleName").trim(), "Title name");
				Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, AirPay_Payment_MA_Panel_PageObject.ChannelName, Excel_Handling.Get_Data(ModuleName, "ChannelName").trim(), "Channel name");			
				if((Excel_Handling.Get_Data(ModuleName, "ChannelName").trim().contains("pg"))||(Excel_Handling.Get_Data(ModuleName, "ChannelName").trim().contains("emi"))
						||(Excel_Handling.Get_Data(ModuleName, "ChannelName").trim().contains("pos")))
				{	
					Assert.clickButton(driver, AirPay_Payment_MA_Panel_PageObject.MA_DomesticRadioBtn, "Domestic radio button");
					Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, AirPay_Payment_MA_Panel_PageObject.MA_CardTypeDropDown, Excel_Handling.Get_Data(ModuleName, "Card_Type").trim(), "Card Type");
					Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, AirPay_Payment_MA_Panel_PageObject.MA_SchemeTypeDropDown, Excel_Handling.Get_Data(ModuleName, "Scheme_type").trim(), "Scheme Type");					
					Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, AirPay_Payment_MA_Panel_PageObject.AmountSelectBox, "Greater than", "Amount Greater Than");				
					Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.AmountEditBox, Excel_Handling.Get_Data(ModuleName, "MaxAmtVal"), "Title Name");
				}
				else if(Excel_Handling.Get_Data(ModuleName, "ChannelName").trim().contains("imps"))
				{					
					Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, AirPay_Payment_MA_Panel_PageObject.AmountSelectBox, "Greater than", "Amount Greater Than");				
					Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.AmountEditBox, Excel_Handling.Get_Data(ModuleName, "MaxAmtVal"), "Title Name");	
				}else
				{				
					Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, AirPay_Payment_MA_Panel_PageObject.CardBankName, Excel_Handling.Get_Data(ModuleName, "BankName_MAPanel"), "Channel name");
					Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, AirPay_Payment_MA_Panel_PageObject.AmountSelectBox, "Greater than", "Amount Greater Than");				
					Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.AmountEditBox, Excel_Handling.Get_Data(ModuleName, "MaxAmtVal"), "Title Name");
				}
				Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.PTCEditBox, Excel_Handling.Get_Data(ModuleName, "PTC_Vlaue"), "PTC value");
				Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.MSFEditBox, Excel_Handling.Get_Data(ModuleName, "MSF_Value"), "MSF Value");						
				Extent_Reporting.Log_report_img("Respective Details entered", "Passed", driver);

			}else{

				Extent_Reporting.Log_Fail("Rule Title Name should be want unique" , "Failed", driver);
			}				
		}catch(Exception t){
			Extent_Reporting.Log_Fail("Rule Title Name should be want unique", "Failed", driver);
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective menu does not exost", "Failed", driver);
			throw new Exception("Serch filter does not exist");

		}
	}

	public void DuplicateMerchantRuleAdd() throws Exception{
		try{
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.TitleInput, "Respective Text Xpath"))
			{									
				Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.TitleInput, TextVal, "Title name");
				Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, AirPay_Payment_MA_Panel_PageObject.ChannelName, Excel_Handling.Get_Data(ModuleName, "ChannelName").trim(), "Channel name");			
				if((Excel_Handling.Get_Data(ModuleName, "ChannelName").trim().contains("pg"))||(Excel_Handling.Get_Data(ModuleName, "ChannelName").trim().contains("emi"))
						||(Excel_Handling.Get_Data(ModuleName, "ChannelName").trim().contains("pos")))
				{	
					Assert.clickButton(driver, AirPay_Payment_MA_Panel_PageObject.MA_DomesticRadioBtn, "Domestic radio button");
					Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, AirPay_Payment_MA_Panel_PageObject.MA_CardTypeDropDown, Excel_Handling.Get_Data(ModuleName, "Card_Type").trim(), "Card Type");
					Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, AirPay_Payment_MA_Panel_PageObject.MA_SchemeTypeDropDown, Excel_Handling.Get_Data(ModuleName, "Scheme_type").trim(), "Scheme Type");					
					Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, AirPay_Payment_MA_Panel_PageObject.AmountSelectBox, "Greater than", "Amount Greater Than");				
					Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.AmountEditBox, Excel_Handling.Get_Data(ModuleName, "MaxAmtVal"), "Title Name");
				}
				else if(Excel_Handling.Get_Data(ModuleName, "ChannelName").trim().contains("imps"))
				{					
					Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, AirPay_Payment_MA_Panel_PageObject.AmountSelectBox, "Greater than", "Amount Greater Than");				
					Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.AmountEditBox, Excel_Handling.Get_Data(ModuleName, "MaxAmtVal"), "Title Name");	
				}else
				{				
					Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, AirPay_Payment_MA_Panel_PageObject.CardBankName, Excel_Handling.Get_Data(ModuleName, "BankName_MAPanel"), "Channel name");
					Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, AirPay_Payment_MA_Panel_PageObject.AmountSelectBox, "Greater than", "Amount Greater Than");				
					Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.AmountEditBox, Excel_Handling.Get_Data(ModuleName, "MaxAmtVal"), "Title Name");
				}
				Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.PTCEditBox, Excel_Handling.Get_Data(ModuleName, "PTC_Vlaue"), "PTC value");
				Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.MSFEditBox, Excel_Handling.Get_Data(ModuleName, "MSF_Value"), "MSF Value");						
				Extent_Reporting.Log_report_img("Respective Details entered", "Passed", driver);
			}else{

				Extent_Reporting.Log_Fail("Rule Title Name should be want unique" , "Failed", driver);
			}				
		}catch(Exception t){
			Extent_Reporting.Log_Fail("Rule Title Name should be want unique", "Failed", driver);
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective menu does not exost", "Failed", driver);
			throw new Exception("Serch filter does not exist");

		}
	}



	public void EditSurchargeRuleVerifyPage() throws Exception{
		try{
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.TitleInput, "Respective Text Xpath"))
			{									
				Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.TitleInput, "Title name");
				Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.ChannelName, "Channel name");
				Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.AmountSelectBox, "Amount Greater Than");				
				Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.AmountEditBox,  "Title Name");
				Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.PTCEditBox, "PTC value");
				Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.MSFEditBox,  "MSF Value");						
			}else{

				Extent_Reporting.Log_Fail("Rule Title Name should be want unique" , "Failed", driver);
			}				
		}catch(Exception t){
			Extent_Reporting.Log_Fail("Rule Title Name should be want unique", "Failed", driver);
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective menu does not exost", "Failed", driver);
			throw new Exception("Serch filter does not exist");

		}
	}

	public void EditConvienceSurchargeRuleVerifyPage() throws Exception{
		try{
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.TitleInput, "Respective Text Xpath"))
			{									
				Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.TitleInput, "Title name");
				Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.ChannelName, "Channel name");
				Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.AmountSelectBox, "Amount Greater Than");				
				Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.AmountEditBox,  "Title Name");
				Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.PTCEditBox, "PTC value");
				Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.MSFEditBox,  "MSF Value");
				String DataPTCVal= Excel_Handling.Get_Data(ModuleName, "PTC_Vlaue").trim();
				String MSFDataVal= Excel_Handling.Get_Data(ModuleName, "MSF_Value").trim();
				double ptcaddvalue =Double.parseDouble(DataPTCVal);
				double msfaddvalue =Double.parseDouble(MSFDataVal);
				ptcaddvalue++;
				msfaddvalue++;
				System.out.println(ptcaddvalue);
				System.out.println(msfaddvalue);				
				Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.PTCEditBox,Double.toString(ptcaddvalue) , "PTC value");
				Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.MSFEditBox,Double.toString(msfaddvalue) , "MSF Value");
			}else{

				Extent_Reporting.Log_Fail("Rule Title Name should be want unique" , "Failed", driver);
			}				
		}catch(Exception t){
			Extent_Reporting.Log_Fail("Rule Title Name should be want unique", "Failed", driver);
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective menu does not exost", "Failed", driver);
			throw new Exception("Serch filter does not exist");

		}
	}


	public void BankNameEnableDisable() throws Exception{
		try{			
			List<WebElement> BankDropCount = driver.findElements(By.xpath(AirPay_Payment_MA_Panel_PageObject.BankNameDropBox));
			System.out.println("Bank name count: "+BankDropCount );
			for(int i=0;i<BankDropCount.size();i++)
			{				
				boolean bankEnable = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.BankNameDropBox+"["+(i+1)+"]")).isEnabled();
				System.out.println("BankStatus: "+bankEnable);	
				System.out.println("I count:"+ (i+1));
				if(bankEnable==true)
				{					
					Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.BankNameDropBox+"["+i+1+"]", Excel_Handling.Get_Data(ModuleName, "BankName_MAPanel").trim(), "Bank Name drop down");
					break;					
				}
				if(i==BankDropCount.size()-1){
					Extent_Reporting.Log_Fail("Respective Bank Name does not exist", "Failed", driver);
				}			
			}			
		}catch(Exception t){
			Extent_Reporting.Log_Fail("Rule Title Name should be want unique", "Failed", driver);
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective menu does not exost", "Failed", driver);
			throw new Exception("Serch filter does not exist");

		}



	}

	public void RuleCreatedVerify() throws Exception{
		try{
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.RuleCreatedVerify, "Details Rule button"))
			{				
				String FetchName = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.FetchRulename)).getText().trim();
				Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.RuleEditButton, "Edit button");
				Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.RuleCreatedVerify, "Details button");
				Assert.Clickbtn(driver, AirPay_Payment_MA_Panel_PageObject.RuleCreatedVerify, "Rule Created");
				if(FetchName.contains(Excel_Handling.Get_Data(ModuleName, "RuleTitleName").trim()))
				{
					Extent_Reporting.Log_report_img("Rule Created successfully", "Passed", driver);
				}else{
					Extent_Reporting.Log_Fail("Rule Title Name did not matched" , "Failed", driver);
				}
			}else{

				Extent_Reporting.Log_Fail("Rule Title Name does not exist" , "Failed", driver);
			}				
		}catch(Exception t){
			Extent_Reporting.Log_Fail("Rule Title Name does not exist" , "Failed", driver);
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective menu does not exost", "Failed", driver);
			throw new Exception("Serch filter does not exist");

		}
	}

	public void MerchantRuleCreatedVerify() throws Throwable{
		try{
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.FetchRulename, "Rule name"))
			{				
				String FetchName = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.FetchRulename)).getText().trim();
				Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.RuleEditButton, "Edit button");
				Assert.Javascriptexecutor_forClick(driver, AirPay_Payment_MA_Panel_PageObject.RuleEditButton, "Edit button");
				if(FetchName.contains(Excel_Handling.Get_Data(ModuleName, "RuleTitleName").trim()))
				{
					Extent_Reporting.Log_report_img("Rule Created successfully", "Passed", driver);
				}else{
					Extent_Reporting.Log_Fail("Rule Title Name did not matched" , "Failed", driver);
				}
			}else{

				Extent_Reporting.Log_Fail("Rule Title Name does not exist" , "Failed", driver);
			}				
		}catch(Exception t){
			Extent_Reporting.Log_Fail("Rule Title Name does not exist" , "Failed", driver);
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective menu does not exost", "Failed", driver);
			throw new Exception("Serch filter does not exist");

		}
	}

	public void MerchantRuleCreatedVerifyTest() throws Throwable{
		try{
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.FetchRulename, "Rule name"))
			{				
				String FetchName = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.FetchRulename)).getText().trim();
				Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.RuleEditButton, "Edit button");
				//Assert.Javascriptexecutor_forClick(driver, AirPay_Payment_MA_Panel_PageObject.RuleEditButton, "Edit button");
				if(FetchName.contains(Excel_Handling.Get_Data(ModuleName, "RuleTitleName").trim()))
				{
					Extent_Reporting.Log_report_img("Rule Created successfully", "Passed", driver);
				}else{
					Extent_Reporting.Log_Fail("Rule Title Name did not matched" , "Failed", driver);
				}
			}else{

				Extent_Reporting.Log_Fail("Rule Title Name does not exist" , "Failed", driver);
			}				
		}catch(Exception t){
			Extent_Reporting.Log_Fail("Rule Title Name does not exist" , "Failed", driver);
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective menu does not exost", "Failed", driver);
			throw new Exception("Serch filter does not exist");

		}
	}


	public void RuleCreatedEditMode() throws Exception{
		try{
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.RuleEditButton, "Details Rule button"))
			{				
				Assert.Clickbtn(driver, AirPay_Payment_MA_Panel_PageObject.RuleEditButton, "Rule Edit button");
				Extent_Reporting.Log_report_img("Rule Edit button clicked" , "Failed", driver);				
			}else{
				Extent_Reporting.Log_Fail("Rule Title Name does not exist" , "Failed", driver);
			}				
		}catch(Exception t){
			Extent_Reporting.Log_Fail("Rule Title Name does not exist" , "Failed", driver);
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective menu does not exost", "Failed", driver);
			throw new Exception("Serch filter does not exist");

		}
	}


	public void RuleAddDetails() throws Exception{
		try{
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.TitleInput, "Respective Text Xpath"))
			{									
				Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.TitleInput, TextVal, "Title name");
				Assert.Clickbtn(driver, AirPay_Payment_MA_Panel_PageObject.MCC_FieldClick, "MCCField box");
				Thread.sleep(1000);
				Assert.Clickbtn(driver, AirPay_Payment_MA_Panel_PageObject.MCC_FirstOptionClick, "MCC First option");
				Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, AirPay_Payment_MA_Panel_PageObject.ChannelName, Excel_Handling.Get_Data(ModuleName, "ChannelName"), "Channel name");
				//Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, AirPay_Payment_MA_Panel_PageObject.ChannelName, "netbnk", "Channel name");
				Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, AirPay_Payment_MA_Panel_PageObject.CardBankName, Excel_Handling.Get_Data(ModuleName, "BankName_MAPanel"), "Channel name");
				Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, AirPay_Payment_MA_Panel_PageObject.AmountSelectBox, "Greater than", "Amount Greater Than");				
				Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.AmountEditBox, Excel_Handling.Get_Data(ModuleName, "MaxAmtVal"), "Title Name");
				Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.PTCEditBox, Excel_Handling.Get_Data(ModuleName, "PTC_Vlaue"), "PTC value");
				Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.MSFEditBox, Excel_Handling.Get_Data(ModuleName, "MSF_Value"), "MSF Value");						
			}else{

				Extent_Reporting.Log_Fail("Respective text Does not exist" , "Failed", driver);
			}				
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective menu does not exost", "Failed", driver);
			throw new Exception("Serch filter does not exist");

		}
	}

	public void MCC_MultipleChoiceDropDownVerify() throws Exception{
		try{
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.TitleInput, "Respective Text Xpath"))
			{									
				Assert.Clickbtn(driver, AirPay_Payment_MA_Panel_PageObject.MCC_FieldClick, "MCCField box");
				Thread.sleep(1000);
				Assert.Clickbtn(driver, AirPay_Payment_MA_Panel_PageObject.MCC_FirstOptionClick, "MCC First option");
				Assert.Clickbtn(driver, AirPay_Payment_MA_Panel_PageObject.MCC_FieldClick, "MCCField box");
				Thread.sleep(1000);
				Assert.Clickbtn(driver, AirPay_Payment_MA_Panel_PageObject.MCC_SecondOptionClick, "MCC second option");
				Assert.Clickbtn(driver, AirPay_Payment_MA_Panel_PageObject.MCC_FieldClick, "MCCField box");
				Thread.sleep(1000);
				Assert.Clickbtn(driver, AirPay_Payment_MA_Panel_PageObject.MCC_thirdOptionClick, "MCC third option");
				Extent_Reporting.Log_report_img("It's selecting more than one MCC drop box value", "Passed", driver);
				Thread.sleep(1000);				
				List<WebElement> crossCount = driver.findElements(By.xpath("//span[@class='select2-selection__choice__remove']"));
				for(int i=1;i<=crossCount.size();i++)
				{
					driver.findElement(By.xpath("//span[@class='select2-selection__choice__remove']")).click();
				}
				Extent_Reporting.Log_report_img("It's removed MCC drop box value", "Passed", driver);
				Thread.sleep(1000);
			}else{

				Extent_Reporting.Log_Fail("Respective text Does not exist" , "Failed", driver);
			}				
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective menu does not exost", "Failed", driver);
			throw new Exception("Serch filter does not exist");

		}
	}


	public void ErrorNotifications(String xpath, String ErrXpath, String passVal,String ExpectedVal) throws Exception{
		try{
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, xpath, "Respective Text Xpath"))
			{									
				Assert.inputText(driver, xpath, passVal, "Title Name");
				Assert.Clickbtn(driver, AirPay_Payment_MA_Panel_PageObject.SurchargeSubmitBtn, "Submit button");
				String TextVal = driver.findElement(By.xpath(ErrXpath)).getText().trim();
				System.out.println(TextVal);
				if(TextVal.equalsIgnoreCase(ExpectedVal.trim()))
				{
					Extent_Reporting.Log_Pass("Field name is: "+TextVal, "Expected as: "+ExpectedVal);
					Extent_Reporting.Log_report_img("Respective Message is exist", "As expected", driver);			
				}else{
					Extent_Reporting.Log_Fail("Respective text Does not exist as "+TextVal , "Failed", driver);
				}						
			}else{

				Extent_Reporting.Log_Fail("Respective text Does not exist" , "Failed", driver);
			}				
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective Message does not exost", "Failed", driver);
			throw new Exception("Serch filter does not exist");

		}
	}








	public void DuplicateRuleNameErrorExist(String MesgName) throws Exception{
		try{

			Assert.waitForPageToLoad(driver);
			Thread.sleep(5000);
			WebElement hiddenDiv = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.ExistRuleErrorMsg));
			String errMsg = hiddenDiv.getText(); // does not work (returns "" as expected)
			String script = "return arguments[0].innerText";
			errMsg = (String) ((JavascriptExecutor) driver).executeScript(script, hiddenDiv);			
			System.out.println(errMsg);
			if(errMsg.contains(MesgName))
			{
				Extent_Reporting.Log_Pass("Repective  Message is exist", "Respective Msg is exist as :"+errMsg);
				Extent_Reporting.Log_report_img("Respective Message is exist", "Passed", driver);	

			}else
			{	
				Extent_Reporting.Log_Fail("Repective Message does not exist", "Error Msg is:"+errMsg, driver);
			}	

		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective menu does not exost", "Failed", driver);
			throw new Exception("Serch filter does not exist");

		}
	}






	public static String TextVal = null;
	public void GetTextFromWeb(String Xpath) throws Exception{
		try{
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, Xpath, "Respective Text Xpath"))
			{	
				TextVal = driver.findElement(By.xpath(Xpath)).getText().trim();
				System.out.println(TextVal);
				if(!TextVal.isEmpty()){
					Extent_Reporting.Log_Pass("Field name is: "+TextVal, "As Expected");
					Extent_Reporting.Log_report_img("Respective Link or field is exist", "As expected", driver);			
				}else{
					Extent_Reporting.Log_Fail("Respective text Does not exist as "+TextVal , "Failed", driver);
				}			
			}else{

				Extent_Reporting.Log_Fail("Respective text Does not exist" , "Failed", driver);
			}				
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective menu does not exost", "Failed", driver);
			throw new Exception("Serch filter does not exist");

		}
	}

	public void InputValue(String Xpath,String HardCodeVal) throws Exception{
		try{
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, Xpath, "Respective Text Xpath"))
			{	
				Assert.inputText(driver, Xpath, HardCodeVal, "Value entered");		
			}else{

				Extent_Reporting.Log_Fail("Respective Input field Does not exist" , "Failed", driver);
			}				
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective menu does not exost", "Failed", driver);
			throw new Exception("Serch filter does not exist");

		}
	}



	public void DetailsLinkClicked(String Xpath, String Deatils) throws Exception{
		try{
			if(Assert.isElementDisplayed(driver, Xpath, Deatils+" is"))
			{				
				Assert.Clickbtn(driver, Xpath, Deatils+" ");
				Extent_Reporting.Log_Pass("Field name is: "+Deatils, "Expected as: "+Deatils);
				Extent_Reporting.Log_report_img("Respective Link Clicked", "Passed", driver);								
			}else{				
				Extent_Reporting.Log_Fail("Respective text Does not exist" , "Failed", driver);
			}				
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective Link does not exost", "Failed", driver);
			throw new Exception("Serch filter does not exist");			
		}
	}



	public static String flag = null;
	public void Verify_Button_Color(String Xpath) throws Exception{
		try{
			if(Assert.isElementDisplayed(driver, Xpath, "RespectiveButton"))
			{					
				WebElement switchLabel = driver.findElement(By.xpath(Xpath));
				String pseudo = ((JavascriptExecutor)driver)
						.executeScript("return window.getComputedStyle(arguments[0]).getPropertyValue('right');",switchLabel).toString();		
				System.out.println("sudo:"+pseudo);
				if(pseudo.equalsIgnoreCase("47px")){
					System.out.println("OFF Save Card");
					Assert.Clickbtn(driver, Xpath, "OFF Clicked");
					Thread.sleep(5000);
					Extent_Reporting.Log_Pass("ON Activated", "Passed");
					Extent_Reporting.Log_report_img("ON Activate", "IMG", driver);
					flag= "ON";
				}else if(pseudo.equalsIgnoreCase("0px")){					
					System.out.println("ON Save Card");
					Assert.Clickbtn(driver, Xpath, "ON Clicked");
					Thread.sleep(5000);
					Extent_Reporting.Log_Pass("OFF Activated", "Passed");
					Extent_Reporting.Log_report_img("OFF Activate", "IMG", driver);
					flag = "OFF";
				}else{
					Extent_Reporting.Log_Fail("Save button does not exist", "Failed", driver);
				}						
			}else{

				Extent_Reporting.Log_Fail("Respective text Does not exist" , "Failed", driver);
			}				
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective menu does not exost", "Failed", driver);
			throw new Exception("Serch filter does not exist");

		}
	}
	
	
	public String Verify_SurchargeFlag_Mode(String Xpath) throws Exception{
		try{
			if(Assert.isElementDisplayed(driver, Xpath, "RespectiveButton"))
			{					
				WebElement switchLabel = driver.findElement(By.xpath(Xpath));
				String pseudo = ((JavascriptExecutor)driver)
						.executeScript("return window.getComputedStyle(arguments[0]).getPropertyValue('right');",switchLabel).toString();		
				System.out.println("sudo:"+pseudo);
				if(pseudo.equalsIgnoreCase("47px"))
				{
					System.out.println("OFF Save Card");
					Thread.sleep(5000);
					Extent_Reporting.Log_Pass("ON Activated", "Passed");
					Extent_Reporting.Log_report_img("ON Activate", "IMG", driver);
					flag= "OFF";
				}else if(pseudo.equalsIgnoreCase("0px"))
				{					
					System.out.println("ON Save Card");
					Thread.sleep(5000);
					Extent_Reporting.Log_Pass("OFF Activated", "Passed");
					Extent_Reporting.Log_report_img("OFF Activate", "IMG", driver);
					flag = "ON";
				}else{
					Extent_Reporting.Log_Fail("Save button does not exist", "Failed", driver);
				}						
			}else{

				Extent_Reporting.Log_Fail("Respective text Does not exist" , "Failed", driver);
			}
			return flag;
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective menu does not exost", "Failed", driver);
			throw new Exception("Serch filter does not exist");

		}
	}

	public void Verify_Surchagre_OFFVerify(String Xpath) throws Exception{
		try{
			if(Assert.isElementDisplayed(driver, Xpath, "RespectiveButton"))
			{					
				WebElement switchLabel = driver.findElement(By.xpath(Xpath));
				String pseudo = ((JavascriptExecutor)driver)
						.executeScript("return window.getComputedStyle(arguments[0]).getPropertyValue('right');",switchLabel).toString();		
				System.out.println("sudo:"+pseudo);
				if(pseudo.equalsIgnoreCase("47px")){
					System.out.println("OFF Save Card");
					Thread.sleep(5000);
					Extent_Reporting.Log_Pass("OFF Activated", "Passed");
					Extent_Reporting.Log_report_img("OFF Activated", "IMG", driver);
					flag= "ON";
				}else if(pseudo.equalsIgnoreCase("0px")){					
					Extent_Reporting.Log_Fail("Default rule should not be OFF", "Failed", driver);
					Extent_Reporting.Log_report_img("OFF Activate", "IMG", driver);
				}else{
					Extent_Reporting.Log_Fail("Save button does not exist", "Failed", driver);
				}						
			}else{

				Extent_Reporting.Log_Fail("Respective text Does not exist" , "Failed", driver);
			}				
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective menu does not exost", "Failed", driver);
			throw new Exception("Serch filter does not exist");

		}
	}


	public void Verify_Surchagre_Button(String Xpath) throws Exception{
		try{
			if(Assert.isElementDisplayed(driver, Xpath, "RespectiveButton"))
			{					
				WebElement switchLabel = driver.findElement(By.xpath(Xpath));
				String pseudo = ((JavascriptExecutor)driver)
						.executeScript("return window.getComputedStyle(arguments[0]).getPropertyValue('right');",switchLabel).toString();		
				System.out.println("sudo:"+pseudo);
				if(pseudo.equalsIgnoreCase("0px")){					
					System.out.println("ON Save Card");
					Assert.Clickbtn(driver, Xpath, "ON Clicked");
					Thread.sleep(5000);
					Assert.Clickbtn(driver, AirPay_Payment_MA_Panel_PageObject.MM_Update_surchagrebtn, "Update button");					
					Extent_Reporting.Log_Pass("OFF Activated", "Passed");
					Extent_Reporting.Log_report_img("OFF Activate", "IMG", driver);
					flag = "OFF";
				}else if(pseudo.equalsIgnoreCase("0px")){										
					Extent_Reporting.Log_Fail("Default rule should not be OFF", "Failed", driver);
					Extent_Reporting.Log_report_img("OFF Activate", "IMG", driver);
					flag = "OFF";
				}else{
					Extent_Reporting.Log_Fail("Save button does not exist", "Failed", driver);
				}						
			}else{

				Extent_Reporting.Log_Fail("Respective text Does not exist" , "Failed", driver);
			}				
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective menu does not exost", "Failed", driver);
			throw new Exception("Serch filter does not exist");

		}
	}

	public void SubmitBtnClick() throws Exception{
		try{
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.MM_SigleSubmitBtn, "Submit button"))
			{	
				Assert.Clickbtn(driver, AirPay_Payment_MA_Panel_PageObject.MM_SigleSubmitBtn, "Submit button");
				Assert.waitForPageToLoad(driver);
				Thread.sleep(5000);
				Extent_Reporting.Log_report_img("Submit button", "Passed", driver);			
			}else{
				Extent_Reporting.Log_Fail("Submit button does not exost", "Failed", driver);
			}				
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Submit button does not exost", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");

		}
	}



	public void ON_OFF_SaveCrad() throws Exception{
		try{
			Assert.waitForPageToLoad(driver);
			if(flag.equalsIgnoreCase("ON"))
			{	
				boolean checkbxselected = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_PP_SaveCardchkbox)).isSelected();
				System.out.println(checkbxselected);
				if(checkbxselected==true)
				{
					Extent_Reporting.Log_Pass("Check box selected", "Passed");
					Extent_Reporting.Log_report_img("Submit button", "Passed", driver);
				}else{
					Extent_Reporting.Log_Fail("Check boc shuld be check", "Failed", driver);
				}

			}else if(flag.equalsIgnoreCase("OFF")){
				boolean checkbxselected = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_PP_SaveCardchkbox)).isSelected();
				if(checkbxselected==false)
				{
					Extent_Reporting.Log_Pass("Check box not selected", "Passed");
					Extent_Reporting.Log_report_img("Submit button", "Passed", driver);

				}else{
					Extent_Reporting.Log_Fail("Check boc shuld not be checked by default", "Failed", driver);
				}				
			}else{
				Extent_Reporting.Log_Fail("Save Card option does not exist", "Failed", driver);
			}
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Save Card option does not exist", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");

		}
	}

	public void Enable_Disable_SaveCardChkBox() throws Exception{
		try{
			Assert.waitForPageToLoad(driver);
			if(flag.equalsIgnoreCase("ON"))
			{					
				try{
					if(Assert.isElementDisplay(driver, AirPay_Payment_MA_Panel_PageObject.MM_PP_SaveCardchkbox))
					{
						Extent_Reporting.Log_Pass("Save card check box is exist as expected ", "Passed");
						Extent_Reporting.Log_report_img("Save card check box", "Passed", driver);	
					}
				}catch(Exception t){

					Extent_Reporting.Log_Fail("Check box should be present for save Card but its not there", "Failed", driver);				
				}		
			}else if(flag.equalsIgnoreCase("OFF")){
				try{
					if(Assert.isElementDisplay(driver, AirPay_Payment_MA_Panel_PageObject.MM_PP_SaveCardchkbox))
					{
						Extent_Reporting.Log_Fail("Check box should Not be present for Save Card", "Failed", driver);						
					}
				}catch(Exception t){
					Extent_Reporting.Log_Pass("Save card check box is exist as expected ", "Passed");
					Extent_Reporting.Log_report_img("Save card check box", "Passed", driver);

				}					
			}else{
				Extent_Reporting.Log_Fail("Save Card option does not exist", "Failed", driver);
			}
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Save Card option does not exist", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");

		}
	}


	public static int Counter;
	public void Domain_URLFindOut() throws Exception{
		try{
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails, "URL Detail Table"))
			{					
				List<WebElement> URLRows = driver.findElements(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails));
				System.out.println(""+URLRows.size());
				for(int i=1;i<=URLRows.size();i=i+2)
				{
					String DomainName = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+i+"]/td[2]")).getText().trim();
					String SuccessURL = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+i+"]/td[3]")).getText().trim();
					String IpnUrl = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+i+"]/td[4]")).getText().trim();
					if(DomainName.equalsIgnoreCase(Excel_Handling.Get_Data(ModuleName, "Doamin_name").trim())
							&& SuccessURL.equalsIgnoreCase(Excel_Handling.Get_Data(ModuleName, "SuccessURL").trim())
							&& IpnUrl.equalsIgnoreCase(Excel_Handling.Get_Data(ModuleName, "IpnURL").trim())){

						Extent_Reporting.Log_Pass("Exact URL Row Find out at :"+i+"th Position", "Passed");
						Assert.clickButton(driver, AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+i+"]/td[7]/div[@class='btnlink']/a", "Details Button");
						Counter = i;
						break;						
					}
					if(i==URLRows.size())
					{
						Extent_Reporting.Log_Fail("Respective URL Does not exist", "Failed", driver);
					}
				}
			}else{
				Extent_Reporting.Log_Fail("Respective URL Does not exist", "Failed", driver);
			}				
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective URL Does not exist", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");

		}
	}


	public static int Maxval=1;
	public static int Minval=1;
	public void BANK_DetailsFindOut() throws Exception{
		try{
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails, "URL Detail Table"))
			{					
				List<WebElement> Bank_URLRows = driver.findElements(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+Counter+"]/td[7]/div[@class='btnlink']"
						+AirPay_Payment_MA_Panel_PageObject.MM_Bank_URL));
				System.out.println("Bank Rows: "+Bank_URLRows.size());
				for(int i=1;i<=Bank_URLRows.size();i++)
				{
					String BankName = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+Counter+"]/td[7]/div[@class='btnlink']"+AirPay_Payment_MA_Panel_PageObject.MM_Bank_URL+"["+i+"]/td[1]")).getText().trim();
					System.out.println("bankName:"+BankName);
					String MidType = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+Counter+"]/td[7]/div[@class='btnlink']"+AirPay_Payment_MA_Panel_PageObject.MM_Bank_URL+"["+i+"]/td[2]")).getText().trim();
					System.out.println("bankName:"+MidType);
					String BankStatus = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+Counter+"]/td[7]/div[@class='btnlink']"+AirPay_Payment_MA_Panel_PageObject.MM_Bank_URL+"["+i+"]/td[3]")).getText().trim();
					System.out.println("bankName:"+BankStatus);
					if(BankName.equalsIgnoreCase(Excel_Handling.Get_Data(ModuleName, "BankName_MAPanel").trim())
							&& MidType.equalsIgnoreCase(Excel_Handling.Get_Data(ModuleName, "TransactionTypr").trim())
							&& BankStatus.equalsIgnoreCase(Excel_Handling.Get_Data(ModuleName, "Status").trim()))
					{						
						List<WebElement> priorityElement= driver.findElements(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+Counter+"]/td[7]/div[@class='btnlink']"
								+AirPay_Payment_MA_Panel_PageObject.MM_Bank_URL+"/td/input"));

						for(int j =1;j<priorityElement.size();j++)
						{						
							String priortival = driver.findElement(By.xpath("((//*[@class='table border_table']/tbody/tr)["+Counter+"]/td[7]/div[@class='btnlink']//following::tbody[1]/tr/td/input)["+j+"]")).getAttribute("value");
							int priIntval = Integer.parseInt(priortival);

							if(Minval>priIntval)
							{
								Minval = priIntval;	
								System.out.println("Minval: "+Minval);

							}
							if(Maxval<priIntval)
							{	
								Maxval = priIntval;	
								System.out.println("Maxval:"+Maxval);
							}
							if(j==priorityElement.size())
							{
								Extent_Reporting.Log_Fail("There is an issue with priority", "failed", driver);
							}																			
						}
						System.out.println("Maxium val: "+Maxval);
						System.out.println("minium val :"+Minval);		
						Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+Counter+"]/td[7]/div[@class='btnlink']"
								+AirPay_Payment_MA_Panel_PageObject.MM_Bank_URL+"/td/input[@value='"+Maxval+"']", Integer.toString(Maxval+2), "Max val");					
						Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+Counter+"]/td[7]/div[@class='btnlink']"
								+AirPay_Payment_MA_Panel_PageObject.MM_Bank_URL+"/td/input[@value='"+Minval+"']", Integer.toString(Maxval+1), "Minval");						
						Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+Counter+"]/td[7]/div[@class='btnlink']"+AirPay_Payment_MA_Panel_PageObject.MM_Bank_URL+"["+i+"]/td/input",Integer.toString(Minval), "Min val");						
						Extent_Reporting.Log_Pass("Exact URL Row Find out at :"+i+"th Position", "Passed state");
						Thread.sleep(10000);
						break;	

					}
					if(i==Bank_URLRows.size())
					{
						Extent_Reporting.Log_Fail("Respective URL Does not exist", "Failed", driver);
					}									
				}
			}							
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective URL Does not exist", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");			
		}
	}

	

	public void Bank_DetailsURL(String active, String inactive ) throws Exception{
		try{
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.MM_BankDetails, "BANK URL Detail Table"))
			{					
				List<WebElement> URLRows = driver.findElements(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindBANKDetails));
				System.out.println(""+URLRows.size());
				for(int i=1;i<=URLRows.size();i++)
				{
					String BankName = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindBANKDetails+"["+i+"]/td[1]")).getText().trim();
					String ChannelName = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindBANKDetails+"["+i+"]/td[2]")).getText().trim();
					String MidType = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindBANKDetails+"["+i+"]/td[3]")).getText().trim();
					String Status = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindBANKDetails+"["+i+"]/td[4]")).getText().trim();

					if(BankName.equalsIgnoreCase(Excel_Handling.Get_Data(ModuleName, "BankName_MAPanel").trim())
							&& ChannelName.equalsIgnoreCase(Excel_Handling.Get_Data(ModuleName, "ChannelName").trim())
							&& MidType.equalsIgnoreCase(Excel_Handling.Get_Data(ModuleName, "TransactionTypr").trim())
							&& (Status.equalsIgnoreCase(active)||Status.equalsIgnoreCase(inactive)))
					{

						Extent_Reporting.Log_Pass("Exact URL Row Find out at :"+i+"th Position", "Passed");
						Assert.clickButton(driver, AirPay_Payment_MA_Panel_PageObject.MM_FindBANKDetails+"["+i+"]/td[5]/span/a", "Details Button");
						Counter = i;
						break;						
					}
					if(i==URLRows.size())
					{
						Extent_Reporting.Log_Fail("Respective Bank name Status not matched", "Failed", driver);
					}
				}
			}else{
				Extent_Reporting.Log_Fail("Respective URL Does not exist", "Failed", driver);
			}				
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective URL Does not exist", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");

		}
	}

	public void Bank_DetailsStatusActiveInactive(String active,String inactive) throws Exception{
		try{
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.MM_BankDetails, "BANK URL Detail Table"))
			{					
				List<WebElement> URLRows = driver.findElements(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindBANKDetails));
				System.out.println(""+URLRows.size());
				for(int i=1;i<=URLRows.size();i++)
				{
					String BankName = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindBANKDetails+"["+i+"]/td[1]")).getText().trim();
					String ChannelName = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindBANKDetails+"["+i+"]/td[2]")).getText().trim();
					String MidType = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindBANKDetails+"["+i+"]/td[3]")).getText().trim();
					String Status = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindBANKDetails+"["+i+"]/td[4]")).getText().trim();
					System.out.println(Excel_Handling.Get_Data(ModuleName, "BankName_MAPanel"));
					System.out.println(Excel_Handling.Get_Data(ModuleName, "ChannelName"));
					System.out.println(Excel_Handling.Get_Data(ModuleName, "TransactionTypr"));

					if(BankName.equalsIgnoreCase(Excel_Handling.Get_Data(ModuleName, "BankName_MAPanel").trim())
							&& ChannelName.equalsIgnoreCase(Excel_Handling.Get_Data(ModuleName, "ChannelName").trim())
							&& MidType.equalsIgnoreCase(Excel_Handling.Get_Data(ModuleName, "TransactionTypr").trim())
							&& (Status.equalsIgnoreCase(active)||Status.equalsIgnoreCase(inactive)))
					{

						Extent_Reporting.Log_Pass("Exact URL Row Find out at :"+i+"th Position", "Passed");
						Assert.clickButton(driver, AirPay_Payment_MA_Panel_PageObject.MM_FindBANKDetails+"["+i+"]/td[5]/span/a", "Details Button");
						Counter = i;
						break;						
					}
					if(i==URLRows.size())
					{
						Extent_Reporting.Log_Fail("Respective Bank name Status not matched", "Failed", driver);
					}
				}
			}else{
				Extent_Reporting.Log_Fail("Respective URL Does not exist", "Failed", driver);
			}				
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective URL Does not exist", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");

		}
	}

	public void BankDetails_Page() throws Exception{
		try{
			Thread.sleep(20000);
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.MMBankdetailsPage, "Bank details Page")){			
				Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, AirPay_Payment_MA_Panel_PageObject.MMbankStatus, Excel_Handling.Get_Data(ModuleName, "Status").trim(), "Status Drop Down");			
				Extent_Reporting.Log_Pass("Bank Details Entered", "Passed");
				Extent_Reporting.Log_report_img("Bank Details", "Image", driver);
			}else{
				Extent_Reporting.Log_Fail("Bank details Page does not exist", "Failed", driver);

			}			
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Bank details Page does not exist", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");			
		}
	}

	public static String MINADDONE =null;
	public static String MINMINUSONE =null;
	public static String MAXADDONE =null;
	public static String MAXMINUSONE =null;

	public void BankDetails_MinumAndMaxAmt_Val() throws Exception{
		try{
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.BankURLMiniumVal, "Bank details Page")){						
				Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.BankURLMiniumVal, Excel_Handling.Get_Data(ModuleName, "MinumAmtVal").trim(), "Minium Amout");
				Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.BankURLMaxiumVal, Excel_Handling.Get_Data(ModuleName, "MaxAmtVal").trim(), "Maxium Amout");				
				Extent_Reporting.Log_Pass("Bank Details Entered", "Passed");
				Extent_Reporting.Log_report_img("Bank Details", "Image", driver);
				double MinVal =Double.parseDouble(Excel_Handling.Get_Data(ModuleName, "MinumAmtVal").trim());
				double MaxVal=Double.parseDouble(Excel_Handling.Get_Data(ModuleName, "MaxAmtVal").trim());
				double MinAddOne = MinVal+0.00;
				double MinMinusOne = MinVal-0.01;
				double MaxAddOne = MaxVal+0.01;
				double MaxMinusOne = MaxVal-0.00;
				MINADDONE = String.valueOf(MinAddOne);
				MINMINUSONE= String.valueOf(MinMinusOne);
				MAXADDONE = String.valueOf(MaxAddOne);
				MAXMINUSONE = String.valueOf(MaxMinusOne);			
			}else{
				Extent_Reporting.Log_Fail("Bank details Page does not exist", "Failed", driver);

			}			
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Bank details Page does not exist", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");			
		}
	}




	public void URLDetails_Page() throws Exception{
		try{
			Thread.sleep(20000);
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.MMURLdetailsPage, "URL details Page")){			
				Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, AirPay_Payment_MA_Panel_PageObject.MMbankStatus, Excel_Handling.Get_Data(ModuleName, "Status").trim(), "Status Drop Down");			
				Extent_Reporting.Log_Pass("Bank Details Entered", "Passed");
				Extent_Reporting.Log_report_img("Bank Details", "Image", driver);
			}else{
				Extent_Reporting.Log_Fail("Bank details Page does not exist", "Failed", driver);

			}			
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Bank details Page does not exist", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");			
		}
	}


	public void BankDetails_PageSaveBtn() throws Exception{
		try{
			//Thread.sleep(20000);
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.MMBankSaveBtn, "Bank details Save Button")){							
				Assert.Clickbtn(driver, AirPay_Payment_MA_Panel_PageObject.MMBankSaveBtn, "Save Button");
				Extent_Reporting.Log_Pass("Save Button", "Passed");
				Extent_Reporting.Log_report_img("Save Button Clicked", "Passed", driver);
				Thread.sleep(2000);
				//Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.BankEditSuccMsg, "Edit successfully Done");			
				Thread.sleep(20000);
				Extent_Reporting.Log_report_img("Save Button Clicked", "Passed", driver);
			}else{
				Extent_Reporting.Log_Fail("Bank details Page does not exist", "Failed", driver);

			}			
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Bank details Page does not exist", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");			
		}
	}

	public void ConfigurationModifiedSuccess() throws Exception
	{
		try{
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.URLEditSuccMsg, "Edit successfully Done"))
			{	
				Extent_Reporting.Log_report_img("Modified Successfully", "Passed", driver);
				Extent_Reporting.Log_Pass("Respective message is dispalyed as expected", "Passed");
				Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.URLEditSuccMsg, "Edit successfully Done");			
			}else{
				Extent_Reporting.Log_Fail("Edit successfully not exist", "Failed", driver);

			}			
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Bank details Page does not exist", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");			
		}
	}



	public void URL_Details(String active,String inactive) throws Throwable{
		try{
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails, "URL Detail Table"))
			{					
				List<WebElement> Bank_URLRows = driver.findElements(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+Counter+"]/td[7]/div[@class='btnlink']"
						+AirPay_Payment_MA_Panel_PageObject.MM_Bank_URL));
				System.out.println("Bank Rows: "+Bank_URLRows.size());
				for(int i=1;i<=Bank_URLRows.size();i++)
				{
					String BankName = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+Counter+"]/td[7]/div[@class='btnlink']"+AirPay_Payment_MA_Panel_PageObject.MM_Bank_URL+"["+i+"]/td[1]")).getText().trim();
					System.out.println("bankName:"+BankName);
					String MidType = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+Counter+"]/td[7]/div[@class='btnlink']"+AirPay_Payment_MA_Panel_PageObject.MM_Bank_URL+"["+i+"]/td[2]")).getText().trim();
					System.out.println("bankName:"+MidType);
					String BankStatus = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+Counter+"]/td[7]/div[@class='btnlink']"+AirPay_Payment_MA_Panel_PageObject.MM_Bank_URL+"["+i+"]/td[3]")).getText().trim();
					System.out.println("bankName:"+BankStatus);
					if(BankName.equalsIgnoreCase(Excel_Handling.Get_Data(ModuleName, "BankName_MAPanel").trim())
							&& MidType.equalsIgnoreCase(Excel_Handling.Get_Data(ModuleName, "TransactionTypr").trim())
							&& (BankStatus.equalsIgnoreCase(active)|| BankStatus.equalsIgnoreCase(inactive)))
					{
						Extent_Reporting.Log_Pass("Exact URL Row Find out at :"+i+"th Position is", "Passed");
						Assert.Javascriptexecutor_forClick(driver, AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+Counter+"]/td[7]/div[@class='btnlink']"+AirPay_Payment_MA_Panel_PageObject.MM_Bank_URL+"["+i+"]/td[5]/span/a", "Edit button");						
						Counter = i;
						break;	
					}
					if(i==Bank_URLRows.size())
					{
						Extent_Reporting.Log_Fail("Respective URL Does not exist", "Failed", driver);
					}									
				}
			}							
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective URL Does not exist", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");

		}
	}
	public static String AmtVal = null;
	public void SummaryAmtVerify(String PassedVal) throws Exception{
		try{			
			if(Excel_Handling.Get_Data(ModuleName, "Surcharge_Mode").contains("OFF"))
			{		
			
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.TotAmt, "Passed Amount"))
			{
				Assert.Clickbtn(driver, "//div[@class='sumbtn desksumbtn iplus']", "Amount Plus button");			
				 AmtVal = driver.findElement(By.xpath("//div[@class='main-amount-block show-amnt']//following::span[@id='total_amount']")).getText().trim();
				System.out.println("Amounnt value: "+AmtVal);
				if(AmtVal.contains(PassedVal)){
					Extent_Reporting.Log_report_img("Passed Amount is exist", "Passed", driver);
					Extent_Reporting.Log_Pass("Passed Amount is exist as expected"+AmtVal, "Max and Min Amount");
				}else{
					Extent_Reporting.Log_Fail("Amount didn't match", "Failed", driver);
				}
			}
			}else{
				AmtVal = driver.findElement(By.xpath("//span[@class='final-amount']")).getText().trim();
				Extent_Reporting.Log_report_img("Surcharge not applied", "Passed", driver);
				Extent_Reporting.Log_Pass("Surcharge Amount not applied ", "Total Amount: "+AmtVal);
			}	
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Amount didn't match", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");

		}
	}
	
	public static List<WebElement> OpenCurrentMenuCount =null;
	public void Dashboard(String DatasheetFieldName) throws Exception {
		try{	
			Create_TestNGXML test = new Create_TestNGXML();
			OpenCurrentMenuCount = driver.findElements(By.xpath(MainMenu));
			for(int i=0;i<OpenCurrentMenuCount.size();i++)
			{				
				String MenuName = OpenCurrentMenuCount.get(i).getText().trim();
				System.out.println("MenuName:"+MenuName);
				if(MenuName.equalsIgnoreCase(DatasheetFieldName))
				{
					if(Excel_Handling.Get_Data(ModuleName, "PermissionAccess").equalsIgnoreCase("Y"))
					{
						OpenCurrentMenuCount.get(i).click();
						if(NavigationPageVerify()==true)
						{	
							test.NavigationWithTodaysDate("Pass");
							break;
						}else{
							test.NavigationWithTodaysDate("Fail");
							break;
						}																
					}else{
						test.NavigationWithTodaysDate("You have Givena a Wrong Permission Access?");
						break;
					}
				}
				if(i==OpenCurrentMenuCount.size()-1)
				{
					if(Excel_Handling.Get_Data(ModuleName, "PermissionAccess").equalsIgnoreCase("N"))
					{
						Extent_Reporting.Log_Pass(ModuleName+" Menu does not exist for "+ sheetName +" Ids", "Passed");
						test.NavigationWithTodaysDate("Pass");
						break;
					}else
					{
						test.NavigationWithTodaysDate("You have Givena a Wrong Permission Access?");
						break;
					}
				}
			}	
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Amount didn't match", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");

		}
	}
	public void ModuleName(String DatasheetFieldName, String ModuleNvigationXpath) throws Throwable {
		try{	
			Create_TestNGXML test = new Create_TestNGXML();
			OpenCurrentMenuCount = driver.findElements(By.xpath(MainMenu));
			for(int i=0;i<OpenCurrentMenuCount.size();i++)
			{				
				String MenuName = OpenCurrentMenuCount.get(i).getText().trim();
				System.out.println("MenuName :"+MenuName);
				if(MenuName.equalsIgnoreCase(DatasheetFieldName))
				{
					Assert.Javascriptexecutor_forClick(driver, ModuleNvigationXpath, DatasheetFieldName+" Menu is");
					break;
				}
				if(i==OpenCurrentMenuCount.size()-1)
				{
					if(Excel_Handling.Get_Data(ModuleName, "PermissionAccess").trim().equalsIgnoreCase("N"))
					{
						test.NavigationWithTodaysDate("Pass");					
					}else{
						Extent_Reporting.Log_Fail(MenuName+" Menu Does not exist", "Fail", driver);
						test.NavigationWithTodaysDate("Fail");					
					}
					Extent_Reporting.Log_Fail(DatasheetFieldName+" Menu Does not exist ", "Fail", driver);
				}
			}	
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Amount didn't match", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");

		}
	}
	
	
	public static List<String> stringList = null;
	public static String[] stringArr = null;
	public static String[] stringArrDrop = null;

	public void AdvanceSearchListVal(String DatasheetFieldName) throws Throwable {
		try{
			stringList = new ArrayList<String>();
			List<WebElement> list = driver.findElements(By.xpath(AdvanceSearchInputField));	
			for(int i=0;i<list.size();i++)
			{
				stringList.add(list.get(i).getAttribute("placeholder").trim());
				System.out.println(list.get(i).getAttribute("placeholder").trim());
				stringArr = stringList.toArray( new String[] {});
							
			}		
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Amount didn't match", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");

		}
	}
	
	public void AdvanceSearchDropBoxVal(String DatasheetFieldName) throws Throwable {
		try{
			stringList = new ArrayList<String>();
			List<WebElement> list = driver.findElements(By.xpath(AdvanceSearchSelectField));		
			for(int i=0;i<list.size();i++)
			{
				Select select1 =new Select(list.get(i));
				String bankName =  select1.getFirstSelectedOption().getText();
				System.out.println("bankName:"+bankName);
				stringList.add(bankName.trim());
				//System.out.println(list.get(i).getAttribute("placeholder").trim());
				stringArrDrop = stringList.toArray( new String[] {});
							
			}		
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Amount didn't match", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");

		}
	}
	
	
	
	
	public void AdvanceSearchVerification() throws Exception{
		try{
			int i=0;
			for(String fieldNameVerify:stringArr){				
				System.out.println("value: " +fieldNameVerify);
				i=i+1;
				if(fieldNameVerify.equalsIgnoreCase(Excel_Handling.Get_Data(ModuleName, "Fields").trim()))
				{
					if(Excel_Handling.Get_Data(ModuleName, "PermissionAccess").trim().equalsIgnoreCase("Y"))
					{
						System.out.println("Pass");
						test.NavigationWithTodaysDate("Pass");				
						break;				
					}else{
						test.NavigationWithTodaysDate("Fail");
						break;
					}				
				}
				else
				{	
					System.out.println(stringArr.length);
					if(stringArr.length==i)
					{
						if(Excel_Handling.Get_Data(ModuleName, "PermissionAccess").trim().equalsIgnoreCase("N"))
						{
							test.NavigationWithTodaysDate("Pass");					
						}else{
							test.NavigationWithTodaysDate("Fail");					

						}
					}				
				}
				
			}
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Amount didn't match", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");

		}
		
		
	}
	
	
	public void AdvanceSearchDropbox() throws Exception{
		try{
			int i=0;
			for(String fieldNameVerify:stringArrDrop){				
				System.out.println("value: " +fieldNameVerify);
				i=i+1;
				if(fieldNameVerify.equalsIgnoreCase(Excel_Handling.Get_Data(ModuleName, "Fields").trim()))
				{
					if(Excel_Handling.Get_Data(ModuleName, "PermissionAccess").trim().equalsIgnoreCase("Y"))
					{
						System.out.println("Pass");
						test.NavigationWithTodaysDate("Pass");				
						break;				
					}else{
						test.NavigationWithTodaysDate("Fail");
						break;
					}				
				}
				else
				{	
					System.out.println(stringArr.length);
					if(stringArrDrop.length==i)
					{
						if(Excel_Handling.Get_Data(ModuleName, "PermissionAccess").trim().equalsIgnoreCase("N"))
						{
							test.NavigationWithTodaysDate("Pass");	
							break;
						}
					}				
				}
				
			}
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Amount didn't match", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");

		}
		
		
	}
	
	
	

}