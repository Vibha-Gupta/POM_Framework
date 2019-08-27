package com.zoopla.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zoopla.base.BaseClass;
import com.zoopla.utility.TestUtility;

public class PropertyDetailsPage extends BaseClass {
	
	AgentPage AgentPage;
	public static String agentName;
	public static String altLogoText;
	public static String agentTelephoneNumber;
	
	@FindBy(xpath = "//div[@class='dp-sidebar-wrapper__contact']//img")
	@CacheLookup
	WebElement agentLogo;
	
	@FindBy(xpath="//div[@class='dp-sidebar-wrapper__contact']//h4[@class='ui-agent__name']") 
	@CacheLookup
	WebElement agentNameLocator;
	
	@FindBy(xpath="//div[@class='dp-sidebar-wrapper__contact']//a[@class='ui-link']")
	@CacheLookup
	WebElement agentTelephoneNumberLocator;
	
	public PropertyDetailsPage() {PageFactory.initElements(driver, this); }

	public String getLogo() {
		TestUtility.highLightElement(driver, agentLogo);
		altLogoText = agentLogo.getAttribute("alt");
		return altLogoText;
	}
	
	public String getAgentName() {
		TestUtility.highLightElement(driver, agentNameLocator);
		agentName = agentNameLocator.getText();
		return agentName;
	}
	
	public String getAgentTelephoneNumber() {
		TestUtility.highLightElement(driver, agentTelephoneNumberLocator);
		agentTelephoneNumber = agentTelephoneNumberLocator.getText();
		agentTelephoneNumber = agentTelephoneNumber.replace("Call", "");
		return agentTelephoneNumber;
	}
	
	public AgentPage clickOnAgentName() {
		TestUtility.highLightElement(driver, agentNameLocator);
		agentNameLocator.click();
		return new AgentPage();
	}
	
	
}