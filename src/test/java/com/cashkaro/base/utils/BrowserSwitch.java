package com.cashkaro.base.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class BrowserSwitch {

    static final Logger logger = LogManager.getLogger(BrowserSwitch.class.getName());

    WebDriver driver = BrowserFactory.getCurrentBrowser();
    String winHandleBefore = driver.getWindowHandle();

    public void switchToChildWindow() throws InterruptedException {

        logger.info("Parent Window : " + winHandleBefore);
        Thread.sleep(10000);
        for (String winHandle : driver.getWindowHandles()) {
            logger.info("Child Window : " + winHandle);
            String title = driver.switchTo().window(winHandle).getTitle();
            logger.info("Current WIndow Title : " + title);
        }
    }


    public void switchToParentWindow() {
        driver.close();
//Switch back to original browser (first window)
        driver.switchTo().window(winHandleBefore);
//continue with original browser (first window)
    }
}
