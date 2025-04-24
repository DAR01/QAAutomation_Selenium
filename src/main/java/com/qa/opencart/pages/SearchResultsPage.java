package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {

	//write in 3 steps:
			private ElementUtil eleUtil;
			//create a driver reference
			private WebDriver driver;

			//1. maintain a private By locators also called Object repository
			private final By resultsProduct = By.cssSelector("div.product-thumb");
			
			//2. public page constructors...
			public SearchResultsPage(WebDriver driver) {
				this.driver = driver;
				eleUtil = new ElementUtil(driver);
			}



			public int getResultsProductCount() {
				System.out.println("print the searchCount...");
				int searchCount = 
						eleUtil.waitForAllElementsVisible(resultsProduct, AppConstants.MEDIUM_DEFAULT_TIMEOUT).size();
			System.out.println("total number of search products: " + searchCount);
			return searchCount;
			}
			
			public ProductInfoPage selectProduct(String productName) {
				
				System.out.println("productName: "+ productName);
				eleUtil.doClick(By.linkText(productName));
				System.out.println("productName: "+ productName + " is opened");
				return new ProductInfoPage(driver);
			}
			
			
			
			
			
			
}
