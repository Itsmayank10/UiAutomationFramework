package com.ui.tests;

import com.ui.pages.HomePage;
import com.ui.utility.BrowserUtility;
import org.testng.annotations.BeforeMethod;

import static com.ui.constants.Browser.CHROME;

public class BaseTest {
    protected HomePage homePage;

    @BeforeMethod(description = "Setup method to initialize HomePage with Chrome browser")
    public void setUp() {
        homePage = new HomePage(CHROME,true);
    }

    public BrowserUtility getInstanceOfHomePage() {
        return homePage;
    }
}
