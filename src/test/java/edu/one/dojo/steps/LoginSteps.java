package edu.one.dojo.steps;

import edu.one.dojo.actions.HomePageActions;
import edu.one.dojo.actions.LoginPageActions;
import edu.one.dojo.utils.HelperClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class LoginSteps {

    // private final WebDriver driver = new ChromeDriver();
    private final LoginPageActions loginPageActions = new LoginPageActions();
    private final HomePageActions homePageActions = new HomePageActions();

    @Given("user navigates to {string} by opening Chrome")
    public void user_navigates_to_login_page_by_opening_chrome(String page) {
        HelperClass.openPage(page);
//        driver.get(page);
    }

    @When("user enters correct {string} AND {string} values")
    public void userEntersCorrectANDValues(String username, String password) {
        //HelperClass.takeStepScreenshot("screenshot", "loginpage");
        loginPageActions.login(username, password);
        //driver.findElement(By.id("username")).sendKeys(username);
        //driver.findElement(By.id("password")).sendKeys(password);
        //driver.findElement(By.xpath("/html/body/div/form/button")).click();
    }

    @Then("user is directed to the homepage")
    public void user_is_directed_to_the_homepage() {
        //Assertions.assertEquals(driver.findElement(By.id("status")).getText(), "Login success");
        //HelperClass.takeStepScreenshot("screenshot", "homepage");
        Assertions.assertEquals(homePageActions.getHomePageText(), "Login success");

    }

}
