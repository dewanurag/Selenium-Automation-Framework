package com.ui.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyser implements IRetryAnalyzer{
	private static final int MAX_RETRIES = 1;
	int retries =0;
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		
		if(result.getStatus()==ITestResult.FAILURE) {
			if(retries<MAX_RETRIES) {
				retries++;
				return true;
			}
		}
		return false;
	}

}
