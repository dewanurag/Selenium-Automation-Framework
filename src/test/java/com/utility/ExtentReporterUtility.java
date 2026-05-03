package com.utility;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterUtility {
	
	private static ExtentReports extentReports;
	//we have class variable for ExtentReports, as its enough for dumping info for all threads
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
	public static void setupSparkReporter(String reportName) {
		try {
			ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+File.separator+reportName);
			extentReports = new ExtentReports();
			extentReports.attachReporter(extentSparkReporter);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void createExtentTest(String testName) {
		ExtentTest test = extentReports.createTest(testName);
		extentTest.set(test);
	}
	
	public static ExtentTest getTest() {
		return extentTest.get();
	}
	
	public static void flushReport() {
		extentReports.flush();
	}

}
