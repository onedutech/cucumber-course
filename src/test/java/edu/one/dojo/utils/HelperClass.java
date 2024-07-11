package edu.one.dojo.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.time.Duration;

public class HelperClass {

    private static HelperClass helperClass;
    private static WebDriver driver;

    private HelperClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    public static void openPage(String url) {
        driver.get(url);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setUpDriver() {
        if (helperClass == null) {
            helperClass = new HelperClass();
        }
    }

    public static void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
        helperClass = null;
    }

    public static void takeStepScreenshot(String filename, String testName) {
        TakesScreenshot screenshot = ((TakesScreenshot) getDriver());
        File srcImage = screenshot.getScreenshotAs(OutputType.FILE);
        String filepath = "target/reports/ExtentReporter/" + testName + "/" + filename + ".png";
        File destFile = new File(filepath);
        try {
            FileUtils.copyFile(srcImage, destFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}