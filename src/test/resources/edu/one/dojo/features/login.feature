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
