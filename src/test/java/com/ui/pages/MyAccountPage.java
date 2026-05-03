package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;

public final class MyAccountPage extends BrowserUtility {
	private static final By USER_NAME_LOCATOR = By.xpath("//div[@class='header_user_info']/descendant::span");
	public MyAccountPage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getUserName() {
		return getVisibleText(USER_NAME_LOCATOR);
	}
	

}
