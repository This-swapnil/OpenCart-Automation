package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    //locators
    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtEmailAddress;
    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtPassword;
    @FindBy(xpath = "//button[normalize-space()='Login']")
    WebElement btnLogin;

    //constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void setEmail(String email) {
        txtEmailAddress.sendKeys(email);
    }

    public void setPassword(String pwd) {
        txtPassword.sendKeys(pwd);
    }

    public void clickLogin() {
        btnLogin.click();
    }
}
