package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.AccountRegistrationPage;
import pageObject.HomePage;

public class TC001_AccountRegistrationTest extends BaseCase {

    @Test(groups = {"Regression", "Master"})
    void verifyAccountRegistration() {

        logger.info("**** TC001_AccountRegistrationTest ****");

        try {
            //click My Account dropdown and then click Register
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked on MyAccount Link");

            hp.clickRegister();
            logger.info("Clicked on Register Link");

            //Access WebElements from the Registration page and register the user
            AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

            logger.info("Providing customer details...");
            regpage.setFirstName(randomString().toUpperCase());
            regpage.setLastName(randomString().toUpperCase());

            regpage.setEmail(randomString().toUpperCase() + "@gmail.com");

            String password = randomAlphaNumeric();
            regpage.setPassword(password);

            regpage.setPrivacyPolicy();
            regpage.clickSubmit();

            logger.info("Validating expected message...");
            String confMsg = regpage.getConfirmationMsg();

            if (confMsg.equals("Your Account Has Been Created!")) {
                Assert.assertTrue(true);
            } else {
                logger.error("Test failed...");
                logger.debug("Debug logs..");
                Assert.fail();
            }
//            Assert.assertEquals(confMsg, "Your Account Has Been Created!!");
        } catch (Exception e) {
            logger.error(e.getMessage());
            Assert.fail();
        }
        logger.info("**** Finished TC001_AccountRegistrationTest ****");
    }
}
