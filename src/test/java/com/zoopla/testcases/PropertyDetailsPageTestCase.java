package com.zoopla.testcases;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.zoopla.base.BaseClass;
import com.zoopla.constant.PropertyListPageConstant;
import com.zoopla.pages.AgentPage;
import com.zoopla.pages.HomePage;
import com.zoopla.pages.PropertyDetailsPage;
import com.zoopla.pages.PropertyListPage;


public class PropertyDetailsPageTestCase extends BaseClass {

	static final Logger log = Logger.getLogger(PropertyDetailsPageTestCase.class);
	
	HomePage HomePage;
	PropertyDetailsPage PropertyDetailsPage;
	PropertyListPage PropertyListPage;
	AgentPage AgentPage;

	public PropertyDetailsPageTestCase() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		log.debug("...............Executing PropertyDetailsPageTestCase @BeforeMethod:setUp...............");
		browserInitialization();
		HomePage = new HomePage();
		PropertyListPage  = new PropertyListPage();
		PropertyDetailsPage = new PropertyDetailsPage();
		AgentPage = new AgentPage();
		
		//Sequence of Prior steps to be called before each test case
		HomePage.SearchLocation();
		PropertyListPage.clickOnSelectedProperty(PropertyListPageConstant.propertyPosition);
	}

	@AfterMethod
	public void terminateBrowser() {
		log.debug("...............Executing PropertyDetailsPageTestCase @AfterMethod:terminateBrowser...............");
		driver.quit();
	}

	@Test
	public void getAgentDetailsTestCase() {
		log.debug("********Executing 'getAgentDetails' Test case*********");
		log.debug(PropertyDetailsPage.getLogo());
	    log.debug(PropertyDetailsPage.getAgentName());
		log.debug(PropertyDetailsPage.getAgentTelephoneNumber());
	}
	
	@Test
	public void clickOnAgentNameTestCase() {
		log.debug("********Executing 'clickOnAgentName' Test case*********");
		AgentPage = PropertyDetailsPage.clickOnAgentName();
	}


}
