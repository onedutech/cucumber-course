Feature: Access home page
  En tant que Romeo, I want to offer a drink to Juliette so that we can discuss together (and maybe more).

  @login
  Scenario Outline: Authentication
    Given user navigates to "<login_page>" by opening Chrome
    When user enters correct "<username>" AND "<password>" values
    Then user is directed to the homepage

    Examples:
      | login_page                      | username | password |
      | http://localhost:8080/home.html | login    | pass     |
