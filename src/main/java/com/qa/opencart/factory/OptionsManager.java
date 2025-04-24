package com.qa.opencart.factory;

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
			co.addArguments("--incognito");
			
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
		return eo;
	}

}
