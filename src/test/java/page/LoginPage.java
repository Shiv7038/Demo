package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.driver.manage().window().maximize();
        PageFactory.initElements(driver, this);
    }

    public static void switchToMainWindow1(WebDriver driver, String homeWindow) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @FindBy(xpath ="//*[@id='txtLogin']")
    protected WebElement username;

    @FindBy(xpath ="//h2[text()='Sign in']")
    protected WebElement signInText;

    @FindBy(xpath ="//*[@id='txtPassword']")
    protected WebElement password;

    @FindBy(xpath ="//button[text()='Sign In']")
    protected WebElement signinbutton;
}
