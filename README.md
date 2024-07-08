Pour créer un tutoriel d'implémentation en se basant sur les classes mentionnées, nous allons supposer que ces classes
sont destinées à l'automatisation de tests avec Cucumber et Selenium en Java. Voici un guide détaillé pour mettre en
place un projet de test Cucumber avec ces classes.

### Prérequis

Avant de commencer, assurez-vous d'avoir les outils suivants installés sur votre machine :

1. **Java Development Kit (JDK)**
2. **Maven**
3. **Un IDE comme IntelliJ IDEA ou Eclipse**

### Étape 1 : Configuration du projet Maven

Créez un projet Maven. Utilisez l'arborescence suivante :

```
MyCucumberProject
|-- pom.xml
|-- src
    |-- main
    |   |-- java
    |   |   |-- utils
    |   |   |   |-- HelperClass.java
    |   |-- resources
    |-- test
        |-- java
            |-- steps
            |   |-- CommonSteps.java
            |   |-- LoginSteps.java
            |-- actions
            |   |-- HomePageActions.java
            |   |-- LoginPageActions.java
            |-- locators
            |   |-- HomePageLocators.java
            |   |-- LoginPageLocators.java
            |-- runner
                |-- RunCucumberTest.java
```

### Étape 2 : Configuration du `pom.xml`

Ajoutez les dépendances nécessaires pour Cucumber, Selenium et JUnit dans votre `pom.xml` :

```xml

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.example</groupId>
    <artifactId>MyCucumberProject</artifactId>
    <version>1.0-SNAPSHOT</version>

    <dependencies>
        <!-- Cucumber dependencies -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>7.2.3</version>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit</artifactId>
            <version>7.2.3</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-spring</artifactId>
            <version>7.2.3</version>
        </dependency>

        <!-- Selenium dependencies -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>4.1.0</version>
        </dependency>

        <!-- JUnit dependencies -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.2</version>
            <scope>test</scope>
        </dependency>

        <!-- Additional dependencies can be added here -->
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>11</source>
                    <target>11</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.22.2</version>
                <configuration>
                    <includes>
                        <include>**/RunCucumberTest.java</include>
                    </includes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

### Étape 3 : Création des classes d'actions et de locators

**HomePageLocators.java**

```java
package locators;

import org.openqa.selenium.By;

public class HomePageLocators {
    public static final By LOGIN_BUTTON = By.id("loginButton");
    public static final By WELCOME_MESSAGE = By.id("welcomeMessage");
}
```

**LoginPageLocators.java**

```java
package locators;

import org.openqa.selenium.By;

public class LoginPageLocators {
    public static final By USERNAME_FIELD = By.id("username");
    public static final By PASSWORD_FIELD = By.id("password");
    public static final By SUBMIT_BUTTON = By.id("submit");
}
```

**HomePageActions.java**

```java
package actions;

import locators.HomePageLocators;
import org.openqa.selenium.WebDriver;

public class HomePageActions {
    WebDriver driver;

    public HomePageActions(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLoginButton() {
        driver.findElement(HomePageLocators.LOGIN_BUTTON).click();
    }

    public String getWelcomeMessage() {
        return driver.findElement(HomePageLocators.WELCOME_MESSAGE).getText();
    }
}
```

**LoginPageActions.java**

```java
package actions;

import locators.LoginPageLocators;
import org.openqa.selenium.WebDriver;

public class LoginPageActions {
    WebDriver driver;

    public LoginPageActions(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(LoginPageLocators.USERNAME_FIELD).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(LoginPageLocators.PASSWORD_FIELD).sendKeys(password);
    }

    public void clickSubmitButton() {
        driver.findElement(LoginPageLocators.SUBMIT_BUTTON).click();
    }
}
```

### Étape 4 : Création des classes de steps

**CommonSteps.java**

```java
package steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CommonSteps {
    protected static WebDriver driver;

    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
```

**LoginSteps.java**

```java
package steps;

import actions.HomePageActions;
import actions.LoginPageActions;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class LoginSteps extends CommonSteps {

    private HomePageActions homePageActions;
    private LoginPageActions loginPageActions;

    @Before
    public void setUpScenario() {
        setUp();
        homePageActions = new HomePageActions(driver);
        loginPageActions = new LoginPageActions(driver);
    }

    @After
    public void tearDownScenario() {
        tearDown();
    }

    @Given("I am on the home page")
    public void iAmOnTheHomePage() {
        driver.get("http://example.com");
    }

    @When("I navigate to the login page")
    public void iNavigateToTheLoginPage() {
        homePageActions.clickLoginButton();
    }

    @When("I enter username {string} and password {string}")
    public void iEnterUsernameAndPassword(String username, String password) {
        loginPageActions.enterUsername(username);
        loginPageActions.enterPassword(password);
    }

    @When("I submit the login form")
    public void iSubmitTheLoginForm() {
        loginPageActions.clickSubmitButton();
    }

    @Then("I should see the welcome message {string}")
    public void iShouldSeeTheWelcomeMessage(String expectedMessage) {
        String actualMessage = homePageActions.getWelcomeMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}
```

### Étape 5 : Création de la classe `RunCucumberTest.java`

```java
package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "steps"
)
public class RunCucumberTest {
}
```

### Étape 6 : Création des fichiers de fonctionnalités Cucumber

Créez un fichier `login.feature` sous `src/test/resources/features` :

```gherkin
Feature: Login

  Scenario: Successful login
    Given I am on the home page
    When I navigate to the login page
    And I enter username "user1" and password "password1"
    And I submit the login form
    Then I should see the welcome message "Welcome user1"
```

### Conclusion

Vous avez maintenant un projet de test Cucumber et Selenium configuré avec Maven. Vous pouvez exécuter vos tests en
exécutant la classe `RunCucumberTest`. Ce tutoriel vous donne une structure de base que vous pouvez étendre en ajoutant
plus de scénarios, d'actions et de locators selon vos besoins.