package com.zoopla.testcases;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.zoopla.base.BaseClass;
import com.zoopla.constant.HomePageConstant;
import com.zoopla.pages.HomePage;
import com.zoopla.pages.PropertyListPage;
import com.zoopla.utility.TestUtility;

public class HomePageTestCase extends BaseClass {
	
	public HomePage HomePage;
	public PropertyListPage PropertyListPage;

	static final Logger log = Logger.getLogger(HomePageTestCase.class);

	public HomePageTestCase() {
		super();
	}

	@BeforeMethod
	public void setUp() throws Throwable {
		log.debug("...............Executing HomePageTestCase @BeforeMethod:setUp...............");
		browserInitialization();
		HomePage = new HomePage();
		PropertyListPage = new PropertyListPage();
	}

	@AfterMethod
	public void terminateBrowser() {
		log.debug("...............Executing HomePageTestCase @AfterMethod:terminateBrowser...............");
		driver.quit();
	}
	
    @Test
	public void VerifyPageTitle() {
		log.debug("********Executing 'VerifyPageTitle' Test case*********");
		TestUtility.verifyPageTitle(HomePageConstant.pageTitleConstant);
	}

	@Test
	public void SearchHomeForSaleByLocation() {
		log.debug("********Executing 'SearchHomeForSaleByLocation' Test case*********");
		HomePage.inputLocation();
		PropertyListPage = HomePage.clickOnSearchButton();
		TestUtility.verifyPageTitle(HomePageConstant.pageTitleForLondon);
		log.debug("********Successfully searched properties in "+ HomePageConstant.locationConstant);
	}

}
