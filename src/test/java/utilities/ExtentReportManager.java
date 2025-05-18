package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testCases.BaseCase;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ExtentReportManager implements ITestListener {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report_" + timeStamp + ".html";
        sparkReporter = new ExtentSparkReporter("reports/" + repName);

        sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
        sparkReporter.config().setReportName("OpenCart Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "OpenCart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");

        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, result.getName() + " got successfully excecuted!");
    }

    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getTestClass().getName());

        test.log(Status.FAIL, result.getName() + " got failed!");
        test.log(Status.INFO, result.getThrowable().getMessage());

        try {
            String imgPath = new BaseCase().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName() + " got skipped!");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext context) {
        extent.flush();

        String pathOfExtentReport = System.getProperty("user.dir") + "/reports/" + repName;
        File extentReport = new File(pathOfExtentReport);

        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }

/*
        try {
            URL url = new URL("file:///" + System.getProperty("user.dir") + "/reports/" + repName);

            //create the email
            ImageHtmlEmail email = new ImageHtmlEmail();
            email.setDataSourceResolver(new DataSourceUrlResolver(url));
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("sonawaneswapnil645@gmail.com", "@Swapnil@0098@"));
            email.setSSLOnConnect(true);
            email.setFrom("sonawaneswapnil645@gmail.com");
            email.setSubject("Test Result");
            email.setMsg("Please find attached report...");
            email.addTo("sswapnil0098@gmail.com");
            email.attach(url, "extent report", "please check report...");
            email.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/

    }
}
