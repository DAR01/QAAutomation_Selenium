package com.qa.opencart.tests;

import static com.qa.opencart.constants.AppConstants.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class AccountPageTest extends BaseTest{
	
	
	//testng will first execute BeforeTest in the basetest and then come here and execute before class 
	@BeforeClass
	public void accPageSetup() {
		//make sure to attach the new return account page to a reference to prevent gc to destroy it
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		
	}
	
	@Test
	public void accPageTitleTest() {
			//String actTitle = loginPage.getLoginPageTitle();
		System.out.println("3. current account Page title is: " + accPage.getAccPageTitle());
			Assert.assertEquals(accPage.getAccPageTitle(), HOME_PAGE_TITLE);
		}
	
	
	
	@Test
	public void accPageURLTest() {
		System.out.println("4. current account page url is:" + accPage.getAccPageURL());
		Assert.assertTrue(accPage.getAccPageURL().contains(HOME_PAGE_FRACTION_URL));
		
	}
	
	
	@Test
	public void accPageHedersTest() {
		List<String> actHeadersList = accPage.getAccPageHeaders();
		Assert.assertEquals(actHeadersList,expectedAccPageHeadersList);
	}
	
	
}
	

	
	


