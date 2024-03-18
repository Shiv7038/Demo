package listener;


import Base.BaseClass;
import extentReport.ExtentManager;
import extentReport.LogUtil;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TestListener extends BaseClass implements ITestListener, ISuiteListener {

    String overallSummaryRow = new String();
    String overallSummaryTable = new String();
    String passedTestRow = new String();
    String passedSummaryTable = new String();
    String failedTestRow = new String();
    String failedSummaryTable = new String();
    String status = "";
    private boolean sendEmail = true;

    List<ITestNGMethod> passedTests = new ArrayList<ITestNGMethod>();
    List<ITestNGMethod> failedTests = new ArrayList<ITestNGMethod>();
    List<ITestNGMethod> skippedTests = new ArrayList<ITestNGMethod>();

    @Override
    public void onStart(ISuite suite) {
        LogUtil.loggerMessage("Start of Suite");
        extentReport = ExtentManager.getReporter();
    }

    // Before starting all tests, below method runs.
    @Override
    public void onStart(ITestContext iTestContext) {

        //   iTestContext.setAttribute("WebDriver", this.getDriver());
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {

        String testName = getTestName(iTestResult);
        String testDescription = iTestResult.getMethod().getDescription();
        String timeStamp = new SimpleDateFormat("dd/MM/yyy HH:mm:ss").format(Calendar.getInstance().getTime());
        String browserName = "Chrome";
        String className = iTestResult.getTestClass().getName().substring(iTestResult.getInstanceName().lastIndexOf('.') + 1);

        if (testDescription != null) {
            extentTest = ExtentManager.startTest(testName, testDescription);
        } else {
            extentTest = ExtentManager.startTest(testName, "");
        }
        extentTest.assignAuthor("Shiv");
        extentTest.assignCategory(className);
        LogUtil.info("Test Case " + testName + " started on browser " + browserName + " at " + timeStamp);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

        passedTests.add(iTestResult.getMethod());

        // Operations for Email report
        if (sendEmail) {
            if (1 == iTestResult.getStatus()) {
                status = "<td><font color='green'>PASSED</font></td>";
            }
        }
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

        failedTests.add(iTestResult.getMethod());

        String testName = getTestName(iTestResult);
        Object testClass = iTestResult.getInstance();

        WebDriver webDriver = ((BaseClass) testClass).getDriver();

        // Extentreports log and screenshot operations for failed tests
        if (iTestResult.getThrowable().getMessage() == null) {
            LogUtil.fail("Test Case " + testName + " Failed", webDriver);
        } else {
            LogUtil.fail("Test Case " + testName + " Failed with following error", webDriver);
            LogUtil.fail(iTestResult.getThrowable());
        }

        // Operations for Email report
        if (sendEmail) {
            if (2 == iTestResult.getStatus()) {
                status = "<td><font color='red'>FAILED</font></td>";
            } else {
                status = "<td><font color='#B7950B'>BROKEN</font></td>";
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

        skippedTests.add(iTestResult.getMethod());

        // Extentreports log operation for skipped tests.
        LogUtil.skip("Test case " + getTestName(iTestResult) + " Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    // After ending all tests, below method runs.
    @Override
    public void onFinish(ITestContext iTestContext) {

        // Do tier down operations for extentreports reporting!
        extentReport.flush();
    }

    @Override
    public void onFinish(ISuite suite) {
        LogUtil.loggerMessage("Failed to move report");
    }

    /**
     * Get the test case name and class.
     *
     * @param iTestResult Test results
     * @return Returns test method name.
     */
    public static String getTestName(ITestResult iTestResult) {
        String methodName = iTestResult.getMethod().getConstructorOrMethod().getName();
        String className = iTestResult.getTestClass().getName().substring(iTestResult.getInstanceName().lastIndexOf('.') + 1);
        return className + "_" + methodName;
    }
}
