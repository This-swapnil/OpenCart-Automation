package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

    //Locators
    @FindBy(xpath = "//h1[normalize-space()='My Account']")
    WebElement msgHeading;

    @FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
    WebElement lnkLogout;

    //constructor
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public boolean isMyAccountPageExists() {
        try {
            return (msgHeading.isDisplayed());
        } catch (Exception e) {
            return false;
        }
    }

    public void clickLogout() {
        lnkLogout.click();
    }
}
