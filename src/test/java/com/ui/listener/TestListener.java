package com.ui.listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.tests.BaseTest;
import com.ui.utility.BrowserUtility;
import org.testng.*;

import static com.ui.utility.ExtentReportUtility.*;

public class TestListener implements ITestListener {
    ExtentSparkReporter extentSparkReporter; // Reporter to generate HTML reports
    ExtentReports extentReports; // Main report object to manage the report
    ExtentTest extentTest; // Represents a single test in the report

    public void onStart(ITestContext context) {
        setupSparkReport("TestReport"); // Initialize the report with a name
    }

    public void onTestStart(ITestResult result) {
        // Create a new test in the report for each test method that starts
        createExtentTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        // Log the success of the test in the report
        getExtentTest().log(Status.PASS, "Test passed: " + result.getMethod().getMethodName());
    }

    public void onTestFailure(ITestResult result) {
        // Log the failure of the test in the report
        getExtentTest().log(Status.FAIL, "Test failed: " + result.getMethod().getMethodName());
        // Optionally, you can log the exception stack trace
        if (result.getThrowable() != null) {
            getExtentTest().log(Status.FAIL, result.getThrowable());
        }
        // Screenshot when a test fails (if applicable)
        Object testClass = result.getInstance();
        BrowserUtility browserUtility = ((BaseTest) testClass).getInstanceOfHomePage();
        String screenShotPath = browserUtility.takeScreenshot(
            result.getMethod().getMethodName());  // Name of the screenshot file
        if (screenShotPath != null) {
            getExtentTest().addScreenCaptureFromPath(screenShotPath, "Screenshot for failed test: " + result.getMethod().getMethodName());
        } else {
            getExtentTest().log(Status.INFO, "No screenshot available for this test failure.");
        }
    }

    public void onTestSkipped(ITestResult result) {
        // Log the skipped test in the report
        getExtentTest().log(Status.SKIP, "Test skipped: " + result.getMethod().getMethodName());
    }

    public void onFinish(ITestContext context) {
        // Flush the report to write all logs and results
        flushReport();
    }


}
