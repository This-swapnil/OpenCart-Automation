package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseCase {

    @Test(groups = {"Datadriven", "Master"}, dataProvider = "LoginData", dataProviderClass = DataProviders.class)
    public void verify_loginDDT(String email, String pwd, String exp) {

        logger.info("**** Starting TC003_LoginDDT ****");
        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            //Login Page
            LoginPage lp = new LoginPage(driver);
            lp.setEmail(email);
            lp.setPassword(pwd);
            lp.clickLogin();

            Thread.sleep(3000);

            //MyAccount
            MyAccountPage macc = new MyAccountPage(driver);
            boolean target_page = macc.isMyAccountPageExists();

       /*
        Data is valid --> login success -> test pass -> logout
                          login failed -> test fail

        Data is invalid --> login success -> test fail -> logout
                            login failed -> test pass

        */

            if (exp.equalsIgnoreCase("valid")) {
                if (target_page) {
                    macc.clickLogout();
                    Assert.assertTrue(true);
                } else {
                    Assert.fail();
                }
            }

            if (exp.equalsIgnoreCase("invalid")) {
                if (target_page) {
                    macc.clickLogout();
                    Assert.fail();
                } else {
                    Assert.assertTrue(true);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            Assert.fail();
        }
        logger.info("**** Finished TC003_LoginDDT ****");
    }
}
