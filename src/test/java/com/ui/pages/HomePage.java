package com.ui.pages;

import com.ui.constants.Browser;
import com.ui.utility.BrowserUtility;
import org.openqa.selenium.By;

import static com.ui.constants.ENV.QA;
import static com.ui.utility.JsonUtils.readJson;

public final class HomePage extends BrowserUtility {
    private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[@title='Log in to your customer account']");

    public HomePage(Browser browserName, boolean isHeadless) {
        super(browserName,isHeadless); // To call the constructor of the parent class BrowserUtility from child class HomePage
        goToWebsite(readJson(QA).getUrl());
        maximizeWindow();
    }

    public LoginPage goToLoginPage() {
        clickOnElement(SIGN_IN_LINK_LOCATOR);
        LoginPage loginPage = new LoginPage(getDriver());
        return loginPage; // Return an instance of LoginPage
    }
}
