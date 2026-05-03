package com.ui.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import static org.testng.Assert.*;

import java.lang.reflect.Method;

import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static com.constants.Browsers.*;
import com.constants.Browsers;
import com.ui.dataproviders.LoginDataProvider;
import com.ui.listeners.AnnotationTransformer;
import com.ui.listeners.RetryAnalyser;
import com.ui.listeners.TestListener;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.ui.pages.MyAccountPage;
import com.ui.pojo.User;
import com.utility.JSONReaderUtil;
import com.utility.LoggerUtility;

@Listeners({TestListener.class, AnnotationTransformer.class })
public class LoginTest3 extends BaseTest{
	
	@Test(description = "test correct username displayed", groups = {"Regression"})
	public void validateUserName4() {
		assertEquals(homepage.goToLoginPage()
				.doLoginWith("weloj40165@nyspring.com", "gugugaga")
				.getUserName(), "Anurag Dewangan");
	}
	
	@Test(dataProvider = "credentials", 
	          dataProviderClass = LoginDataProvider.class)
	    public void testLogin(User user) {
	        System.out.println("Testing with: " + user.getEmail() + " | " + user.getPassword());
	        assertEquals(homepage.goToLoginPage()
					.doLoginWith(user.getEmail(), user.getPassword())
					.getUserName(), "Anurag Dewangan");
	    }
	
	@Test(dataProvider = "credsFromCSV", 
	          dataProviderClass = LoginDataProvider.class)
	    public void testLoginFromCSV(User user) {
	        System.out.println("Testing with: " + user.getEmail() + " | " + user.getPassword());
	        assertEquals(homepage.goToLoginPage()
					.doLoginWith(user.getEmail(), user.getPassword())
					.getUserName(), "Anurag Dewangan");
	    }
	@Test(dataProvider = "credentialsFromXLSX", 
	          dataProviderClass = LoginDataProvider.class, groups = {"Regression"})
	    public void testLoginFromXLSX(User user) {
	   
		System.out.println("Testing with: " + user.getEmail() + " | " + user.getPassword());
	        assertEquals(homepage.goToLoginPage()
					.doLoginWith(user.getEmail(), user.getPassword())
					.getUserName(), "Anurag Dewangan");
	    }
	
	
}
