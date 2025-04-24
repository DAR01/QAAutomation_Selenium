package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exceptions.BrowserException;

public class DriverFactory {	
	
	 WebDriver driver;
	 Properties prop;
	 OptionsManager optionsManager;
	 public static String highlight;
	 
	 /**
	  * Tis method is used to init browser on the basis of the supplied browser name.
	  * @param browserName
	  * @return
	  */
	 public WebDriver initDriver(Properties prop) {
		 String browserName = prop.getProperty("browser");
		 optionsManager = new OptionsManager(prop);
		 highlight = prop.getProperty("highlight");
		 // System.out.println("browser name :" + browserName);
		 
		 switch(browserName.toLowerCase().trim()) {
			case "chrome":
				driver = new ChromeDriver(optionsManager.getChromeOptions());
				break;
			case "safari": 
				driver = new SafariDriver();
				break;
			case "edge": 
				driver = new EdgeDriver(optionsManager.getEdgeOptions());
				break;				
			case "firefox": 
				driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
				break;
			default:
				System.out.println("Please enter valid browser name ..." + browserName);
				throw new BrowserException("==== INVALID BROWSER====");
		
			}
		 	// first try with hard coded url: driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		driver.get(prop.getProperty("url"));	
		 driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			return driver;	 
		 

	 }
	 
	 
	 
	 public Properties initProp() {
		 prop = new Properties();
		 try{FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
		 prop.load(ip);
		 }catch(FileNotFoundException e) {e.printStackTrace();}
		 catch(IOException e) {e.printStackTrace();}
		 
	 return prop;
	 }
	 
}
	 

