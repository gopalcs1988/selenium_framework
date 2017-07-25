package com.cashkaro.Pages;

import com.cashkaro.base.seleniumcore.BrowserInteractions;
import com.cashkaro.base.utils.DropdownSelectMethod;
import com.cashkaro.base.utils.LocatorType;
import com.cashkaro.base.utils.SeleniumUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QuotePage {

    static final Logger logger = LogManager.getLogger(QuotePage.class.getName());

    String zincEnvironment = ".//*[@id='topButtonRow']/input[@title='CPQ Zinc']";
    String newQuoteButton = "//.[text()='Quotes']/ancestor::td/following-sibling::td/input[@value='New']";
    String quoteCreatePage = "//.[contains(text(),'Quote Summary Edit')]";
    String quoteNameValue = "//td[text()='Quote Name']/following-sibling::td";
    String quoteId = "//td[text()='Quote ID']/following-sibling::td";
    String backToOpportunity = "//a[contains(text(),'Back to Opportunity')]";
    String channelPartner = "Channel_Partner__c";
    String quoteNameField = "name";
    String effectiveDate = "expirationDate";
    String saveButton = "//input[@value='Save']";
    String quoteDetailsPage = "//.[contains(text(),'System Summary')]";


    public void quoteCreation() throws Exception {
        BrowserInteractions.click(zincEnvironment, LocatorType.XPATH);
        BrowserInteractions.switchFrame("itarget");
        BrowserInteractions.click(newQuoteButton, LocatorType.XPATH);
        BrowserInteractions.isElementPresent(quoteCreatePage, LocatorType.XPATH);
        BrowserInteractions.clearAndEnterText(quoteNameField, LocatorType.ID, "Test " + BrowserInteractions.getCurrentDate());
        BrowserInteractions.selectValueFromDropDown(channelPartner, DropdownSelectMethod.TEXT, "CHANNEL: AT&T Public Safety", LocatorType.ID);
        BrowserInteractions.clearAndEnterText(effectiveDate, LocatorType.ID, BrowserInteractions.getCurrentDateValue());
        BrowserInteractions.click(saveButton, LocatorType.XPATH);
        Boolean quoteDetails = BrowserInteractions.isElementPresent(quoteDetailsPage, LocatorType.XPATH);
        if (quoteDetails) {
            logger.info("Quote Created Successfully");
            String quoteName = BrowserInteractions.getText(quoteNameValue, LocatorType.XPATH);
            logger.info("Quote Name : " + quoteName);
            String quoteID = BrowserInteractions.getText(quoteId, LocatorType.XPATH);
            logger.info("Quote ID : " + quoteID);
        } else {
            logger.info("Quote is not saved successfully");
            SeleniumUtils.takeSnapshot();
            throw new Exception("Quote is not saved successfully");
        }
        BrowserInteractions.switchToDefaultFrame();

    }

    public void backToOpportunity() throws Exception {
        BrowserInteractions.click(backToOpportunity, LocatorType.XPATH);
    }
}

