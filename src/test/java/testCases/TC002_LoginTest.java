package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;

public class TC002_LoginTest extends BaseCase {

    @Test(groups = {"Sanity", "Master"})
    public void verifyLogin() {
        logger.info("**** Starting TC002_LoginTest ****");

        try {//HomePage
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            //Login Page
            LoginPage lp = new LoginPage(driver);
            lp.setEmail(p.getProperty("email"));
            lp.setPassword(p.getProperty("password"));
            lp.clickLogin();

            //MyAccount
            MyAccountPage macc = new MyAccountPage(driver);
            boolean target_page = macc.isMyAccountPageExists();

            Assert.assertTrue(target_page, "Login Failed");
        } catch (Exception e) {
            logger.error("Test TC002_LoginTest is failed...");
            logger.error(e.getMessage());
            Assert.fail();
        }
        logger.info("**** Finished TC002_LoginTest ****");
    }
}
