package Base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.BrowserFactry;
import utils.ReadConfig;

import java.time.Duration;

public class BaseClass {
WebDriver driver;
    @BeforeMethod
    public void m1(){
        BrowserFactry.get("chrome");
         driver=BrowserFactry.getDriver();
        driver.get(ReadConfig.getProperties().getProperty("testsiteurl"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void m2(){
        driver.close();
        driver.quit();
    }


}
