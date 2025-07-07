package com.ui.tests;

import com.ui.dataProvider.LoginDataProvider;
import com.ui.listener.TestListener;
import com.ui.pages.HomePage;
import com.ui.pojo.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.ui.constants.Browser.CHROME;
import static org.testng.Assert.assertEquals;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {

    @Test(description = "Login test with valid credentials", dataProviderClass = LoginDataProvider.class, dataProvider = "loginDataProvider")
    public void loginTest(User user) {
        String userName = homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName();
        assertEquals(userName, "Mayank Sharma", "User name does not match after login.");
    }

    @Test(description = "Login test with valid credentials", dataProviderClass = LoginDataProvider.class, dataProvider = "loginDataProviderFromCsv")
    public void loginCSVTest(User user) {
        String userName = homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName();
        assertEquals(userName, "Mayank Sharma", "User name does not match after login.");
    }


}
