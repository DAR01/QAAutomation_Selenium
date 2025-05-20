package com.qa.opencart.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Manage all the properties options and leave the driver factory clean.
 */
public class OptionsManager {
	private Properties prop;
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOptions() {
		ChromeOptions co = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("running in headless mode...");
			co.addArguments("--headless");
		
		}
		
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			System.out.println("running in incognito mode....");
			co.addArguments("--incognito");}
			
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			co.setCapability("browserName", "chrome");
			co.setBrowserVersion(prop.getProperty("browserVersion").trim());
			
			Map<String, Object> selenoidOptions = new HashMap<>();
			selenoidOptions.put("screenResolution","1280x1024x24");
			selenoidOptions.put("enableVNC",true);
			selenoidOptions.put("name", prop.getProperty("testname"));
			co.setCapability("selenoid:Options", selenoidOptions);
		}
			
		return co;
	}
	
	
	public FirefoxOptions getFirefoxOptions() {
		FirefoxOptions fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			fo.addArguments("--headless");
			System.out.println("running in headless mode.");
		}
		
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			fo.addArguments("--incognito");
			System.out.println("running in incognito mode.");
		}
		
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			fo.setCapability("browserName", "firefox");
			fo.setBrowserVersion(prop.getProperty("browserVersion").trim());
			
			Map<String, Object> selenoidOptions = new HashMap<>();
			selenoidOptions.put("screenResolution","1280x1024x24");
			selenoidOptions.put("enableVNC",true);
			selenoidOptions.put("name", prop.getProperty("testname"));
			fo.setCapability("selenoid:Options", selenoidOptions);
		}
		
		return fo;
	}
	
	
	public EdgeOptions getEdgeOptions() {
		EdgeOptions eo = new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			eo.addArguments("--headless");
			System.out.println("running in headless mode.");
		}
		
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			eo.addArguments("--inPrivate");
			System.out.println("running in incognito mode.");
		}
		
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			eo.setCapability("browserName", "edge");
			eo.setBrowserVersion(prop.getProperty("browserVersion").trim());
			
			Map<String, Object> selenoidOptions = new HashMap<>();
			selenoidOptions.put("screenResolution","1280x1024x24");
			selenoidOptions.put("enableVNC",true);
			selenoidOptions.put("name", prop.getProperty("testname"));
			eo.setCapability("selenoid:Options", selenoidOptions);
		}
		
		return eo;
	}

}
