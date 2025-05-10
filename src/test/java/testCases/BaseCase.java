package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseCase {
    public WebDriver driver;

    @BeforeClass
    void setup() {
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        driver.get("http://localhost/opencart-4.1.0.3/upload/index.php");
    }

    @AfterClass
    void tearDown() {
        driver.quit();
    }

    public String randomString() {
        String generatedstring = RandomStringUtils.randomAlphabetic(5);
        return generatedstring;
    }

    public String randomAlphaNumeric() {
        String generatedstring = RandomStringUtils.randomAlphabetic(3);
        String generatenumber = RandomStringUtils.randomNumeric(3);
        return (generatedstring + generatenumber);
    }
}
