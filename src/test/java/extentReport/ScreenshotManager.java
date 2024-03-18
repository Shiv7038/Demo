package extentReport;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotManager {

    public static String takeScreenshot(WebDriver driver) {
        String base64Screenshot = null;

        ((JavascriptExecutor) driver).executeScript("document.body.style.zoom='80%'");
        base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
       // Log.loggerMessage("Screenshot Captured");
        ((JavascriptExecutor) driver).executeScript("document.body.style.zoom='100%'");
        return base64Screenshot;
    }
}
