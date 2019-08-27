
package com.zoopla.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.zoopla.base.BaseClass;
import com.zoopla.constant.HomePageConstant;
import com.zoopla.utility.TestUtility;

public class HomePage extends BaseClass {
	
	

	@FindBy(xpath = "//input[@id='search-input-location']")
	WebElement inputLocation;
	
	@FindBy(xpath="//button[@type='submit' and @id='search-submit']")
	WebElement searchButton;
	
	public HomePage() {PageFactory.initElements(driver, this);}
	
	PropertyListPage PropertyListPage;

	public void inputLocation() {
		TestUtility.highLightElement(driver, inputLocation);
		inputLocation.sendKeys(HomePageConstant.locationConstant);
		try {
		Thread.sleep(1000);
		}catch(Exception e) {
			e.getMessage();
		}
		inputLocation.click();
	}	
	
	public PropertyListPage clickOnSearchButton()  {
		WebDriverWait waitForSearchButtonToAppear = new WebDriverWait(driver, 120);
		waitForSearchButtonToAppear.until(ExpectedConditions.elementToBeClickable(searchButton));
		TestUtility.highLightElement(driver, searchButton);
		searchButton.click();
		return new PropertyListPage();
	}
	
	public PropertyListPage SearchLocation() {
		inputLocation();
		clickOnSearchButton();
		return new PropertyListPage();
	}
}
