package com.cashkaro.base.utils;


import com.cashkaro.base.FPXBaseTestSuite;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SeleniumUtils {

    static final Logger logger = LogManager.getLogger(SeleniumUtils.class.getName());

    public static void highlightElement(WebElement element) {
        WebDriver driver = BrowserFactory.getCurrentBrowser();
        for (int i = 0; i < 1; i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: black; border: 3px solid black;");
        }
    }

    public static String fileCreation(String filename) throws IOException {
        String complete_filename = "";
        try {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd HH-mm-ss");

            complete_filename = (dateFormat.format(date) + "_" + filename + ".png");
            System.out.println(complete_filename);
        } catch (Exception e) {
            System.out.println("Date format error");
        }
        return complete_filename;
    }

/*
@BeforeMethod()
    public void getMethodName(Method method)
    {
        testcasename=method.getName();
        System.out.println("testcase Name"+testcasename);
    }
*/

    public static void takeSnapshot()
            throws IOException {

        String path = System.getProperty("user.dir") + "\\TestDataFiles\\Snapshots\\" + SeleniumUtils.fileCreation(FPXBaseTestSuite.testcasename + "");
        WebDriver driver = BrowserFactory.getCurrentBrowser();
        try {

            File scrFile = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(path), true);

        } catch (Exception e) {
            logger.info("Snapshot Error");

        }
    }
}
