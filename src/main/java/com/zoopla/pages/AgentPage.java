package com.zoopla.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zoopla.base.BaseClass;
import com.zoopla.utility.TestUtility;

public class AgentPage extends BaseClass{
	
	@FindBy(xpath="//h1[@class='bottom-half']")
	@CacheLookup
	WebElement agentNameLocator;
	
	@FindBy(xpath="//img[@class='agent_logo']")
	WebElement agentLogoLocator;
	
	@FindBy(xpath="//p[@class='medium clearfix bottom-half']//span[@class='agent_phone']")
	@CacheLookup
	WebElement phoneNumberLocatorOnAgentPage;
	
	public AgentPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String getAgentName() {
		TestUtility.highLightElement(driver, agentNameLocator);
		String actualAgentName = agentNameLocator.getText();
		return actualAgentName;
	}
	
	public String getAgentPhoneNumber() {
		TestUtility.highLightElement(driver, phoneNumberLocatorOnAgentPage);
		String actualAgentPhoneNumber = phoneNumberLocatorOnAgentPage.getText();
		return actualAgentPhoneNumber;
	}
	
	public String getAgentLogoAltText() {
		TestUtility.highLightElement(driver, agentLogoLocator);
		String actualLogoAltText = agentLogoLocator.getAttribute("alt");
		return actualLogoAltText;
	}	

}
