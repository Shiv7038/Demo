package utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class WaitUtil {

    /**
     * Ensures that the page is completely loaded using java script
     *
     * @param driver the current driver
     */
    public static void waitForPageToLoadJs(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    /**
     * Ensures that the page is completely loaded
     *
     * @param driver the current driver
     */
    public static void waitForPageToLoad(WebDriver driver) {
        String source1 = null, source2 = null;
        do {
            source1 = driver.getPageSource();
            waitForPageToLoadJs(driver);
            source2 = driver.getPageSource();
        } while (!source1.equals(source2));
    }

    /**
     * Wait for element text to be present
     *
     * @param driver
     * @param webElement
     * @param value
     * @return
     */
    public static boolean waitForTheElementToBePresent(WebDriver driver, WebElement webElement, String value) {
        try {
            FluentWait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);

            boolean result = wait.until(ExpectedConditions.textToBePresentInElement(webElement, value));
            LogUtil.pass("WebElement Is Present : " + value);
            return result;
        } catch (Exception e) {
            LogUtil.fail("WebElement Is Not Present : " + value);
            return false;
        }
    }

    /**
     * wait For The textToBePresentInElement
     *
     * @param driver
     * @param webElement
     * @param text
     * @return
     */

    public static boolean waitForThetextToBePresentInElement(WebDriver driver, WebElement webElement, String expectedText) {
        try {
            FluentWait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);

            boolean result = wait.until(ExpectedConditions.textToBePresentInElement(webElement, expectedText));
            LogUtil.pass("Expected Text Is Present On WebElement : " + expectedText);
            return result;
        } catch (Exception e) {
            LogUtil.fail("Expected Text Is Present On WebElement : " + expectedText);
            return false;
        }
    }


    /**
     * Wait for the element visible.
     *
     * @param driver         the driver
     * @param webElementName the web element name
     * @return true, if successful
     */
    public static boolean waitForTheElementVisible(WebDriver driver, WebElement webElementName, String elementName) {
        try {
            FluentWait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
            WebElement result = wait.until(ExpectedConditions.visibilityOf(webElementName));
            if (result != null) {
                LogUtil.pass("WebElement Is Present: " + elementName);
                return true;
            } else {
                LogUtil.fail("WebElement Is Not Present:" + elementName);
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * wait For The Visibility Of AllElements
     *
     * @param driver
     * @param webElements
     * @param elementName
     * @return
     */

    public static boolean waitForTheVisibilityOfAllElements(WebDriver driver, List<WebElement> webElements, String elementName) {
        try {
            FluentWait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
            List<WebElement> result = wait.until(ExpectedConditions.visibilityOfAllElements(webElements));
            if (result != null) {
                LogUtil.pass("All WebElements Are Visible : " + elementName);
                return true;
            } else {
                LogUtil.fail("All WebElements Are Visible :" + elementName);
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * wait For The Frame To Be Available And Switch To It
     *
     * @param driver
     * @param webElementName
     * @param elementName
     * @return
     */
    public static boolean waitForTheFrameToBeAvailableAndSwitchToIt(WebDriver driver, WebElement webElementName, String elementName) {
        try {
            FluentWait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
            WebDriver result = wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(webElementName));
            if (result != null) {
                LogUtil.pass("Frame WebElements Is Present and Switch to It: " + elementName);
                return true;
            } else {
                LogUtil.fail("Frame WebElement Is Not Present:" + elementName);
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * wait For The Alert To Be Present
     *
     * @param driver
     * @return
     */
    public static boolean waitForTheAlertToBePresent(WebDriver driver) {
        try {
            FluentWait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            if (alert != null) {
                LogUtil.pass("Alert is present");
                return true;
            } else {
                LogUtil.fail("Alert is not Present");
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean waitForWebElementIsCliceble(WebDriver driver, WebElement webElementName, String elementName) {
        try {
            FluentWait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
            WebElement result = wait.until(ExpectedConditions.elementToBeClickable(webElementName));
            if (result != null) {
                LogUtil.pass("WebElement is clickeble : " + elementName);
                return true;
            } else {
                LogUtil.fail("WebElement is not clickeble:" + elementName);
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

        /*
         Wait for Text To Be Removed
     */

    public static boolean waitForTextToBeRemoved(WebDriver driver, WebElement element, String textName) {
        try {
            FluentWait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
            boolean result = wait.until(textToBeRemoved(element, textName));
            if (result) {
                LogUtil.info("Text is not present" + textName);
                return true;
            } else {
                LogUtil.fail("Text is present" + textName);
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * wait For Child Window To Be Open
     *
     * @param driver
     * @param expectedChildWindow
     * @return
     */
    public static boolean waitForChildWindowToBeOpen(WebDriver driver, int expectedChildWindow) {
        try {
            FluentWait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
            boolean result = wait.until(ExpectedConditions.numberOfWindowsToBe(expectedChildWindow));
            if (result) {
                LogUtil.pass("Expected child window able to open");
                return true;
            } else {
                LogUtil.fail("Expected child window are not able to open");
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /*
    Text To Be Removed
     */

    private static ExpectedCondition<Boolean> textToBeRemoved(WebElement element, String text) {
        return driver -> {
            return !element.getText().contains(text);
        };
    }

    /*
 wait For The Element Visible And Handle Popup no record found
 */
//    public static void waitForTheElementVisibleAndHandlePopup(WebDriver driver, WebElement element, String elementName) {
//        boolean flag = true;
//        try {
//            BrowserActions.noRecordFoundPopUpHandler(driver);
//            flag = false;
//        } catch (Exception e) {
//
//        }
//        if (flag == true) {
//            WaitUtil.waitForTheElementVisible(driver, element, "elementName");
//        }
//    }

    /**
     * Common method for fixed time wait(Avoid to use)
     *
     * @param time Time in seconds
     */
    public static void waitFixedTime(long time) {
        try {
            Thread.sleep(time * 1000);
        } catch (Throwable e) {
            LogUtil.fail("Error in wait");
        }
    }

}
