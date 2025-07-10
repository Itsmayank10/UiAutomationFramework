package com.ui.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LambdaTestUtility {
    private static final String HUB_URL = "https://hub.lambdatest.com/wd/hub";
    private static ThreadLocal<WebDriver> driverLocal = new ThreadLocal<>();
    private static ThreadLocal<DesiredCapabilities> capabilitiesLocal = new ThreadLocal<>();

    public static WebDriver initializeLambdaTestSession(String browser, String testName) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("browserVersion", "127");

        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("user", "itsmayank10");
        ltOptions.put("accessKey", "LT_OP2iBK8L7pHVsYPbR4FnGXHeC0XKWOOKgZsWQsk2QmaLNna");
        ltOptions.put("build", "Selenium 4");
        ltOptions.put("name", testName);
        ltOptions.put("platformName", "Windows 10");
        ltOptions.put("seCdp", true);
        ltOptions.put("selenium_version", "4.23.0");

        capabilities.setCapability("LT:Options", ltOptions);

        WebDriver driver = null;

        try {
            driver = new RemoteWebDriver(new URL(HUB_URL), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        driverLocal.set(driver);
        return driverLocal.get();
    }

    public void quitSession() {
        if (driverLocal.get() != null) {
            driverLocal.get().quit();
            driverLocal.remove();
        }
    }
}
