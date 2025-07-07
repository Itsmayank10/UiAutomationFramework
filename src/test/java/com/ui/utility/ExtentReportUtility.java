package com.ui.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportUtility {
    private static ExtentReports extentReports;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static void setupSparkReport(String reportName) {
        // Initialize the ExtentSparkReporter with the report file path
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/" + reportName + ".html");
        extentSparkReporter.config().setReportName("UI Test Report");
        extentSparkReporter.config().setDocumentTitle("UI Test Results");

        // Create an instance of ExtentReports and attach the reporter
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
    }

    public static void createExtentTest(String testName) {
        // Create a new test in the report and set it in the ThreadLocal variable
        ExtentTest test = extentReports.createTest(testName);
        extentTest.set(test);
    }

    public static ExtentTest getExtentTest() {
        // Return the current test from the ThreadLocal variable
        return extentTest.get();
    }

    public static void flushReport() {
        // Flush the report to write all logs and results
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}
