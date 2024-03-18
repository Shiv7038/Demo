package extentReport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class LogUtil {

    public static final Logger logger = LogManager.getLogger(LogUtil.class);

    /**
     * Message print the test case message in the log (level=info)
     *
     * @param description test case
     */
    public static void info(String description) {
        if (description != null) {
            logger.info(description);
            ExtentManager.info(description);
        }
    }

    /**
     * Message print the test case message in the log (level=info)
     *
     * @param description test case
     */
    public static void pass(String description) {
        if (description != null) {
            logger.info(description);
            ExtentManager.pass(description);
        }
    }

    /**
     * Message print the test case message in the log (level=info)
     *
     * @param description test case
     */
    public static void pass(String description, WebDriver driver) {
        if (description != null) {
            logger.info(description);
            ExtentManager.pass(description, driver);
        }
    }

    /**
     * Message print the test case message in the log (level=info)
     *
     * @param description test case
     */
    public static void fail(String description) {
        if (description != null) {
            logger.error(description);
            ExtentManager.fail(description);
        }
    }

    /**
     * Message print the test case message in the log (level=info)
     *
     * @param description test case
     * @param driver The current driver
     */
    public static void fail(String description, WebDriver driver) {
        if (description != null) {
            logger.error(description);
            ExtentManager.fail(description, driver);
        }
    }

    /**
     * Message print the test case message in the log (level=info) and fail message in extent report
     *
     * @param description description of the message to be printed
     */
    public static void fail(Throwable description) {
        if (description != null) {
            logger.error(description);
           ExtentManager.fail(description);
        }
    }

    /**
     * Message print the test case message in the log (level=info)
     *
     * @param description test case
     */
    public static void skip(String description) {
        if (description != null) {
            logger.info(description);
            ExtentManager.skip(description);
        }
    }

    /**
     * Message print the test case message in the log (level=info)
     *
     * @param description test case
     */
    public static void loggerMessage(String description) {
        if (description != null) {
            logger.info(description);
        }
    }

    /**
     * Asserts that a condition is true or false, depends upon the status. Then it will print the verified message if status is
     * true, else stop the script and print the failed message
     *
     * @param status boolean or expression returning boolean
     * @param messageTxt message to be logged for status
     */
    public static void assertThat(boolean status, String passMessageTxt, String failMessageTxt) {
        if (!status) {
           // ExtentManager.fail(failMessageTxt);
            logger.error(failMessageTxt);
            Assert.assertTrue(status, passMessageTxt);
        } else {
            Assert.assertTrue(status, passMessageTxt);
            ExtentManager.pass(passMessageTxt);
            logger.info(passMessageTxt);
        }
    }

    /**
     * Soft Asserts that a condition is true or false, depends upon the status. Then it will print the verified message if status
     * is
     * true, else continue the script and print the failed message
     *
     * @param status boolean or expression returning boolean
     * @param messageTxt message to be logged for status
     */
    public static void softAssert(boolean status, String messageTxt) {
        if (!status) {
            ExtentManager.fail(messageTxt + " is not displayed");
            logger.error(messageTxt + " is not displayed");
        } else {
            ExtentManager.pass(messageTxt + " is displayed");
            logger.info(messageTxt + " is displayed");
        }
    }

}
