package com.odoo.aspire.page.login;

import com.odoo.aspire.data.AccountDataFactory;
import com.odoo.aspire.enums.page.Login;
import com.odoo.aspire.model.Account;
import com.odoo.aspire.page.base.BasePage;
import com.odoo.aspire.page.home.HomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LoginPage extends BasePage {
    @FindBy(css = "#login")
    private WebElement EMAIL_LOGIN_FIELD;

    @FindBy(css = "label[for='login']")
    private WebElement EMAIL_FIELD_LABEL;

    @FindBy(css = "#password")
    private WebElement PASSWORD_LOGIN_FIELD;

    @FindBy(css = "label[for='password']")
    private WebElement PASSWORD_FIELD_LABEL;

    @FindBy(css = "button[type='submit']")
    private WebElement LOGIN_BUTTON;

    @Step
    public void inputEmail(String email) {
        inputField(EMAIL_LOGIN_FIELD, email);
    }

    @Step
    public void inputPassword(String password) {
        inputField(PASSWORD_LOGIN_FIELD, password);
    }

    @Step
    public void clickOnLoginButton() {
        this.LOGIN_BUTTON.click();
    }

    @Step
    public void verifyLoginPageUI() {
        String expectedPageTitle = Login.TITLE.getValue();
        String expectedPageUrl = Login.URL.getValue();
        String expectedEmailFieldLabel = Login.EMAIL_FIELD_LABEL.getValue();
        String expectedPasswordFieldLabel = Login.PASSWORD_FIELD_LABEL.getValue();
        String expectedLoginButtonLabel = Login.LOGIN_BUTTON_LABEL.getValue();

        verifyField(EMAIL_FIELD_LABEL, expectedEmailFieldLabel);
        verifyField(PASSWORD_FIELD_LABEL, expectedPasswordFieldLabel);
        verifyField(LOGIN_BUTTON, expectedLoginButtonLabel);
        verifyPageTitle(expectedPageTitle);
        verifyPageUrl(expectedPageUrl);
    }

    @Step
    public HomePage login() {
        Account accountInformation = new AccountDataFactory().getAccountInfo();
        inputEmail(accountInformation.getEmail());
        inputPassword(accountInformation.getPassword());
        clickOnLoginButton();
        return new HomePage();
    }

    @Step
    public HomePage loginTest() {
        verifyLoginPageUI();
        Account accountInformation = new AccountDataFactory().getAccountInfo();
        inputEmail(accountInformation.getEmail());
        inputPassword(accountInformation.getPassword());
        clickOnLoginButton();
        return new HomePage();
    }

    @Step
    public void verifyLoginSuccessfully() {
        HomePage landingPage = loginTest();
        Assert.assertTrue(landingPage.landingPageIsDisplayed());
    }

}
