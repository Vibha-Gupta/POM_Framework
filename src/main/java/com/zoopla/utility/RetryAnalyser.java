package com.zoopla.utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyser implements IRetryAnalyzer {
	
	int counter = 0;
	int retryLimit = 0; // CHange the limit accordingly
	
	public boolean retry(ITestResult result) {
		if (counter < retryLimit) {
			counter++;
			return true;
		}else
		{
			return false;
		}
		
		
	}

}
