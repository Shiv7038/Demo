package test;

import Base.BaseClass;
import actions.LoginAction;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static utils.BrowserFactry.getDriver;

public class LoginTest extends BaseClass {
    @Test
    public void loginTest(){
        WebDriver driver= getDriver();
        LoginAction lp = new LoginAction(driver);
       lp.verifyLogin();

    }
}
