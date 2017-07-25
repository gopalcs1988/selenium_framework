package com.cashkaro.Pages;


import com.cashkaro.base.seleniumcore.BrowserInteractions;
import com.cashkaro.base.utils.LocatorType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommonObjects {
    static final Logger logger = LogManager.getLogger(CommonObjects.class.getName());
    String username = "username";
    String password = "password";
    String submit = "Login";
    String profileName = ".//*[@id='globalHeaderNameMink']/span[contains(text(),'Rajalakshmi')]";
    String logout = ".//*[text()='Logout']";
    String LoginPageText = "//*[contains(text(),'Forgot Your Password')]";

    public void homePage()throws Exception{
        try {
            BrowserInteractions.navigateToURL(BrowserInteractions.propertyFileRead().getProperty("Environment"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void login(String uname, String pword) throws Exception {

        try {
            BrowserInteractions.navigateToURL(BrowserInteractions.propertyFileRead().getProperty("Environment"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Accessing Salesforce  Environment");
        BrowserInteractions.clearAndEnterText(username, LocatorType.ID, uname);
        BrowserInteractions.clearAndEnterText(password, LocatorType.ID, pword);
        BrowserInteractions.click(submit, LocatorType.ID);
        BrowserInteractions.verifyElementPresence(profileName, LocatorType.XPATH);
    }

    public void logoutLinkOtherThanHomePage() throws Exception {
        BrowserInteractions.click(profileName, LocatorType.XPATH);
        BrowserInteractions.click(logout, LocatorType.XPATH);
        logger.info("Able to locate the logout link");
        BrowserInteractions.verifyElementPresence(LoginPageText, LocatorType.XPATH);
        logger.info("Able to access login page");
    }
}
