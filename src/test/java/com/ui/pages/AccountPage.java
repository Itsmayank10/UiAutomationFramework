package com.ui.pages;

import com.ui.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public final class AccountPage extends BrowserUtility {
    private static final By USER_NAME_LOCATOR = By.xpath("//a[@title=\"View my customer account\"]/span");
    private static final By SEARCH_TEXT_BOX_LOCATOR = By.id("search_query_top");
    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public String getUserName() {
        return getVisibleText(USER_NAME_LOCATOR);
    }

    public SearchResultPage searchProduct(String productName) {
        enterText(SEARCH_TEXT_BOX_LOCATOR, productName);
        enterKeys(SEARCH_TEXT_BOX_LOCATOR, Keys.ENTER); // Simulate pressing Enter to search
        SearchResultPage searchResultPage = new SearchResultPage(getDriver());
        return searchResultPage; // Return an instance of SearchResultPage after searching
    }
}
