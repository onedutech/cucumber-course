# Tutoriel de prise en main de Cucumber avec Selenium

**[Cucumber](https://cucumber.io/)** est un framework de tests pour le **B**ehavior **D**riven **D**evelopment,
initialement développé en Ruby, mais proposant
aujourd'hui [différentes implémentations pour de nombreux autres langages de programmation](https://docs.cucumber.io/installation).
Le site de référence est: **[cucumber.io](https://cucumber.io)**.

Dans l'écosystème Java, [Cucumber](https://cucumber.io/) est aujourd'hui un des frameworks BDD les plus utilisés.

Dans ce tutoriel, nous verrons comment implémenter selenium avec cucumber pour pouvoir tester les fonctionnalités des
pages web.

### Prérequis

Avant de commencer, assurez-vous d'avoir les outils suivants installés sur votre machine :

1. **Java Development Kit (JDK)**
2. **Maven**
3. **Un IDE comme IntelliJ IDEA ou Eclipse**

### Configuration du projet Maven

Créez un projet Maven. Utilisez l'arborescence suivante :

```

|-- pom.xml
|-- src
    |-- main
        |-- java
        |-- resources
    |-- test
        |-- java
            |-- utils
            |   |-- HelperClass.java
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
        |-- resources                
                |-- login.feature        
```

### Configuration du `pom.xml`

Ajoutez les dépendances nécessaires pour Cucumber, Selenium et JUnit dans votre `pom.xml` :

```XML

<dependencies>
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-java</artifactId>
        <version>7.18.0</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-junit</artifactId>
        <version>7.18.0</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.10.2</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.21.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

### Application à tester

Dans le cadre de ce tutoriel, l'application témoin qui sera testée est à récupérer depuis le
lien [Spring Login]( https://github.com/onedutech/springlogin)

### Création des fichiers de fonctionnalités Cucumber

Créez un fichier `login.feature` sous `src/test/resources/features` :

```gherkin
Feature: Access home page
  As a user, I want to access home page.

  @login
  Scenario Outline: Authentication
    Given user navigates to "<home_page>" by opening Chrome
    When user enters correct "<username>" AND "<password>" values
    Then user is directed to the homepage

    Examples:
      | home_page                       | username | password |
      | http://localhost:8080/home.html | login    | pass     |
```

### Création de la classe `RunCucumberTest.java`

```java
package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/edu/one/dojo/features",
        glue = "edu.one.dojo.steps",
        plugin = {"json:target/cucumber.json", "pretty", "html:target/site/cucumber.html"}
)
public class RunCucumberTest {
}
```

### Création des classes de steps

**LoginSteps.java** : Cette classe contient les étapes de test relatives au fichier de feature.

```java
package steps;

public class LoginSteps {

    private WebDriver driver = new ChromeDriver();

    @Given("user navigates to {string} by opening Chrome")
    public void user_navigates_to_login_page_by_opening_chrome(String page) {
        driver.get(page);
    }

    @When("user enters correct {string} AND {string} values")
    public void userEntersCorrectANDValues(String username, String password) {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.xpath("/html/body/div/form/button")).click();
    }

    @Then("user is directed to the homepage")
    public void user_is_directed_to_the_homepage() {
        Assertions.assertEquals(driver.findElement(By.id("status")).getText(), "Login success");
    }
}

```

-----------------

## Framework selenium – Page Object Model et Page Factory

### Page Object Model (POM)

Le Page Object Model (POM) est un modèle de conception pour les tests d'automatisation qui crée une couche d'abstraction
entre les pages web de l'application et les tests automatisés. En utilisant le POM, chaque page de l'application est
représentée par une classe distincte. Cette classe contient les
localisateurs pour différents éléments Web (comme un bouton, un champ de texte, une liste déroulante, etc.) présente sur
la page et les méthodes pour effectuer des actions sur ces éléments.

### Pourquoi utiliser le Page Object Model ?

- **Réutilisabilité**: Les localisateurs sont définis en un seul endroit, ce qui facilite leur réutilisation dans
  différents tests.
- **Maintenance facile**: Si un localisateur change, vous n'avez qu'à le mettre à jour dans la classe des localisateurs,
  plutôt que dans chaque script de test.
- **Séparation des préoccupations**: Les localisateurs (représentant la structure de la page) sont séparés des actions
  et des assertions (représentant la logique du test), ce qui rend le code plus propre et mieux organisé.

#### Utilisation de l’annotation @FindBy

Contrairement à l’approche habituelle d’initialisation des éléments de page Web à l’aide de FindElement ou
FindElements, Page Factory utilise l’ annotation `@FindBy` . Les annotations utilisées dans Page Factory sont
descriptives. De plus, ils aident à améliorer la lisibilité du code.

```java
@FindBy(id = "username")
public WebElement username;
```

### Création des classes de locators

**HomePageLocators.java**

```java
package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageLocators {

    @FindBy(id = "status")
    public WebElement status;

}
```

**LoginPageLocators.java**

```java
package locators;

public class LoginPageLocators {

    @FindBy(id = "username")
    public WebElement username;

    @FindBy(id = "password")
    public WebElement password;

    @FindBy(xpath = "/html/body/div/form/button")
    public WebElement login;


}
```

### Page Factory

Page Factory est une classe fournie par Selenium WebDriver pour implémenter le modèle d’objet de page. Le référentiel
d’objets de page est séparé des méthodes de test à l’aide du concept Page Factory. En l’utilisant, vous pouvez
initialiser les objets de page (POM) ou les instancier directement.
En termes simples, le modèle d’objet de page vous permet
de créer des classes Java distinctes pour différentes pages d’un site Web. Ces différentes classes contiennent les
localisateurs pour différents éléments Web (comme un bouton, un champ de texte, une liste déroulante, etc.) présente sur
la page et les méthodes pour effectuer des actions sur ces éléments. Ce faisant, vous pouvez simplifier votre code et
séparer les méthodes de test et le référentiel d’objets.

#### Initialisation des éléments à l’aide de initElements

Il s’agit d’une méthode statique utilisée pour initialiser les éléments Web que nous localisons à l’aide de `@FindBy` ou
d’autres annotations, instanciant ainsi la classe de page.

```java

PageFactory.initElements(WebDriver driver,PageObjectClass pageObject);

```

### Création des classes d'actions

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

### Création des classes de steps

**CommonSteps.java** : Cette classe permettra de définir les hooks de test unitaire

```java
package steps;

public class CommonSteps {

    @Before
    public void setup() {
        HelperClass.setUpDriver();
    }

    @After
    public void tearDown() {
        HelperClass.tearDown();
    }

}
```

**LoginSteps.java** : Cette classe contient les étapes de test relatives au fichier de feature/

```java
package steps;

public class LoginSteps {

    LoginPageActions loginPageActions = new LoginPageActions();
    HomePageActions homePageActions = new HomePageActions();

    @Given("user navigates to {string} by opening Chrome")
    public void user_navigates_to_login_page_by_opening_chrome(String page) {
        HelperClass.openPage(page);
    }

    @When("user enters correct {string} AND {string} values")
    public void userEntersCorrectANDValues(String username, String password) {
        loginPageActions.login(username, password);
    }

    @Then("user is directed to the homepage")
    public void user_is_directed_to_the_homepage() {
        Assertions.assertEquals(homePageActions.getHomePageText(), "Login success");
    }
}

```

### Conclusion

En utilisant le Page Object Model, les localisateurs sont séparés des actions, ce qui facilite la maintenance et la
lisibilité du code. Les classes LoginPageLocators et HomePageLocators définissent les éléments de la page, tandis que
les classes d'actions comme LoginPageActions et HomePageActions définissent les interactions avec ces éléments. Cela
permet de créer des tests automatisés robustes et faciles à maintenir.