package com.ui.utility;

import com.ui.constants.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserUtility {
    private WebDriver driver;

    public BrowserUtility(WebDriver driver) {
        this.driver = driver;
    }

    public BrowserUtility(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        }
        else {
            throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
    }

    public BrowserUtility(Browser browserName) {
        if (browserName == Browser.CHROME) {
            driver = new ChromeDriver();
        }
        else if (browserName == Browser.SAFARI) {
            driver = new SafariDriver();
        }
        else if (browserName == Browser.FIREFOX) {
            // Add Firefox driver initialization here
            throw new UnsupportedOperationException("Firefox is not supported yet.");
        }
        else if (browserName == Browser.EDGE) {
            // Add Edge driver initialization here
            throw new UnsupportedOperationException("Edge is not supported yet.");
        }
        else if (browserName == Browser.OPERA) {
            // Add Opera driver initialization here
            throw new UnsupportedOperationException("Opera is not supported yet.");
        }
        else {
            throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void goToWebsite(String url) {
        driver.get(url);
    }

    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    public void clickOnElement(By locator) {
        try {
            driver.findElement(locator).click();
        } catch (Exception e) {
            System.out.println("Element not found: " + locator);
            e.printStackTrace();
        }
    }

    public void enterText(By locator, String text) {
        try {
            driver.findElement(locator).sendKeys(text);
        } catch (Exception e) {
            System.out.println("Unable to enter text in element: " + locator);
            e.printStackTrace();
        }
    }

    public String getVisibleText(By locator) {
        try {
            return driver.findElement(locator).getText();
        } catch (Exception e) {
            System.out.println("Unable to get text from element: " + locator);
            e.printStackTrace();
            return null;
        }
    }
}
