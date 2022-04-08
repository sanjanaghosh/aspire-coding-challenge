package com.odoo.aspire.test;

import com.odoo.aspire.config.AppConfigManager;
import com.odoo.aspire.driver.BrowserFactory;
import com.odoo.aspire.driver.DriverManager;
import com.odoo.aspire.model.Product;
import com.odoo.aspire.page.inventory.main.InventoryPage;
import com.odoo.aspire.page.inventory.products.CreateProductPage;
import com.odoo.aspire.page.inventory.products.ProductsPage;
import com.odoo.aspire.page.home.HomePage;
import com.odoo.aspire.page.login.LoginPage;
import com.odoo.aspire.page.manufacturing.main.ManufacturingPage;
import com.odoo.aspire.page.manufacturing.orders.CreateOrdersPage;
import com.odoo.aspire.reports.TestListener;
import com.odoo.aspire.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

@Listeners({TestListener.class})
public class EndToEndFlow {
    LoginPage loginPage;
    HomePage homePage;
    InventoryPage inventoryPage;
    ProductsPage productsPage;
    CreateProductPage createProductPage;
    Product product;
    ManufacturingPage manufacturingPage;
    CreateOrdersPage createOrdersPage;

    @BeforeSuite
    @Parameters("browser")
    public void beforeSuite(@Optional("chrome") String browser) {
        Utilities.setAllureEnvironmentInformation();
        WebDriver driver = BrowserFactory.valueOf(browser.toUpperCase()).createDriver();
        DriverManager.setDriver(driver);
        DriverManager.getDriver().get(AppConfigManager.configuration().url());
    }

    @Test(description = "Login into app and landing on the home page")
    public void loginToOdooPage(){
        loginPage = new LoginPage();
        homePage = loginPage.login();
    }

    @Test(description = "Navigate To Inventory Page", priority = 1)
    public void navigateToInventoryPage() {
        inventoryPage = homePage.redirectToInventoryPage();
    }

    @Test(description = "Navigate To Products Page", priority = 2)
    public void navigateToProductsPage() {
        productsPage = inventoryPage.redirectToProductsPage();
    }

    @Test(description = "Navigate To create product Page", priority = 3)
    public void navigateToCreteProductPage() {
        createProductPage = productsPage.redirectToCreateProductPage();
        createProductPage.createProductPageIsDisplayed();
    }

    @Test(description = "Create new product", priority = 4)
    public void createNewProduct() {
        product = createProductPage.getProductInformation();
        createProductPage.createNewProductTest(product);
    }

    @Test(description = "return to home page", priority = 5)
    public void returnToHomePage() {
        homePage = createProductPage.clickOnHomeMenu();
    }

    @Test(description = "Navigate To Manufacturing Page", priority = 6)
    public void navigateToManufacturingPage() {
        manufacturingPage = homePage.redirectToManufacturingPage();
    }

    @Test(description = "Navigate To create order Page", priority = 7)
    public void navigateToCreateOrderPage() {
        createOrdersPage = manufacturingPage.redirectToCreateOrdersPage();
    }

    @Test(description = "Create New Order Test", priority = 8)
    public void createNewOrderTest() {
        createOrdersPage.createNewOrderTest(product);
    }

    @AfterSuite
    public void postCondition() {
        DriverManager.quit();
    }
}
