package com.ui.listener;

import com.ui.constants.ENV;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import static com.ui.utility.JsonUtils.readJson;
import static com.ui.utility.PropertiesUtils.readProperty;

public class MyRetryAnalyzer implements IRetryAnalyzer {
    private static final int MAX_RETRY = readJson(ENV.QA).getMAX_NUMBER_OF_ATTEMPTS();
    private int currentAttempt = 1;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (currentAttempt <= MAX_RETRY) {
            System.out.println("Retrying test " + iTestResult.getName() + " for the " + currentAttempt + " time.");
            currentAttempt++;
            return true; // Retry the test
        } else {
            System.out.println("Test " + iTestResult.getName() + " failed after " + MAX_RETRY + " attempts.");
            return false;
        }
    }
}
