package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {
	
	
	@BeforeClass
	public void productInfoSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	} 
	
	
	
	//Follow this pattern:
	//AAA: Arrange-Act-Assert(1 assertion per test)
	
	//to use same data for other test parametrise the test by getting the data from testdata file, for that the annotation @dataprovider has to be used.
	//@data provider will always return 2D array
	
	@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][]{
				{"macbook", "MacBook Pro"},
				{"macbook", "MacBook Air"}, 
				{"imac", "iMac"},
				{"samsung", "Samsung SyncMaster 941BW"},
				{"samsung", "Samsung Galaxy Tab 10.1"},
				};
	}
	
	@Test(dataProvider = "getProductTestData")
	//the number of parameter has to be equal to the number of column of the dataprovider
	public void productHeaderTest(String searchKey, String productName) {
		System.out.println("searching key macbook from dataprovider list...");
		searchResultsPage = accPage.doSearch(searchKey);
		System.out.println("selecting Macbook Pro from Dataprovider list...");
		productInfoPage = searchResultsPage.selectProduct(productName);
		System.out.println("Verifiying the product header with searchkey and productname from the dataprovider list...");
		String actHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(actHeader, productName);
	}
	
	
	
	@Test
	public void productHeaderTest() {
		System.out.println("searching macbook...");
		searchResultsPage = accPage.doSearch("macbook");
		System.out.println("selecting Macbook Pro...");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		System.out.println("Verifiying the product header...");
		String actHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(actHeader, "MacBook Pro");
	}
	
	//TODO Why is necessary that the below test depends on the above one.
	// TODO create a testdataprovider for the other tests
	@Test
	public void productImageCountTest() {
		//when asserting 1 or single feature/fonctionality with one assertion use the hard Assertion
		searchResultsPage = accPage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		int actImageCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(actImageCount, 4);
	}
	
	@Test
	public void productInfoTest() {
		searchResultsPage = accPage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> actualProductDetailsMap = productInfoPage.getProductDetailsMap();
		
		
		//all this assertion are hard assertion
		Assert.assertEquals(actualProductDetailsMap.get("productheader"), "MacBook Pro");
		Assert.assertEquals(actualProductDetailsMap.get("Brand"), "Apple");
		Assert.assertEquals(actualProductDetailsMap.get("Product Code"), "Product 18");
		Assert.assertEquals(actualProductDetailsMap.get("Availability"), "Out Of Stock");
		Assert.assertEquals(actualProductDetailsMap.get("productprice"), "$2,000.00");
		Assert.assertEquals(actualProductDetailsMap.get("extaxprice"), "$2,000.00");
		
//		//better to use softassert, a class in testng, but you need to creat it.
//		//When asserting 1 functionality/feature with multiple assertions then use softAssertion
//		SoftAssert softAssert = new SoftAssert();
//		softAssert.assertEquals(actualProductDetailsMap.get("productheader"), "MacBook Pro");
//		softAssert.assertEquals(actualProductDetailsMap.get("Brand"), "Apple");
//		softAssert.assertEquals(actualProductDetailsMap.get("Product Code"), "Product 18");
//		softAssert.assertEquals(actualProductDetailsMap.get("Availability"), "Out Of Stock");
//		softAssert.assertEquals(actualProductDetailsMap.get("productprice"), "$2,000.00");
//		softAssert.assertEquals(actualProductDetailsMap.get("extaxprice"), "$2,000.00");
//		//when using softAssertion you need to add as last statement softAssert 
//		
//		softAssert.assertAll();
	}
	
	
	
	

}
