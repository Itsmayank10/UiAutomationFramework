package com.ui.tests;

import com.ui.constants.Browser;
import com.ui.pages.HomePage;
import com.ui.utility.BrowserUtility;
import com.ui.utility.LambdaTestUtility;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static com.ui.constants.Browser.CHROME;

public class BaseTest {
    protected HomePage homePage;
    private boolean isLambdaTest;

    @Parameters({"browser","isLambdaTest", "isHeadless"})
    @BeforeMethod(description = "Setup method to initialize HomePage with Chrome browser")
    public void setUp(
            @Optional("chrome") String browser,
            @Optional("false") boolean isLambdaTest,
            @Optional("true") boolean isHeadless, ITestResult result) {
        WebDriver lambdaDriver = null;
        this.isLambdaTest = isLambdaTest;
        if (isLambdaTest) {
            // Initialize the browser using LambdaTestUtility
            lambdaDriver = LambdaTestUtility.initializeLambdaTestSession(browser, result.getMethod().getMethodName());
            homePage = new HomePage(lambdaDriver);
        } else {
            // Initialize the browser using BrowserUtility
            homePage = new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadless);
        }
    }

    public BrowserUtility getInstanceOfHomePage() {
        return homePage;
    }

    @AfterMethod(description = "Teardown method to quit the browser session")
    public void tearDown() {
        if (isLambdaTest) {
            LambdaTestUtility lambdaTestUtility = new LambdaTestUtility();
            lambdaTestUtility.quitSession();
        } else {
            if (homePage != null) {
                homePage.getDriver().quit();
            }
        }
    }
}
