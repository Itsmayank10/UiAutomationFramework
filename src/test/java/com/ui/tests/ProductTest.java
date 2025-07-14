package com.ui.tests;

import com.ui.pages.AccountPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductTest extends BaseTest {
    private AccountPage accountPage;
    @BeforeMethod(description = "Setup method to log in with user to initialize ProductTest")
    public void setup(){
        accountPage = homePage.goToLoginPage().doLoginWith("4ubhkh8gb1@daouse.com","Test@123");
    }

    @Test(description = "Test to verify product search and details are correctly displayed")
    public void productSearchAndDetailsTest() {
        String title = accountPage.searchProduct("Summer Dress").getProductListingTitle();
        System.out.println("Product Listing Title: " + title);
        assertTrue(title.contains("SUMMER DRESS"), "Product listing title does not contain 'Summer Dress'");
    }
}
