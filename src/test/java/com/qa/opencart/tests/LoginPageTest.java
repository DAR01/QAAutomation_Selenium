package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountsPage;

import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

import static com.qa.opencart.constants.AppConstants.*;

@Feature("F1: Opencart login page")
@Epic("E12: Opencart Happy part")
@Story("US 45: opencart user joruney")
public class LoginPageTest extends BaseTest{
	
	
	@Test
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		ChainTestListener.log("checking login page title example");
		Assert.assertEquals(actTitle, LOGIN_PAGE_TITLE);
	}

	
	@Step("fetching the page title") // this comes from allure report
	@Severity(SeverityLevel.BLOCKER)// this comes from allure report
	@Owner("jean Paul Gauthier")// this comes from allure report
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
