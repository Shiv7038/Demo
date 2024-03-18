package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.testng.SkipException;

import java.net.URL;

public class BrowserFactry {

    static WebDriver driver = null;

    public static WebDriver get(String browserName) {

        try {
            if (browserName.equalsIgnoreCase("chrome")) {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("----ignore-certificate-errors-spki-list");
                chromeOptions.addArguments("--test-type");
                chromeOptions.addArguments("allow-running-insecure-content");
                chromeOptions.addArguments("--disable-popup-blocking");
                chromeOptions.addArguments("--disable-extensions");
                chromeOptions.addArguments("--lang=en");
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.setAcceptInsecureCerts(true);
                chromeOptions.addArguments("--disable-application-cache");
                    driver = new ChromeDriver(chromeOptions);
                }
            else if (browserName.equalsIgnoreCase("edge")) {
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("----ignore-certificate-errors-spki-list");
                edgeOptions.addArguments("--test-type");
                edgeOptions.addArguments("allow-running-insecure-content");
                edgeOptions.addArguments("--disable-popup-blocking");
                edgeOptions.addArguments("--disable-extensions");
                edgeOptions.addArguments("--lang=en");
                edgeOptions.addArguments("--remote-allow-origins=*");
                edgeOptions.setAcceptInsecureCerts(true);
                edgeOptions.addArguments("--disable-application-cache");
                driver = new EdgeDriver(edgeOptions);
            }
        } catch (UnreachableBrowserException e) {
            e.printStackTrace();
            throw new SkipException("Browser is not started or down.");
        }
        return driver;
    }

    public static WebDriver getDriver(){
        return driver;
    }
}
