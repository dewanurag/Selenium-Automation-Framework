package com.ui.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.constants.Browsers;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.ui.pages.MyAccountPage;
import com.utility.BrowserUtility;

public class LoginTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriver driver = new EdgeDriver(); //loose coupling 
//		//driver is a reference variable for uniquely identifying object stored in heap memory,
//		//as new keyword associated with heap memory
//		//whenever we create WebDriver instance it would trigger a browser session
//		BrowserUtility bu = new BrowserUtility(driver);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//		bu.goToWebsite("https://automationpractice.techwithjatin.com/");
//		By signInLinkLocator = By.xpath("//a[contains(text(),'Sign')]");
//		bu.clickOn(signInLinkLocator);
//		By emailLocator = By.id("email");
//		By passwordLocator = By.id("passwd");
//		By submitLoginLocator = By.id("SubmitLogin");
//		bu.entertext(emailLocator, "weloj40165@nyspring.com");
//		bu.entertext(passwordLocator, "gugugaga");
//		bu.clickOn(submitLoginLocator);
		HomePage homepage = new HomePage(Browsers.EDGE,true);
		LoginPage loginpage = homepage.goToLoginPage();
		MyAccountPage myAccountPage =  loginpage.doLoginWith("weloj40165@nyspring.com", "gugugaga");
		//Assert.assertEquals(myAccountPage.getUserName(), "Anurag Dewangan");
		
	}

}
