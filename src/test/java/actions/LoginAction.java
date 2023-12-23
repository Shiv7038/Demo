package actions;

import org.openqa.selenium.WebDriver;
import page.LoginPage;
import utils.ReadConfig;
import utils.VerifyUlis;
import utils.WaitUtil;

public class LoginAction extends LoginPage {
    private WebDriver driver = null;

    public LoginAction(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void verifySignInPage(){
        WaitUtil.waitForTheElementVisible(driver,signInText,"SignIn");
        String text=signInText.getText().trim();
        VerifyUlis.verifyTowTextAreEquels(text,"Sign in");
    }
    public void enterCredentials() {
        username.sendKeys(ReadConfig.getProperties().getProperty("username"));
        password.sendKeys(ReadConfig.getProperties().getProperty("password"));
    }

    public void clickOnLogin() {
        signinbutton.click();
    }

    public String getTitleOfPage()
    {
      //  driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        String titleofpage = driver.getTitle();

        return titleofpage ;
    }

    public void verifyLogin(){
        verifySignInPage();
        enterCredentials();
        clickOnLogin();
    }
}
