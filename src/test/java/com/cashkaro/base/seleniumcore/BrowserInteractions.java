package com.cashkaro.base.seleniumcore;

import com.cashkaro.base.FPXBaseTestSuite;
import com.cashkaro.base.utils.BrowserFactory;
import com.cashkaro.base.utils.DropdownSelectMethod;
import com.cashkaro.base.utils.LocatorType;
import com.cashkaro.base.utils.SeleniumUtils;

import com.google.common.base.Function;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class BrowserInteractions {

    static final Logger logger = LogManager.getLogger(BrowserInteractions.class.getName());
    static WebElement element;
    static WebElement element2;


    public static void navigateToURL(String URL) throws Exception {
        BrowserFactory.getCurrentBrowser().get(URL);
    }


    public static void click(String selector, LocatorType type) throws Exception {
        element = getElement(type, selector, true);
        element.click();
    }

    public static void clearAndEnterText(String selector, LocatorType type, String value) throws Exception {
        element = getElement(type, selector, true);
        element.clear();
        element.sendKeys(value);
    }

    public static void verifyElementPresence(String selector, LocatorType type) throws Exception {

        element = getElement(type, selector, true);
    }

    public static String getTextFromInputField(String selector, LocatorType type) throws Exception {
        element = getElement(type, selector, true);
        String domain_name = element.getAttribute("value");
        return domain_name;
    }

    public static String getInnerText(String selector, LocatorType type) throws Exception {
        element = getElement(type, selector, true);
        String domain_name = element.getAttribute("textContent");
        return domain_name;
    }

    public static void clickFromList(String value, LocatorType type, String page) throws Exception {
        String selector = "//div[@class='k-grid-content']//div[contains(text(),'" + value + "')]/ancestor::tr";
        element = getElement(type, selector, true);
        String dataId = element.getAttribute("data-uid");
        if (page.equals("Opportunity")) {
            String selector1 = "//div[@class='k-grid-content-locked']//tr[@data-uid='" + dataId + "']//span[contains(@class,'icon-info2')]";
            element = getElement(type, selector1, true);
            element.click();
        } else {
            String selector1 = "//div[@class='k-grid-content-locked']//tr[@data-uid='" + dataId + "']//input[contains(@class,'row-selection')]";
            element = getElement(type, selector1, true);
            element.click();
        }
    }

    public static void clickInfoFromList(String value, LocatorType type, String page) throws Exception {
        String selector = "//div[@class='k-grid-content']//div[contains(text(),'" + value + "')]/ancestor::tr";
        element = getElement(type, selector, true);
        String dataId = element.getAttribute("data-uid");
        if (page.equals("Opportunity")) {
            String selector1 = "//div[@class='k-grid-content-locked']//tr[@data-uid='" + dataId + "']//span[contains(@class,'icon-info2')]";
            element = getElement(type, selector1, true);
            element.click();
        } else {
            String selector1 = "//div[@class='k-grid-content-locked']//tr[@data-uid='" + dataId + "']//span[contains(@class,'objectPop')]";
            element = getElement(type, selector1, true);
            element.click();
        }
    }

    public static void selectValueFromDropDown(String selector, DropdownSelectMethod option, String optionValue, LocatorType type) throws Exception {

        WebElement dropDownListBox = getElement(type, selector, true);

        Select dropDownOption = new Select(dropDownListBox);

        switch (option) {
            case INDEX:
                dropDownOption.selectByIndex(Integer.parseInt(optionValue));
                break;
            case VALUE:
                dropDownOption.selectByValue(optionValue);
                break;
            case TEXT:
                dropDownOption.selectByVisibleText(optionValue);
                break;
            default:
                break;
        }
    }

    public static void isEditable(String selector, LocatorType type) throws Exception {
        element = getElement(type, selector, true);
        element.isEnabled();
    }

    public static String getText(String selector, LocatorType type) throws Exception {
        element = getElement(type, selector, true);
        String text = element.getText();
        return text;
    }

    public static List getList(String selector) throws Exception {
        WebDriver driver = BrowserFactory.getCurrentBrowser();
        List<WebElement> labels = driver.findElements(By.xpath("//div[contains(@class," + selector + ")]"));
        return labels;
    }

    public static int intCount(String selector, LocatorType type) throws Exception {
        element = getElement(type, selector, true);
        String count = element.getText();
        System.out.println("pitm" + count);
        return Integer.parseInt(count);
    }

    public static void pageRefresh() throws Exception {
        BrowserFactory.getCurrentBrowser().navigate().refresh();
    }

    public static boolean isElementPresent(String selector, LocatorType type) throws Exception {
        try {
            element = getElement(type, selector, true);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean isElementNotPresent(String selector) throws Exception {
        WebDriver driver = BrowserFactory.getCurrentBrowser();
        int size = driver.findElements(By.xpath(selector)).size();
        logger.info("size value :  " + size);
        if (size == 1)
            return true;
        else {
            return false;
        }
    }

    public static void dragAndDrop(String fromSelector, String toSelector, LocatorType type) throws Exception {
        element = getElement(type, fromSelector, true);
        element2 = getElement(type, toSelector, true);
        dragAndDrop(element, element2);
    }

    public static void dragAndDrop(WebElement sourceElement, WebElement destinationElement) {
        try {
            WebDriver driver = BrowserFactory.getCurrentBrowser();
            if (sourceElement.isDisplayed() && destinationElement.isDisplayed()) {
                Actions action = new Actions(driver);
                logger.info("Driver value" + driver);
                action.dragAndDrop(sourceElement, destinationElement).build().perform();
            } else {
                System.out.println("Element was not displayed to drag");
            }
        } catch (StaleElementReferenceException e) {
            System.out.println("Element with " + sourceElement + "or" + destinationElement + "is not attached to the page document "
                    + e.getStackTrace());
        } catch (NoSuchElementException e) {
            System.out.println("Element " + sourceElement + "or" + destinationElement + " was not found in DOM " + e.getStackTrace());
        } catch (Exception e) {
            System.out.println("Error occurred while performing drag and drop operation " + e.getStackTrace());
        }
    }

    public static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        //get current date time with Date()
        Date date = new Date();
        //System.out.println(dateFormat.format(date)); don't print it, but save it!
        return dateFormat.format(date);
    }

    public static String getCurrentDateValue() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        //get current date time with Date()
        Date date = new Date();
        //System.out.println(dateFormat.format(date)); don't print it, but save it!
        return dateFormat.format(date);
    }

    public static Properties propertyFileRead() {
        Properties prop = new Properties();
        InputStream input;
        try {
            input = new FileInputStream(FPXBaseTestSuite.propertyFileName);
            // load a properties file
            prop.load(input);
            // get the property value and print it out
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
    }

    public static void alertAccept() throws Exception {
        WebDriver driver = BrowserFactory.getCurrentBrowser();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public static void switchFrame(String frameId) throws Exception {
        WebDriver driver = BrowserFactory.getCurrentBrowser();
        driver.switchTo().defaultContent();
        driver.switchTo().frame(frameId);

    }
    public static void switchToChildWindow() throws Exception {
        WebDriver driver = BrowserFactory.getCurrentBrowser();
        String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
        String subWindowHandler = null;

        Set<String> handles = driver.getWindowHandles(); // get all window handles
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()){
            subWindowHandler = iterator.next();
        }
        driver.switchTo().window(subWindowHandler);

    }

    public static void switchToDefaultFrame() throws Exception {
        WebDriver driver = BrowserFactory.getCurrentBrowser();
        driver.switchTo().defaultContent();
    }

    public static WebElement getElement(LocatorType type, String selector, boolean checkForPresence) throws Exception {

        By by;
        switch (type) {
            case XPATH:
                by = By.xpath(selector);
                break;
            case ID:
                by = By.id(selector);
                break;
            case CSSSELECTOR:
                by = By.cssSelector(selector);
                break;
            case NAME:
                by = By.name(selector);
                break;
            case CLASSNAME:
                by = By.className(selector);
                break;
            case LINKTEXT:
                by = By.linkText(selector);
                break;
            case PARTIALLINKTEXT:
                by = By.partialLinkText(selector);
                break;
            case TAGNAME:
                by = By.tagName(selector);
                break;
            default:
                logger.info("Unknown Locator type");
                throw new Exception("Unknown locator type");
        }

        try {
            if (checkForPresence) {
                element = waitForElementPresence(by);
                SeleniumUtils.highlightElement(element);
            }
        } catch (Exception e) {
            SeleniumUtils.takeSnapshot();
            logger.info("Element not visible in the page" + "with locator: " + selector + " Type used:" + type.name());
            throw new Exception("Element not visible in the page" + "with locator: " + selector + " Type used:" + type.name());
        }
        return element;
    }

    private static WebElement waitForElementPresence(final By by) {
        WebDriver driver = BrowserFactory.getCurrentBrowser();

        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(60, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);


        return wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(by);

            }
        });
    }

       public static void threadSleep(int value) throws InterruptedException {
        Thread.sleep(value);
    }

    public static void waitUntilElementIsHidden(String selector, LocatorType type, boolean checkForPresence) {
        WebDriver browser = BrowserFactory.getCurrentBrowser();

        try {
            element = getElement(type, selector, checkForPresence);
            FluentWait<WebDriver> wait = new FluentWait<WebDriver>(browser)
                    .withTimeout(45, TimeUnit.SECONDS)
                    .pollingEvery(2, TimeUnit.MILLISECONDS)
                    .ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.invisibilityOfElementLocated((By) element));
        } catch (Exception e) {
            //Do nothing-suppress exception and let the above methods handle it
        }
    }

    public static void implicitwait() {
        BrowserFactory.getCurrentBrowser().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

}







