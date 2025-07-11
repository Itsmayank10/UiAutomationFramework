package com.ui.utility;

import com.ui.constants.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;

import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BrowserUtility {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    public WebDriver getDriver() {
        return driver.get();
    }

    public BrowserUtility(WebDriver driver) {
        this.driver.set(driver);
    }

    public BrowserUtility(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            driver.set(new ChromeDriver());
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
    }

    public BrowserUtility(Browser browserName) {
        if (browserName == Browser.CHROME) {
            driver.set(new ChromeDriver());
        } else if (browserName == Browser.SAFARI) {
            driver.set(new SafariDriver());
        } else if (browserName == Browser.FIREFOX) {
            // Add Firefox driver initialization here
            throw new UnsupportedOperationException("Firefox is not supported yet.");
        } else if (browserName == Browser.EDGE) {
            // Add Edge driver initialization here
            throw new UnsupportedOperationException("Edge is not supported yet.");
        } else if (browserName == Browser.OPERA) {
            // Add Opera driver initialization here
            throw new UnsupportedOperationException("Opera is not supported yet.");
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
    }

    public BrowserUtility(Browser browserName, boolean isHeadless) {
        if (browserName == Browser.CHROME) {
            ChromeOptions options = new ChromeOptions();
            if (isHeadless) {
                options.addArguments("--headless");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--disable-gpu");
                driver.set(new ChromeDriver(options));
            } else {
                driver.set(new ChromeDriver(options));
            }
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
    }

    public void goToWebsite(String url) {
        driver.get().get(url);
    }

    public void maximizeWindow() {
        driver.get().manage().window().maximize();
    }

    public void clickOnElement(By locator) {
        try {
            driver.get().findElement(locator).click();
        } catch (Exception e) {
            System.out.println("Element not found: " + locator);
            e.printStackTrace();
        }
    }

    public void enterText(By locator, String text) {
        try {
            driver.get().findElement(locator).sendKeys(text);
        } catch (Exception e) {
            System.out.println("Unable to enter text in element: " + locator);
            e.printStackTrace();
        }
    }

    public String getVisibleText(By locator) {
        try {
            return driver.get().findElement(locator).getText();
        } catch (Exception e) {
            System.out.println("Unable to get text from element: " + locator);
            e.printStackTrace();
            return null;
        }
    }

    public void closeBrowser() {
        if (driver != null) {
            driver.get().quit();
        }
    }

    public String takeScreenshot(String name) {
        TakesScreenshot screenshot = (TakesScreenshot) driver.get();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH-mm-ss_dd-MM-yyyy");
        String timestamp = formatter.format(date);
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        String path = "./screenshot/" + name + "_" + timestamp + ".png";
        File destFile = new File(path);
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            System.out.println("Failed to take screenshot: " + name + ". Error: " + e.getMessage());
            e.printStackTrace();
        }
        return path;
    }
}
