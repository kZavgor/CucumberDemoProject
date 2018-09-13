Feature: Post code validation

  @UI
  Scenario: Website base page contains mandatory elements

    Given I am on a base page
    Then  I see Weather Checker header
    And   I see postcode input box
    And   I see submit button

  @UI
  Scenario: Weather forecast table contains mandatory properties

    Given I am on a base page
    When  I enter "W6 0NW" post code
    Then  I should see weather forecast table
    And   I should see "Time" weather property
    And   I should see "Humidity" weather property
    And   I should see "Temperature" weather property

  @API
  Scenario Outline: Geolocation API handles correctly invalid or non existing post codes.

    Given I send "W6 0NW" post code to weather app
    Then  I receive current weather forecast
    And   I should see "<weather property>" property returned in weather forecast

    Examples:

      | weather property |
      | time             |
      | humidity         |
      | temperature      |

