package test;

import Base.BaseClass;
import actions.LoginAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.LogUtil;

import static utils.BrowserFactry.getDriver;

public class LoginTest extends BaseClass {
    @Test(enabled = true)
    public void loginTest(){
        WebDriver driver= getDriver();
        LoginAction lp = new LoginAction(driver);
       lp.verifyLogin();
    }
    public static final Logger logger = LogManager.getLogger(LoginTest.class);

    @Test
    public void log(){
        logger.info("hii");
        logger.error("error");
        System.out.println("Hii");
    }
}
