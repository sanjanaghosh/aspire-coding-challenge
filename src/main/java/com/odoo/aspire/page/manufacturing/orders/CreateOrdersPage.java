package com.odoo.aspire.page.manufacturing.orders;

import com.odoo.aspire.enums.data.OrderStatus;
import com.odoo.aspire.model.Product;
import com.odoo.aspire.page.base.BasePage;
import com.odoo.aspire.page.inventory.products.CreateProductPage;
import com.odoo.aspire.page.inventory.main.InventoryPage;
import com.odoo.aspire.page.inventory.products.ProductsPage;
import com.odoo.aspire.page.home.HomePage;
import com.odoo.aspire.page.login.LoginPage;
import com.odoo.aspire.page.manufacturing.main.ManufacturingPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CreateOrdersPage extends BasePage {
    @FindBy(css = "button[title='Save record']")
    private WebElement SAVE_BUTTON;

    @FindBy(css = "button[title='Discard record']")
    private WebElement DISCARD_BUTTON;

    @FindBy(css = "button[name=action_confirm]")
    private WebElement CONFIRM_BUTTON;

    @FindBy(xpath = "//label[text()='Product']/parent::td/following-sibling::td//input")
    private WebElement PRODUCT_DROPDOWN;

    @FindBy(css = "td[name='product_id'] input")
    private WebElement PRODUCT_LINE_DROPDOWN;

    @FindBy(xpath = "//a[text()='Manufacturing Orders']")
    private WebElement MANUFACTURING_ORDERS_BREADCRUMB;

    @FindBy(xpath = "//span[text()='Mark as Done']/parent::button[@class='btn btn-primary']")
    private WebElement MARK_AS_DONE_BUTTON;

    @FindBy(xpath = "//div[@name='state']/button[@title='Current state']")
    private WebElement CURRENT_STATUS;

    @FindBy(css = "a[name='product_id'] span")
    private WebElement PRODUCT_NAME;

    @Step
    public void clickOnConfirmButton() {
        clickOnElement(CONFIRM_BUTTON);
    }

    @Step
    public void clickOnSaveButton() {
        clickOnElement(SAVE_BUTTON);
    }

    @Step
    public void selectProduct(String productName) {
        String xpath = "//ul[contains(@style, 'display: block')]/li[@class='ui-menu-item']/a[text()='%s']";
        inputAutocompleteDropdown(PRODUCT_DROPDOWN, xpath, productName);
    }

    @Step
    public void clickOnMarkAsDoneButton() {
        clickOnElement(MARK_AS_DONE_BUTTON);
        acceptFirstModal();
    }

    @Step
    public void verifyTheCurrentState(String status) {
        sleep(2);
        String currentStatus = getTextFromElement(CURRENT_STATUS);
        Assert.assertEquals(currentStatus, status);
    }

    @Step
    public void openCreateOrderPage(Product product){
        LoginPage loginPage = new LoginPage();
        HomePage landingPage = loginPage.login();
        InventoryPage inventoryPage = landingPage.redirectToInventoryPage();
        ProductsPage productsPage = inventoryPage.redirectToProductsPage();
        CreateProductPage createProductPage = productsPage.redirectToCreateProductPage();
        createProductPage.createProductPageIsDisplayed();
        createProductPage.createNewProduct(product);
        landingPage = createProductPage.clickOnHomeMenu();
        ManufacturingPage manufacturingPage = landingPage.redirectToManufacturingPage();
        manufacturingPage.redirectToCreateOrdersPage();
    }

    @Step
    public void createNewOrderTest(Product productInformation) {
        String productName = productInformation.getProductName();
        selectProduct(productName);
        clickOnSaveButton();
        verifyTheCurrentState(OrderStatus.DRAFT.getValue());
        clickOnConfirmButton();
        verifyTheCurrentState(OrderStatus.CONFIRMED.getValue());
        clickOnMarkAsDoneButton();
        verifyTheCurrentState(OrderStatus.DONE.getValue());

        String actualOrderProductName = getTextFromElement(PRODUCT_NAME);
        Assert.assertEquals(actualOrderProductName, productName);

    }

}
