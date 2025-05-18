package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    WebDriver driver;
    @FindBy(xpath = "//span[normalize-space()='My Account']")
    WebElement lnkMyAccount;
    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement lnkRegister;
    @FindBy(xpath = "//a[normalize-space()='Login']")
    WebElement linkLogin;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickMyAccount() {
        lnkMyAccount.click();
    }

    public void clickRegister() {
        lnkRegister.click();
    }

    public void clickLogin() {
        linkLogin.click();
    }
}
