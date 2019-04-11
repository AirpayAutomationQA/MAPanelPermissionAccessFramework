package com.Airpay.InitialSetup;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.Airpay.Utilities.Constants;

public class Driver_Setup {

	public WebDriver driver;
	public String ModuleName = null;
	public String driverPath = Constants.drivePath;

	public WebDriver getDriver(){
		return driver;

	}
	public void setDriver(String browserType, String appURL) throws InterruptedException{
		switch(browserType){

		case "IE":
			driver = initIEDriver(appURL);
			break;

		case "CHROME":
			driver = initChromeDriver(appURL);
			break;

		case "FIREFOX":
			driver = initfirefoxDriver(appURL);
			break;

		default :
			System.out.println("you have enetered as invalid browser");
		}		
	}

	public WebDriver initChromeDriver(String appURL) throws InterruptedException {
		//driver.get("chrome://settings/clearBrowserData");
		
		System.out.println("Launching google chrome driver!!! .");
		System.setProperty("webdriver.chrome.driver", driverPath
				+ "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		//driver.navigate().to("chrome://settings/clearBrowserData");
		Thread.sleep(5000);
		//driver.findElement(By.xpath("//*[@id='clearBrowsingDataConfirm']")).click();
		driver.navigate().to(appURL);
		driver.manage().deleteAllCookies();
		return driver;
	}


	public WebDriver initIEDriver(String appURL) {
		System.out.println("Launching google IE driver!!! .");
		System.setProperty("webdriver.ie.driver", driverPath
				+ "IEDriverServer.exe");

		DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
		cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		cap.setCapability("nativeEvents", false);
		cap.setCapability("ignoreProctedModeSettings", true);
		cap.setCapability("disable-popup-blocking", true);

		driver = new InternetExplorerDriver(cap);
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		driver.manage().deleteAllCookies();
		return driver;


	}

	public WebDriver initfirefoxDriver(String appURL) {
		System.out.println("Launching Firefor driver  !!!.");
		System.setProperty("webdriver.gecko.driver", driverPath+ "geckodriver.exe");		
		//DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		driver= new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}
	
	@Parameters({ "browserType", "appURL","ModuleName" })
	@BeforeClass
	public void initializeTestBaseSetup(String browserType, String appURL,String tcID) {
		try {
			setDriver(browserType.toUpperCase(), appURL);
			ModuleName = tcID;

		} catch (Exception e) {
			System.out.println("Error....." + e.getStackTrace());
		}
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
