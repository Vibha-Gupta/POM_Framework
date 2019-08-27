package com.zoopla.utility;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.zoopla.base.BaseClass;

/**
 * @author vibha
 *
 */
public class TestUtility extends BaseClass {
	static final Logger log = Logger.getLogger(TestUtility.class);
	public static long page_Load_Timeout = 120;
	public static long implicitly_Wait = 60;

	public static String TEST_DATA_SHEET_PATH = (BasePath + "/src/main/java/com/zoopla/testdata/ExcelSheet_TestData.xlsx");
	public static Robot robot;

	public static StringSelection stringSelection;

	/*
	 * Switch to iFrame function
	 */
	public static void switchToFrame(String iframePath) {

		try {
		driver.switchTo().frame(iframePath);
		}catch(Exception e) {
			log.debug("iFrame is not present :" + e.getMessage());
		}
	}

	/*
	 * Mouse hover event
	 */
	public static void mouseHoverEvent(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	/*
	 * Mouse hover and click on element
	 */
	public static void mouseHoverEventClick(WebDriver driver, WebElement element) {
		try {
			Actions action = new Actions(driver);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			action.moveToElement(element).build().perform();
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
			//element.click();
		} catch (Exception e) {
			log.debug("*************Inside Exception catch block for 'mouseHoverEventClick' method.");
			log.debug(e.getMessage());
		}
	}

	/*
	 * Read data from the excel file
	 */
	public static Object[][] getTestData(String sheetName) {

		FileInputStream file = null;
		Workbook book = null;
		Sheet sheet;

		try {
			file = new FileInputStream(TEST_DATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			log.debug("*************Inside FileNotFoundException catch block for 'getTestData' method.");
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (EncryptedDocumentException e) {
			log.debug("*************Inside EncryptedDocumentException catch block for 'getTestData' method.");
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			log.debug("*************Inside InvalidFormatException catch block for 'getTestData' method.");
			e.printStackTrace();
		} catch (IOException e) {
			log.debug("*************Inside IOException catch block for 'getTestData' method.");
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		return data;
	}

	/*
	 * Highlight the WebPage elements
	 */
	public static void highLightElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			log.debug("*************Inside Exception catch block for 'highLightElement' method.");
			log.debug(e.getMessage());
		}
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px white;');", element);
	}

	/*
	 * Page scroll down
	 */
	public static void pageScrollDown() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

	}

	/*
	 * Page Scroll Up
	 */
	public static void pageScrollUp() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-300)");

	}

	/*
	 * Scroll the page till the element
	 */
	public static void scrollToElement(WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);

	}

	/*
	 * get the current date
	 */
	public static String currentDate() {

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		String formateDate = formatter.format(date);
		return formateDate;
	}

	/*
	 * Multilist value
	 */
	public static String multiListWebElement(String xpath, String optionName) {

		List<WebElement> listofElement = driver.findElements(By.xpath(xpath));
		log.debug("Size of list through 'multiListWebElement' method: "+listofElement.size());

		for (int i = 0; i < listofElement.size(); i++) {
			if (listofElement.get(i).getText().contains(optionName)) {
				highLightElement(driver, listofElement.get(i));
				listofElement.get(i).click();
			}
		}
		return optionName;
	}

	/*
	 * Random Number Creation
	 */
	public static String generateRandomChars(String candidateChars, int length) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
		}
		return sb.toString();
	}

	/*
	 * Get the values from drop down list option
	 */

	public static void getTheDropDownValue(String xpath, String dropdownValue) {

		WebElement selectDropDown = driver.findElement(By.xpath(xpath));
		Select value = new Select(selectDropDown);
		List<WebElement> listAll = value.getOptions();

		log.debug("Size of Dropdown by 'gettheDropDownValue' method: "+ listAll.size());

		for (WebElement optionName : listAll) {
			log.debug(optionName.getText());
			if (optionName.getText().contains(dropdownValue)) {
				optionName.click();
			}
		}
	}

	/*
	 * Upload file from the local machine
	 */

	public static void uploadFileLocalMachine() throws InterruptedException {

		try {
		StringSelection stringSelection = new StringSelection(BasePath + "/Error.png");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

		robot.setAutoDelay(3000);

		robot.keyPress(KeyEvent.VK_META);
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_G);
		robot.keyRelease(KeyEvent.VK_META);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		robot.keyRelease(KeyEvent.VK_G);
		robot.keyPress(KeyEvent.VK_META);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_META);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		// robot.delay(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
	}catch(Exception e) {
		log.debug("Inside Execption block, Not able to upload File locally :" + e.getMessage());
	}
	}

	/*
	 * Upload multiple file
	 */

	public static void uploadMultipleFile(String WebElementPath, String filePath) {

		List<WebElement> uploadMultiple = driver.findElements(By.xpath(WebElementPath));

		try {
		for (int i = 0; i < uploadMultiple.size(); i++) {
			log.debug(uploadMultiple.get(i).getText());
			Thread.sleep(3000);
			uploadMultiple.get(i).click();
			uploadMultiple.get(i).sendKeys(filePath);
		}
		}catch(Exception e) {
			log.debug("Inside Execption block, multiple File not able to upload :" + e.getMessage());
		}
	}

	/*
	 * Get 1st value clickable from input field
	 */
	public static void clickOnInputDropDownvalue(String addSymptomCodeTextFieldDropDownList) {

		List<WebElement> listofDropDown = driver.findElements(By.xpath(addSymptomCodeTextFieldDropDownList));
		try {
		for (int i = 0; i < listofDropDown.size(); i++) {
			// JavascriptExecutor executor = (JavascriptExecutor)driver;
			// executor.executeScript("arguments[0].listofDropDown.get(0).click();",
			// listofDropDown);
			listofDropDown.get(0).click();
		}
		}catch(Exception e) {
			
			log.debug("Drop down value is not present :" + e.getMessage());
		}

	}

	/*
	 * Validate text
	 */
	public static void verifyText(WebElement locator, String value) {
		try {

			highLightElement(driver, locator);
			String actual = locator.getText();

			if (actual.equals(value)) {
				log.debug("Test Passed");
			} else {
				log.debug("text not  matched");
				log.debug(actual + "  not equals  " + value);
			}
		} catch (NoSuchElementException e) {
			log.debug(e.getMessage());
			log.debug("No Element Found" + locator);
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
	}

	/*
	 * Click on the WebElement
	 */
	public static void click(WebElement locator) {
		try {
			highLightElement(driver, locator);
			locator.click();
		} catch (NoSuchElementException e) {
			log.debug(locator + "not found");
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
	}

	/*
	 * Validate currentUrl
	 */
	public static void verifyCurrentUrl(WebElement locator, String value) {
		try {
			highLightElement(driver, locator);
			locator.click();
			String url = driver.getCurrentUrl();
			Assert.assertEquals(url, value);
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
	}

	/*
	 * Click on element and verify the url of next page
	 */
	public static void clickAndVerifyUrl(WebElement locator, String value) {
		try {
			highLightElement(driver, locator);
			locator.click();
			driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
			String valueofUrl = driver.getCurrentUrl();

			if (valueofUrl.contains(value)) {
			} else {
				log.debug("text doesn't match");
				log.debug(value + "  not equals  " + valueofUrl);
			}
		}

		catch (Exception e) {
			log.debug(e.getMessage());
		}
	}

	/*
	 * Verify page Title
	 */
	public static void verifyPageTitle(String expectedTitle) {
		SoftAssert softAssert = new SoftAssert();
		try {
			String pagetitle = driver.getTitle();
			log.debug("*****Actual Page title displayed is: " + pagetitle);
			softAssert.assertEquals(pagetitle, expectedTitle);
		}
		catch (Exception e) {
			log.debug("Page Title doesn't match" + e.getMessage());
		}
	}

	/*
	 * Verify Image is present
	 */
	public static void verifyImagePresent(WebElement element) {
		try {
			boolean img = element.isDisplayed();
			if (img == true) {
				highLightElement(driver, element);
			} else {
				log.debug(" Image is not present");
			}
		} catch (NoSuchElementException e) {
			log.debug(e.getMessage());
		}

		catch (Exception e) {
			log.debug(e.getMessage());
		}
	}

	/*
	 * Download the file
	 */
	public static void downloadFile() {

		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_TAB);
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_TAB);
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_TAB);
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(2000);

		}

		catch (Exception e) {
			log.debug(e.getMessage());
		}
	}

	/*
	 * Send value in the text field
	 */
	public static void senKeysValueInTextField(WebElement elementPath, String value) {

		try {
			highLightElement(driver, elementPath);
			elementPath.sendKeys(value);
		} catch (Exception e) {
			log.debug(elementPath + "not found");
		}
	}

	/*
	 * Assertion for text comparison
	 */
	public static void assertionForText(String Expected, WebElement locator) {
		Assert.assertEquals(Expected, locator.getText());
	}
	
	
	/*
	 * Force Reload the browser
	 */
	public static void forceReloadBrowser() {
		try {
		driver.navigate().refresh();
		//driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		}catch(Exception e) {
			log.debug("Please check you internet connection :" + e.getMessage());
		}
	}
	
	/*
	 * Takes screenshot 
	 */
	
	public static void takeScreenshot() throws IOException{
		
		TakesScreenshot  screen = (TakesScreenshot) driver;     // Convert object i.e TypeCast driver 
		
		File srcPath = screen.getScreenshotAs(OutputType.FILE);  // capture screenshot and keep it as file 
		
		String destinationPath = System.getProperty("user.dir") +"/screenshot/"+System.currentTimeMillis()+" .png";     //save in project directory and give the name to the image
		FileUtils.copyFile(srcPath, new File(destinationPath));    // copy target file on given path 		
	}
	
	
}
