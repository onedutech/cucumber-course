package edu.one.dojo.actions;

import edu.one.dojo.locators.HomePageLocators;
import edu.one.dojo.utils.HelperClass;
import org.openqa.selenium.support.PageFactory;

public class HomePageActions {
    HomePageLocators homePageLocators;

    public HomePageActions() {
        this.homePageLocators = new HomePageLocators();
        PageFactory.initElements(HelperClass.getDriver(), homePageLocators);
    }

    // Get the login status from Home Page
    public String getHomePageText() {
        return homePageLocators.status.getText();
    }
}
