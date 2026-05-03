package com.ui.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.constants.Browsers;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.ui.pages.MyAccountPage;

public class LoginTest2 {
	
	@Test(description = "test correct username displayed", groups = {"Regression"})
	public void validateUserName() {
		WebDriver driver = new EdgeDriver();
		HomePage homepage = new HomePage(Browsers.EDGE,true);
		LoginPage loginpage = homepage.goToLoginPage();
		MyAccountPage myAccountPage =  loginpage.doLoginWith("weloj40165@nyspring.com", "gugugaga");
		Assert.assertEquals(myAccountPage.getUserName(), "Anurag Dewangan");
		driver.close();
	}
	
	@Test(description = "test correct username displayed", groups = {"Regression"})
	public void validateUserName2() {
		HomePage homepage = new HomePage(Browsers.EDGE,false);
		LoginPage loginpage = homepage.goToLoginPage();
		MyAccountPage myAccountPage =  loginpage.doLoginWith("weloj40165@nyspring.com", "gugugaga");
		Assert.assertEquals(myAccountPage.getUserName(), "Anurag Dewangan");
		myAccountPage.getDriver().close();
	}
	
	@Test(description = "test correct username displayed", groups = {"Regression"})
	public void validateUserName3() {
		HomePage homepage = new HomePage(Browsers.CHROME,true);
		LoginPage loginpage = homepage.goToLoginPage();
		MyAccountPage myAccountPage =  loginpage.doLoginWith("weloj40165@nyspring.com", "gugugaga");
		Assert.assertEquals(myAccountPage.getUserName(), "Anurag Dewangan");
		myAccountPage.getDriver().close();
	}
	
	//homepage.goToLoginPage().doLoginWith("weloj40165@nyspring.com", "gugugaga").getUserName();
	@Test(description = "test correct username displayed", groups = {"Regression"})
	public void validateUserName4() {
		HomePage homepage = new HomePage(Browsers.CHROME,true);
		Assert.assertEquals(homepage.goToLoginPage().doLoginWith("weloj40165@nyspring.com", "gugugaga").getUserName(), "Anurag Dewangan");
		homepage.getDriver().close();
	}
	
}
