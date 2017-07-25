package com.cashkaro;


import com.cashkaro.Pages.*;
import com.cashkaro.base.FPXBaseTestSuite;
import com.cashkaro.base.seleniumcore.BrowserInteractions;
import com.cashkaro.base.utils.BrowserSwitch;
import com.cashkaro.base.utils.BrowserUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Registration extends FPXBaseTestSuite {

    static final Logger logger = LogManager.getLogger(Registration.class.getName());

    RegisterPage registerPage=null;
    CommonObjects commonObjects = null;
    Opportunity opportunity = null;
    ProductPage productPage = null;

    @BeforeClass
    public void createInstance() {
        registerPage = new RegisterPage();
        commonObjects = new CommonObjects();
        opportunity = new Opportunity();
        productPage = new ProductPage();

    }

    @Test
    public void register() throws Exception {
        BrowserUtils.clearBrowserCache();
        logger.info("Account creation in  CashKaro.com");
        commonObjects.homePage();
        System.out.println("Able to access the application");
        registerPage.registration();
        System.out.println("Successfully created new account");

    }

}
