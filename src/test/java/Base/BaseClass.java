package Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import extentReport.LogUtil;
import listener.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import utils.BrowserFactry;
import utils.ReadConfig;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;
@Listeners(TestListener.class)
public class BaseClass {
    public static String homeWindow = null;
    public static ExtentReports extentReport;

    WebDriver driver;
    public static ExtentTest extentTest;
    public static String className = "";
    public static String browserName = "Chrome";
    private ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    /**
     * Before Suite will be executed before the test suite.
     *
     * @param context The current test context
     * @throws Exception
     */

    @BeforeSuite(alwaysRun = true)
    public void beforeSuiteMethod(ITestContext context) throws Exception {

        Set<String> componentSet = new HashSet<String>();
        Set<String> testNamesSet = new HashSet<String>();
        ITestNGMethod[] tests = context.getAllTestMethods();
        String suiteName = context.getName();

        for (int count = 0; count < tests.length; count++) {
            testNamesSet.add(tests[count].getId());
            componentSet.add(tests[count].getTestClass().getName().split(".tests.")[0].split("Test")[0]);
        }

    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(ITestContext context, Method method) throws ClassNotFoundException, SQLException {
        LogUtil.loggerMessage("Set the Driver");
       // setDriver(browserName);
        BrowserFactry.get(ReadConfig.getProperties().getProperty("browserName"));
        //  BrowserFactry.get(browser);
        driver=BrowserFactry.getDriver();
        driver.get(ReadConfig.getProperties().getProperty("testsiteurl"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        driver.manage().window().maximize();
       // utils.LogUtil.info("Browser Loanch");
        LogUtil.loggerMessage("Loading Home page");
        // loadHomepage(getDriver());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestContext context, Method method) {
        getDriver().quit();
    }

//    public void setDriver(String browserName) {
//        WebDriver driver = WebDriverFactory.get("chrome");
//        webDriverThreadLocal.set(driver);
//        driver.manage().window().maximize();
//    }

    private void loadHomepage(WebDriver driver) {
        String homeURL ="" ;
        driver.get(homeURL);
    }

    public WebDriver getDriver() {
        return webDriverThreadLocal.get();
    }



}
