package com.utility;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LambdaTestUtil {
	public static final String HUB_URL = "https://hub.lambdatest.com/wd/hub";
	private static ThreadLocal<DesiredCapabilities> capabilitiesLocal = new ThreadLocal<>();
	private static ThreadLocal<WebDriver> driverLocal = new ThreadLocal<>();
	
	public static WebDriver initializeLambdaTest(String browser, String testName) {
		DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browserVersion", "latest");
        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("user", "dewanurag23");
        ltOptions.put("accessKey","XLJrbq0KV3nj8pzLYxS4YpaBsneohjgt850vECRjysUimAMj5L");
        ltOptions.put("build", "Selenium 4");
        ltOptions.put("name", testName);
        ltOptions.put("platformName", "Windows 10");
        ltOptions.put("seCdp", true);
        ltOptions.put("selenium_version", "latest");
        capabilities.setCapability("LT:Options", ltOptions);
        capabilitiesLocal.set(capabilities);
        WebDriver driver = null;
        try {
			driver = new RemoteWebDriver(new URL(HUB_URL), capabilitiesLocal.get());
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        driverLocal.set(driver);
        return driverLocal.get();
        
	}
	
	public static void quitSession() {
		if(driverLocal.get()!=null) {
			driverLocal.get().quit();
		}
	}
}
