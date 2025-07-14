package com.ui.pages;

import com.ui.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultPage extends BrowserUtility {
    private static final By PRODUCT_LISTING_TITLE_LOCATOR = By.xpath("//h1[@class='page-heading  product-listing']");
    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public String getProductListingTitle() {
        return getVisibleText(PRODUCT_LISTING_TITLE_LOCATOR);
    }
}
