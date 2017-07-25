package com.cashkaro.Pages;

import com.cashkaro.base.seleniumcore.BrowserInteractions;
import com.cashkaro.base.utils.DropdownSelectMethod;
import com.cashkaro.base.utils.LocatorType;
import com.cashkaro.base.utils.SeleniumUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Opportunity {
    static final Logger logger = LogManager.getLogger(Opportunity.class.getName());

    String opportunitySearch = "sen";
    String opportunitySearchTextBox = "sbstr";
    String opportunitySearchButton = ".//*[@id='sbsearch']//input[@type='submit']";
    String opportunityNameValue = "" + com.cashkaro.base.seleniumcore.BrowserInteractions.propertyFileRead().getProperty("OpportunityName") + "";
    String opportunityText = ".//*[@id='ep']/div[2]/div[3]/table/tbody/tr//td[text()='" + com.cashkaro.base.seleniumcore.BrowserInteractions.propertyFileRead().getProperty("OpportunityName") + "']";
    String opportunityTitle = "//.[contains(text(),'Opportunity Detail')]";
    String opportunityDetails = "//h2[contains(text(),'" + BrowserInteractions.propertyFileRead().getProperty("OpportunityName") + "')]";
    String opportunityPageVerify = "//.[contains(text(),'Opportunity')]";
    String summary_opportunityName = ".//*[@id='ep']/div[2]/div[3]/table/tbody/tr/td[text()='Opportunity Name']/following-sibling::td";


    public void opportunitySearch() throws Exception {
        BrowserInteractions.selectValueFromDropDown(opportunitySearch, DropdownSelectMethod.TEXT, "Opportunities", LocatorType.ID);
        BrowserInteractions.clearAndEnterText(opportunitySearchTextBox, LocatorType.ID, opportunityNameValue);
        BrowserInteractions.click(opportunitySearchButton, LocatorType.XPATH);
        BrowserInteractions.click(opportunityTitle, LocatorType.XPATH);
        BrowserInteractions.verifyElementPresence(opportunityText, LocatorType.XPATH);
/*        BrowserInteractions.click(opportunityText, LocatorType.XPATH);
        BrowserInteractions.verifyElementPresence(opportunityDetails, LocatorType.XPATH);*/
    }

    public void opportunityValidation() throws Exception {
        BrowserInteractions.verifyElementPresence(opportunityTitle, LocatorType.XPATH);
        String opportunityName = BrowserInteractions.getText(summary_opportunityName, LocatorType.XPATH);
        logger.info("Opportunity Name:  " + opportunityName);
        if (opportunityName.contains(opportunityNameValue)) {
            logger.info("Got the search result for the Opportunity");
        } else {
            logger.info("Doesn't got the opportunity result");
            SeleniumUtils.takeSnapshot();
            throw new Exception("Doesn't got the opportunity result");
        }
    }


}
