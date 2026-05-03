package com.ui.listeners;

import java.io.File;
import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.tests.BaseTest;
import com.utility.BrowserUtility;
import com.utility.ExtentReporterUtility;
import com.utility.LoggerUtility;

public class TestListener implements ITestListener {
	Logger logger = LoggerUtility.getLogger(this.getClass());
	@Override
	public void onTestStart(ITestResult result) {
		logger.info(result.getMethod().getMethodName());
		logger.info(result.getMethod().getDescription());
		logger.info(Arrays.toString(result.getMethod().getGroups()));
		ExtentReporterUtility.createExtentTest(result.getMethod().getMethodName());
	}

	@Override
	public void onStart(ITestContext context) {
		logger.info("Test suite started");
		ExtentReporterUtility.setupSparkReporter("report.html");
	}

	@Override
	public void onFinish(ITestContext context) {
		logger.info("Test suite completed");
		ExtentReporterUtility.flushReport();
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentReporterUtility.getTest().log(Status.PASS, result.getMethod().getMethodName()+" Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		logger.info(result.getMethod().getMethodName()+" Failed");
		logger.error(result.getThrowable().getMessage());
		ExtentReporterUtility.getTest().log(Status.FAIL, result.getMethod().getMethodName()+" Failed");
		ExtentReporterUtility.getTest().log(Status.FAIL,result.getThrowable().getMessage());
		Object testClass = result.getInstance();
		BrowserUtility browserUtil = ((BaseTest) testClass).getInstance();
		String screenshotPath = browserUtil.takeScreenshot(result.getMethod().getMethodName());
		ExtentReporterUtility.getTest().addScreenCaptureFromPath(screenshotPath);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger.warn(result.getMethod().getMethodName()+" Skipped");
		ExtentReporterUtility.getTest().log(Status.SKIP, result.getMethod().getMethodName()+" Skipped");
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

}
