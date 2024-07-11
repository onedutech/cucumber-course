package edu.one.dojo.actions;

import edu.one.dojo.locators.LoginPageLocators;
import edu.one.dojo.utils.HelperClass;
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


    public void login(String strUserName, String strPassword) {
        // Fill user name
        this.setUserName(strUserName);
        // Fill password
        this.setPassword(strPassword);
        // Click Login button
        this.clickLogin();
    }

}
