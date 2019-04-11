package com.Airpay.Utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Airpay.Reporting.Extent_Reporting;
import com.gargoylesoftware.htmlunit.javascript.host.dom.Document;

@SuppressWarnings("unused")
public class ElementAction {

	//Long log = new Long(this.getClass().getName());

	public boolean isElementPresentByXpath(String xpath,WebDriver driver,String Element_Name) throws InterruptedException
	{
		try
		{
			driver.findElement(By.xpath(xpath));
			Extent_Reporting.Log_Pass(Element_Name+" Exist",Element_Name+" Exist");
		}
		catch(Throwable t)
		{
			//Log.error("Element not Found -->"+t.getMessage());
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", driver);
			t.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean isElementPresentByLinkText(String linkText,WebDriver driver) throws InterruptedException
	{
		try
		{
			driver.findElement(By.linkText(linkText));
			Extent_Reporting.Log_Pass(linkText+" Exist",linkText+" Exist");
		}
		catch(Throwable t)
		{
			Log.error("Element not Found -->"+t.getMessage());
			Extent_Reporting.Log_Fail(linkText+" does not Exist",linkText+" does not Exist", driver);
			t.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean isElementPresentByID(String ID,WebDriver driver,String Element_Name) throws InterruptedException
	{
		try
		{
			driver.findElement(By.id(ID));
			Extent_Reporting.Log_Pass(Element_Name+" Exist",Element_Name+" Exist");
		}
		catch(Throwable t)
		{
			Log.info("Element not Found -->"+t.getMessage());
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", driver);
			t.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean isElementPresentByName(String Name,WebDriver driver,String Element_Name) throws InterruptedException
	{
		try
		{
			driver.findElement(By.name(Name));
			Extent_Reporting.Log_Pass(Element_Name+" Exist",Element_Name+" Exist");
		}
		catch(Throwable t)
		{
			Log.info("Element not Found -->"+t.getMessage());
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", driver);
			t.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean isElementPresentByClassName(String className,WebDriver driver,String Element_Name) throws InterruptedException
	{
		try
		{
			driver.findElement(By.className(className));
			Extent_Reporting.Log_Pass(Element_Name+" Exist",Element_Name+" Exist");
		}
		catch(Throwable t)
		{
			Log.info("Element not Found -->"+t.getMessage());
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", driver);
			t.printStackTrace();
			return false;
		}
		return true;
	}


	public void clickButtonID(WebDriver screenName,String ID,String Element_Name) throws Exception
	{
		try
		{
		
			WebElement webButton = screenName.findElement(By.id(ID));
			webButton.click();
			Extent_Reporting.Log_Pass(Element_Name+" Clicked",Element_Name+" Clicked");
		}
		catch(Throwable t)
		{
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}
	public void clickButton(WebDriver screenName,String ObjectxPath,String Element_Name) throws Exception
	{
		try
		{
			WebElement webButton = screenName.findElement(By.xpath(ObjectxPath));
			//webButton.click();
			webButton.sendKeys(Keys.ENTER);
			Extent_Reporting.Log_Pass(Element_Name+" Clicked",Element_Name+" Clicked");
		}
		catch(Throwable t)
		{ 
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}
	public void clickLink(WebDriver screenName,String ObjectxPath,String Element_Name) throws Exception
	{
		try
		{
			WebElement webButton = screenName.findElement(By.xpath(ObjectxPath));
			webButton.click();
			Extent_Reporting.Log_Pass(Element_Name+" Clicked",Element_Name+" Clicked");
		}
		catch(Throwable t)
		{ 
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}
	
	public void clickLinkByLinkText(WebDriver screenName,String linkText,String Element_Name) throws Exception
	{
	    try
		{
	    	Thread.sleep(5000);
	    	System.out.println("link:"+linkText);
			WebElement webButton = screenName.findElement(By.linkText(linkText));
			webButton.click();
			Extent_Reporting.Log_Pass(Element_Name+" Clicked",Element_Name+" Clicked");
		}
	    catch(Throwable t)
		{ 
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}

	public boolean clickFirst(WebDriver screenName,String ObjectxPath,String Element_Name) throws Exception
	{
		boolean flag=true;
		try
		{
			WebElement webButton = screenName.findElement(By.xpath(ObjectxPath));
			webButton.click();
			Extent_Reporting.Log_Pass(Element_Name+" Clicked",Element_Name+" Clicked");
		}
		 catch(Throwable t)
			{ 
				Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
				t.printStackTrace();
				throw new Exception("Element Not Present");
			}
		return flag;
	}
	public void inputText(WebDriver screenName,String ObjectxPath,String sValue,String Element_Name) throws Exception
	{
		try
		{
			WebElement inputText = screenName.findElement(By.xpath(ObjectxPath));
			elementHighlight(screenName,inputText);
			inputText.clear();
			inputText.sendKeys(sValue);
			Extent_Reporting.Log_Pass(Element_Name+" Entered",sValue + " entered in "+ Element_Name);
		}
		 catch(Throwable t)
		{ 
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
		
	}
	
	public void inputTextPaste(WebDriver screenName,String ObjectxPath,String Element_Name) throws Exception
	{
		try
		{						
			WebElement _1232=screenName.findElement(By.xpath(ObjectxPath));
			 // String _1232="";
			  //Type "webdriver" in First Name
			 // txtFName.sendKeys("webdriver2222@@");
			  //Create an object for Actions Class
			  Actions a = new Actions(screenName);
			  //select the value which is typed in FirstName field
			 // a.sendKeys(txtFName, Keys.chord(Keys.CONTROL,"a")).perform();
			  //Performing copy action using CTRl+C
			 // a.sendKeys(txtLName1212,Keys.chord(Keys.CONTROL,"c")).perform();
			  //Performing paste action using CTRL+V in LastName field
			  a.sendKeys(_1232, Keys.chord(Keys.CONTROL,"v")).perform();
				Extent_Reporting.Log_Pass(Element_Name+" Entered",_1232 + " entered in "+ Element_Name);						
			/*
			WebElement inputText = screenName.findElement(By.xpath(ObjectxPath));
			elementHighlight(screenName,inputText);
			inputText.clear();
			inputText.sendKeys(inputText,Keys.chord(Keys.CONTROL,"C")));*/
		}
		 catch(Throwable t)
		{ 
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
		
	}
	
	public void removeAttribute(WebDriver driver) {
		List<WebElement> inputs = driver.findElements(By.tagName("input"));
		for (WebElement input : inputs) {
			((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly','readonly')", input);
		}
	}

	public void typeNonEditable(WebDriver driver,String ObjectxPath,String sValue,String Element_Name) throws Exception {
		removeAttribute(driver);
		inputText(driver,ObjectxPath,sValue,Element_Name);
	}
	
	public void enterText(WebDriver screenName, String ObjectxPath, String sValue,String Element_Name) throws Exception {
		Actions actions = new Actions(screenName);
		try
		{
			WebElement inputText = screenName.findElement(By.xpath(ObjectxPath));
			actions.moveToElement(inputText);
			actions.click();
			actions.sendKeys(sValue);
			actions.build().perform();
			Extent_Reporting.Log_Pass(Element_Name+" Entered",sValue + " entered in "+ Element_Name);
			
		}  catch(Throwable t)
		{ 
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
		
	}
	public void inputTextByID(WebDriver screenName,String ID,String sValue,String Element_Name) throws Exception
	{
		try
		{	WebElement inputText = screenName.findElement(By.id(ID));
			inputText.sendKeys(sValue);
			Extent_Reporting.Log_Pass(Element_Name+" Entered",sValue + " entered in "+ Element_Name);
		}  catch(Throwable t)
		{ 
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
		
	}

	public void selectCheckBox(WebDriver screenName,String ObjectxPath,String Element_Name) throws Exception
	{
		try
		{	WebElement checkBox = screenName.findElement(By.xpath(ObjectxPath));
			checkBox.click();
			Extent_Reporting.Log_Pass(Element_Name+" checkbox clicked",Element_Name+" checkbox clicked");
		}  catch(Throwable t)
		{ 
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}
	public void selectRadio(WebDriver screenName,String ObjectxPath,String Element_Name) throws Exception
	{
		try
		{
			WebElement radioButton = screenName.findElement(By.xpath(ObjectxPath));
			radioButton.click();
			Extent_Reporting.Log_Pass(Element_Name+" radiobutton clicked",Element_Name+" radiobutton clicked");
		}  catch(Throwable t)
		{ 
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}

	public String getInputTextValue(WebDriver screenName,String ObjectxPath,String Element_Name) throws Exception
	{
		try
		{
			WebElement inputText = screenName.findElement(By.xpath(ObjectxPath));
			Extent_Reporting.Log_Pass(Element_Name+" exist",Element_Name+" has "+ inputText.getText().trim());
			return inputText.getText();
		}
		 catch(Throwable t)
			{ 
				Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
				t.printStackTrace();
				throw new Exception("Element Not Present");
			}
	}
	
	public String getInputValue(WebDriver screenName,String ObjectxPath,String Element_Name) throws Exception
	{
		try
		{
			System.out.println("getinput");
			WebElement inputText = screenName.findElement(By.xpath(ObjectxPath));
			Extent_Reporting.Log_Pass(Element_Name+" exist",Element_Name+" has "+ inputText.getText());
			return inputText.getAttribute("value");
		}
		catch(Throwable t)
		{ 
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}

	public void clearInputTextValue(WebDriver screenName,String ObjectxPath,String Element_Name) throws Exception
	{
		try
		{
			WebElement inputText = screenName.findElement(By.xpath(ObjectxPath));
			inputText.clear();
		}
		catch(Throwable t)
		{ 
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}
	public void clearInputTextValueByName(WebDriver screenName,String name,String Element_Name) throws Exception
	{
		try
		{
			WebElement inputText = screenName.findElement(By.name(name));
			inputText.clear();
		}
		catch(Throwable t)
		{ 
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}
	public void clearAndInputTextValue(WebDriver screenName,String ObjectxPath,String value,String Element_Name) throws Exception
	{
		
		try
		{
			WebElement inputText = screenName.findElement(By.xpath(ObjectxPath));
			Thread.sleep(1000);
			inputText.clear();
			Thread.sleep(1000);
			inputText.sendKeys(value);
			Extent_Reporting.Log_Pass(Element_Name+" cleared & Entered with "+value,Element_Name+" cleared & Entered with "+value);
		}
		catch(Throwable t)
		{ 
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}
	public void selectDropBoxValue(WebDriver screenName,String ObjectxPath,String value,String Element_Name) throws Exception
	{
		try
		{
			WebElement selectDropBox = screenName.findElement(By.xpath(ObjectxPath));
			screenName.findElement(By.xpath(ObjectxPath)).click();
			Select select = new Select(selectDropBox);
			//select.selectByVisibleText(value);
		//	Thread.sleep(500);
			select.selectByValue(value);
			Extent_Reporting.Log_Pass(Element_Name+" selected with "+value,Element_Name+" selected with "+value);
		}
		catch(Throwable t)
		{ 
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}

	}
	
	public void selectDropBoxByVisibleText(WebDriver screenName,String ObjectxPath,String value,String Element_Name) throws Exception
	{
		//Thread.sleep(500);
		try
		{
			WebElement selectDropBox = screenName.findElement(By.xpath(ObjectxPath));
			Select select = new Select(selectDropBox);
			//Thread.sleep(500);
			select.selectByVisibleText(value);
			Extent_Reporting.Log_Pass(Element_Name+" selected with "+value,Element_Name+" selected with "+value);
		}
		catch(Throwable t)
		{ 
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}
	
	public void selectDropBoxValueByID(WebDriver screenName,String ID,String value,String Element_Name) throws Exception
	{
		try
		{
			WebElement selectDropBox = screenName.findElement(By.id(ID));
			Select select = new Select(selectDropBox);
			select.selectByValue(value);
			Extent_Reporting.Log_Pass(Element_Name+" selected with "+value,Element_Name+" selected with "+value);
		}
		catch(Throwable t)
		{ 
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}

	}
	public void selectDropBoxValueByName(WebDriver screenName,String Name,String value,String Element_Name) throws Exception
	{
		try
		{
			WebElement selectDropBox = screenName.findElement(By.name(Name));
			Select select = new Select(selectDropBox);
			select.selectByValue(value);
			Extent_Reporting.Log_Pass(Element_Name+" selected with "+value,Element_Name+" selected with "+value);
		}
		catch(Throwable t)
		{ 
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}

	}
	public void selectDropBoxValue(WebDriver screenName,String ObjectxPath,int index,String Element_Name) throws Exception
	{
		try
		{
			WebElement selectDropBox = screenName.findElement(By.xpath(ObjectxPath));
			Select select = new Select(selectDropBox);
			select.selectByIndex(index);
			Extent_Reporting.Log_Pass(Element_Name+" selected with "+index,Element_Name+" selected with "+index);
		}
		catch(Throwable t)
		{ 
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}

	}
	public void selectDropBoxDefaultValue(WebDriver screenName,String ObjectxPath,String Element_Name) throws Exception
	{
		try
		{
			WebElement selectDropBox = screenName.findElement(By.xpath(ObjectxPath));
			Select select = new Select(selectDropBox);
			select.selectByIndex(0);
			Extent_Reporting.Log_Pass(Element_Name+" selected with default value ",Element_Name+" selected with default value ");
		}
		catch(Throwable t)
		{ 
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}

	}
	public String getDropBoxDefaultValue(WebDriver screenName,String ObjectxPath,String Element_Name) throws Exception
	{
		try
		{
			WebElement selectDropBox = screenName.findElement(By.xpath(ObjectxPath));
			Select select = new Select(selectDropBox);
			Extent_Reporting.Log_Pass(Element_Name+" selected value is "+select.getFirstSelectedOption().getText(),Element_Name+" selected value is "+select.getFirstSelectedOption().getText());
			return select.getFirstSelectedOption().getText();
			
		}
		catch(Throwable t)
		{ 
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}
	public String getDropBoxSelectedValue(WebDriver screenName,String ObjectxPath,int index,String Element_Name) throws Exception
	{
		try
		{
			WebElement selectDropBox = screenName.findElement(By.xpath(ObjectxPath));
			Select select = new Select(selectDropBox);
			Extent_Reporting.Log_Pass(Element_Name+" selected value is "+select.getFirstSelectedOption().getText(),Element_Name+" selected value is "+select.getFirstSelectedOption().getText());
			return select.getFirstSelectedOption().getText();
		}
	
		catch(Throwable t)
		{ 
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}

	public int getDropBoxSize(WebDriver screenName,String ObjectxPath,String Element_Name) throws Exception
	{
		try
		{
			WebElement selectDropBox = screenName.findElement(By.xpath(ObjectxPath));
			Select select = new Select(selectDropBox);
			Extent_Reporting.Log_Pass(Element_Name+" dropbox size is "+select.getOptions().size(),Element_Name+" dropbox size is "+select.getOptions().size());
			return select.getOptions().size();
		}
		catch(Throwable t)
		{ 
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}



	public String[] getDropBoxValue(WebDriver screenName,String ObjectxPath,String Element_Name) throws Exception
	{
		try
		{
			WebElement selectDropBox = screenName.findElement(By.xpath(ObjectxPath));
			Select select =new Select(selectDropBox);
			List<WebElement> optionValue = select.getOptions();
			String[] value = new String[optionValue.size()];
			for(int i =0;i<optionValue.size();i++)
				value[i] = optionValue.get(i).getText();
			return value;
		}
		catch(Throwable t)
		{ 
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}

	}

	public int getTotalTableCell(WebDriver driver,String ObjectxPath,String Element_Name) throws Exception
	{
		try
		{
			Extent_Reporting.Log_Pass(Element_Name+" table size is "+driver.findElements(By.xpath(ObjectxPath)).size(),Element_Name+" table size is "+driver.findElements(By.xpath(ObjectxPath)).size());
			return driver.findElements(By.xpath(ObjectxPath)).size();
		}
		catch(Throwable t)
		{ 
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", driver);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}

	public int getElementsSize(WebDriver driver,String ObjectxPath,String Element_Name) throws Exception
	{
		try
		{
			Extent_Reporting.Log_Pass(Element_Name+" element size is "+driver.findElements(By.xpath(ObjectxPath)).size(),Element_Name+" element size is "+driver.findElements(By.xpath(ObjectxPath)).size());
			return driver.findElements(By.xpath(ObjectxPath)).size();
		}
		catch(Throwable t)
		{ 
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", driver);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}


	public int getElementCount(WebDriver driver,String className,String Element_Name) throws Exception
	{
		int count=0;

		try
		{
			count = driver.findElements(By.className(className)).size();
			Extent_Reporting.Log_Pass(Element_Name+" element count "+count,Element_Name+" element size is "+count);
		}
		catch(Throwable t)
		{ 
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", driver);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
		return count;
	}

	public boolean isElementDisplayed(WebDriver driver,String xpath,String Element_Name) throws Exception
	{
		boolean flag=false;
		try
		{
			if(driver.findElement(By.xpath(xpath)).isDisplayed())
			{	Extent_Reporting.Log_Pass(Element_Name +" is displayed ", Element_Name +" is displayed ");
				flag = true;
			}
		}
		catch(Throwable e)
		{
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", driver);
			flag=false;
			e.printStackTrace();
			flag = false;
			throw new Exception("Element Not Present");
			
		}
		return flag;

	}
	public boolean isElementDisplay(WebDriver driver,String xpath)
	{
		boolean flag=false;
		try
		{
			if(driver.findElement(By.xpath(xpath)) != null)
			{	elementHighlight(driver,driver.findElement(By.xpath(xpath)));
				flag = true;
			}
		}
		catch(Throwable e)
		{
			e.printStackTrace();
			flag = false;
		}
		return flag;

	}
	
	//  Rohit - ETS -- 11 June 2015
	public void mouseHoverAction_3(WebDriver driver,String mainElementXP, String subElementXP,String subSubElementXP,String Element_Name) throws Exception{
        try
        {
			Actions action = new Actions(driver);
			WebElement mainElement = driver.findElement(By.xpath(mainElementXP));
			action.moveToElement(mainElement).perform();
			WebElement subElement = driver.findElement(By.xpath(subElementXP));
			action.moveToElement(subElement).perform();
			WebElement subSubElement = driver.findElement(By.xpath(subSubElementXP));
			action.moveToElement(subSubElement);
			action.click();
			action.perform();
			Log.info("Click action is performed on the selected Product Type");
			Extent_Reporting.Log_Pass("Click action is performed on the selected Product Type"+Element_Name,"Click action is performed on the selected Product Type"+Element_Name);
	     }
        catch(Throwable t)
		{ 
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", driver);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}
	
	public void mouseHoverAction_2(WebDriver driver,String mainElementXP, String subElementXP,String Element_Name) throws Exception{
	      try
	      {
			Actions action = new Actions(driver);
			WebElement mainElement = driver.findElement(By.xpath(mainElementXP));
			action.moveToElement(mainElement).click().perform();
			WebElement subElement = driver.findElement(By.xpath(subElementXP));
			action.moveToElement(subElement).perform();
			action.moveToElement(subElement).click();
			action.moveToElement(subElement).perform();
			//driver.findElement(By.xpath(subElementXP)).click();
		//	driver.findElement(By.xpath(subElementXP)).sendKeys(Keys.ENTER);
			//action.perform();
			//action.click(subElement);
			//Log.info("Click action is performed on the selected Product Type");
			Extent_Reporting.Log_Pass("Click action is performed on the selected Product Type"+Element_Name,"Click action is performed on the selected Product Type"+Element_Name);
	    }
	   catch(Throwable t)
		{ 
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", driver);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
		}
	public void mouseHoverAction_4(WebDriver driver,String mainElementXP, String subElementXP,String Element_Name) throws Exception{
      try
      {
		Actions action = new Actions(driver);
		WebElement mainElement = driver.findElement(By.xpath(mainElementXP));
		action.moveToElement(mainElement).perform();
		WebElement subElement = driver.findElement(By.xpath(subElementXP));
		action.moveToElement(subElement).perform();
		action.moveToElement(subElement).click();
		//driver.findElement(By.xpath(subElementXP)).click();
	//	driver.findElement(By.xpath(subElementXP)).sendKeys(Keys.ENTER);
		//action.perform();
		//action.click(subElement);
		//Log.info("Click action is performed on the selected Product Type");
		Extent_Reporting.Log_Pass("Click action is performed on the selected Product Type"+Element_Name,"Click action is performed on the selected Product Type"+Element_Name);
    }
   catch(Throwable t)
	{ 
		Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", driver);
		t.printStackTrace();
		throw new Exception("Element Not Present");
	}
	}
	public void mouseHoverAction(WebDriver driver,String mainElementXP,String Element_Name) throws Exception{
      try
      {
		Actions action = new Actions(driver);
		WebElement mainElement = driver.findElement(By.xpath(mainElementXP));
		action.moveToElement(mainElement).clickAndHold().build().perform();
		action.release().perform();
	//action.perform();
		Log.info("Click action is performed on the selected Product Type");
		Extent_Reporting.Log_Pass("Click action is performed on the selected Product Type"+Element_Name,"Click action is performed on the selected Product Type"+Element_Name);
    }
   catch(Throwable t)
	{ 
		Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", driver);
		t.printStackTrace();
		throw new Exception("Element Not Present");
	}
	}
	
	public void mouseHoverAction(WebDriver driver,String mainElementXP, String subElementXP,String subSubElementXP, String subBesideElementXP,String Element_Name) throws Exception{
    try
    {
		Actions action = new Actions(driver);
		WebElement mainElement = driver.findElement(By.xpath(mainElementXP));
		action.moveToElement(mainElement).perform();
		WebElement subElement = driver.findElement(By.xpath(subElementXP));
		action.moveToElement(subElement).perform();
		WebElement subSubElement = driver.findElement(By.xpath(subSubElementXP));
		action.moveToElement(subSubElement).perform();
		WebElement subBesideElement = driver.findElement(By.xpath(subBesideElementXP));
		action.moveToElement(subBesideElement).perform();
		action.click();
		action.perform();
		Log.info("Click action is performed on the selected Product Type");
		Extent_Reporting.Log_Pass("Click action is performed on the selected Product Type"+Element_Name,"Click action is performed on the selected Product Type"+Element_Name);
    }
   catch(Throwable t)
	{ 
		Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", driver);
		t.printStackTrace();
		throw new Exception("Element Not Present");
	}
	}
	
	public void selectclass(WebDriver driver,WebElement parent,String elementToSelect,String Element_Name) throws Exception{
        try
        {
		Select dropdown = new Select(parent);
		dropdown.selectByVisibleText(elementToSelect);
		Extent_Reporting.Log_Pass("selected "+elementToSelect,"selected "+elementToSelect+"in "+parent);
        }
       catch(Throwable t)
    	{ 
    		Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", driver);
    		t.printStackTrace();
    		throw new Exception("Element Not Present");
    	}
    	

	}
	
	
	public void getWindowHandle(WebDriver driver , String title) throws InterruptedException {
		int cnt=0;
		int l=0;
		do
		{
		
		Set<String> handles = driver.getWindowHandles();

		String[] browser =	handles.toArray(new String[0]);
		System.out.println("Number of browsers opened are"
				+ browser.length);
		for (int i=0; i<handles.size();i++) {
		            try
		            {
					driver.switchTo().window(browser[i]);
					System.out.println(driver.getTitle());
					if(driver.getTitle().contains(title)){
						System.out.println(driver.getTitle()+"found");
						driver.getWindowHandle();
						cnt=1;
						break;
					  }
		            }
            
			catch(Throwable t)
			{
				System.out.println("Browser not opened");
			}

		}

		if (cnt==1)
		{
			break;
		}
		if(l==20)
		{
			break;
		}
		Thread.sleep(500);
		l=l+1;
		}while(true);
		

	} 
	
	public void selectWindowIfElementPresent(WebDriver driver, String element) throws InterruptedException {
		Thread.sleep(2000);
		Set<String> windows = driver.getWindowHandles();
		Object[] win = windows.toArray(); System.out.println(win.length);
		driver.switchTo().window(win[0].toString());
		if (isElementDisplay(driver, element)) {
			Extent_Reporting.Log_Pass("Selected Pop Up : " + driver.switchTo().window(win[0].toString()).getTitle(),"Selected Pop Up : " + driver.switchTo().window(win[0].toString()).getTitle());
		} else {
			driver.switchTo().window(win[1].toString());
			System.out.println("Selected Pop Up : " + driver.switchTo().window(win[1].toString()).getTitle());
		}
	}
	
	public void waitForElementNotPresent(WebDriver driver, String element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(element)));
		} catch (TimeoutException te) {
		}
	}
	
	public void waitForElementVisible(WebDriver driver, String element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
		} catch (NoSuchWindowException e) {
		} catch (InvalidElementStateException e) {
		} catch (TimeoutException te) {
		} catch (NoSuchElementException e) {
		} catch (WebDriverException we) {
		}
	}
	
	public void waitForElementClickable(WebDriver driver, String element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
		} catch (NoSuchWindowException e) {
		} catch (InvalidElementStateException e) {
		} catch (TimeoutException te) {
		} catch (NoSuchElementException e) {
		} catch (WebDriverException we) {
		}
	}
	
	public void waitForTextPresent(WebDriver driver, String text) throws InterruptedException {
		try {
			Thread.sleep(2000);
			long timer = 0;
			while (isTextPresent(driver, text) == false && timer < Long.valueOf("30000")) {
				Thread.sleep(500);
				timer += 5000;
			}
		} catch (NoSuchWindowException e) {
		}
	}
	
	public void waitForPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Integer.parseInt("60"), TimeUnit.SECONDS);
	}

	
	public boolean isTextPresent(WebDriver driver, String text) {
		boolean flag = false;
		try {
			flag = driver.findElement(By.xpath("//*[contains(.,'" + text + "')]")).isDisplayed();
		} catch (NoSuchElementException e) {
			return flag;
		} catch (NoSuchWindowException e) {
			return flag;
		}
		return flag;
	}
	
	public void getTableData(WebDriver driver , String xpath)
	{
		// Grab the table 
		WebElement table = driver.findElement(By.xpath(xpath)); 

		// Now get all the TR elements from the table 
		List<WebElement> allRows = table.findElements(By.tagName("tr")); 

		// And iterate over them, getting the cells 
		for (WebElement row : allRows) { 
		    List<WebElement> cells = row.findElements(By.tagName("td")); 

		    // Print the contents of each cell
		    for (WebElement cell : cells) { 
		        System.out.println(cell.getText());
		    }
		}
	}
	
	
	//Function for fetching the value from the object when value attribute is not present.
	public String getObjectValue(WebDriver driver,WebElement webElement) {
        JavascriptExecutor e = (JavascriptExecutor) driver;
        return (String) e.executeScript(String.format("return $('#%s').val();", webElement.getAttribute("id")));
	}
	
	public String getObjectValueClass(WebDriver driver,WebElement webElement) {
        JavascriptExecutor e = (JavascriptExecutor) driver;
        return (String) e.executeScript(String.format("return $('#%s').val();", webElement.getAttribute("class")));
	}
	
	/*public void doubleClick(WebDriver driver,WebElement myElemment) throws InterruptedException
	{
		if(isElementPresentByXpath(driver, myElemment))
		{
		Actions action = new Actions(driver);
		action.moveToElement(myElemment);
		Thread.sleep(2000);
		action.doubleClick();
		//action.doubleClick(myElemment);
		action.build().perform();
		}
		else
		{
			throw new Exception("selectDropBoxValue() --- >Element Not Present");
		}
	}
	 */
	public int getElementCountXPath(WebDriver driver, String ObjectPath,String Element_Name) throws InterruptedException {
		int count = 0;
		if (isElementPresentByXpath(ObjectPath, driver,Element_Name)) {
			count = driver.findElements(By.xpath(ObjectPath)).size();
		}
		return count;
	}
	
	public void acceptAlert(WebDriver driver) throws InterruptedException {
		try {
			Alert alert = waitforAlertPresent(driver);
			if (!alert.equals(null))
				alert.accept();
		} catch (Throwable ex) {
		}
	}
	
	public Alert waitforAlertPresent(WebDriver driver) throws InterruptedException {
		int i = 0;
		Alert alert = null;
		while (i++ < 30) {
			try {
				alert = driver.switchTo().alert();
				return alert;
			} catch (NoAlertPresentException e) {
				Thread.sleep(1000);
				continue;
			}
		}
		return alert;
	}
	public void waitForPopUp(WebDriver driver, String b) throws InterruptedException {
		Thread.sleep(3000);
		try {
			selectPopUp(driver, b);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (NoSuchWindowException e1) {
		} catch (Exception e) {
		}
	}
	
	public void selectPopUp(WebDriver driver, String arg) {
		boolean flag = false;
		try {
			for (int i = 0; i < 2 && flag == false; i++) {
				Set<String> pops = driver.getWindowHandles();
					Iterator<String> it = pops.iterator();
					if (pops.size() > 1) {
						System.out.println("No of Windows " + pops.size());
						for (int j = 0; j < pops.size() && flag == false; j++) {
							String popupHandle = it.next().toString();
							if (driver.switchTo().window(popupHandle).getTitle().contains(arg)) {
								driver.switchTo().window(popupHandle);
								flag = true;
							}
						}flag = true;
						pops.clear();
					}
			}
		} catch (NoSuchWindowException e) {
			System.out.println("Not able to Navigate to Window " + arg);
		} catch (Exception e) {
		}
	}
	
	public void checkUsingJavaScript(WebDriver driver, String obj,String ObjectName ) throws InterruptedException {
		try
		{
		WebElement element = null;
		if(obj.startsWith("id")){
			element = driver.findElement(By.id(obj.split("id:")[1]));	
		}else if(obj.startsWith("name")){
			element = driver.findElement(By.name(obj.split("name:")[1]));
		}else{
			element = driver.findElement(By.xpath(obj));
		}
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		Extent_Reporting.Log_Pass(ObjectName+" clicked", ObjectName+" Ciicked");
		}
		catch(Throwable t)
		{
		
			Extent_Reporting.Log_Fail(ObjectName+" Not Present", ObjectName+" Not Present", driver);
			t.printStackTrace();
			new Exception(ObjectName+" not present");
		}
	}


	public void waitForFrameAndSwitch(WebDriver driver , String frameName) {
		WebDriverWait wait = new WebDriverWait(driver,60);
		driver.switchTo().defaultContent();
		
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
		
	}
	
	public void waitForElementVisible(WebDriver driver, String element,String Element_Name) throws Throwable {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
		} 
		 catch (Throwable we) {
			 we.printStackTrace();
			 Extent_Reporting.Log_Fail(Element_Name+"Not visible", Element_Name+"Not visible", driver);
		}
	}
public void Javascriptexecutor_forClick(WebDriver driver , String frameName,String XpathObject,String ObjectName) throws Throwable {
	waitForPageToLoad(driver);
	waitForFrameAndSwitch(driver, frameName);
	waitForPageToLoad(driver);
	waitForElementVisible(driver, XpathObject,ObjectName);
	try
	{
			WebElement e=driver.findElement(By.xpath(XpathObject));
	
			JavascriptExecutor js = (JavascriptExecutor)driver;
			
			
			js.executeScript("arguments[0].click();", e);
			Extent_Reporting.Log_Pass(ObjectName+" clicked", ObjectName+" Ciicked");
			js=null;
			e=null;
	}
	catch(Throwable t)
	{
	
		Extent_Reporting.Log_Fail(ObjectName+" Not Present", ObjectName+" Not Present", driver);
		t.printStackTrace();
		new Exception(ObjectName+" not present");
	}

	
}

public void clickLinkBypartialLinkText(WebDriver screenName,String linkText,String Element_Name) throws Exception
{
    try
	{
		WebElement webButton = screenName.findElement(By.partialLinkText(linkText));
		webButton.click();
		Extent_Reporting.Log_Pass(Element_Name+" Clicked",Element_Name+" Clicked");
	}
    catch(Throwable t)
	{ 
		Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
		t.printStackTrace();
		throw new Exception("Element Not Present");
	}
}
public void clickLinkBypartialLinkTextIfExist(WebDriver screenName,String linkText,String Element_Name) throws Exception
{
    try
	{
		WebElement webButton = screenName.findElement(By.partialLinkText(linkText));
		webButton.click();
		Extent_Reporting.Log_Pass(Element_Name+" Clicked",Element_Name+" Clicked");
	}
    catch(Throwable t)
	{ 
	//	Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
		//t.printStackTrace();
		//throw new Exception("Element Not Present");
	}
}

public void clickLinkByLinkTextIfExist(WebDriver screenName,String linkText,String Element_Name) throws Exception
{
    try
	{
		WebElement webButton = screenName.findElement(By.linkText(linkText));
		webButton.click();
		Extent_Reporting.Log_Pass(Element_Name+" Clicked",Element_Name+" Clicked");
	}
    catch(Throwable t)
	{ 
		
	}
}

public boolean  CheckifExist(WebDriver driver, String element) {
	try {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 1);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
		waitForPageToLoad(driver);
		return true;
		
	} catch (NoSuchWindowException e) {
		System.out.println("not exist");
		waitForPageToLoad(driver);
		return false;
	} catch (InvalidElementStateException e) {
		waitForPageToLoad(driver);
		return false;
	} catch (TimeoutException te) {
		waitForPageToLoad(driver);
		return false;
	} catch (NoSuchElementException e) {
		waitForPageToLoad(driver);
		return false;
	} catch (WebDriverException we) {
		waitForPageToLoad(driver);
		return false;
	}
}
public boolean  CheckifExistAndReport(WebDriver driver, String element,String Element_Name) throws InterruptedException {
	try {
	//	driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 1);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
		Extent_Reporting.Log_Pass(Element_Name + " Exist", Element_Name + " is Exist");
		System.out.println("Element Exist");
		waitForPageToLoad(driver);
		return true;
		
	} catch (Throwable t) {
		waitForPageToLoad(driver);
		System.out.println("not exist");
		Extent_Reporting.Log_Fail(Element_Name + "does not Exist", Element_Name + "does not Exist",driver);
		
		return false;
	}
		
	
}
public void Clickbtn(WebDriver screenName,String ObjectxPath,String Element_Name) throws Exception
{
	Thread.sleep(500);
	try
	{
		System.out.println("path :"+ObjectxPath);
		WebElement webButton = screenName.findElement(By.xpath(ObjectxPath));
		
		webButton.click();
		//webButton.sendKeys(Keys.ENTER);
		Extent_Reporting.Log_Pass(Element_Name+" Clicked",Element_Name+" Clicked");
	}
	catch(Throwable t)
	{ 
		Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
		t.printStackTrace();
		throw new Exception("Element Not Present");
	}
}


public int getTableRowCount(WebDriver driver,String xapth)
{
	try
	{
	WebElement htmltable=driver.findElement(By.xpath(xapth));
	elementHighlight(driver,htmltable);
	List<WebElement> rows=htmltable.findElements(By.tagName("tr"));
	//List<WebElement> columns=rows.get(1).findElements(By.tagName("td"));

	//System.out.println("Number of columns:"+rows.size());
	System.out.println(rows.size());
	 return rows.size();
	}
	catch(Throwable t)
	{
		t.printStackTrace();
		return 0;
	}
	
}

public boolean CheckTableContains(WebDriver driver,String xapth,String value,String Element_Name) throws InterruptedException
{
	int cnt=0;
	try
	{
	WebElement htmltable=driver.findElement(By.xpath(xapth));

	List<WebElement> allRows=htmltable.findElements(By.tagName("tr"));
	//List<WebElement> columns=rows.get(1).findElements(By.tagName("td"));

	//System.out.println("Number of columns:"+rows.size());
	
		for (WebElement row : allRows) { 
		    List<WebElement> cells = row.findElements(By.tagName("td")); 
	
		    // Print the contents of each cell
		    for (WebElement cell : cells) { 
		    //	System.out.println(cell.getText());
		        if (cell.getText().contains(value))
		        		
		        {
		        	cnt=1;
		        	break;
		        }
		       
		    }
		    
		    if (cnt==1)
	        {
	        	break;
	        }
		    
		}
		if (cnt==1)
    	{Extent_Reporting.Log_Pass(value + " Exists in "+Element_Name, value + " Exists in table "+Element_Name);
    	return true;
    	}
	    else
	    {
	    	Extent_Reporting.Log_Fail(value + " does not exists in table "+Element_Name, value + " does not xists in table "+Element_Name,driver);
	    }
		
	}
	catch(Throwable t)
	{
		Extent_Reporting.Log_Fail(value + " does not exists in table ", value + " does not xists in table ",driver);
		return false;
	}
	
	return true;
}
public boolean CheckTableHeaderContains(WebDriver driver,String xapth,String value) throws InterruptedException
{
	int cnt=0;
	try
	{
	WebElement htmltable=driver.findElement(By.xpath(xapth));

	List<WebElement> allRows=htmltable.findElements(By.tagName("tr"));
	//List<WebElement> columns=rows.get(1).findElements(By.tagName("td"));

	//System.out.println("Number of columns:"+rows.size());
	
		for (WebElement row : allRows) { 
		    List<WebElement> cells = row.findElements(By.tagName("th")); 
	
		    // Print the contents of each cell
		    for (WebElement cell : cells) { 
		        if (cell.getText().contains(value))
		        {
		        	cnt=1;
		        	break;
		        }
		       
		    }
		    if (cnt==1)
	        {
	        	break;
	        }
		    
		}
		
		if (cnt==1)
    	{Extent_Reporting.Log_Pass(value + " Exists in table ", value + " Exists in table ");
    	return true;
    	}
	    else
	    {
	    	Extent_Reporting.Log_Fail(value + " does not exists in table ", value + " does not xists in table ",driver);
	    }
			
	}
	catch(Throwable t)
	{
		Extent_Reporting.Log_Fail(value + " does not exists in table ", value + " does not xists in table ",driver);
		return false;
	}
	
	return true;
}

	public boolean checkElementClickable(WebDriver driver, String element,String Web_Element) throws InterruptedException {
	try {
		
	    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 1);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
		Extent_Reporting.Log_Pass(Web_Element+" Element is clickable", Web_Element+" Element is clickable");
		waitForPageToLoad(driver);
		return true;
	} catch (Throwable e) {
		waitForPageToLoad(driver);
		Extent_Reporting.Log_Fail(Web_Element+" Element is not clickable", Web_Element+" Element is not clickable",driver);
		return false;
	} 
	
	
	}
	
	public boolean CheckTableallRowsofColumnContains(WebDriver driver,String xapth,String value,int column,String Element_Name ) throws InterruptedException
	{
		int cnt=0;
		try
		{
		WebElement htmltable=driver.findElement(By.xpath(xapth));

		List<WebElement> allRows=htmltable.findElements(By.tagName("tr"));
		//List<WebElement> columns=rows.get(1).findElements(By.tagName("td"));

		System.out.println("Number of rows:"+allRows.size());
		
			for (WebElement row : allRows) { 
			    List<WebElement> cells = row.findElements(By.tagName("td")); 
		
			    // Print the contents of each cell
			  //  for (WebElement cell : cells) { 
			    System.out.println(cells.get(column).getText());
			        if (cells.get(column).getText().contains(value))
			        {
			        	cnt=1;
			        	
			        }
			        else
			        {
			        	cnt=0;
			        }	
			       
			    
			    
			   
			    
			}
			if (cnt==1)
	    	{Extent_Reporting.Log_Pass(value + " Exists in all records of table "+Element_Name, value + " Exists in in all records of table "+Element_Name);
	    	return true;
	    	}
		    else
		    {
		    	Extent_Reporting.Log_Fail(value + " does not exists in  all records of table "+Element_Name, value + " does not exists in  all records of table "+Element_Name,driver);
		    }
			System.out.println("*********************completed");
		}
		catch(Throwable t)
		{
			Extent_Reporting.Log_Fail(value + " does not exists in  all records of table "+Element_Name, value + " does not eists in all records of table "+Element_Name,driver);
			t.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean  CheckifTextExistAndReport(WebDriver driver, String element,String Element_Name) throws InterruptedException {
		try {
			//driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
			String Strelement="//*[contains(text(),'"+element+"')]";
			driver.findElement(By.xpath(Strelement));
			Extent_Reporting.Log_Pass(Element_Name + " Exist", Element_Name + " is Exist");
			System.out.println("Element Exist");
		//	return true;
			//driver.switchTo().defaultContent();
			//System.out.println(""+driver.getPageSource().toString());
			//System.out.println("");
		//	if (driver.getPageSource().contains(element))
		//	{
			waitForPageToLoad(driver);
				return true;
		//	}
		//	else
		//	{
			//	return false;
		//	}
			
			
		} catch (Throwable t) {
			waitForPageToLoad(driver);
			System.out.println("not exist");
			t.printStackTrace();
			Extent_Reporting.Log_Fail(Element_Name + " does not Exist", Element_Name + "does not Exist",driver);
			
			return false;
		}
			
		
		
		
	}
	
	public void Verifydroplist(WebDriver driver,String Xpath,String ExpectedValues,String Element_name ) throws Exception
	{
		String listvalues="";
		try
		{
		
			
			
		
		WebElement dropdown = driver.findElement(By.xpath(Xpath));  
		
		Select select = new Select(dropdown);  
		List<WebElement> options = select.getOptions();  
		for(WebElement we:options)  
		{  
			listvalues=listvalues+we.getText();  
		}  
		
		
		String val[]=ExpectedValues.split(";");
		int cnt=1;
		for(String x:val)
			
		{
			if (listvalues.contains(x))
			{
				
			}
			else
			{
				cnt=0;
				Extent_Reporting.Log_Fail("Value does not exist in drop list "+Element_name,x +" Value does not exist in drop list "+Element_name, driver);
			}
			
		}
		
		if (cnt==1)
		{
			Extent_Reporting.Log_Pass("Value exist in drop list "+Element_name,ExpectedValues+" Value  exist in drop list "+Element_name);
			
		}
	  }
		 catch(Throwable t)
		{
			
			Extent_Reporting.Log_Fail(Element_name+" does not Exist",Element_name+" does not Exist", driver);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}

	public void elementHighlight(WebDriver driver,WebElement element) {
	/*	for (int i = 0; i < 4; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);",element, "color: red; border: 3px solid red;");
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);",element, "");
			
		}
		*/
		
	}
	public void WaitUntilExist(WebDriver driver,String Element)
	{
		RemovewaitForPageToLoad(driver);
		int i=1;
		do
		{
			
			//System.out.println("loop "+i);
			try
			{
				driver.findElement(By.xpath(Element));
			//	System.out.println("Element found Exiting ");
				break;
			}
			catch(Throwable t)
			{
			//	System.out.println("Element not found");
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i=i+1;
			if (i==50)
			{
				break;
			}
			
		}while(true);
		waitForPageToLoad(driver);
	}
	public void RemovewaitForPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Integer.parseInt("0"), TimeUnit.SECONDS);
	}
	public void Waituntilsiappear(WebDriver driver,String Element)
	{
		RemovewaitForPageToLoad(driver);
		do
		{
			try
			{
				driver.findElement(By.xpath(Element));
			}
			catch(Throwable t)
			{
				break;
			}
			
			
			
		}while(true);
		waitForPageToLoad(driver);
	}
	/*
	driver.switchTo().defaultContent();
	
	 frames=driver.findElements(By.tagName("iframe"));
	 for (int i=0;i<frames.size();i++)
	 {
		 System.out.println(frames.get(i).getAttribute("id"));
		 
		 driver.switchTo().frame(frames.get(i));
		 Thread.sleep(1000);
		
			try{
				//action.Clickbtn(driver, RandomSearchIDradio, "RandomSearchIDradio");
				//break;
				driver.findElement(By.xpath(claimsubmisionpage_label));
				System.out.println("claimsubmisionpage_label Element Identified");
				break;
			}
			catch(Throwable t)
			{
				
			}
		 
		 
		 driver.switchTo().defaultContent();
		 Thread.sleep(1000);
		 frames=driver.findElements(By.tagName("iframe"));
	 }
	*/
	public boolean isElementEnabled(WebDriver driver,String xpath,String Element_Name)
	{
		boolean flag=false;
		try
		{
			if(driver.findElement(By.xpath(xpath)).isEnabled())
			{	Extent_Reporting.Log_Pass(Element_Name +" is Enabled ", Element_Name +" is Enabled ");
				flag = true;
			}
		}
		catch(Throwable e)
		{
			Extent_Reporting.Log_Pass(Element_Name +" is not Exists ", Element_Name +" is not Exists ");
			flag = false;
		}
		return flag;

	}
	public void clickButtonwithEnterKeytwice(WebDriver screenName,String ObjectxPath,String Element_Name) throws Exception
	{
		try
		{
			WebElement webButton = screenName.findElement(By.xpath(ObjectxPath));
			//webButton.click();
			webButton.sendKeys(Keys.ENTER);
			webButton.sendKeys(Keys.ENTER);
			Extent_Reporting.Log_Pass(Element_Name+" Clicked",Element_Name+" Clicked");
		}
		catch(Throwable t)
		{ 
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}
	
	public void selectDropBoxValuebyVisibleTextwithoutClick(WebDriver screenName,String ObjectxPath,String value,String Element_Name) throws Exception
	{
		try
		{
			WebElement selectDropBox = screenName.findElement(By.xpath(ObjectxPath));
		//	screenName.findElement(By.xpath(ObjectxPath)).click();
			Select select = new Select(selectDropBox);
			select.selectByVisibleText(value);
		//	Thread.sleep(500);
		//	select.selectByValue(value);
			Extent_Reporting.Log_Pass(Element_Name+" selected with "+value,Element_Name+" selected with "+value);
		}
		catch(Throwable t)
		{ 
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}

	}
	public void Javascriptexecutor_forClick(WebDriver driver ,String XpathObject,String ObjectName) throws Throwable {
		waitForPageToLoad(driver);
		//waitForFrameAndSwitch(driver, frameName);
		waitForPageToLoad(driver);
		waitForElementVisible(driver, XpathObject,ObjectName);
		try
		{
				WebElement e=driver.findElement(By.xpath(XpathObject));
				
				JavascriptExecutor js = (JavascriptExecutor)driver;
				
				
				js.executeScript("arguments[0].click();", e);
				Extent_Reporting.Log_Pass(ObjectName+" clicked", ObjectName+" Ciicked");
				js=null;
				e=null;
		}
		catch(Throwable t)
		{
		
			Extent_Reporting.Log_Fail(ObjectName+" Not Present", ObjectName+" Not Present", driver);
			t.printStackTrace();
			new Exception(ObjectName+" not present");
		}

		
	}
	
	public void Javascriptexecutor_InputText(WebDriver driver ,String XpathObject,String ObjectName) throws Throwable {
		waitForPageToLoad(driver);
		//waitForFrameAndSwitch(driver, frameName);
		waitForPageToLoad(driver);
		waitForElementVisible(driver, XpathObject,ObjectName);
		try
		{			
			WebElement TextBox = driver.findElement(By.xpath(XpathObject));
			//WebElement e=driver.			
			JavascriptExecutor js= (JavascriptExecutor) driver;
			js.executeScript("arguments[0].value='email@domain.com';$(arguments[0]).trigger('change');" , TextBox);			
				Extent_Reporting.Log_Pass(ObjectName+" clicked1", ObjectName+" Ciicked");
				js=null;
				TextBox=null;
		}
		catch(Throwable t)
		{
		
			Extent_Reporting.Log_Fail(ObjectName+" Not Present", ObjectName+" Not Present", driver);
			t.printStackTrace();
			new Exception(ObjectName+" not present");
		}

		
	}
	
	public void waituntiltablerecordload(WebDriver driver,String Xpath,int recordcount) throws InterruptedException
	{
		int l=0;
		do
		{
			Thread.sleep(500);
			l=l+1;
			
			if(l==5 )
			{
				break;
			}
			if (getTableRowCount(driver, Xpath)>=recordcount)
			{
				break;
			}
		}while(getTableRowCount(driver, Xpath)<recordcount);
	}
	public boolean WaitUntilNumberofWindowHandleOpen(WebDriver driver,int n) throws InterruptedException {
		
		String[] browser;
		int i=1;
		int cnt=0;
		do
		{
			Set<String> handles = driver.getWindowHandles();

			 browser =	handles.toArray(new String[0]);
			System.out.println("Number of browsers opened are"+ browser.length);
			Thread.sleep(500);
			
			if(browser.length==n) 
			{
				cnt=1;
				break;
			}
			if (i==20)
			{
			cnt=1;
			break;
			}
			
		}while(true);
		if (cnt==1)
		{
			return true;
			
		}
		else
		{
			return false;
		}
		

	} 
	
	public boolean  CheckifExistAndReportWithWait(WebDriver driver, String element,String Element_Name) throws InterruptedException {
		try {
			waitForPageToLoad(driver);
		    driver.findElement(By.xpath(element));
			Extent_Reporting.Log_Pass(Element_Name + " Exist", Element_Name + " is Exist");
			System.out.println("Element Exist");
			
			return true;
			
		} catch (Throwable t) {
			waitForPageToLoad(driver);
			System.out.println("not exist");
			t.printStackTrace();
			Extent_Reporting.Log_Fail(Element_Name + "does not Exist", Element_Name + "does not Exist",driver);
			
			return false;
		}
			
		
	}
	public boolean  ClickifTextExistAndReport(WebDriver driver, String element) throws InterruptedException {
		try {
			//driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
			String Strelement="//*[contains(text(),'"+element+"')]";
			driver.findElement(By.xpath(Strelement)).click();
			Extent_Reporting.Log_Pass(element + " clicked", element + " is clicked");
			System.out.println("Element Exist");
		//	return true;
			//driver.switchTo().defaultContent();
			//System.out.println(""+driver.getPageSource().toString());
			//System.out.println("");
		//	if (driver.getPageSource().contains(element))
		//	{
			waitForPageToLoad(driver);
				return true;
		//	}
		//	else
		//	{
			//	return false;
		//	}
			
			
		} catch (Throwable t) {
			waitForPageToLoad(driver);
			System.out.println("not exist");
			t.printStackTrace();
			Extent_Reporting.Log_Fail(element + " does not Exist", element + "does not Exist",driver);
			
			return false;
		}
			
		
		
		
	}
	
	
	
	
	public void clickDropDown(WebDriver screenName,String ObjectxPath,String Element_Name) throws Exception
	{
		try
		{
			WebElement webButton = screenName.findElement(By.xpath(ObjectxPath));
			webButton.click();
			Extent_Reporting.Log_Pass(Element_Name+" Clicked",Element_Name+" Clicked");
		}
		catch(Throwable t)
		{ 
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}
	

public void inputTextwithClick(WebDriver screenName,String ObjectxPath,String sValue,String Element_Name) throws Exception
 {
   try{
                   WebElement inputText = screenName.findElement(By.xpath(ObjectxPath));
                   inputText.click();
                   Thread.sleep(100);
                   System.out.println(sValue);
                   inputText.sendKeys(sValue);
                   inputText.sendKeys(Keys.ENTER);
                   Extent_Reporting.Log_Pass(Element_Name+" Entered",sValue + " entered in "+ Element_Name);
            }

             catch(Throwable t)

            { 

                    Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);

                    t.printStackTrace();

                    throw new Exception("Element Not Present");

            }

            

    }
	  public void MouseClick(WebDriver driver,String Xpath) throws AWTException
	    {
	    	  Robot bot = new Robot();
	    	   WebElement e= driver.findElement(By.xpath(Xpath));
	    	   int x=e.getLocation().getX();
	    	   int y=e.getLocation().getY();
	    	   System.out.println(x+" "+y);
	    	    bot.mouseMove(x+8, y+70);    
	    	    bot.mousePress(InputEvent.BUTTON1_MASK);
	    	    bot.mouseRelease(InputEvent.BUTTON1_MASK);
	    }

	//**********verify image.***********	
			public String Verify_Image(WebDriver screenName,String ObjectxPath,String Element_Name) throws Exception
			{
				try
				{
					WebElement ImageFile = screenName.findElement(By.xpath(ObjectxPath));			        
			        Boolean ImagePresent = (Boolean) ((JavascriptExecutor)screenName).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);
			        if (!ImagePresent)
			        {
			            System.out.println("Image not displayed.");
						Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
			        }
			        else
			        {
			            System.out.println("Image displayed.");
						Extent_Reporting.Log_Pass(Element_Name+" exist",Element_Name+" As expected");
						Extent_Reporting.Log_report_img("Respective Image is exist", "Image", screenName);
			        }								
					return ImageFile.getAttribute("src");
				}
				catch(Throwable t)
				{ 
					Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
					t.printStackTrace();
					throw new Exception("Image Not Present");
				}
		}
			
			
			
	public void Check_Validation_NoSpecialChar_Alphabetic(WebDriver screenName,String ObjectxPath,String CharSequence, String Value,String Element_Name) throws Exception{
		try{
			
			WebElement InputText = screenName.findElement(By.xpath(ObjectxPath));	
			InputText.click();
			InputText.clear();
			InputText.sendKeys(Value);
			Thread.sleep(1000);
			//Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
			Pattern pattern = Pattern.compile(CharSequence, Pattern.CASE_INSENSITIVE);
			//Pattern pattern = Pattern.compile("[^'a-zA-Z]");

			String ExpectedResult = screenName.findElement(By.xpath(ObjectxPath)).getAttribute("value");		      
		    Matcher matcher = pattern.matcher(ExpectedResult);		 
		    if (matcher.matches()) {
					Extent_Reporting.Log_Fail("string '"+ExpectedResult + "' contains special character","string '"+ExpectedResult + "' contains special character", screenName);
		      } else {
					Extent_Reporting.Log_Pass("string '"+ExpectedResult + "' doesn't contains special character","string '"+ExpectedResult + "' doesn't contains special character");
		      }
		}catch(Throwable t)
		{ 
			Extent_Reporting.Log_Fail(Element_Name+" does not Exist",Element_Name+" does not Exist", screenName);
			t.printStackTrace();
			throw new Exception("Image Not Present");
		}
		
	}
	
	public void analyzeLog(WebDriver ScreenName) {
        LogEntries logEntrie = ScreenName.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntrie) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel()+ " " + entry.getMessage());
            //do something useful with the data
        }
    }
	
}
