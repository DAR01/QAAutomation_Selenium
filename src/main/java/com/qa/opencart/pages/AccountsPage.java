package com.qa.opencart.pages;

import static com.qa.opencart.constants.AppConstants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	//write in 3 steps:
		private ElementUtil eleUtil;
		//create a driver reference
		
		private WebDriver driver;
			//1. maintain a private By locators also called Object repository
			
		//2. public page constructors...
		public AccountsPage(WebDriver driver) {
			this.driver = driver;
			eleUtil = new ElementUtil(driver);
		}

		//On the home/Account page you check that title and menu elements are properly displayed.
		
		private final By headers = By.cssSelector("div#content>h2");
		private final By search = By.name("search");
		private final By searchIcon = By.cssSelector("#search > span > button");
		
		public String getAccPageTitle() {
			// TODO Auto-generated method stub
				String title = eleUtil.waitForTitleIs(HOME_PAGE_TITLE, DEFAULT_TIMEOUT);
				//String title = driver.getTitle();
				System.out.println("1. Account page title: "+ title);
				return title;
			}

		public String getAccPageURL() {
			String url = eleUtil.waitForURLContains(HOME_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
			System.out.println("2. Account page fraction url is: "+ url);
			return url;
		}
			
		public List<String> getAccPageHeaders() {
			List<WebElement> headerList = eleUtil.getElements(headers);
			List<String> headerValList = new ArrayList<String>();
			for(WebElement e : headerList) {
				String text = e.getText();
				headerValList.add(text);
			}
			System.out.println("Acc page headers List: "+ headerValList);
			return headerValList;
		}
		
		
		public SearchResultsPage doSearch(String searchKey) {
			System.out.println("search key is: " + searchKey);
			eleUtil.doSendKeys(search, searchKey);
			eleUtil.doClick(searchIcon);
			return new SearchResultsPage(driver);
		}
		
		
		
		}


