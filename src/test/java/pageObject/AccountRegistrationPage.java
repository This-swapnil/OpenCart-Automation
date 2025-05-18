package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountRegistrationPage extends BasePage {
    //Locators
    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement txtFirstName;
    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement txtLastName;
    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtEmail;
    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtPassword;
    @FindBy(xpath = "//input[@name='agree']")
    WebElement chkdPolicy;
    @FindBy(xpath = "//button[normalize-space()='Continue']")
    WebElement btnClick;
    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msgConfirmation;

    //constructor
    public AccountRegistrationPage(WebDriver driver) {
        super(driver);
    }

    //Action Methods
    public void setFirstName(String fname) {
        txtFirstName.sendKeys(fname);
    }

    public void setLastName(String lname) {
        txtLastName.sendKeys(lname);
    }

    public void setEmail(String email) {
        txtEmail.sendKeys(email);
    }

    public void setPassword(String pwd) {
        txtPassword.sendKeys(pwd);
    }

    public void scrollToBottom() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void setPrivacyPolicy() {
//        js.executeScript("arguments[0].scrollIntoView(true);", chkdPolicy);
        wait.until(ExpectedConditions.elementToBeClickable(chkdPolicy)).click();
//        Thread.sleep(2000);
//        wait.until(ExpectedConditions.visibilityOf(chkdPolicy));
//        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", chkdPolicy);
//        wait.until(ExpectedConditions.elementToBeClickable(chkdPolicy)).click();
//        chkdPolicy.click();
    }

    public void clickSubmit() {
        btnClick.click();
    }

    public String getConfirmationMsg() {
        try {
            return msgConfirmation.getText();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
