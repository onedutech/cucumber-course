package edu.one.dojo.steps;

import org.openqa.selenium.support.PageFactory;

public class LoginPageActions {
    LoginPageLocators loginPageLocators = null;

    public LoginPageActions() {
        this.loginPageLocators = new LoginPageLocators();
        PageFactory.initElements(HelperClass.getDriver(), loginPageLocators);
    }

    // Set username in username textbox
    public void setUserName(String strUserName) {
        this.loginPageLocators.username.sendKeys(strUserName);
    }

    // Set password in password textbox
    public void setPassword(String strPassword) {
        this.loginPageLocators.password.sendKeys(strPassword);
    }

    // Click on login button
    public void clickLogin() {
        this.loginPageLocators.login.click();
    }


    // Get the error message when username is blank
    public String getMissingUsernameText() {
        return this.loginPageLocators.missingUsernameErrorMessage.getText();
    }

    // Get the Error Message
    public String getErrorMessage() {
        return this.loginPageLocators.errorMessage.getText();
    }

    public void login(String strUserName, String strPassword) {

        // Fill user name
        this.setUserName(strUserName);

        // Fill password
        this.setPassword(strPassword);

        // Click Login button
        this.clickLogin();

    }

}
