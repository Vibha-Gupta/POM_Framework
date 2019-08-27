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

public class AgentPageTestCase extends BaseClass {

	static final Logger log = Logger.getLogger(AgentPageTestCase.class);
	
	HomePage HomePage;
	PropertyListPage PropertyListPage;
	AgentPage AgentPage;
	PropertyDetailsPage PropertyDetailsPage;
	
	String agentnameFromPropDetailsPage ;
	String TelNumbFromPropDetailsPage;

	public AgentPageTestCase() {
		super();
	}

	@BeforeMethod
	public void setUp() throws Throwable {
		browserInitialization();
		HomePage = new HomePage();
		PropertyListPage  = new PropertyListPage();
		PropertyDetailsPage = new PropertyDetailsPage();
		AgentPage = new AgentPage();
		
		//Sequence of Prior steps to be called before each test case
		HomePage.SearchLocation();
		PropertyListPage.clickOnSelectedProperty(PropertyListPageConstant.propertyPosition);
		agentnameFromPropDetailsPage = PropertyDetailsPage.getAgentName();
		TelNumbFromPropDetailsPage = PropertyDetailsPage.getAgentTelephoneNumber();
		PropertyDetailsPage.clickOnAgentName();
		
	}

	@AfterMethod
	public void terminateBrowser() {
		driver.quit();
	}

	@Test
	public void verifyAgentNameTestCase() {
		if (AgentPage.getAgentName().contains(agentnameFromPropDetailsPage)){
			log.debug("verifyAgentNameTestCase - Pass");
		}else {
			log.debug("verifyAgentNameTestCase - Fail");
		}
	}
	
	@Test
	public void verifyAgentPhoneNumberTestCase() {
		if (AgentPage.getAgentPhoneNumber().contains(TelNumbFromPropDetailsPage)){
			log.debug("verifyAgentPhoneNumber - Pass");
		}else {
			log.debug("verifyAgentPhoneNumber - Fail");
		}
		
	}

}
