package com.cashkaro.Pages;

import com.cashkaro.base.seleniumcore.BrowserInteractions;
import com.cashkaro.base.utils.LocatorType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegisterPage {

    static final Logger logger = LogManager.getLogger(RegisterPage.class.getName());

    String joinFreeButton = "//a[text()='JOIN FREE']";
    String joinWithFacebook = "//*[@id='close_and_go_fb']";
    String email = ".//*[@id='email']";
    String password = ".//*[@id='pass']";
    String continue_button =".//*[@id='u_0_2']";
    String okay_button="//button[text()='Okay']";
   /* String firstName="./*//*[@id='firstname']";
    String email="./*//*[@id='email']";
    String confirm_Email="./*//*[@id='con_email']";
    String password="./*//*[@id='pwd']";
*/

    public void registration() throws Exception {
        BrowserInteractions.click(joinFreeButton,LocatorType.XPATH);
        BrowserInteractions.click(joinWithFacebook,LocatorType.XPATH);
        BrowserInteractions.switchToChildWindow();
        BrowserInteractions.clearAndEnterText(email,LocatorType.XPATH,"rajagopal1988@gmail.com");
        BrowserInteractions.clearAndEnterText(password,LocatorType.XPATH,"filehippo5503425");
        BrowserInteractions.click(continue_button,LocatorType.XPATH);

     /*   BrowserInteractions.clearAndEnterText(firstName, LocatorType.XPATH, "Testing");
        BrowserInteractions.clearAndEnterText(email,LocatorType.XPATH,"aa1@gmail.com");
        BrowserInteractions.clearAndEnterText(confirm_Email,LocatorType.XPATH,"aa1@gmail.com");
        BrowserInteractions.isElementPresent(password,LocatorType.XPATH);
        BrowserInteractions.clearAndEnterText(password,LocatorType.XPATH,"course1#");*/
    }
}

