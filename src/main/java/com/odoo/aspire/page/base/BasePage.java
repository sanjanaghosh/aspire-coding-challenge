package com.odoo.aspire.page.base;

import com.odoo.aspire.config.AppConfigManager;
import com.odoo.aspire.driver.DriverManager;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class BasePage extends AbstractPageObject {
    public static final Logger logger = LogManager.getLogger(BasePage.class);
    private final WebDriver driver = DriverManager.getDriver();

    @Step
    public void inputField(WebElement element, String value) {
        logger.info(String.format("inputField: \n %s", value, element));
        element.click();
        element.clear();
        element.sendKeys(value);
    }

    @Step
    public void inputAutocompleteDropdown(WebElement element, String xpathItem, String value) {
        logger.info(String.format("inputAutocompleteDropdown: \n %s", value, element));
        element.click();
        sleep(2);
        element.clear();
        element.sendKeys(value);
        handleElementByCustomValue(xpathItem, value).click();
    }

    @Step
    public String getCurrentPageTitle() {
        String currentPageTitle = driver.getTitle().trim();
        logger.info(String.format("getCurrentPageTitle: %s", currentPageTitle));
        return currentPageTitle;
    }

    @Step
    public String getTextFromElement(WebElement element) {
        String value = element.getText().trim();
        logger.info(String.format("getTextFromElement: %s", element, value));
        return value;
    }

    @Step
    public String getCurrentPageUrl() {
        String currentUrl = driver.getCurrentUrl();
        logger.info(String.format("getCurrentPageUrl: %s", currentUrl));
        return currentUrl;
    }

    @Step
    public void verifyField(WebElement element, String expectedResult) {
        String actualResult = getTextFromElement(element);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Step
    public void verifyPageTitle(String expectedResult) {
        String actualResult = getCurrentPageTitle();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Step
    public void verifyPageUrl(String expectedResult) {
        String actualResult = getCurrentPageUrl();
        Assert.assertTrue(actualResult.contains(expectedResult));
    }

    @Step
    public boolean verifyElementIsDisplayed(WebElement element) {
        waitForElementVisible(element);
        return element.isDisplayed();
    }

    public WebElement waitForElementVisible(WebElement element) {
        Duration duration = Duration.ofSeconds(AppConfigManager.configuration().timeout());
        WebDriverWait wait = new WebDriverWait(driver, duration);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementVisible(By by) {
        Duration duration = Duration.ofSeconds(AppConfigManager.configuration().timeout());
        WebDriverWait wait = new WebDriverWait(driver, duration);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    @Step
    public void selectItemFromDropdown(WebElement element, String item) {
        logger.info(String.format("selectItemFromDropdown:\n %s", item, element));
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(item);
    }

    @Step
    public void clickOnElement(WebElement element) {
        logger.info(String.format("clickOnElement: \n %s", element));
        waitForElementVisible(element);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }

    public void acceptFirstModal() {
        try {
            WebElement ok = driver.switchTo().activeElement().findElement(By.xpath("/html[1]/body[1]/div[2]/div[5]/div[1]/div[1]/div[1]/footer[1]/button[1]"));
            sleep(5);
            ok.click();
            logger.info("OK button is clicked in pop-up window");
        } catch (NoSuchElementException noSuchElementException){
                logger.error("Unable to click Ok :: " + noSuchElementException.getMessage());
        }
        try{
            WebElement apply = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[5]/div[1]/div[1]/div[1]/div[1]/footer[1]/div[1]/footer[1]/button[1]"));
            sleep(5);
            apply.click();
            logger.info("Apply button is clicked in pop-up window");
        }catch (NoSuchElementException noSuchElementException){
            logger.error("Unable to click Apply :: " + noSuchElementException.getMessage());
        }
    }

    public WebElement handleElementByCustomValue(String xpath, String value) {
        xpath = String.format(xpath, value);
        By by = By.xpath(xpath);
        return waitForElementVisible(by);
    }

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
