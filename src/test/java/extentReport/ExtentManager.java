package extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ExtentManager {
    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    static ExtentReports extent;

    /**
     * Generates Extent Report using the configuration.
     *
     * @return Returns object of extent report
     */
    public synchronized static ExtentReports getReporter() {
        if (extent == null) {

            // Set HTML reporting file location
            String reportPath = System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "Report.html";

            ExtentSparkReporter sparkReport = new ExtentSparkReporter(reportPath);
            extent = new ExtentReports();
            sparkReport.viewConfigurer().viewOrder().as(new ViewName[] {ViewName.DASHBOARD, ViewName.TEST, ViewName.CATEGORY})
                    .apply();
            extent.attachReporter(sparkReport);
            sparkReport.config().setProtocol(Protocol.HTTPS);
            sparkReport.config().setEncoding("UTF-8");
            sparkReport.config().setDocumentTitle("ExtentReport");
            sparkReport.config().setTheme(Theme.STANDARD);
            sparkReport.config().setReportName("ExtentReport");

            extent.setSystemInfo("Browser : ", "Chrome");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("URL", "URLQA");
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("OS Name", System.getProperty("os.name"));
        }
        return extent;
    }

    /**
     * Get the current Test
     *
     * @return Returns extent test
     */
    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    /**
     * Creates a New test and adds it to Extent Test Map
     *
     * @param testName Name of the test
     * @param desciption Description of the test
     * @return Returns Extent test
     */
    public static synchronized ExtentTest startTest(String testName, String desciption) {
        ExtentTest test = extent.createTest(testName, desciption);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }

    /**
     * To log the given message to the reporter at INFO level
     *
     * @param message
     */
    public static void info(String message) {
        getTest().log(Status.INFO, message);
    }

    /**
     * To log the given message to the reporter at PASS level
     *
     * @param passMessage Message to be displayed
     */
    public static void pass(String passMessage) {
        getTest().log(Status.PASS, "<font color=\"green\">" + passMessage + "</font>");
    }

    /**
     * To log the given message to the reporter at PASS level
     *
     * @param passMessage Message to be displayed
     */
    public static void pass(String passMessage, WebDriver driver) {
        getTest().pass("<font color=\"green\">" + passMessage + "</font> ",
                MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotManager.takeScreenshot(driver)).build());
    }

    /**
     * To log the given message to the reporter at FAIL level
     *
     * @param failMessage Message to be displayed
     */
    public static void fail(String failMessage) {
        getTest().log(Status.FAIL, "<font color=\"red\">" + failMessage + "</font>");
    }

    /**
     * To log the given message to the reporter at FAIL level
     *
     * @param failMessage Message to be displayed
     */
    public static void fail(String failMessage, WebDriver driver) {
        getTest().fail("<font color=\"red\">" + failMessage + "</font> ",
                MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotManager.takeScreenshot(driver)).build());
    }

    /**
     * To log the given message to the reporter at FAIL level
     *
     * @param throwableMessage Information of the message to be printed.
     */
    public static void fail(Throwable throwableMessage) {
        getTest().log(Status.FAIL, throwableMessage);
    }

    /**
     * To log the given message to the reporter at SKIP level
     *
     * @param message Message to be displayed
     */
    public static void skip(String message) {
        getTest().log(Status.SKIP, "<font color=\"orange\">" + message + "</font>");
    }
}
