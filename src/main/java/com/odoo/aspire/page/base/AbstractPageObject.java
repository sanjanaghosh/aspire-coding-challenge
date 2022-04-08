package com.odoo.aspire.page.base;

import com.odoo.aspire.driver.DriverManager;
import com.odoo.aspire.config.AppConfigManager;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static org.openqa.selenium.support.PageFactory.initElements;

public class AbstractPageObject {

    protected AbstractPageObject() {
        initElements(new AjaxElementLocatorFactory(DriverManager.getDriver(), AppConfigManager.configuration().timeout()), this);
    }
}
