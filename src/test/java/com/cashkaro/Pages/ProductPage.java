package com.cashkaro.Pages;

import com.cashkaro.base.seleniumcore.BrowserInteractions;
import com.cashkaro.base.utils.DropdownSelectMethod;
import com.cashkaro.base.utils.LocatorType;
import com.cashkaro.base.utils.SeleniumUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductPage {
    static final Logger logger = LogManager.getLogger(ProductPage.class.getName());

    String newButton = "new_button-button";
    String newProductLink = ".//*[@id='yui-gen0']/a[text()='New Site']";
    String configurableSites = "IDPS_5fSelectSiteToConfigure";
    String quantity = "IDSPARE_5fSPRBKRM_5fREPL_2dHASP_OQI";
    String submitChanges = "cfgsubmitchanges";

    String addToQuote = ".//*[@value='Add to Quote']";

    String nextButton = "forward";
    String submitting = ".//*[@id='cfgsubmittingchanges' and @style='display:none;']";
    String childOpportunityText = ".//*[@id='products']//.[contains(text(),'FPX Child1')]";

    String quoteGuidelines = ".//*[@id='QuoteGuidelinesView']//a";
    String airbusSite = ".//*[@id='logo']/a/h1[contains(text(),'Airbus DS Communications')]";

    String siteNotes = ".//a[text()='Site Notes']";
    String notes = "IDProductNotes_VOIG";
    String saveButton = "//input[@value='Save']";
    String customizeButton = "//div[@id='products']//.[text()='Sites']/ancestor::div[@class='csHeader']/following-sibling::div//a[text()='Customize']";
    String moreButton = "//a[text()='More']";
    String copyButton = "//a[text()='Copy']";
    String displayName = "displayNameInput";
    String productName = ".//*[@id='pageContentDiv']//td[text()='Name']/following-sibling::td";


    public void productCreation() throws Exception {
        BrowserInteractions.switchFrame("itarget");
        BrowserInteractions.click(newButton, LocatorType.ID);
        BrowserInteractions.click(newProductLink, LocatorType.XPATH);
        BrowserInteractions.selectValueFromDropDown(configurableSites, DropdownSelectMethod.TEXT, "FPX Child1", LocatorType.ID);
        BrowserInteractions.click(nextButton, LocatorType.ID);
        BrowserInteractions.clearAndEnterText(quantity, LocatorType.ID, "1");
        BrowserInteractions.click(submitChanges, LocatorType.ID);
        BrowserInteractions.verifyElementPresence(submitting, LocatorType.XPATH);
        BrowserInteractions.click(addToQuote, LocatorType.XPATH);
        Boolean elementPresent = BrowserInteractions.isElementPresent(childOpportunityText, LocatorType.XPATH);
        if (elementPresent) {
            logger.info("Successfully Product added to the Quote");
        } else {
            SeleniumUtils.takeSnapshot();
            logger.info("Product is not added to the Quote");
            throw new Exception("Product is not added to the Quote");
        }
        BrowserInteractions.switchToDefaultFrame();
    }

    public void productPage() throws Exception {
        BrowserInteractions.switchFrame("itarget");
        BrowserInteractions.click(newButton, LocatorType.ID);
        BrowserInteractions.click(newProductLink, LocatorType.XPATH);
        BrowserInteractions.selectValueFromDropDown(configurableSites, DropdownSelectMethod.TEXT, "FPX Child1", LocatorType.ID);
        BrowserInteractions.click(nextButton, LocatorType.ID);
    }

    public void quoteGuidelines() throws Exception {
        BrowserInteractions.click(quoteGuidelines, LocatorType.XPATH);
    }

    public void airbusCommunication() throws Exception {
        Boolean textVerify = BrowserInteractions.isElementPresent(airbusSite, LocatorType.XPATH);
        if (textVerify) {
            logger.info("Able to access Airbus site");
        } else {
            logger.info("Unable to access Airbus site");
            SeleniumUtils.takeSnapshot();
            throw new Exception("Unable to access Airbus Site");
        }
    }


    public void productCopy() throws Exception {
        BrowserInteractions.switchFrame("itarget");
        BrowserInteractions.click(moreButton, LocatorType.XPATH);
        BrowserInteractions.click(copyButton, LocatorType.XPATH);
        BrowserInteractions.clearAndEnterText(displayName, LocatorType.ID, "Copy Product");
        BrowserInteractions.click(saveButton, LocatorType.XPATH);
        String displayName = BrowserInteractions.getText(productName, LocatorType.XPATH);
        if (displayName.equalsIgnoreCase("Copy Product")) {
            logger.info("Successfully able to copy the product");
        } else {
            logger.info("Unable to copy the product");
            SeleniumUtils.takeSnapshot();
            throw new Exception("Unable to copy the product");
        }
        BrowserInteractions.switchToDefaultFrame();
    }

    public void siteNotes() throws Exception {
        BrowserInteractions.clearAndEnterText(quantity, LocatorType.ID, "1");
        BrowserInteractions.click(submitChanges, LocatorType.ID);
        BrowserInteractions.verifyElementPresence(submitting, LocatorType.XPATH);
        BrowserInteractions.click(siteNotes, LocatorType.XPATH);
        BrowserInteractions.verifyElementPresence(notes, LocatorType.ID);
        BrowserInteractions.clearAndEnterText(notes, LocatorType.ID, "Testing");
        BrowserInteractions.click(submitChanges, LocatorType.ID);
        BrowserInteractions.verifyElementPresence(submitting, LocatorType.XPATH);
        BrowserInteractions.click(addToQuote, LocatorType.XPATH);
        BrowserInteractions.click(customizeButton, LocatorType.XPATH);
        BrowserInteractions.click(siteNotes, LocatorType.XPATH);
        String notesValue = BrowserInteractions.getText(notes, LocatorType.ID);
        if (notesValue.equalsIgnoreCase("Testing")) {
            logger.info("Notes value is saved successfully");
        } else {
            logger.info("Notes value is not saved successfully");
            SeleniumUtils.takeSnapshot();
            throw new Exception("Notes value is not saved successfully");
        }


    }

}
