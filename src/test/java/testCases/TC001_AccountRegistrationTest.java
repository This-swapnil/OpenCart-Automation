package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.AccountRegistrationPage;
import pageObject.HomePage;

public class TC001_AccountRegistrationTest extends BaseCase {

    @Test
    void verifyAccountRegistration() {
        //click My Account dropdown and then click Register
        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();
        hp.clickRegister();

        //Access WebElements from the Registration page and register the user
        AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

        regpage.setFirstName(randomString().toUpperCase());
        regpage.setLastName(randomString().toUpperCase());

        regpage.setEmail(randomString().toUpperCase() + "@gmail.com");

        String password = randomAlphaNumeric();
        regpage.setPassword(password);

        regpage.setPrivacyPolicy();
        regpage.clickSubmit();

        String confMsg = regpage.getConfirmationMsg();
        Assert.assertEquals(confMsg, "Your Account Has Been Created!");
    }
}
