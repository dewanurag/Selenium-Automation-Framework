package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;

public final class LoginPage extends BrowserUtility{
	//always use final with static, but not all static with final
	private static final By EMAIL_LOCATOR = By.id("email");
	private static final By PASSWORD_LOCATOR = By.id("passwd");
	private static final By SUBMIT_LOGIN_LOCATOR = By.id("SubmitLogin");

	public LoginPage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MyAccountPage doLoginWith(String email, String password) {
		enterText(EMAIL_LOCATOR, email);
		enterText(PASSWORD_LOCATOR, password);
		clickOn(SUBMIT_LOGIN_LOCATOR);
		MyAccountPage myAccountPage = new MyAccountPage();
		return myAccountPage;
	}

}
