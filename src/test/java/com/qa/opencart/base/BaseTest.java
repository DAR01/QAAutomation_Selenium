package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {
	
	WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	
	//prop = df.initProp();
	//protected b/c Baseclass and its child are not in the same package and I want only the child to access it.
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	protected RegisterPage registerPage;
	
	@Parameters({"browser"}) //to define the browser name to be used defined/from xml suite 
	@BeforeTest
	public void setup(String browserName) {
		df = new DriverFactory();
		prop = df.initProp();
		
		//browser name is parse from the .xml testng file
		if(browserName!=null) {
			prop.setProperty("browser", browserName); // the change is done on the fly, @ runtime without permanently changing the value of the browser in the config.prop 
		}
		
		
		//first attemp of implementation: driver = df.initDriver("chrome");
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
	}
	
	@AfterTest
	public void teardown() {
		driver.quit();
	}

}
