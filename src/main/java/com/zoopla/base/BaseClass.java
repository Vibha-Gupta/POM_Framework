package com.zoopla.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.zoopla.utility.TestUtility;
import com.zoopla.utility.WebEventListener;

	public class BaseClass{
		public static WebDriver driver; // initialization WebDriver object
		public static Properties prop; // initialization the Properties object
		public static EventFiringWebDriver fireEvent;
		public static WebEventListener eventListener;
		public static String BasePath = System.getProperty("user.dir"); // VALUE IS "Users/vibha/Documents/Workspace/servifyWorkshop"
		static final Logger log = Logger.getLogger(BaseClass.class);
		
		public static ExtentReports extent;
		public static ExtentTest logger;
		
		
		public BaseClass() {
			prop = new Properties(); // initialization object
			try {
				// FileInputStream is meant for reading data from properties file
				FileInputStream ip = new FileInputStream(BasePath + "/src/main/java/com/zoopla/config/config.properties");
				
				extent = new ExtentReports (System.getProperty("user.dir") +"/HtmlReport/ServifyWorkShopApplication.html", true);
				extent.loadConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
				
				//Path of Log4j Properties file
				PropertyConfigurator.configure(BasePath+"/log4j.properties");
				try {
					//Load properties file
					prop.load(ip);
					//log.debug("Config properties file loaded.");
				} catch (IOException e) 
				{ // catch the error
					log.error("********Inside IOException Catch block on BaseClass Constructor***********");
					log.debug(e.getStackTrace());
				}
			} catch (FileNotFoundException e) 
			{
				log.error("********Inside FileNotFoundException Catch block on BaseClass Constructor***********");
				log.debug(e.getMessage()); // catch the exception error message
			}
		}
		
		public static void browserInitialization() 
		{
			log.debug("Inside 'browserInitialization' method.");
			String browserName = prop.getProperty("BROWSER");     // get the browser property from config file 
			if (browserName.equalsIgnoreCase("chrome")) 
			{
				System.setProperty("webdriver.chrome.driver", BasePath + "/chromedriver");
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("firefox")) 
			{
				System.setProperty("webdriver.gecko.driver", BasePath + "/geckodriver");
				driver = new FirefoxDriver();
			}
			/*
			//Initialized event listener
			fireEvent = new EventFiringWebDriver(driver);
			eventListener = new WebEventListener();
			fireEvent.register(eventListener);
			driver = fireEvent;
*/
			driver.manage().deleteAllCookies(); // Delete all the website cookies
			//driver.manage().timeouts().pageLoadTimeout(TestUtility.page_Load_Timeout,TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(TestUtility.implicitly_Wait, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(prop.getProperty("URL"));
		}
		
		@AfterSuite(alwaysRun = true)
		public void terminateDriver() {
			log.debug("Executing 'terminateDriver' method.");
			driver.quit();
		}
	}