package com.ui.tests;

import static com.constants.Browsers.CHROME;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.constants.Browsers;
import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LambdaTestUtil;

public class BaseTest {
	protected HomePage homepage;
	private boolean isLambdaTest ;
	private boolean isHeadless;
	private Browsers browser;
	@Parameters({"browser","isLambdaTest","isHeadless"})
	//browser is the name of variable
	@BeforeMethod(description = "load homepage before each @Test block")
	public void setup(@Optional("chrome") String browser,@Optional("false") boolean isLambdatest,@Optional("true") boolean isHeadless, Method method) {
		this.isHeadless = isHeadless;
		this.isLambdaTest = isLambdatest;
		if(browser.equalsIgnoreCase("edge")) {
			this.browser = Browsers.EDGE;
		}
		else if(browser.equalsIgnoreCase("chrome")) {
			this.browser = Browsers.CHROME;
		}
		else {
			this.browser = Browsers.FIREFOX;
		}
		WebDriver lambdaDriver;
		if(isLambdaTest) {
		lambdaDriver = LambdaTestUtil.initializeLambdaTest(this.browser.toString(), method.getName());
		homepage = new HomePage(this.browser, isHeadless);
		}
		else {
		homepage = new HomePage(this.browser,isHeadless);
		}
	}
	
	
	@AfterMethod
	public void tearDownSession() {
		//homepage.getDriver().close();
		if(isLambdaTest)LambdaTestUtil.quitSession();
		else homepage.quitDriver();
	}
	
	public BrowserUtility getInstance() {
		return homepage;
	}
	
}
