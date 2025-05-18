package testCases;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseCase {
    public static WebDriver driver;
    public Logger logger = LogManager.getLogger(this.getClass());   //Log4j
    public Properties p;

    @BeforeClass(groups = {"Sanity", "Master", "Regression"})
    @Parameters({"OS", "browser"})
    void setup(String os, String br) throws IOException {

        //Loading config.properties file
        FileReader file = new FileReader("./src/test/resources/config.properties");
        p = new Properties();
        p.load(file);

        //logger = LogManager.getLogger(this.getClass());

        //remote
        if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            //OS
            if (os.equalsIgnoreCase("linux")) {
                capabilities.setPlatform(Platform.LINUX);
            } else if (os.equalsIgnoreCase("windows")) {
                capabilities.setPlatform(Platform.WIN11);
            } else {
                System.out.println("No matching os");
                return;
            }

            //Browser
            if (br.equalsIgnoreCase("chrome")) {
                capabilities.setBrowserName("chrome");
            } else if (br.equalsIgnoreCase("edge")) {
                capabilities.setBrowserName("MicrosoftEdge");
            } else {
                System.out.println("No matching browser");
                return;
            }

            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        }

        //local
        if (p.getProperty("execution_env").equalsIgnoreCase("local")) {

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
        }


        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        driver.get(p.getProperty("appURL"));    //Reading URL from config.properties file
    }

    @AfterClass(groups = {"Sanity", "Master", "Regression"})
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

    public String captureScreen(String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir") + "/screenshots/" + tname + "_" + timeStamp + ".png";
        System.out.println("ScreenShot file path: " + targetFilePath);
        File targetFile = new File(targetFilePath);

//        sourceFile.renameTo(targetFile);
        FileUtils.copyFile(sourceFile, targetFile);
        return targetFilePath;
    }
}
