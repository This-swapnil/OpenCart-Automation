package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseCase {
    public WebDriver driver;
    public Logger logger;   //Log4j
    public Properties p;

    @BeforeClass
    @Parameters({"OS", "browser"})
    void setup(String os, String br) throws IOException {

        //Loading config.properties file
        FileReader file = new FileReader("./src/test/resources/config.properties");
        p = new Properties();
        p.load(file);

        logger = LogManager.getLogger(this.getClass());

        switch (br.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;

            case "edge":
                driver = new EdgeDriver();
                break;

            case "firefox":
                driver = new FirefoxDriver();
                break;

            default:
                System.out.println("Invalid browser name...");
                return;
        }


        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        driver.get(p.getProperty("appURL"));    //Reading URL from config.properties file
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
