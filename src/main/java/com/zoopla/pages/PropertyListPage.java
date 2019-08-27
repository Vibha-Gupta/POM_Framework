package com.zoopla.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zoopla.base.BaseClass;
import com.zoopla.utility.TestUtility;

public class PropertyListPage extends BaseClass {
	
	
	@FindBy(xpath="//ul[@class='listing-results clearfix js-gtm-list']//li[@class='srp clearfix   ']")
	WebElement listOfProperties;
	
	@FindBy(xpath="//ul[@class='listing-results clearfix js-gtm-list']//li[@class='srp clearfix   ']"
			+ "[5]//div[2]//a[@class='listing-results-price text-price']")
	WebElement propertyPrice;
	
	public PropertyListPage() {
		PageFactory.initElements(driver, this);}
	
	HomePage HomePage;
	PropertyDetailsPage PropertyDetailsPage;
	
	public int getPropertyListCount() {
		List<WebElement> propertyList = driver.findElements(By.xpath
				("//ul[@class='listing-results clearfix js-gtm-list']//li[@class='srp clearfix   ']"));
		int propertyListCount = propertyList.size();
		return propertyListCount;
	}

	public String getSelectedPropertyPrice(int propertyPosition) {
			String selectedPropertyPrice = driver.findElement(By.xpath("//ul[@class='listing-results clearfix js-gtm-list']"
					+ "//li[@class='srp clearfix   ']"
					+ "["+propertyPosition+"]//div[2]//a[@class='listing-results-price text-price']")).getText();
			if (selectedPropertyPrice.indexOf(" ")>0) {
				selectedPropertyPrice = selectedPropertyPrice.substring(0, selectedPropertyPrice.indexOf(" "));
			}
		return selectedPropertyPrice;
	}
	
	public ArrayList<String> getAllPropertiesPrice() {
		ArrayList<String> propertyList = new ArrayList<String>();
		String selectedPropertyPrice="";
	    int i = getPropertyListCount();
	    int j=0;
		for (j=1; j<=i; j++) {
			selectedPropertyPrice = driver.findElement(By.xpath("//ul[@class='listing-results clearfix js-gtm-list']"
					+ "//li[@class='srp clearfix   ']"
					+ "["+j+"]//div[2]//a[@class='listing-results-price text-price']")).getText();
			if (selectedPropertyPrice.indexOf(" ")>0) {
				selectedPropertyPrice = selectedPropertyPrice.substring(0, selectedPropertyPrice.indexOf(" "));
			}
			propertyList.add(selectedPropertyPrice);
		}
		return propertyList;
	}
	
	public ArrayList<String> getPropertiesPriceInSortedOrder() {
		ArrayList<String> sortedPropertyList = new ArrayList<String>();
		String selectedPropertyPrice="";
	    int i = getPropertyListCount();
	    int j=0;
		for (j=1; j<=i; j++) {
			selectedPropertyPrice = driver.findElement(By.xpath("//ul[@class='listing-results clearfix js-gtm-list']"
					+ "//li[@class='srp clearfix   ']"
					+ "["+j+"]//div[2]//a[@class='listing-results-price text-price']")).getText();
			if (selectedPropertyPrice.indexOf(" ")>0) {
				selectedPropertyPrice = selectedPropertyPrice.substring(0, selectedPropertyPrice.indexOf(" "));
			}
			sortedPropertyList.add(selectedPropertyPrice);
			sortedPropertyList.sort(null);
		}
		return sortedPropertyList;
	}
	
	public PropertyDetailsPage clickOnSelectedProperty(int selectProperty) {
		WebElement selectPropertyLocator = driver.findElement(By.xpath("//ul[@class='listing-results clearfix js-gtm-list']"
				+ "//li[@class='srp clearfix   ']"
	+             "["+selectProperty+"]//div[@class='listing-results-left']//div//a[@class='photo-hover']"));
		TestUtility.highLightElement(driver, selectPropertyLocator);
		selectPropertyLocator.click();
		 return PropertyDetailsPage; 
	}
}
