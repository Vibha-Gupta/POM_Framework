package com.zoopla.testcases;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.zoopla.base.BaseClass;
import com.zoopla.constant.HomePageConstant;
import com.zoopla.constant.PropertyListPageConstant;
import com.zoopla.pages.HomePage;
import com.zoopla.pages.PropertyDetailsPage;
import com.zoopla.pages.PropertyListPage;

public class PropertyListPageTestCase extends BaseClass {

	HomePage HomePage;
	PropertyListPage PropertyListPage;
	PropertyDetailsPage PropertyDetailsPage;
	
	static final Logger log = Logger.getLogger(PropertyListPageTestCase.class); 
	
	public PropertyListPageTestCase() 
	{
		super();
	}
	
	@BeforeMethod(alwaysRun = true)  
	public void setUp() {
		log.debug("...............Executing PropertyListPageTestcase @BeforeMethod:Setup...............");
		browserInitialization();
		HomePage = new HomePage();
		PropertyListPage  = new PropertyListPage();
		PropertyDetailsPage = new PropertyDetailsPage();
	}
	
	@AfterMethod(alwaysRun = true) 
	public void terminateBrowser() 
	{
		log.debug("...............Executing PropertyListPageTestcase @AfterMethod:terminateBrowser...............");
		driver.quit();
	}
	
	@Test
	public void printCountOfPropertiesDisplayedTestCase() 
	{
		log.debug("********Executing 'printCountOfPropertiesDisplayed' Test case*********");
		PropertyListPage = HomePage.SearchLocation();
		log.debug("********The total properties found with search Location '" + HomePageConstant.locationConstant  + 
				"' are: "+PropertyListPage.getPropertyListCount());
	}
	
	@Test
	public void printSelectedPropertyPriceTestCase() 
	{
		log.debug("********Executing 'printSelectedPropertyPrice' Test case*********");
		PropertyListPage = HomePage.SearchLocation();
		log.debug("Displaying the price of selected property with position " 
				+ PropertyListPageConstant.propertyPosition + " is " + 
				PropertyListPage.getSelectedPropertyPrice(PropertyListPageConstant.propertyPosition));
	}
	
	@Test
	public void printAllPropertyPriceTestCase() 
	{
		log.debug("********Executing 'printAllPropertyPrice' Test case*********");
		PropertyListPage = HomePage.SearchLocation();
		log.debug("Displaying the number and price of properties below: ");
		log.debug(PropertyListPage.getAllPropertiesPrice());
	}
	
	@Test
	public void printPropertyPriceInSortedOrderTestCase() 
	{
		log.debug("********Executing 'printPropertyPriceInSortedOrder' Test case*********");
		PropertyListPage = HomePage.SearchLocation();
		log.debug("Displaying the price of properties in sorted order: ");
		log.debug(PropertyListPage.getPropertiesPriceInSortedOrder());
	}
	
	@Test
	public void clickOnSelectedPropertyTestCase() 
	{
		log.debug("********Executing 'clickOnSelectedProperty' Test case*********");
		PropertyListPage = HomePage.SearchLocation();
		PropertyDetailsPage = PropertyListPage.clickOnSelectedProperty(PropertyListPageConstant.propertyPosition);
		log.debug("********Successfully clicked on the selected property********");
	}
	
	
}
