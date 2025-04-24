package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	//write in 3 steps:
			private ElementUtil eleUtil;
			//create a driver reference
			
			private WebDriver driver;
				//1. maintain a private By locators also called Object repository
			
			private final By prodcutHeader = By.tagName("h1");
			private final By productImages = By.cssSelector("ul.thumbnails img");
			private final By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
			private final By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
			
			
			private Map<String, String> productMap ; 
			//private productMap;
			
			//2. public page constructors...
			public ProductInfoPage(WebDriver driver) {
				this.driver = driver;
				eleUtil = new ElementUtil(driver);
				//productMap = new HashMap<String, String>();
			}

			//On the home/Account page you check that title and menu elements are properly displayed.
			
			public String getProductHeader() {
				System.out.println("getting product header name: ...");
				String header = eleUtil.waitForElementVisible(prodcutHeader, AppConstants.MEDIUM_DEFAULT_TIMEOUT).getText();
				System.out.println("product header is: " + header);
				return header;
			}
			
			
			public int getProductImagesCount() {
				int imageCount = eleUtil.waitForAllElementsVisible(productImages, AppConstants.LONG_DEFAULT_TIMEOUT).size();
			System.out.println("Total number of images: " + imageCount);
			return imageCount;
			}
			
			
			
			
			public Map<String, String> getProductDetailsMap() {
				productMap = new TreeMap<String, String>();
				//productMap = new LinkedHashMap<String,String>();
				//productMap = new HashMap<String, String>();
				productMap.put("productheader", getProductHeader());
				System.out.println("This productheader is:  " + productMap.get("productheader"));
				productMap.put("productimages", String.valueOf(getProductImagesCount()));
				System.out.println("This productimages count is:  " + productMap.get("productimages"));
				getProductMetaData();
				getProductPriceData();
				System.out.println("Full product details: "+ productMap);
				return productMap;
			}
			
			
			
			private void getProductMetaData() {
			List<WebElement> MetaList =	eleUtil.waitForAllElementsVisible(productMetaData, AppConstants.LONG_DEFAULT_TIMEOUT);
			//System.out.println(MetaList);
			//System.out.println(MetaList.get(0).getText());
			for(WebElement e : MetaList) {
				System.out.println("First productMap: "+ productMap);
				String metaData = e.getText();
				String meta [] = metaData.split(":");
				String metaKey= meta[0];
				String metaValue = meta[1].trim();
				productMap.put(metaKey, metaValue);
				}
			//System.out.println("current productMap: "+ productMap);
			}
			
			private void getProductPriceData() {
			//	productMap = new HashMap<String, String>();
					List<WebElement> priceList =	eleUtil.waitForAllElementsVisible(productPriceData, AppConstants.LONG_DEFAULT_TIMEOUT);
					String productPrice = priceList.get(0).getText();
					String exTaxPrice = priceList.get(1).getText().split(":")[1].trim();
					productMap.put("productprice", productPrice);
					productMap.put("extaxprice", exTaxPrice);
			}
			
			
}
