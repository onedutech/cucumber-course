package edu.one.dojo.steps;

import org.openqa.selenium.support.PageFactory;

public class HomePageActions {
    HomePageLocators homePageLocators = null;

    public HomePageActions() {
        this.homePageLocators = new HomePageLocators();
        PageFactory.initElements(HelperClass.getDriver(), homePageLocators);
    }

    // Get the login status from Home Page
    public String getHomePageText() {
        return homePageLocators.status.getText();
    }
}
