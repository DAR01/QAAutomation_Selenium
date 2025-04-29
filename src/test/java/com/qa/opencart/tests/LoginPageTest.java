package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountsPage;

import static com.qa.opencart.constants.AppConstants.*;

public class LoginPageTest extends BaseTest{
	
	
	@Test
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		ChainTestListener.log("checking login page title example");
		Assert.assertEquals(actTitle, LOGIN_PAGE_TITLE);
	}

	@Test
	public void loginPageURLTest() {
		String actURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(LOGIN_PAGE_FRACTION_URL));
	}
	
	@Test(description = "checking the forgot password") 
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	
	@Test(priority = Short.MAX_VALUE, description = "doing login check")
	public void doLoginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccPageTitle(), HOME_PAGE_TITLE);
	}
	

	@Test(enabled=true, description = "WIP---forgot pwd check")
	public void forgotPwd() {
		System.out.println("forgot pwd");
	}
	
}
