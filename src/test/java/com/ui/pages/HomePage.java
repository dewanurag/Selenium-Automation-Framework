package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browsers;
import com.constants.Environments;
import com.utility.BrowserUtility;
import com.utility.PropertiesUtil;

//Inherting abstract class
//Cant make object of Abstract class directly
public final class HomePage extends BrowserUtility{
	private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(),'Sign')]");
	
//	public HomePage(WebDriver driver) {
//		super(driver); //to call the parent class constructor from the child class constructor
//		//since BrowserUtility class had parameterised constructor we need to call it in here
//		goToWebsite("https://automationpractice.techwithjatin.com/");
//	}
//	
//	public HomePage(String string) {
//		// TODO Auto-generated constructor stub
//		super(string);
//		goToWebsite("https://automationpractice.techwithjatin.com/");
//	}

	public HomePage(Browsers browser, boolean isHeadless) {
		// TODO Auto-generated constructor stub
		super(browser,isHeadless);
		goToWebsite(PropertiesUtil.getProperty("URL", Environments.QA));
	}

	public LoginPage goToLoginPage() { //Page Functions---> 
		//in POM we cant use void return type
		clickOn(SIGN_IN_LINK_LOCATOR);
		LoginPage loginPage = new LoginPage();
		return loginPage;
	}
}
