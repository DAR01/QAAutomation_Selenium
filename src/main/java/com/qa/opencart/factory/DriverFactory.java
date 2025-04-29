package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	// whenever creating an object of threadlocal make it static.
	//
	public static ThreadLocal<WebDriver> tDriver = new ThreadLocal<WebDriver>();
	public static String highlight;

	/**
	 * Tis method is used to init browser on the basis of the supplied browser name.
	 * 
	 * @param browserName
	 * @return
	 */
	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
		optionsManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight");
		// System.out.println("browser name :" + browserName);

		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			// this allow to bypass the race condition, as every thread will have their own
			// copy of the threadlocal generic which in this case is the webdriver
			tDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			// driver = new ChromeDriver(optionsManager.getChromeOptions());
			break;
		case "safari":
			tDriver.set(new SafariDriver());
			// driver = new SafariDriver();
			break;
		case "edge":
			tDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			// driver = new EdgeDriver(optionsManager.getEdgeOptions());
			break;
		case "firefox":
			tDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			break;
		default:
			System.out.println("Please enter valid browser name ..." + browserName);
			throw new BrowserException("==== INVALID BROWSER====");

		}
		// first try with hard coded url:
		// driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		getDriver().get(prop.getProperty("url"));
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		return getDriver();

	}

	/**
	 * 
	 * getDriver : get the local thread local copy of the driver
	 * 
	 */
	public static WebDriver getDriver() {
		return tDriver.get();
	}

	// mvn clean from the command line -D indicates parameter name for cmd line and
	// env is the source: mvn clean install -Denv="qa"

	/**
	 * 
	 * fetch the env value from file and initialize the Properties
	 */

	public Properties initProp() {

		// to fetch env use:
		String envName = System.getProperty("env");
		System.out.println("Running the tests on env : " + envName);
		FileInputStream ip = null;
		prop = new Properties();

		try {
			if (envName == null) {
				System.out.println("Your env is null, hence running the tests on QA env...BY DEFAULT");
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			} else {
				System.out.println("Running the tests on env : " + envName);
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;
				default:
					throw new FrameworkException("==== Invalid Env Name, PLEASE SUPPLY A VALIDE ENVIRONMENT=========");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	/**
	 * Take a screenshot
	 * 
	 */
	public static File getScreenShotFile() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		return srcFile;
	}

	public static byte[] getScreenShotBytee() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
	}

	public static String getScreenShotBase64() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
	}

}
