package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.constants.Browsers;

public abstract class BrowserUtility {

    // ThreadLocal — each thread gets its own driver instance
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();
    
    Logger logger = LoggerUtility.getLogger(this.getClass());

    public WebDriver getDriver() {
        return driver.get();  // gets THIS thread's driver
    }
    
    public BrowserUtility() {
        // used by child pages after first page navigation
    }
    
    public BrowserUtility(Browsers browser, boolean isHeadLess) {
        logger.info("Launching browser: " + browser + " for thread: " 
                    + Thread.currentThread().getName());
        if (browser == Browsers.CHROME ) {
        	if(isHeadLess==true) {
        	ChromeOptions options = new ChromeOptions();
        	options.addArguments("--headless=old");
        	options.addArguments("--window-size=1920,1080");
            driver.set(new ChromeDriver(options));
            logger.info("ChromeDriver initialized for thread: " 
                        + Thread.currentThread().getName());
        	}
        	else {
        		driver.set(new ChromeDriver());
                logger.info("ChromeDriver initialized for thread: " 
                            + Thread.currentThread().getName());
        	}
        	} 
        else if (browser == Browsers.EDGE) {
        	if(isHeadLess) {
        		EdgeOptions options = new EdgeOptions();
        		options.addArguments("--headless=old");
        		options.addArguments("disable-gpu");
        		driver.set(new EdgeDriver(options));
        	}
        	else {
            driver.set(new EdgeDriver());
        	}
        	logger.info("EdgeDriver initialized for thread: " 
                    + Thread.currentThread().getName());
        	} 
        else if (browser == Browsers.FIREFOX) {
        	if(isHeadLess) {
        		FirefoxOptions options = new FirefoxOptions();
        		options.addArguments("--headless=old");
        		options.addArguments("disable-gpu");
        		driver.set(new FirefoxDriver(options));
        	}
        	else {
            driver.set(new FirefoxDriver());
        	}
        	logger.info("FireFox driver initialized for thread: " 
                    + Thread.currentThread().getName());
        	} 
        else {
            logger.error("Unsupported browser: " + browser);
        }
        wait.set(new WebDriverWait(driver.get(), Duration.ofSeconds(30)));
        logger.info("WebDriverWait initialized for thread: " 
                    + Thread.currentThread().getName());
    }
    
    public BrowserUtility(Browsers browser) {
        logger.info("Launching browser: " + browser + " for thread: " 
                    + Thread.currentThread().getName());
        if (browser == Browsers.CHROME) {
            driver.set(new ChromeDriver());
            logger.info("ChromeDriver initialized for thread: " 
                        + Thread.currentThread().getName());
        } else if (browser == Browsers.EDGE) {
            driver.set(new EdgeDriver());
            logger.info("EdgeDriver initialized for thread: " 
                        + Thread.currentThread().getName());
        } else {
            logger.error("Unsupported browser: " + browser);
        }
        wait.set(new WebDriverWait(driver.get(), Duration.ofSeconds(30)));
        logger.info("WebDriverWait initialized for thread: " 
                    + Thread.currentThread().getName());
    }
    
    

    public void goToWebsite(String url) {
        logger.info("Maximizing window | Thread: " + Thread.currentThread().getName());
        driver.get().manage().window().maximize();
        logger.info("Navigating to: " + url);
        driver.get().get(url);
    }

    public void clickOn(By locator) {
        logger.info("Clicking on: " + locator 
                    + " | Thread: " + Thread.currentThread().getName());
        WebElement element = wait.get()
                .until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public void enterText(By locator, String text) {
        logger.info("Entering text into: " + locator 
                    + " | Thread: " + Thread.currentThread().getName());
        WebElement element = wait.get()
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.sendKeys(text);
    }

    public String getVisibleText(By locator) {
        logger.info("Getting text from: " + locator 
                    + " | Thread: " + Thread.currentThread().getName());
        WebElement element = wait.get()
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        String text = element.getText();
        logger.info("Retrieved text: '" + text + "'");
        return text;
    }

    // CRITICAL — call this in @AfterMethod to prevent memory leaks
    public void quitDriver() {
        if (driver.get() != null) {
            logger.info("Quitting driver for thread: " + Thread.currentThread().getName());
            driver.get().quit();
            driver.remove();  // remove from ThreadLocal to prevent memory leak
            wait.remove();
        }
    }
    
    public String takeScreenshot(String name) {
    	TakesScreenshot takesScreenshot = (TakesScreenshot)getDriver();
    	File screenShotData = takesScreenshot.getScreenshotAs(OutputType.FILE);
    	Date date = new Date();
    	SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy-HH-mm-ss");
    	String timeStamp = format.format(date);
    	//String path = System.getProperty("user.dir")+File.separator+"screenshots"+name;
    	String path = System.getProperty("user.dir")+
    			File.separator+"test-output"+File.separator+"screenshots"+File.separator+name+"-"+timeStamp+".png";
    	File screenShotFileDest = new File(path);
    	
    	try {
			FileUtils.copyFile(screenShotData, screenShotFileDest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	logger.info(name+"has failed, its screenshot is at "+ path);
    	return path;
    }
}