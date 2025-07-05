package com.ui.pages;

import com.ui.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class AccountPage extends BrowserUtility {
    private static final By USER_NAME_LOCATOR = By.xpath("//a[@title=\"View my customer account\"]/span");
    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public String getUserName() {
        return getVisibleText(USER_NAME_LOCATOR);
    }
}
