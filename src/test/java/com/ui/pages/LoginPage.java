package com.ui.pages;

import com.ui.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class LoginPage extends BrowserUtility {
    private static final By EMAIL_TEXT_BOX_LOCATOR = By.id("email");
    private static final By PASSWORD_TEXT_BOX_LOCATOR = By.id("passwd");
    private static final By SIGN_IN_BUTTON_LOCATOR = By.id("SubmitLogin");

    private static final By ERROR_MESSAGE_LOCATOR = By.xpath("//div[@class='alert alert-danger']//li"); //div[contains(@class,"alert")]/ol/li

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public AccountPage doLoginWith(String email, String password) {
        enterText(EMAIL_TEXT_BOX_LOCATOR, email);
        enterText(PASSWORD_TEXT_BOX_LOCATOR, password);
        clickOnElement(SIGN_IN_BUTTON_LOCATOR);
        AccountPage accountPage = new AccountPage(getDriver());
        return accountPage; // Return an instance of AccountPage after login
    }

    public LoginPage doLoginWithInvalidCredentials(String email, String password) {
        enterText(EMAIL_TEXT_BOX_LOCATOR, email);
        enterText(PASSWORD_TEXT_BOX_LOCATOR, password);
        clickOnElement(SIGN_IN_BUTTON_LOCATOR);
        LoginPage loginPage = new LoginPage(getDriver());
        return loginPage; // Return an instance of LoginPage after attempting login with invalid credentials
    }

    public String getErrorMessage() {
        return getVisibleText(ERROR_MESSAGE_LOCATOR);
    }


}
