package com.odoo.aspire.page.inventory.products;

import com.odoo.aspire.page.base.BasePage;
import com.odoo.aspire.page.inventory.main.InventoryPage;
import com.odoo.aspire.page.home.HomePage;
import com.odoo.aspire.page.login.LoginPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ProductsPage extends BasePage {
    @FindBy(css = "a[title='Home menu']")
    private WebElement HOME_MENU;

    @FindBy(xpath = "//li//span[text()='Products']")
    private WebElement PRODUCT_HEADER;

    @FindBy(css = "button[title='Create record']")
    private WebElement CREATE_NEW_PRODUCT_BUTTON;

    @Step
    public boolean productsPageIsDisplayed() {
        try {
            Assert.assertTrue(verifyElementIsDisplayed(HOME_MENU));
            Assert.assertTrue(verifyElementIsDisplayed(PRODUCT_HEADER));
            Assert.assertTrue(verifyElementIsDisplayed(CREATE_NEW_PRODUCT_BUTTON));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Step
    public void clickOnCreateButton() {
        CREATE_NEW_PRODUCT_BUTTON.click();
    }

    @Step
    public CreateProductPage redirectToCreateProductPage() {
        clickOnCreateButton();
        return new CreateProductPage();
    }

    @Step
    public void openProductsPage() {
        LoginPage loginPage = new LoginPage();
        HomePage landingPage = loginPage.login();
        InventoryPage inventoryPage = landingPage.redirectToInventoryPage();
        inventoryPage.redirectToProductsPage();
        inventoryPage.verifyProductsPageDisplayed();
    }
}
