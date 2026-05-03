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
	        String fullPath = System.getProperty("user.dir") 
	                        + File.separator + reportName;
	        System.out.println(">>> Setting up report at: " + fullPath);
	        
	        ExtentSparkReporter spark = new ExtentSparkReporter(fullPath);
	        extentReports = new ExtentReports();
	        extentReports.attachReporter(spark);
	        System.out.println(">>> Reporter setup complete");
	    } catch (Exception e) {
	        System.out.println(">>> Reporter setup FAILED: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	
	public static void createExtentTest(String testName) {
	    if (extentReports == null) {
	        System.out.println(">>> extentReports NULL in createExtentTest — initializing now");
	        setupSparkReporter("report.html"); // fallback init
	    }
	    ExtentTest test = extentReports.createTest(testName);
	    extentTest.set(test);
	}
	
	public static ExtentTest getTest() {
		return extentTest.get();
	}
	
	public static void flushReport() {
	    if (extentReports != null) {
	        System.out.println(">>> Flushing report");
	        extentReports.flush();
	        System.out.println(">>> Report flushed successfully");
	    } else {
	        System.out.println(">>> extentReports is NULL — onStart() never fired!");
	    }
	}

}
